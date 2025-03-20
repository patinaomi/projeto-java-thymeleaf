package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.Feedback;
import br.com.fiap.challenge.service.ClienteService;
import br.com.fiap.challenge.service.ClinicaService;
import br.com.fiap.challenge.service.DentistaService;
import br.com.fiap.challenge.service.FeedbackService;
import lombok.RequiredArgsConstructor;
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
    public String getFeedbackPage(Model model) {
        List<Feedback> feedbacks = feedbackService.buscarTodos();
        model.addAttribute("feedbacks", feedbacks);
        return "feedback_page";
    }

    @GetMapping("/criar")
    public String getCadastroFeedbackPage(Model model) {
        List<Cliente> clientes = clienteService.buscarTodos();
        List<Dentista> dentistas = dentistaService.buscarTodos();
        List<Clinica> clinicas = clinicaService.buscarTodos();
        model.addAttribute("clientes", clientes);
        model.addAttribute("dentistas", dentistas);
        model.addAttribute("clinicas", clinicas);
        model.addAttribute("novoFeedback", new Feedback());
        return "create_feedback_page";
    }

    @PostMapping("/criar")
    public String criarFeedback(
            @RequestParam("cliente") Integer idCliente,
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

            return "redirect:/feedbacks?sucesso";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao cadastrar feedback: " + e.getMessage());
            return "feedbacks/formulario-feedback";
        }
    }

    @GetMapping("/editar/{id}")
    public String getEditarFeedback(@PathVariable Integer id, Model model) {
        Feedback feedback = feedbackService.buscarPorId(id);
        model.addAttribute("feedbackEdit", feedback);
        return "edit_feedback_page";
    }

    @PostMapping("/editar")
    public String editarFeedback(@RequestParam Integer id, @ModelAttribute Feedback feedback) {
        Feedback feedbackExistente = feedbackService.buscarPorId(id);

        if (feedbackExistente == null) {
            return "redirect:/feedbacks";
        }

        feedbackExistente.setAvaliacao(feedback.getAvaliacao());
        feedbackExistente.setComentario(feedback.getComentario());

        feedbackService.atualizar(id, feedbackExistente);
        return "redirect:/feedbacks";
    }


    @GetMapping("/deletar/{id}")
    public String deletarFeedback(@PathVariable Integer id) {
        try {
            feedbackService.deletar(id);
            return "redirect:/feedbacks";
        } catch (Exception e) {
            return "redirect:/feedbacks?erro=Não foi possível deletar. Este feedback está associado a outros registros.";
        }
    }
}
