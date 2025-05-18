package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.Feedback;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.service.ClienteService;
import br.com.fiap.challenge.service.ClinicaService;
import br.com.fiap.challenge.service.DentistaService;
import br.com.fiap.challenge.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ClienteService clienteService;
    private final DentistaService dentistaService;
    private final ClinicaService clinicaService;

    @GetMapping
    public String getFeedbackPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @RequestParam(value = "created", required = false) String created,
                                  @RequestParam(value = "deleted", required = false) String deleted,
                                  Model model) {
        String email = userDetails.getUsername();
        List<Feedback> feedbacks = feedbackService.buscarPorUsuarioClinica(email);
        model.addAttribute("feedbacks", feedbacks);

        if (created != null) model.addAttribute("feedbackCreated", true);
        if (deleted != null) model.addAttribute("feedbackDeleted", true);
        if (feedbacks.isEmpty()) model.addAttribute("feedbackVazio", true);

        return "feedback_page";
    }

    @GetMapping("/criar")
    public String getCadastroFeedbackPage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        String email = userDetails.getUsername();

        Clinica clinica = clinicaService.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada para o usuário: " + email));

        List<Cliente> clientes = clienteService.buscarTodos();
        List<Dentista> dentistas = dentistaService.buscarPorIdClinica(clinica.getIdClinica());

        model.addAttribute("clientes", clientes);
        model.addAttribute("dentistas", dentistas);
        model.addAttribute("idClinica", clinica.getIdClinica());
        model.addAttribute("novoFeedback", new Feedback());

        return "create_feedback_page";
    }

    @PostMapping("/criar")
    public String criarFeedback(@RequestParam("cliente") Integer idCliente,
                                @RequestParam("dentista") Integer idDentista,
                                @RequestParam("clinica") Integer idClinica,
                                @RequestParam("avaliacao") Float avaliacao,
                                @RequestParam("comentario") String comentario,
                                Model model) {
        try {
            Cliente cliente = clienteService.buscarPorId(idCliente);
            Dentista dentista = dentistaService.buscarPorId(idDentista);
            Clinica clinica = clinicaService.buscarPorId(idClinica);

            Feedback feedback = Feedback.builder()
                    .cliente(cliente)
                    .dentista(dentista)
                    .clinica(clinica)
                    .avaliacao(avaliacao)
                    .comentario(comentario)
                    .build();

            feedbackService.criar(feedback);
            return "redirect:/feedbacks?created=true";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar feedback: " + e.getMessage());
            return "create_feedback_page";
        }
    }

    @GetMapping("/editar/{id}")
    public String getEditarFeedback(@PathVariable Integer id, Model model) {
        Feedback feedback = feedbackService.buscarPorId(id);
        model.addAttribute("feedbackEdit", feedback);
        return "edit_feedback_page";
    }

    @PostMapping("/editar")
    public String editarFeedback(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @RequestParam Integer id,
                                 @ModelAttribute Feedback feedbackForm,
                                 Model model) {
        String email = userDetails.getUsername();
        Clinica clinica = clinicaService.buscarPorUsername(email)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada para o usuário: " + email));

        Feedback feedbackExistente = feedbackService.buscarPorId(id);

        if (!feedbackExistente.getClinica().getIdClinica().equals(clinica.getIdClinica())) {
            return "redirect:/403";
        }
        if (!feedbackExistente.getClinica().getUser().getUsername().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("Você não tem permissão para isso.");
        }

        feedbackExistente.setAvaliacao(feedbackForm.getAvaliacao());
        feedbackExistente.setComentario(feedbackForm.getComentario());

        feedbackService.atualizar(id, feedbackExistente);
        return "redirect:/feedbacks?edited=true";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFeedback(@PathVariable Integer id) {
        try {
            feedbackService.deletar(id);
            return "redirect:/feedbacks?deleted=true";
        } catch (Exception e) {
            return "redirect:/feedbacks?deletedError=true";
        }
    }

    @GetMapping("/resumo")
    public String gerarResumo(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String email = userDetails.getUsername();
        List<Feedback> feedbacks = feedbackService.buscarPorUsuarioClinica(email);
        String resumo = feedbackService.gerarResumo(feedbacks);

        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("resumoIA", resumo);
        return "feedback_page";
    }

}
