package sahak.sahakyan.maxmi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sahak.sahakyan.maxmi.dto.DashboardDTO;
import sahak.sahakyan.maxmi.dto.PasswordDTO;
import sahak.sahakyan.maxmi.entity.User;
import sahak.sahakyan.maxmi.service.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final UserService userService;

    /**
            * ----------------------------| Settings |----------------------------
    */

    @GetMapping("/user/settings")
    @PreAuthorize("hasRole('USER')")
    public String dashboard(Model model, Authentication authentication) {
        System.out.println("----------------------| @GetMapping /user/settings |---------------------------");
//        NullPointer is throwing in the next line ! --->
        User user = userService.findByUsername(authentication.getName());
        System.out.println(user);
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
    public String saveDashboard(@Valid @ModelAttribute("dashboardDTO") DashboardDTO dashboardDTO, Authentication authentication, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "dashboard";
        User oldUser = userService.findByUsername(authentication.getName());
        oldUser = userService.changeUserFromUserDTO(dashboardDTO,oldUser);
        userService.save(oldUser);
        return "redirect:/logout";
    }

    @GetMapping("/user/security")
    @PreAuthorize("hasRole('USER')")
    public String security(Model model, Authentication authentication) {
        System.out.println("-------------------------| @GetMapping /user/security |----------------------");
        User user = userService.findByUsername(authentication.getName());
        System.out.println(user);
        PasswordDTO passwordDTO = new PasswordDTO();
        System.out.println("User Id -- " + user.getId());
        model.addAttribute("id", user.getId());
        model.addAttribute("passwordDTO", passwordDTO);
        return "security";
    }

    @PostMapping("/user/security")
    public String saveSecurity(Authentication authentication, @Valid @ModelAttribute("passwordDTO") PasswordDTO passwordDTO, Model model, BindingResult bindingResult) {
        System.out.println("-------------------------| /user/security |----------------------");
        System.out.println("passwordDTO old password ---- " + passwordDTO.getOldPassword());
        System.out.println("passwordDTO new password ---- " + passwordDTO.getNewPassword());
        User user = userService.findByUsername(authentication.getName());
        if (BCrypt.checkpw(passwordDTO.getOldPassword(), user.getPassword())) {
            System.out.println("First condition was true");
            String newPassword = BCrypt.hashpw(passwordDTO.getNewPassword(), BCrypt.gensalt());
            if (bindingResult.hasErrors()) {
                return "security";
            }
            System.out.println("And Second condition was true");
            user.setPassword(newPassword);
            userService.save(user);
            return "redirect:/logout";
        }
        System.out.println("First condition wasn't true");
        model.addAttribute("isNotPasswordsEquals", true);
        return "security";
    }
    //**************************| END Settings ! |**************************/
}