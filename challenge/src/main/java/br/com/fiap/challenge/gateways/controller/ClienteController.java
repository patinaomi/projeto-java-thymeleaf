package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.gateways.request.ClienteRequest;
import br.com.fiap.challenge.gateways.request.ClienteUpdateRequest;
import br.com.fiap.challenge.gateways.response.ClienteResponse;
import br.com.fiap.challenge.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes", produces = "application/json")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/criar")
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.builder()
                .nome(clienteRequest.getNome())
                .sobrenome(clienteRequest.getSobrenome())
                .email(clienteRequest.getEmail())
                .telefone(clienteRequest.getTelefone())
                .dataNasc(clienteRequest.getDataNasc())
                .endereco(clienteRequest.getEndereco())
                .build();

        Cliente clienteSalvo = clienteService.criar(cliente);

        ClienteResponse clienteResponse = ClienteResponse.builder()
                .nome(clienteSalvo.getNome())
                .sobrenome(clienteSalvo.getSobrenome())
                .email(clienteSalvo.getEmail())
                .telefone(clienteSalvo.getTelefone())
                .dataNasc(clienteSalvo.getDataNasc())
                .endereco(clienteSalvo.getEndereco())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> buscarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<ClienteResponse> clienteResponses = clientes.stream().map(cliente ->
            ClienteResponse.builder()
                    .nome(cliente.getNome())
                    .sobrenome(cliente.getSobrenome())
                    .email(cliente.getEmail())
                    .telefone(cliente.getTelefone())
                    .dataNasc(cliente.getDataNasc())
                    .endereco(cliente.getEndereco())
                    .build()
        ).collect(Collectors.toList());

        return ResponseEntity.ok(clienteResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable String id) {
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            ClienteResponse clienteResponse = ClienteResponse.builder()
                    .nome(cliente.getNome())
                    .sobrenome(cliente.getSobrenome())
                    .email(cliente.getEmail())
                    .telefone(cliente.getTelefone())
                    .dataNasc(cliente.getDataNasc())
                    .endereco(cliente.getEndereco())
                    .build();

            return ResponseEntity.ok(clienteResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable String id, @Valid @RequestBody ClienteRequest clienteRequest) {
        try {
            Cliente cliente = Cliente.builder()
                    .nome(clienteRequest.getNome())
                    .sobrenome(clienteRequest.getSobrenome())
                    .email(clienteRequest.getEmail())
                    .telefone(clienteRequest.getTelefone())
                    .dataNasc(clienteRequest.getDataNasc())
                    .endereco(clienteRequest.getEndereco())
                    .build();

            Cliente clienteAtualizado = clienteService.atualizar(id, cliente);

            ClienteResponse clienteResponse = ClienteResponse.builder()
                    .nome(clienteAtualizado.getNome())
                    .sobrenome(clienteAtualizado.getSobrenome())
                    .email(clienteAtualizado.getEmail())
                    .telefone(clienteAtualizado.getTelefone())
                    .dataNasc(clienteAtualizado.getDataNasc())
                    .endereco(clienteAtualizado.getEndereco())
                    .build();

            return ResponseEntity.ok(clienteResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        try {
            clienteService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarParcialmente(@PathVariable String id, @RequestBody ClienteUpdateRequest clienteUpdateRequest) {
        try {
            Cliente cliente = clienteService.buscarPorId(id);

            if (clienteUpdateRequest.getNome() != null) {
                cliente.setNome(clienteUpdateRequest.getNome());
            }
            if (clienteUpdateRequest.getSobrenome() != null) {
                cliente.setSobrenome(clienteUpdateRequest.getSobrenome());
            }
            if (clienteUpdateRequest.getEmail() != null) {
                cliente.setEmail(clienteUpdateRequest.getEmail());
            }
            if (clienteUpdateRequest.getTelefone() != null) {
                cliente.setTelefone(clienteUpdateRequest.getTelefone());
            }
            if (clienteUpdateRequest.getDataNasc() != null) {
                cliente.setDataNasc(clienteUpdateRequest.getDataNasc());
            }
            if (clienteUpdateRequest.getEndereco() != null) {
                cliente.setEndereco(clienteUpdateRequest.getEndereco());
            }

            Cliente clienteAtualizado = clienteService.atualizar(id, cliente);

            ClienteResponse clienteResponse = ClienteResponse.builder()
                    .nome(clienteAtualizado.getNome())
                    .sobrenome(clienteAtualizado.getSobrenome())
                    .email(clienteAtualizado.getEmail())
                    .telefone(clienteAtualizado.getTelefone())
                    .dataNasc(clienteAtualizado.getDataNasc())
                    .endereco(clienteAtualizado.getEndereco())
                    .build();

            return ResponseEntity.ok(clienteResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
