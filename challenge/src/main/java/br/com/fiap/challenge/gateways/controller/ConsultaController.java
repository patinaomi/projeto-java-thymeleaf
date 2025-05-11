package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Consulta;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.service.ConsultaService;
import br.com.fiap.challenge.service.DentistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;
    private final DentistaService dentistaService;

    @GetMapping
    public String listarConsultas(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        var dentista = dentistaService.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        List<Consulta> consultas = consultaService.buscarPorDentista(dentista.getIdDentista());
        model.addAttribute("consultas", consultas);
        return "consulta_page";
    }
}

