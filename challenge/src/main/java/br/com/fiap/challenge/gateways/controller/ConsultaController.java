package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Consulta;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.gateways.repository.ClienteRepository;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.gateways.repository.DentistaRepository;
import br.com.fiap.challenge.gateways.request.ConsultaRequest;
import br.com.fiap.challenge.gateways.request.ConsultaUpdateRequest;
import br.com.fiap.challenge.gateways.response.ConsultaResponse;
import br.com.fiap.challenge.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ClienteRepository clienteRepository;
    private final ClinicaRepository clinicaRepository;
    private final DentistaRepository dentistaRepository;

    @PostMapping("/criar")
    public ResponseEntity<ConsultaResponse> criar(@Valid @RequestBody ConsultaRequest consultaRequest) {
        Cliente cliente = clienteRepository.findById(consultaRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Clinica clinica = clinicaRepository.findById(consultaRequest.getClinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        Dentista dentista = dentistaRepository.findById(consultaRequest.getDentistaId())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        Consulta consulta = Consulta.builder()
                .cliente(cliente)
                .clinica(clinica)
                .dentista(dentista)
                .tipoServico(consultaRequest.getTipoServico())
                .dataConsulta(consultaRequest.getDataConsulta())
                .statusConsulta(consultaRequest.getStatusConsulta())
                .observacoes(consultaRequest.getObservacoes())
                .sintomas(consultaRequest.getSintomas())
                .tratamentoRecomendado(consultaRequest.getTratamentoRecomendado())
                .custo(consultaRequest.getCusto())
                .prescricao(consultaRequest.getPrescricao())
                .dataRetorno(consultaRequest.getDataRetorno())
                .build();

        Consulta consultaSalva = consultaService.criar(consulta);

        ConsultaResponse consultaResponse = ConsultaResponse.builder()
                .cliente(consultaSalva.getCliente())
                .clinica(consultaSalva.getClinica())
                .dentista(consultaSalva.getDentista())
                .tipoServico(consultaSalva.getTipoServico())
                .dataConsulta(consultaSalva.getDataConsulta())
                .statusConsulta(consultaSalva.getStatusConsulta())
                .observacoes(consultaSalva.getObservacoes())
                .sintomas(consultaSalva.getSintomas())
                .tratamentoRecomendado(consultaSalva.getTratamentoRecomendado())
                .custo(consultaSalva.getCusto())
                .prescricao(consultaSalva.getPrescricao())
                .dataRetorno(consultaSalva.getDataRetorno())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(consultaResponse);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponse>> buscarTodas() {
        List<Consulta> consultas = consultaService.buscarTodas();
        if (consultas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<ConsultaResponse> consultaResponses = consultas.stream().map(consulta ->
                ConsultaResponse.builder()
                        .cliente(consulta.getCliente())
                        .clinica(consulta.getClinica())
                        .dentista(consulta.getDentista())
                        .tipoServico(consulta.getTipoServico())
                        .dataConsulta(consulta.getDataConsulta())
                        .statusConsulta(consulta.getStatusConsulta())
                        .observacoes(consulta.getObservacoes())
                        .sintomas(consulta.getSintomas())
                        .tratamentoRecomendado(consulta.getTratamentoRecomendado())
                        .custo(consulta.getCusto())
                        .prescricao(consulta.getPrescricao())
                        .dataRetorno(consulta.getDataRetorno())
                        .build()
        ).collect(Collectors.toList());

        return ResponseEntity.ok(consultaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable String id) {
        return consultaService.buscarPorId(id)
                .map(consulta -> ResponseEntity.ok(
                        ConsultaResponse.builder()
                                .cliente(consulta.getCliente())
                                .clinica(consulta.getClinica())
                                .dentista(consulta.getDentista())
                                .tipoServico(consulta.getTipoServico())
                                .dataConsulta(consulta.getDataConsulta())
                                .statusConsulta(consulta.getStatusConsulta())
                                .observacoes(consulta.getObservacoes())
                                .sintomas(consulta.getSintomas())
                                .tratamentoRecomendado(consulta.getTratamentoRecomendado())
                                .custo(consulta.getCusto())
                                .prescricao(consulta.getPrescricao())
                                .dataRetorno(consulta.getDataRetorno())
                                .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> atualizar(@PathVariable String id, @Valid @RequestBody ConsultaRequest consultaRequest) {
        Cliente cliente = clienteRepository.findById(consultaRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Clinica clinica = clinicaRepository.findById(consultaRequest.getClinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        Dentista dentista = dentistaRepository.findById(consultaRequest.getDentistaId())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        Consulta consulta = Consulta.builder()
                .cliente(cliente)
                .clinica(clinica)
                .dentista(dentista)
                .tipoServico(consultaRequest.getTipoServico())
                .dataConsulta(consultaRequest.getDataConsulta())
                .statusConsulta(consultaRequest.getStatusConsulta())
                .observacoes(consultaRequest.getObservacoes())
                .sintomas(consultaRequest.getSintomas())
                .tratamentoRecomendado(consultaRequest.getTratamentoRecomendado())
                .custo(consultaRequest.getCusto())
                .prescricao(consultaRequest.getPrescricao())
                .dataRetorno(consultaRequest.getDataRetorno())
                .build();

        return consultaService.atualizar(id, consulta)
                .map(consultaAtualizada -> ResponseEntity.ok(
                        ConsultaResponse.builder()
                                .cliente(consultaAtualizada.getCliente())
                                .clinica(consultaAtualizada.getClinica())
                                .dentista(consultaAtualizada.getDentista())
                                .tipoServico(consultaAtualizada.getTipoServico())
                                .dataConsulta(consultaAtualizada.getDataConsulta())
                                .statusConsulta(consultaAtualizada.getStatusConsulta())
                                .observacoes(consultaAtualizada.getObservacoes())
                                .sintomas(consultaAtualizada.getSintomas())
                                .tratamentoRecomendado(consultaAtualizada.getTratamentoRecomendado())
                                .custo(consultaAtualizada.getCusto())
                                .prescricao(consultaAtualizada.getPrescricao())
                                .dataRetorno(consultaAtualizada.getDataRetorno())
                                .build()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        return consultaService.deletar(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
