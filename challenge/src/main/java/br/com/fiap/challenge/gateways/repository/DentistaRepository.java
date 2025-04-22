package br.com.fiap.challenge.gateways.repository;

import br.com.fiap.challenge.domains.Dentista;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, Integer> {

    Optional<Dentista> findByEmail(String email);
}
