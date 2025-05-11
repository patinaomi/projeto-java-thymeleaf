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
