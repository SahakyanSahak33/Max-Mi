package sahak.sahakyan.maxmi.controller;

import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sahak.sahakyan.maxmi.dto.DashboardDTO;
import sahak.sahakyan.maxmi.dto.PasswordDTO;
import sahak.sahakyan.maxmi.dto.RegistrationErrors;
import sahak.sahakyan.maxmi.dto.UserDTO;
import sahak.sahakyan.maxmi.entity.Product;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.AuthorityService;
import sahak.sahakyan.maxmi.service.ProductService;
import sahak.sahakyan.maxmi.service.UserService;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserService userService;
    private final AuthorityService authorityService;
    private final ProductService productService;

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
            userService.saveUser(user);
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
    public String userHome(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        productService.saveProduct(saveProduct());
        List<Product> arrayList = productService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("products", arrayList);
        return "userhome";
    }
    //**************************| END USER-HOME ! |**************************/

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

    private Product saveProduct() {
        Product product = new Product();
        product.setPrice(1200);
        product.setProductName("Iphone14 Pro");
        product.setDiscount(40);
        product.setDescription("Description of Iphone 14 Pro");
        String imagePath = "C:/Users/SAHAK/Documents/GitHub/MaxMi/src/main/resources/assets/img/Iphone22.png";
        File imageFile = new File(imagePath);
        byte[] imageData = new byte[(int) imageFile.length()];

        try (FileInputStream fis = new FileInputStream(imageFile)) {
            System.out.println(fis.read(imageData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setImage(imageData);
        System.out.println(product);
        return product;
    }
}