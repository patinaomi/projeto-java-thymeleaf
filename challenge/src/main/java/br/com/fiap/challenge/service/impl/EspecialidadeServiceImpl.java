package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Especialidade;
import br.com.fiap.challenge.gateways.repository.EspecialidadeRepository;
import br.com.fiap.challenge.service.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadeServiceImpl implements EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;

    @Override
    public List<Especialidade> buscarTodas() {
        return especialidadeRepository.findAll();
    }
}
