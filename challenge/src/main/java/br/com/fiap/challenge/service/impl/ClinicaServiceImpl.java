package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.service.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicaServiceImpl implements ClinicaService {

    private final ClinicaRepository clinicaRepository;


    @Override
    public Clinica criar(Clinica clinica) {
        return clinicaRepository.save(clinica);
    }

    @Override
    public Clinica buscarPorId(Integer id) {
        return clinicaRepository.findById(id).orElseThrow(() -> new RuntimeException("Clinica não encontrada"));
    }

    @Override
    public List<Clinica> buscarTodos() {
        return clinicaRepository.findAll();
    }

    @Override
    public Optional<Clinica> buscarPorUsername(String username) {
        return clinicaRepository.findByUserUsername(username);
    }

    @Override
    public Page<Clinica> buscarTodosPaginado(Pageable pageable) {
        return null;
    }

    @Override
    public Clinica atualizar(Integer id, Clinica clinica) {
        return null;
    }

    @Override
    public Boolean deletar(Integer id) {
        return clinicaRepository.findById(id).map(clinica -> {
            clinicaRepository.deleteById(id);
            return true;
        }).orElse(false);

    }
}
