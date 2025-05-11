package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback criar(Feedback feedback);
    Feedback buscarPorId(Integer id);
    List<Feedback> buscarTodos();
    List<Feedback> buscarPorUsuarioClinica(String username);
    Feedback atualizar(Integer id, Feedback feedback);
    boolean deletar(Integer id);
}
