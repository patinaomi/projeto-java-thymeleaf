package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.Especialidade;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.gateways.repository.EspecialidadeRepository;
import br.com.fiap.challenge.gateways.request.DentistaRequest;
import br.com.fiap.challenge.gateways.request.DentistaUpdateRequest;
import br.com.fiap.challenge.gateways.response.DentistaResponse;
import br.com.fiap.challenge.service.DentistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
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
        DentistaResponse dentistaResponse = DentistaResponse.builder()
                .nome(dentistaSalvo.getNome())
                .sobrenome(dentistaSalvo.getSobrenome())
                .telefone(dentistaSalvo.getTelefone())
                .clinica(dentistaSalvo.getClinica())
                .especialidade(dentistaSalvo.getEspecialidade())
                .avaliacao(dentistaSalvo.getAvaliacao())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DentistaResponse>> buscarTodos() {
        List<Dentista> dentistas = dentistaService.buscarTodos();
        if (dentistas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<DentistaResponse> dentistaResponses = dentistas.stream()
                .map(dentista -> DentistaResponse.builder()
                        .nome(dentista.getNome())
                        .sobrenome(dentista.getSobrenome())
                        .telefone(dentista.getTelefone())
                        .clinica(dentista.getClinica())
                        .especialidade(dentista.getEspecialidade())
                        .avaliacao(dentista.getAvaliacao())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(dentistaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaResponse> buscarPorId(@PathVariable Integer id) {
        return dentistaService.buscarPorId(id)
                .map(dentista -> ResponseEntity.ok(DentistaResponse.builder()
                        .nome(dentista.getNome())
                        .sobrenome(dentista.getSobrenome())
                        .telefone(dentista.getTelefone())
                        .clinica(dentista.getClinica())
                        .especialidade(dentista.getEspecialidade())
                        .avaliacao(dentista.getAvaliacao())
                        .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaResponse> atualizar(@PathVariable Integer id, @Valid @RequestBody DentistaRequest dentistaRequest) {
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

        return dentistaService.atualizar(id, dentista)
                .map(dentistaAtualizado -> ResponseEntity.ok(DentistaResponse.builder()
                        .nome(dentistaAtualizado.getNome())
                        .sobrenome(dentistaAtualizado.getSobrenome())
                        .telefone(dentistaAtualizado.getTelefone())
                        .clinica(dentistaAtualizado.getClinica())
                        .especialidade(dentistaAtualizado.getEspecialidade())
                        .avaliacao(dentistaAtualizado.getAvaliacao())
                        .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        return dentistaService.deletar(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DentistaResponse> atualizarParcialmente(@PathVariable Integer id, @RequestBody DentistaUpdateRequest dentistaUpdateRequest) {
        return dentistaService.buscarPorId(id)
                .map(dentista -> {
                    if (dentistaUpdateRequest.getNome() != null) {
                        dentista.setNome(dentistaUpdateRequest.getNome());
                    }
                    if (dentistaUpdateRequest.getSobrenome() != null) {
                        dentista.setSobrenome(dentistaUpdateRequest.getSobrenome());
                    }
                    if (dentistaUpdateRequest.getTelefone() != null) {
                        dentista.setTelefone(dentistaUpdateRequest.getTelefone());
                    }
                    if (dentistaUpdateRequest.getAvaliacao() != null) {
                        dentista.setAvaliacao(dentistaUpdateRequest.getAvaliacao());
                    }

                    return dentistaService.atualizar(id, dentista)
                            .map(dentistaAtualizado -> ResponseEntity.ok(DentistaResponse.builder()
                                    .nome(dentistaAtualizado.getNome())
                                    .sobrenome(dentistaAtualizado.getSobrenome())
                                    .telefone(dentistaAtualizado.getTelefone())
                                    .clinica(dentistaAtualizado.getClinica())
                                    .especialidade(dentistaAtualizado.getEspecialidade())
                                    .avaliacao(dentistaAtualizado.getAvaliacao())
                                    .build()))
                            .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
