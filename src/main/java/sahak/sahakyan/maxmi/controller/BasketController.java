package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sahak.sahakyan.maxmi.entity.Basket;
import sahak.sahakyan.maxmi.entity.Product;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.*;

import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasketController {

    private final UserService userService;
    private final ProductService productService;
    private final BasketService basketService;

    /**
     * ----------------------------| Basket |----------------------------
     */
    @RequestMapping(value = "/user/userhome/basket", method = { RequestMethod.GET, RequestMethod.POST })
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
    @GetMapping("/user/basket")
    public String showBasket(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Basket basket = user.getBasket();
        List<Product> productList = basket.getProducts();
        int number = 0;
        for (Product product : productList) {
            number+= product.getPrice();
            byte[] imageData = product.getImage();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            product.setBase64Image(base64Image);
        }
        model.addAttribute("productList",productList);
        model.addAttribute("totalPrice",number);
        return "basket";
    }
    //**************************| END Basket ! |**************************/


}