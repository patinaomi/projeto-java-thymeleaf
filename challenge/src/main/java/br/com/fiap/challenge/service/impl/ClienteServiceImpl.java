package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.gateways.repository.ClienteRepository;
import br.com.fiap.challenge.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final EmailServiceImpl emailService;

    @Override
    public Cliente criar(Cliente cliente) {
        cliente.setTelefone(limparCaracteresTel(cliente.getTelefone()));
        Cliente clienteSalvo = clienteRepository.save(cliente);

        String mensagemEmail = String.format(
                "Olá, %s! Seu cadastro foi realizado com sucesso!",
                cliente.getNome()
        );

        //TODO: Ajustar envio de email
//        emailService.enviarEmail(cliente.getEmail(), "Cadastro Realizado", mensagemEmail);
        return clienteSalvo;
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente atualizar(Integer id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setIdCliente(id);
            cliente.setTelefone(limparCaracteresTel(cliente.getTelefone())); // Limpeza do telefone antes de atualizar
            return clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    @Override
    public void deletar(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public Page<Cliente> buscarTodosPaginado(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    // Método utilitário para limpar caracteres não numéricos do telefone
    private String limparCaracteresTel(String telefone) {
        return telefone != null ? telefone.replaceAll("\\D", "") : null;
    }
}
