package br.com.fiap.challenge.gateways.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getHomePage(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam(value = "logout", required = false) String logout,
                              Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Logout realizado com sucesso.");
        }
        return "login";
    }


    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error",required = false)String error,
                            Model model) {
        if(error !=null) {
            model.addAttribute("error","Usuário ou senha incorretos");
        }
        return "login";
    }

    @GetMapping("access-denied")
    public String accessDenied() {
        return "403";
    }
}
