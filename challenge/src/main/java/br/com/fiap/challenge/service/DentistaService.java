package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Dentista;

import java.util.List;
import java.util.Optional;

public interface DentistaService {

    Dentista criar(Dentista dentista);
    Dentista buscarPorId(Integer id);
    List<Dentista> buscarTodos();
    Optional<Dentista> buscarPorUsername(String username);
    Optional<Dentista> atualizar(Integer id, Dentista dentista);
    boolean deletar(Integer id);
    List<Dentista> buscarPorIdClinica(Integer idClinica);
}
