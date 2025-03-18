package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.Feedback;
import br.com.fiap.challenge.gateways.repository.ClienteRepository;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.gateways.repository.DentistaRepository;
import br.com.fiap.challenge.gateways.request.FeedbackRequest;
import br.com.fiap.challenge.gateways.request.FeedbackUpdateRequest;
import br.com.fiap.challenge.gateways.response.FeedbackResponse;
import br.com.fiap.challenge.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ClienteRepository clienteRepository;
    private final DentistaRepository dentistaRepository;
    private final ClinicaRepository clinicaRepository;

    @PostMapping("/criar")
    public ResponseEntity<FeedbackResponse> criar(@Valid @RequestBody FeedbackRequest feedbackRequest) {
        Cliente cliente = clienteRepository.findById(feedbackRequest.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Dentista dentista = dentistaRepository.findById(feedbackRequest.getDentista())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        Clinica clinica = clinicaRepository.findById(feedbackRequest.getClinica())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));

        Feedback feedback = Feedback.builder()
                .cliente(cliente)
                .dentista(dentista)
                .clinica(clinica)
                .avaliacao(feedbackRequest.getAvaliacao())
                .comentario(feedbackRequest.getComentario())
                .build();

        Feedback feedbackSalvo = feedbackService.criar(feedback);

        FeedbackResponse feedbackResponse = FeedbackResponse.builder()
                .cliente(feedbackSalvo.getCliente())
                .dentista(feedbackSalvo.getDentista())
                .clinica(feedbackSalvo.getClinica())
                .avaliacao(feedbackSalvo.getAvaliacao())
                .comentario(feedbackSalvo.getComentario())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponse);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> buscarTodos() {
        List<Feedback> feedbacks = feedbackService.buscarTodos();
        if (feedbacks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<FeedbackResponse> feedbackResponses = feedbacks.stream()
                .map(f -> FeedbackResponse.builder()
                        .cliente(f.getCliente())
                        .dentista(f.getDentista())
                        .clinica(f.getClinica())
                        .avaliacao(f.getAvaliacao())
                        .comentario(f.getComentario())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(feedbackResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> buscarPorId(@PathVariable String id) {
        Optional<Feedback> feedbackOptional = Optional.ofNullable(feedbackService.buscarPorId(id));
        return feedbackOptional.map(feedback -> ResponseEntity.ok(
                FeedbackResponse.builder()
                        .cliente(feedback.getCliente())
                        .dentista(feedback.getDentista())
                        .clinica(feedback.getClinica())
                        .avaliacao(feedback.getAvaliacao())
                        .comentario(feedback.getComentario())
                        .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponse> atualizar(@PathVariable String id, @Valid @RequestBody FeedbackRequest feedbackRequest) {
        Cliente cliente = clienteRepository.findById(feedbackRequest.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Dentista dentista = dentistaRepository.findById(feedbackRequest.getDentista())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        Clinica clinica = clinicaRepository.findById(feedbackRequest.getClinica())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));

        Feedback feedback = Feedback.builder()
                .cliente(cliente)
                .dentista(dentista)
                .clinica(clinica)
                .avaliacao(feedbackRequest.getAvaliacao())
                .comentario(feedbackRequest.getComentario())
                .build();

        Feedback feedbackAtualizado = feedbackService.atualizar(id, feedback);

        FeedbackResponse feedbackResponse = FeedbackResponse.builder()
                .cliente(feedbackAtualizado.getCliente())
                .dentista(feedbackAtualizado.getDentista())
                .clinica(feedbackAtualizado.getClinica())
                .avaliacao(feedbackAtualizado.getAvaliacao())
                .comentario(feedbackAtualizado.getComentario())
                .build();

        return ResponseEntity.ok(feedbackResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        return feedbackService.deletar(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FeedbackResponse> atualizarParcialmente(@PathVariable String id, @RequestBody FeedbackUpdateRequest feedbackUpdateRequest) {
        Feedback feedback = feedbackService.buscarPorId(id);

        if (feedbackUpdateRequest.getAvaliacao() != null) {
            feedback.setAvaliacao(feedbackUpdateRequest.getAvaliacao());
        }
        if (feedbackUpdateRequest.getComentario() != null) {
            feedback.setComentario(feedbackUpdateRequest.getComentario());
        }

        Feedback feedbackAtualizado = feedbackService.atualizar(id, feedback);

        FeedbackResponse feedbackResponse = FeedbackResponse.builder()
                .cliente(feedbackAtualizado.getCliente())
                .dentista(feedbackAtualizado.getDentista())
                .clinica(feedbackAtualizado.getClinica())
                .avaliacao(feedbackAtualizado.getAvaliacao())
                .comentario(feedbackAtualizado.getComentario())
                .build();

        return ResponseEntity.ok(feedbackResponse);
    }
}
