package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sahak.sahakyan.maxmi.entity.Basket;
import sahak.sahakyan.maxmi.entity.Card;
import sahak.sahakyan.maxmi.entity.Order;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final BasketService basketService;
    private final OrderService orderService;
    private final UserService userService;
    private final DateService dateService;
    private final CardService cardService;


    @GetMapping("/user/order")
    public String order(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Card card = cardService.findCardByUser(user);
        Order order = new Order();
        String str = card.getCardNumber();
        model.addAttribute("order", order);
        model.addAttribute("cardLastNumbers", str.substring(str.length() - 4));
        return "order";
    }

    @PostMapping("/user/order")
    public String saveOrder(@ModelAttribute("order") Order order, Authentication authentication) {
        Basket basket = basketService.findByUserId(userService.findByUsername(authentication.getName()).getId());
        order.setOrderDate(dateService.askDate());
        order.setBasket(basket);
        orderService.saveOrder(order);
        return "redirect:/user/userhome";
    }
}