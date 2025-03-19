package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente criar(Cliente cliente);
    Cliente buscarPorId(Integer id);
    List<Cliente> buscarTodos();
    Cliente atualizar(Integer id, Cliente cliente);
    void deletar(Integer id);
}
