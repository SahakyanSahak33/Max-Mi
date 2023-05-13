package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sahak.sahakyan.maxmi.dto.RegistrationErrors;
import sahak.sahakyan.maxmi.dto.ShopmeUserDetails;
import sahak.sahakyan.maxmi.dto.UserDTO;
import sahak.sahakyan.maxmi.entity.Authority;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.AuthorityService;
import sahak.sahakyan.maxmi.service.UserService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UserService userService;
    private final AuthorityService authorityService;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "login";
    }

    @RequestMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            return "registration";
        } else if (userService.checkUser(user)) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userService.save(user);
            return "redirect:/login";
        }
        RegistrationErrors.repeatedPassword = true;
        return "redirect:/registration-error";
    }

    @RequestMapping("/registration-error")
    public String registrationError(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("registrationError", RegistrationErrors.registrationError);
        model.addAttribute("repeatedPassword", RegistrationErrors.repeatedPassword);
        return "registration";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        System.out.println("----------------------| /login-error.html |------------------------");
        model.addAttribute("loginError", true);
        UserDTO UserDTO = new UserDTO();
        model.addAttribute("userDTO", UserDTO);
        return "login";
    }

    @RequestMapping(value = "/asking")
    public String asking(@ModelAttribute("userLogIn") UserDTO userDTO, Model model) {
        System.out.println("----------------------| /asking |------------------------");
        System.out.println("UserDTO ------- " + userDTO);
        String name = userDTO.getUsername();
        System.out.println("userDTO.getUsername() ------------- " + name);
        User user = userService.findByUsername(name);
        System.out.println(user);
        if (user == null) {
            return "redirect:/login-error";
        }
        String password = userDTO.getPassword();
        if (BCrypt.checkpw(password, user.getPassword())) {
            ShopmeUserDetails userDetails = new ShopmeUserDetails(user);
            System.out.println(userDetails);
            model.addAttribute("userDetails", userDetails);
            return "redirect:/account/index";
        }
        return "redirect:/login-error";
    }

    @RequestMapping("/account/index")
    public String currentUser(@ModelAttribute("userDetails") ShopmeUserDetails userDetails, Model model) {
        System.out.println(userDetails);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userService.findByUsername(userDetails.getUsername());
            return "index";
        } else {
            return "redirect:/login";
        }
    }
}