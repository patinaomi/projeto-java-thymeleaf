package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {

    Consulta criar(Consulta consulta);
    Optional<Consulta> buscarPorId(String id);
    List<Consulta> buscarTodas();
    Optional<Consulta> atualizar(String id, Consulta consulta);
    boolean deletar(String id);
}
