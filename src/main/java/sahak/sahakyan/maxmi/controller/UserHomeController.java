package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sahak.sahakyan.maxmi.entity.Product;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserHomeController {

    private final UserService userService;
    private final ProductService productService;
    private final BasketService basketService;
    private final EmailService emailService;

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
        System.out.println("TotalPages - "  + Math.ceil((double) arrayList.size() / pageSize));
        System.out.println("pageNumber - "  + pageNumber);
        emailService.sendEmail(user.getEmail(),"Message", "6958");
        return "userhome";
    }
    //**************************| END USER-HOME ! |**************************/
}