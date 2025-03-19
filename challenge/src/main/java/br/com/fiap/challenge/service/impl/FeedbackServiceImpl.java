package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Feedback;
import br.com.fiap.challenge.gateways.repository.FeedbackRepository;
import br.com.fiap.challenge.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback criar(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback buscarPorId(Integer id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado"));
    }

    @Override
    public List<Feedback> buscarTodos() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback atualizar(Integer id, Feedback feedback) {
        if (feedbackRepository.existsById(id)) {
            feedback.setIdFeedback(id);
            return feedbackRepository.save(feedback);
        } else {
            throw new RuntimeException("Feedback não encontrado");
        }
    }

    @Override
    public boolean deletar(Integer id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
