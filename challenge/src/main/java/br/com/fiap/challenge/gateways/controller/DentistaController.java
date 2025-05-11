package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Consulta;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.User;
import br.com.fiap.challenge.domains.enums.Role;
import br.com.fiap.challenge.gateways.repository.UserRepository;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.service.ClinicaService;
import br.com.fiap.challenge.service.ConsultaService;
import br.com.fiap.challenge.service.DentistaService;
import br.com.fiap.challenge.service.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dentistas")
@RequiredArgsConstructor
public class DentistaController {

    private final DentistaService dentistaService;
    private final ClinicaService clinicaService;
    private final ConsultaService consultaService;
    private final EspecialidadeService especialidadeService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String dentistaHome(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        Dentista dentista = dentistaService.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        model.addAttribute("nomeDentista", dentista.getNome());
        model.addAttribute("sobrenomeDentista", dentista.getSobrenome());
        model.addAttribute("telefoneDentista", dentista.getTelefone());
        model.addAttribute("emailDentista", dentista.getUser().getUsername());
        model.addAttribute("especialidadeDentista", dentista.getEspecialidade().getNome());
        model.addAttribute("clinicaDentista", dentista.getClinica().getNome());

        return "dentista_home";
    }


    @GetMapping
    public String listarDentistas(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();
        Integer idClinica = clinicaService.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"))
                .getIdClinica();

        List<Dentista> dentistas = dentistaService.buscarPorIdClinica(idClinica);
        model.addAttribute("dentistas", dentistas);
        return "dentista_page";
    }

    @GetMapping("/criar")
    public String getCriarDentistaPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        Dentista dentista = new Dentista();
        dentista.setUser(new User());

        model.addAttribute("novoDentista", dentista);
        model.addAttribute("especialidades", especialidadeService.buscarTodas());
        return "create_dentista_page";
    }

    @PostMapping("/criar")
    public String criarDentista(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @ModelAttribute Dentista dentistaForm) {

        var clinica = clinicaService.buscarPorUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));

        User novoUser = new User(
                null,
                dentistaForm.getUser().getUsername(),
                passwordEncoder.encode(dentistaForm.getUser().getPassword()),
                true,
                Role.DENTISTA
        );

        User usuarioSalvo = userRepository.save(novoUser);

        dentistaForm.setUser(usuarioSalvo);
        dentistaForm.setClinica(clinica);
        dentistaForm.setAvaliacao(0.0f);

        dentistaService.criar(dentistaForm);

        return "redirect:/dentistas";
    }


    @GetMapping("/editar/{id}")
    public String getEditarDentista(@PathVariable Integer id, Model model) {
        Dentista dentista = dentistaService.buscarPorId(id);
        model.addAttribute("dentistaEdit", dentista);
        model.addAttribute("especialidades", especialidadeService.buscarTodas());
        return "edit_dentista_page";
    }

    @PostMapping("/editar")
    public String editarDentista(@ModelAttribute Dentista dentistaForm) {
        Dentista dentistaExistente = dentistaService.buscarPorId(dentistaForm.getIdDentista());

        dentistaForm.setUser(dentistaExistente.getUser());
        dentistaForm.setClinica(dentistaExistente.getClinica());
        dentistaForm.setAvaliacao(dentistaExistente.getAvaliacao());

        dentistaService.atualizar(dentistaForm.getIdDentista(), dentistaForm);
        return "redirect:/dentistas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarDentista(@PathVariable Integer id) {
        dentistaService.deletar(id);
        return "redirect:/dentistas";
    }
}
