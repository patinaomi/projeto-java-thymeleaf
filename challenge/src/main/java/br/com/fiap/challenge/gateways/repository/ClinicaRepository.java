package br.com.fiap.challenge.gateways.repository;

import br.com.fiap.challenge.domains.Clinica;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    Optional<Clinica> findByEmail(String email);
}
