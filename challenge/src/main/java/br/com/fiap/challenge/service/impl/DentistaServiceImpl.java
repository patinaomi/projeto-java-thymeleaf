package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.gateways.repository.DentistaRepository;
import br.com.fiap.challenge.service.DentistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DentistaServiceImpl implements DentistaService {

    private final DentistaRepository dentistaRepository;

    @Override
    public Dentista criar(Dentista dentista) {
        dentista.setTelefone(limparCaracteresTel(dentista.getTelefone()));
        return dentistaRepository.save(dentista);
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

    private String limparCaracteresTel(String telefone) {
        return telefone != null ? telefone.replaceAll("\\D", "") : null;
    }

    public List<Dentista> buscarPorIdClinica(Integer idClinica) {
        return dentistaRepository.findAllByClinicaIdClinica(idClinica);
    }

}
