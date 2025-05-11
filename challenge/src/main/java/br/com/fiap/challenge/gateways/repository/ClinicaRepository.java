package br.com.fiap.challenge.gateways.repository;

import br.com.fiap.challenge.domains.Clinica;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {
    Optional<Clinica> findByUserUsername(String username);

}
