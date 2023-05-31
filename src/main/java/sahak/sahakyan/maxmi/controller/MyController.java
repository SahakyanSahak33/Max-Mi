package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sahak.sahakyan.maxmi.dto.DashboardDTO;
import sahak.sahakyan.maxmi.dto.PasswordDTO;
import sahak.sahakyan.maxmi.dto.RegistrationErrors;
import sahak.sahakyan.maxmi.dto.UserDTO;
import sahak.sahakyan.maxmi.entity.Basket;
import sahak.sahakyan.maxmi.entity.Product;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.AuthorityService;
import sahak.sahakyan.maxmi.service.BasketService;
import sahak.sahakyan.maxmi.service.ProductService;
import sahak.sahakyan.maxmi.service.UserService;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserService userService;
    private final AuthorityService authorityService;
    private final ProductService productService;
    private final BasketService basketService;


    /**
     * ----------------------------| WELCOME |----------------------------
     */
    @GetMapping("/")
    public String home() {
        return "welcome";
    }
    //**************************| END WELCOME ! |**************************/

    /**
     * ----------------------------| LOGIN |----------------------------
     */
    @RequestMapping("/login")
    public String login(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        System.out.println("----------------------| /login-error.html |------------------------");
        model.addAttribute("loginError", true);
        UserDTO UserDTO = new UserDTO();
        model.addAttribute("userDTO", UserDTO);
        return "login.html";
    }

    /*--------| LOGIN METHOD |--------*/
    @PostMapping("/authenticate")
    public String asking(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        System.out.println("----------------------| /asking |------------------------");
        System.out.println("UserDTO ------- " + userDTO);
        String name = userDTO.getUsername();
        System.out.println("userDTO.getUsername() ------------- " + name);
        User user = userService.findByUsername(name);
        System.out.println(user);
        if (user == null) {
            System.out.println("user is Null !");
            return "redirect:/login-error";
        }
        String password = userDTO.getPassword();
        if (BCrypt.checkpw(password, user.getPassword())) {
            model.addAttribute("userName", user.getUsername());
            return "redirect:/user/userhome";
        }
        System.out.println("LOGIN - ERROR");
        return "redirect:/login-error";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        UserDTO UserDTO = new UserDTO();
        model.addAttribute("userDTO", UserDTO);
        return "login";
    }
    //**************************| END LOGIN ! |**************************/

    /**
     * ----------------------------| REGISTRATION |----------------------------
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping("/registration-error")
    public String registrationError(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("registrationError", RegistrationErrors.registrationError);
        model.addAttribute("repeatedPassword", RegistrationErrors.repeatedPassword);
        return "registration";
    }

    /*--------| REGISTRATION METHOD |--------*/
    @PostMapping("/registration")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            return "registration";
        } else if (userService.checkUser(user)) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            Basket basket = new Basket();
            basketService.saveBasket(basket);
            user.setBasket(basket);
            userService.saveUser(user);
            basket.setUser(user);
            basketService.saveBasket(basket);
            return "redirect:/login";
        }
        RegistrationErrors.repeatedPassword = true;
        return "redirect:/registration-error";
    }
    //**************************| END REGISTRATION ! |**************************/

    /**
     * ----------------------------| USER-HOME |----------------------------
     */
    @GetMapping("/user/userhome")
    @PreAuthorize("hasRole('USER')")
    public String userHome(@RequestParam(defaultValue = "1") int pageNumber ,@RequestParam(defaultValue = "0") int productId, Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Product> arrayList = productService.findAll();
        List<Long> longList = basketService.findByUserId(user.getId()).getProducts().stream().map((Product::getProductId)).collect(Collectors.toList());
        Map<Long, Product> productMap = new HashMap<>();
        for (Product product:
             arrayList) {
            productMap.put(product.getProductId(), product);
        }
        for (Long id : longList) {
            System.out.println(id);
            productMap.remove(id);
        }
        arrayList = new ArrayList<Product>(productMap.values());
        int pageSize = 12;
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, arrayList.size());
        List<Product> pageList = arrayList.subList(startIndex, endIndex);
        for (Product product : pageList) {
            byte[] imageData = product.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            product.setBase64Image(base64Image);
        }
        model.addAttribute("pageList", pageList);
        model.addAttribute("longList", longList);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", Math.ceil((double) arrayList.size() / pageSize));
        model.addAttribute("user", user);
        return "userhome";
    }
    //**************************| END USER-HOME ! |**************************/

    /**
     * ----------------------------| Basket |----------------------------
     */
    @GetMapping("/user/userhome/basket")
    public String saveIntoBasket(@RequestParam(defaultValue = "0") int productId , Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        System.out.println("----------------------------| Post Basket |----------------------------");
        System.out.println("Product Id = " + productId);
        Basket basket = user.getBasket();
        Product product = productService.findByProductId((long) productId);
        basket.addProduct(product);
        basketService.saveBasket(basket);
        product.addBasket(basket);
        return "redirect:/user/userhome";
    }
    //**************************| END Basket ! |**************************/

    /**
     * ----------------------------| Settings |----------------------------
     */

    @GetMapping("/user/settings")
    @PreAuthorize("hasRole('USER')")
    public String dashboard(Model model, Authentication authentication) {
        System.out.println("----------------------| @GetMapping /user/settings |---------------------------");
//        NullPointer is throwing in the next line ! --->
        User user = userService.findByUsername(authentication.getName());
        System.out.println(user);
        DashboardDTO dashboardDTO = DashboardDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
        model.addAttribute("dashboardDTO", dashboardDTO);
        return "dashboard";
    }

    @PostMapping("/user/settings")
    public String saveDashboard(@Valid @ModelAttribute("dashboardDTO") DashboardDTO dashboardDTO, Authentication authentication, BindingResult bindingResult) {
        System.out.println("----------------------| /user/settings |---------------------------");
        if (bindingResult.hasErrors()) return "dashboard";
        User oldUser = userService.findByUsername(authentication.getName());
        System.out.println(dashboardDTO);
        System.out.println(oldUser);
        User user = new User();
        user.setFirstName(dashboardDTO.getFirstName());
        user.setLastName(dashboardDTO.getLastName());
        user.setEmail(dashboardDTO.getEmail());
        user.setPhoneNumber(dashboardDTO.getPhoneNumber());
        user.setUsername(dashboardDTO.getUsername());
        user.setDate(oldUser.getDate());
        user.setId(oldUser.getId());
        user.setAuthorities(oldUser.getAuthorities());
        user.setGender(oldUser.getGender());
        user.setEnabled(oldUser.isEnabled());
        user.setPassword(oldUser.getPassword());
        System.out.println(user);
        userService.save(user);
        return "redirect:/logout";
    }

    @GetMapping("/user/security")
    @PreAuthorize("hasRole('USER')")
    public String security(Model model, Authentication authentication) {
        System.out.println("-------------------------| @GetMapping /user/security |----------------------");
        User user = userService.findByUsername(authentication.getName());
        System.out.println(user);
        PasswordDTO passwordDTO = new PasswordDTO();
        System.out.println("User Id -- " + user.getId());
        model.addAttribute("id", user.getId());
        model.addAttribute("passwordDTO", passwordDTO);
        return "security";
    }

    @PostMapping("/user/security")
    public String saveSecurity(Authentication authentication, @Valid @ModelAttribute("passwordDTO") PasswordDTO passwordDTO, Model model, BindingResult bindingResult) {
        System.out.println("-------------------------| /user/security |----------------------");
        System.out.println("passwordDTO old password ---- " + passwordDTO.getOldPassword());
        System.out.println("passwordDTO new password ---- " + passwordDTO.getNewPassword());
        User user = userService.findByUsername(authentication.getName());
        if (BCrypt.checkpw(passwordDTO.getOldPassword(), user.getPassword())) {
            System.out.println("First condition was true");
            String newPassword = BCrypt.hashpw(passwordDTO.getNewPassword(), BCrypt.gensalt());
            if (bindingResult.hasErrors()) {
                return "security";
            }
            System.out.println("And Second condition was true");
            user.setPassword(newPassword);
            userService.save(user);
            return "redirect:/logout";
        }
        System.out.println("First condition wasn't true");
        model.addAttribute("isNotPasswordsEquals", true);
        return "security";
    }
    //**************************| END Settings ! |**************************/
}