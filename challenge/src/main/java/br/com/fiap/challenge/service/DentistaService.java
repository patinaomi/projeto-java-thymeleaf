package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Dentista;

import java.util.List;
import java.util.Optional;

public interface DentistaService {

    Dentista criar(Dentista dentista);
    Optional<Dentista> buscarPorId(Integer id);
    List<Dentista> buscarTodos();
    Optional<Dentista> atualizar(Integer id, Dentista dentista);
    boolean deletar(Integer id);
}
