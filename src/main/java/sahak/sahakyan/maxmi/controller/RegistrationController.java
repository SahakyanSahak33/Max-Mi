package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sahak.sahakyan.maxmi.dto.RegistrationErrors;
import sahak.sahakyan.maxmi.dto.VerificationDTO;
import sahak.sahakyan.maxmi.entity.Basket;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.BasketService;
import sahak.sahakyan.maxmi.service.EmailService;
import sahak.sahakyan.maxmi.service.UserService;
import sahak.sahakyan.maxmi.service.VerificationService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final EmailService emailService;
    private final BasketService basketService;


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
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            return "registration";
        } else if (userService.checkUser(user)) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            Basket basket = new Basket();
            basketService.saveBasket(basket);
            user.setBasket(basket);
            user.setEnabled(false);
            userService.saveUser(user);
            basket.setUser(user);
            basketService.saveBasket(basket);
            VerificationDTO verificationDTO = new VerificationDTO();
            verificationDTO.setCode(emailService.sendVerificationCode(user.getEmail()));
            verificationDTO.setUserId(user.getId());
            model.addAttribute("verificationDTO", verificationDTO);
            return "verification";
        }
        RegistrationErrors.repeatedPassword = true;
        return "redirect:/registration-error";
    }
    //**************************| END REGISTRATION ! |**************************/

}