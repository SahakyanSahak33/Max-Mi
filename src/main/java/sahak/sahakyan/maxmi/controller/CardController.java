package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sahak.sahakyan.maxmi.dto.CardDTO;
import sahak.sahakyan.maxmi.entity.Card;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.*;

@Controller
@RequiredArgsConstructor
public class CardController {

    private final UserService userService;
    private final CardService cardService;

    @GetMapping("/user/card")
    public String card(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        CardDTO card = new CardDTO();
        if (!(cardService.findCardByUser(user)==null)) {
            Card userCard = cardService.findCardByUser(user);
            System.out.println(userCard);
            card = cardService.createCardDTO(userCard);
        }
        System.out.println(card);
        model.addAttribute("userFirstName", user.getFirstName());
        model.addAttribute("userLastName", user.getLastName());
        model.addAttribute("cardDTO", card);
        return "card";
    }
    @PostMapping("/user/card")
    public String saveCard(@ModelAttribute("cardDTO") CardDTO cardDTO, Authentication authentication) {
        System.out.println("Card Save");
        Card card = cardService.createCard(cardDTO, userService.findByUsername(authentication.getName()));
        cardService.saveCard(card);
        return "redirect:/user/userhome";
    }

}