package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Dentista;

import java.util.List;
import java.util.Optional;

public interface DentistaService {

    Dentista criar(Dentista dentista);
    Optional<Dentista> buscarPorId(String id);
    List<Dentista> buscarTodos();
    Optional<Dentista> atualizar(String id, Dentista dentista);
    boolean deletar(String id);
}
