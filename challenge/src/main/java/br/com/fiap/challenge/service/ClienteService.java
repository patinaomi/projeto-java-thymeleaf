package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    Cliente criar(Cliente cliente);
    Cliente buscarPorId(Integer id);
    List<Cliente> buscarTodos();

    Page<Cliente> buscarTodosPaginado(Pageable pageable);
    Cliente atualizar(Integer id, Cliente cliente);
    void deletar(Integer id);
}
