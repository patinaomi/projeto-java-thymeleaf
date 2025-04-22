package br.com.fiap.challenge.security;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.gateways.repository.DentistaRepository;
import br.com.fiap.challenge.service.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Dentista dentista = dentistaRepository.findByEmail(email).orElse(null);
        if (dentista != null) {
            return new UserDetailsImpl(dentista);
        }

        Clinica clinica = clinicaRepository.findByEmail(email).orElse(null);
        if (clinica != null) {
            return new UserDetailsImpl(clinica);
        }

        throw new UsernameNotFoundException("Usuário não encontrado com e-mail: " + email);
    }

    public static UserDetailsImpl getAuthenticatedUserDetails() {
        try {
            return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new AuthenticationException("Não há nenhum usuário autenticado.");
        }
    }

    public static UserDetailsImpl getAuthenticatedUserDetailsOrNull() {
        try {
            return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
