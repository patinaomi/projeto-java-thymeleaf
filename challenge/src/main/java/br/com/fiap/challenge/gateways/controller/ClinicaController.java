package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.service.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clinica")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService service;

    @GetMapping("/home")
    public String clinicaHome(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        Clinica clinica = service.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada para o usuário: " + email));
        model.addAttribute("nomeClinica", clinica.getNome());
        return "clinica/home";
    }

}
