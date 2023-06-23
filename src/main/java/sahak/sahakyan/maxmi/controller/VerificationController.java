package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sahak.sahakyan.maxmi.dto.VerificationDTO;
import sahak.sahakyan.maxmi.entity.Authority;
import sahak.sahakyan.maxmi.entity.Basket;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.BasketService;
import sahak.sahakyan.maxmi.service.EmailService;
import sahak.sahakyan.maxmi.service.UserService;
import sahak.sahakyan.maxmi.service.VerificationService;

@Controller
@RequiredArgsConstructor
public class VerificationController {

    private final UserService userService;
    private final BasketService basketService;
    private final VerificationService verificationService;
    private final EmailService emailService;

    @PostMapping("/verificationCheck")
    public String verificationCheck(@ModelAttribute("verificationDTO") VerificationDTO verificationDTO, Model model) {
        System.out.println("----------------| verificationCheck |----------------------");
        System.out.println(verificationDTO);
        User user = userService.findById(verificationDTO.getUserId());
        if (verificationDTO.getCode().equals(verificationDTO.getNumber().toString())) {
            user.setEnabled(true);
            userService.save(user);
            return "redirect:/user/userhome";
        }
        // Add Model For Error Message*/
        model.addAttribute("isNotCorrect", true);
        return "verification";
    }
}