package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.enums.Role;
import br.com.fiap.challenge.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/redirect")
    public String redirectByRole(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails.hasRole(Role.DENTISTA)) {
            return "redirect:/dentista/home";
        } else if (userDetails.hasRole(Role.CLINICA)) {
            return "redirect:/clinica/home";
        }
        return "redirect:/access-denied";
    }
}

