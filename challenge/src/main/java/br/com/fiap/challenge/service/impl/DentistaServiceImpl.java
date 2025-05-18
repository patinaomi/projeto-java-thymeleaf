package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.gateways.dtos.response.DentistaResponse;
import br.com.fiap.challenge.gateways.repository.DentistaRepository;
import br.com.fiap.challenge.producer.DentistaProducer;
import br.com.fiap.challenge.service.DentistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.fiap.challenge.utils.FormatUtil.formatarNomeCompleto;
import static br.com.fiap.challenge.utils.FormatUtil.limparTelefone;

@Service
@RequiredArgsConstructor
public class DentistaServiceImpl implements DentistaService {

    private final DentistaRepository dentistaRepository;
    private final DentistaProducer dentistaProducer;

    @Override
    public Dentista criar(Dentista dentista) {
        dentista.setNome(formatarNomeCompleto(dentista.getNome()));
        dentista.setSobrenome(formatarNomeCompleto(dentista.getSobrenome()));
        dentista.setTelefone(limparTelefone(dentista.getTelefone()));
        Dentista savedDentista = dentistaRepository.save(dentista);

        dentistaProducer.enviarMensagem(DentistaResponse.builder()
                .nome(savedDentista.getNome())
                .sobrenome(savedDentista.getSobrenome())
                .telefone(savedDentista.getTelefone())
                .email(savedDentista.getUser().getUsername())
                .avaliacao(savedDentista.getAvaliacao())
                .clinica(savedDentista.getClinica())
                .especialidade(savedDentista.getEspecialidade())
                .build()
        );

        return savedDentista;
    }

    @Override
    public Dentista buscarPorId(Integer id) {
        return dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
    }

    @Override
    public List<Dentista> buscarTodos() {
        return dentistaRepository.findAll();
    }

    @Override
    public Optional<Dentista> buscarPorUsername(String username) {
        return dentistaRepository.findByUserUsername(username);
    }

    @Override
    public Optional<Dentista> atualizar(Integer id, Dentista dentista) {
        return dentistaRepository.findById(id).map(dentistaExistente -> {
            dentista.setIdDentista(id);
            dentista.setNome(formatarNomeCompleto(dentista.getNome()));
            dentista.setSobrenome(formatarNomeCompleto(dentista.getSobrenome()));
            dentista.setTelefone(limparTelefone(dentista.getTelefone()));
            return dentistaRepository.save(dentista);
        });
    }

    @Override
    public boolean deletar(Integer id) {
        return dentistaRepository.findById(id).map(dentista -> {
            dentistaRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    public List<Dentista> buscarPorIdClinica(Integer idClinica) {
        return dentistaRepository.findAllByClinicaIdClinica(idClinica);
    }
}
