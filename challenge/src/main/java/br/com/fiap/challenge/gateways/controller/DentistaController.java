package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.Especialidade;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.gateways.repository.EspecialidadeRepository;
import br.com.fiap.challenge.gateways.request.DentistaRequest;
import br.com.fiap.challenge.gateways.response.DentistaResponse;
import br.com.fiap.challenge.service.DentistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dentistas")
@RequiredArgsConstructor
public class DentistaController {

    private final DentistaService dentistaService;
    private final ClinicaRepository clinicaRepository;
    private final EspecialidadeRepository especialidadeRepository;

    @PostMapping("/criar")
    public ResponseEntity<DentistaResponse> criar(@Valid @RequestBody DentistaRequest dentistaRequest) {
        Clinica clinica = clinicaRepository.findById(dentistaRequest.getClinica())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        Especialidade especialidade = especialidadeRepository.findById(dentistaRequest.getEspecialidade())
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));

        Dentista dentista = Dentista.builder()
                .nome(dentistaRequest.getNome())
                .sobrenome(dentistaRequest.getSobrenome())
                .telefone(dentistaRequest.getTelefone())
                .clinica(clinica)
                .especialidade(especialidade)
                .avaliacao(dentistaRequest.getAvaliacao())
                .build();

        Dentista dentistaSalvo = dentistaService.criar(dentista);
        DentistaResponse dentistaResponse = new DentistaResponse(dentistaSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DentistaResponse>> buscarTodos() {
        List<Dentista> dentistas = dentistaService.buscarTodos();
        if (dentistas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<DentistaResponse> dentistaResponses = dentistas.stream()
                .map(DentistaResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dentistaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaResponse> buscarPorId(@PathVariable String id) {
        Optional<Dentista> dentistaOptional = dentistaService.buscarPorId(id);
        return dentistaOptional.map(dentista -> ResponseEntity.ok(new DentistaResponse(dentista)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaResponse> atualizar(@PathVariable String id, @Valid @RequestBody DentistaRequest dentistaRequest) {
        Clinica clinica = clinicaRepository.findById(dentistaRequest.getClinica())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        Especialidade especialidade = especialidadeRepository.findById(dentistaRequest.getEspecialidade())
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));

        Dentista dentista = Dentista.builder()
                .nome(dentistaRequest.getNome())
                .sobrenome(dentistaRequest.getSobrenome())
                .telefone(dentistaRequest.getTelefone())
                .clinica(clinica)
                .especialidade(especialidade)
                .avaliacao(dentistaRequest.getAvaliacao())
                .build();

        Optional<Dentista> dentistaAtualizado = dentistaService.atualizar(id, dentista);
        return dentistaAtualizado.map(d -> ResponseEntity.ok(new DentistaResponse(d)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        return dentistaService.deletar(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
