package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sahak.sahakyan.maxmi.entity.Basket;
import sahak.sahakyan.maxmi.entity.Product;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.*;

@Controller
@RequiredArgsConstructor
public class BasketController {

    private final UserService userService;
    private final ProductService productService;
    private final BasketService basketService;

    /**
     * ----------------------------| Basket |----------------------------
     */
    @PostMapping("/user/userhome/basket")
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


}