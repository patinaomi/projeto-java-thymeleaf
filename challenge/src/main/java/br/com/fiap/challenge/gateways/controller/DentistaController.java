package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.service.DentistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dentistaS")
@RequiredArgsConstructor
public class DentistaController {

    private final DentistaService service;

    @GetMapping("/home")
    public String dentistaHome(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        Dentista dentista = service.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado para o usuário: " + email));
        model.addAttribute("nomeDentista", dentista.getNome());
        return "dentista_home";
    }
}
