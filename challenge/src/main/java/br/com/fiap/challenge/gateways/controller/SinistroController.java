package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Consulta;
import br.com.fiap.challenge.domains.Sinistro;
import br.com.fiap.challenge.gateways.repository.ConsultaRepository;
import br.com.fiap.challenge.gateways.request.SinistroRequest;
import br.com.fiap.challenge.gateways.request.SinistroUpdateRequest;
import br.com.fiap.challenge.gateways.response.SinistroResponse;
import br.com.fiap.challenge.service.SinistroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sinistros")
@RequiredArgsConstructor
public class SinistroController {

    private final SinistroService sinistroService;
    private final ConsultaRepository consultaRepository;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody SinistroRequest sinistroRequest) {
        try {
            Consulta consulta = consultaRepository.findById(sinistroRequest.getConsulta())
                    .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
            Sinistro sinistro = Sinistro.builder()
                    .consulta(consulta)
                    .nome(sinistroRequest.getNome())
                    .descricao(sinistroRequest.getDescricao())
                    .statusSinistro(sinistroRequest.getStatusSinistro())
                    .descricaoStatus(sinistroRequest.getDescricaoStatus())
                    .valorSinistro(sinistroRequest.getValorSinistro())
                    .dataAbertura(sinistroRequest.getDataAbertura())
                    .dataResolucao(sinistroRequest.getDataResolucao())
                    .documentacao(sinistroRequest.getDocumentacao())
                    .build();

            Sinistro sinistroSalvo = sinistroService.criar(sinistro);

            SinistroResponse sinistroResponse = SinistroResponse.builder()
                    .consulta(sinistroSalvo.getConsulta())
                    .nome(sinistroSalvo.getNome())
                    .descricao(sinistroSalvo.getDescricao())
                    .statusSinistro(sinistroSalvo.getStatusSinistro())
                    .descricaoStatus(sinistroSalvo.getDescricaoStatus())
                    .valorSinistro(sinistroSalvo.getValorSinistro())
                    .dataAbertura(sinistroSalvo.getDataAbertura())
                    .dataResolucao(sinistroSalvo.getDataResolucao())
                    .documentacao(sinistroSalvo.getDocumentacao())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(sinistroResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o sinistro: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        try {
            List<Sinistro> sinistros = sinistroService.buscarTodos();
            if (sinistros.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum sinistro encontrado.");
            }

            return ResponseEntity.ok(sinistros);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar sinistros: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Sinistro sinistro = sinistroService.buscarPorId(id);
            SinistroResponse sinistroResponse = SinistroResponse.builder()
                    .consulta(sinistro.getConsulta())
                    .nome(sinistro.getNome())
                    .descricao(sinistro.getDescricao())
                    .statusSinistro(sinistro.getStatusSinistro())
                    .descricaoStatus(sinistro.getDescricaoStatus())
                    .valorSinistro(sinistro.getValorSinistro())
                    .dataAbertura(sinistro.getDataAbertura())
                    .dataResolucao(sinistro.getDataResolucao())
                    .documentacao(sinistro.getDocumentacao())
                    .build();

            return ResponseEntity.ok(sinistroResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sinistro com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar sinistro: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @Valid @RequestBody SinistroRequest sinistroRequest) {
        try {
            Consulta consulta = consultaRepository.findById(sinistroRequest.getConsulta())
                    .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

            Sinistro sinistro = Sinistro.builder()
                    .consulta(consulta)
                    .nome(sinistroRequest.getNome())
                    .descricao(sinistroRequest.getDescricao())
                    .statusSinistro(sinistroRequest.getStatusSinistro())
                    .descricaoStatus(sinistroRequest.getDescricaoStatus())
                    .valorSinistro(sinistroRequest.getValorSinistro())
                    .dataAbertura(sinistroRequest.getDataAbertura())
                    .dataResolucao(sinistroRequest.getDataResolucao())
                    .documentacao(sinistroRequest.getDocumentacao())
                    .build();

            Sinistro sinistroAtualizado = sinistroService.atualizar(id, sinistro);

            SinistroResponse sinistroResponse = SinistroResponse.builder()
                    .consulta(sinistroAtualizado.getConsulta())
                    .nome(sinistroAtualizado.getNome())
                    .descricao(sinistroAtualizado.getDescricao())
                    .statusSinistro(sinistroAtualizado.getStatusSinistro())
                    .descricaoStatus(sinistroAtualizado.getDescricaoStatus())
                    .valorSinistro(sinistroAtualizado.getValorSinistro())
                    .dataAbertura(sinistroAtualizado.getDataAbertura())
                    .dataResolucao(sinistroAtualizado.getDataResolucao())
                    .documentacao(sinistroAtualizado.getDocumentacao())
                    .build();

            return ResponseEntity.ok(sinistroResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sinistro com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar sinistro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        try {
            sinistroService.deletar(id);
            return ResponseEntity.ok("Sinistro com ID " + id + " foi deletado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sinistro com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar sinistro: " + e.getMessage());
        }
    }

    public ResponseEntity<?> atualizarParcialmente(@PathVariable Integer id, @RequestBody SinistroUpdateRequest sinistroUpdateRequest) {
        try {
            Sinistro sinistro = sinistroService.buscarPorId(id);

            if (sinistroUpdateRequest.getNome() != null) {
                sinistro.setNome(sinistroUpdateRequest.getNome());
            }
            if (sinistroUpdateRequest.getDescricao() != null) {
                sinistro.setDescricao(sinistroUpdateRequest.getDescricao());
            }
            if (sinistroUpdateRequest.getStatusSinistro() != null) {
                sinistro.setStatusSinistro(sinistroUpdateRequest.getStatusSinistro());
            }
            if (sinistroUpdateRequest.getDescricaoStatus() != null) {
                sinistro.setDescricaoStatus(sinistroUpdateRequest.getDescricaoStatus());
            }
            if (sinistroUpdateRequest.getValorSinistro() != null) {
                sinistro.setValorSinistro(sinistroUpdateRequest.getValorSinistro());
            }
            if (sinistroUpdateRequest.getDataAbertura() != null) {
                sinistro.setDataAbertura(sinistroUpdateRequest.getDataAbertura());
            }
            if (sinistroUpdateRequest.getDataResolucao() != null) {
                sinistro.setDataResolucao(sinistroUpdateRequest.getDataResolucao());
            }
            if (sinistroUpdateRequest.getDocumentacao() != null) {
                sinistro.setDocumentacao(sinistroUpdateRequest.getDocumentacao());
            }

            Sinistro sinistroAtualizado = sinistroService.atualizar(id, sinistro);

            SinistroResponse sinistroResponse = SinistroResponse.builder()
                    .consulta(sinistroAtualizado.getConsulta())
                    .nome(sinistroAtualizado.getNome())
                    .descricao(sinistroAtualizado.getDescricao())
                    .statusSinistro(sinistroAtualizado.getStatusSinistro())
                    .descricaoStatus(sinistroAtualizado.getDescricaoStatus())
                    .valorSinistro(sinistroAtualizado.getValorSinistro())
                    .dataAbertura(sinistroAtualizado.getDataAbertura())
                    .dataResolucao(sinistroAtualizado.getDataResolucao())
                    .documentacao(sinistroAtualizado.getDocumentacao())
                    .build();

            return ResponseEntity.ok(sinistroResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sinistro com ID " + id + " não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar sinistro: " + e.getMessage());
        }
    }
}
