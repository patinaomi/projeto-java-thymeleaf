package br.com.fiap.challenge.gateways.mvc;

import br.com.fiap.challenge.domains.Feedback;
import br.com.fiap.challenge.gateways.repository.FeedbackRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("feedbacks")
@RequiredArgsConstructor
public class MVCFeedbackController {

    private static final String PAGINA_LISTAGEM = "feedback/listagem-feedbacks";
    private static final String PAGINA_CADASTRO = "feedback/formulario-feedback";
    private static final String REDIRECT_LISTAGEM = "redirect:/feedbacks?sucesso";

    private final FeedbackRepository feedbackRepository;

    @GetMapping
    public String listarFeedbacks(Model model) {
        var feedbacks = feedbackRepository.findAll();
        model.addAttribute("feedbacks", feedbacks);
        return PAGINA_LISTAGEM;
    }

    @GetMapping("formulario")
    public String carregarFormulario(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("feedback", feedbackRepository.findById(id).orElse(new Feedback()));
        } else {
            model.addAttribute("feedback", new Feedback());
        }
        return PAGINA_CADASTRO;
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute("feedback") Feedback feedback, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("feedback", feedback);
            return PAGINA_CADASTRO;
        }

        feedbackRepository.save(feedback);
        return REDIRECT_LISTAGEM;
    }

    @DeleteMapping
    public String deletar(@RequestParam Integer id) {
        feedbackRepository.deleteById(id);
        return REDIRECT_LISTAGEM;
    }
}
