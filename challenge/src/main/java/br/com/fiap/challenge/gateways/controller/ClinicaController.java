package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.service.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clinicas")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService service;

    @GetMapping("/home")
    public String clinicaHome(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        Clinica clinica = service.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada para o usuário: " + email));
        model.addAttribute("nomeClinica", clinica.getNome());
        model.addAttribute("telefoneClinica", clinica.getTelefone());
        model.addAttribute("enderecoClinica", clinica.getEndereco());
        model.addAttribute("emailClinica", clinica.getUser().getUsername());
        return "clinica_home";
    }

    @GetMapping("/editar")
    public String editarClinica(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        Clinica clinica = service.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada para o usuário: " + email));
        model.addAttribute("clinica", clinica);
        return "edit_clinica_page";
    }


    @PostMapping("/editar")
    public String atualizarClinica(@AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute Clinica clinicaForm) {
        String email = userDetails.getUsername();
        Clinica clinicaExistente = service.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada para o usuário: " + email));

        clinicaExistente.setNome(clinicaForm.getNome());
        clinicaExistente.setEndereco(clinicaForm.getEndereco());
        clinicaExistente.setTelefone(clinicaForm.getTelefone());

        service.atualizar(clinicaExistente.getIdClinica(), clinicaExistente);
        return "redirect:/clinicas/home";
    }

}
