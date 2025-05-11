package br.com.fiap.challenge.gateways.repository;

import br.com.fiap.challenge.domains.FormularioDetalhado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioDetalhadoRepository extends JpaRepository<FormularioDetalhado, Integer> {
}
