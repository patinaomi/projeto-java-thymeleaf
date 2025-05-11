package br.com.fiap.challenge.gateways.repository;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Integer> {
    Optional<Dentista> findByUserUsername(String username);
    List<Dentista> findAllByClinicaIdClinica(Integer idClinica);

}
