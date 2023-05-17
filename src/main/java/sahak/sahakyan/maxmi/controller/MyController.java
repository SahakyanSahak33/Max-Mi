package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.AuthorityService;
import sahak.sahakyan.maxmi.service.UserService;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserService userService;
    private final AuthorityService authorityService;

    /**----------------------------| WELCOME |----------------------------*/
    @GetMapping("/")
    public String home() {
        return "welcome";
    }
    //**************************| END WELCOME ! |**************************/

    /**----------------------------| LOGIN |----------------------------*/
        @RequestMapping("/login")
        public String login(Model model) {
            UserDTO userDTO = new UserDTO();
            model.addAttribute("userDTO" , userDTO);
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

    /**----------------------------| REGISTRATION |----------------------------*/
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

    /**----------------------------| USER-HOME |----------------------------*/
    @GetMapping("/user/userhome")
    @PreAuthorize("hasRole('USER')")
    public String userHome(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "userhome";
    }
    //**************************| END USER-HOME ! |**************************/

    /**----------------------------| Settings |----------------------------*/

    @GetMapping("/user/settings")
    @PreAuthorize("hasRole('USER')")
    public String dashboard(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
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
    public String saveDashboard(@ModelAttribute("dashboardDTO") DashboardDTO dashboardDTO) {
        System.out.println("----------------------| /user/settings |---------------------------");
        System.out.println(dashboardDTO);
        User user = userService.findByUsername(dashboardDTO.getUsername());
        System.out.println(user);
//        userService.save(user);
        return "redirect:/user/userhome";
    }
    @GetMapping("/user/security")
    @PreAuthorize("hasRole('USER')")
    public String security(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        PasswordDTO passwordDTO = new PasswordDTO();
        model.addAttribute("passwordDTO", passwordDTO);
        model.addAttribute("userName", user.getUsername());
        return "security";
    }
    @PostMapping("/user/security")
    public String saveSecurity(@ModelAttribute("userName") String username) {
        User user = userService.findByUsername(username);
        userService.save(user);
        return "redirect:/user/userhome";
    }

    //**************************| END Settings ! |**************************/
}