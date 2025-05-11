package br.com.fiap.challenge.gateways.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "home_page";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error",required = false)String error,
                            @RequestParam(value = "logout",required = false)String logout,
                            Model model) {
        if(error !=null) {
            model.addAttribute("error","Usuário ou senha incorretos");
        }
        if(logout != null) {
            model.addAttribute("logout","Deslogado com sucesso");
        }
        return "/login";
    }

    @GetMapping("access-denied")
    public String accessDenied() {
        return "access-denied";
    }

}
