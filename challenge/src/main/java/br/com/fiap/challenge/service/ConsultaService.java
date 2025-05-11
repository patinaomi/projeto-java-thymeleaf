package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {

    Consulta criar(Consulta consulta);
    Optional<Consulta> buscarPorId(Integer id);
    List<Consulta> buscarTodas();
    Optional<Consulta> atualizar(Integer id, Consulta consulta);
    List<Consulta> buscarPorDentista(Integer id);
    boolean deletar(Integer id);
}
