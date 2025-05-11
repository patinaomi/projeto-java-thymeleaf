package br.com.fiap.challenge.gateways.repository;

import br.com.fiap.challenge.domains.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    List<Consulta> findAllByDentistaIdDentista(Integer idDentista);
}
