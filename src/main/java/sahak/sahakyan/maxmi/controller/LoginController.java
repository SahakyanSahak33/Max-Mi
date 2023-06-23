package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sahak.sahakyan.maxmi.dto.UserDTO;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.UserService;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

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
        model.addAttribute("loginError", true);
        UserDTO UserDTO = new UserDTO();
        model.addAttribute("userDTO", UserDTO);
        return "login";
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

}