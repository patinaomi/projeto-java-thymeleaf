package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Clinica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClinicaService {

    Clinica criar(Clinica clinica);
    Clinica buscarPorId(Integer id);
    List<Clinica> buscarTodos();

    Page<Clinica> buscarTodosPaginado(Pageable pageable);
    Clinica atualizar(Integer id, Clinica clinica);
    Boolean deletar(Integer id);
}
