package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Consulta;
import br.com.fiap.challenge.gateways.repository.ConsultaRepository;
import br.com.fiap.challenge.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Override
    public Consulta criar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Override
    public Optional<Consulta> buscarPorId(String id) {
        return consultaRepository.findById(id);
    }

    @Override
    public List<Consulta> buscarTodas() {
        return consultaRepository.findAll();
    }

    @Override
    public Optional<Consulta> atualizar(String id, Consulta consulta) {
        return consultaRepository.findById(id).map(consultaExistente -> {
            consulta.setIdConsulta(id);
            return consultaRepository.save(consulta);
        });
    }

    @Override
    public boolean deletar(String id) {
        return consultaRepository.findById(id).map(consulta -> {
            consultaRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
