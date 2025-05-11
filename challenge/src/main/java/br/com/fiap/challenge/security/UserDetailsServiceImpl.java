package br.com.fiap.challenge.security;

import br.com.fiap.challenge.domains.User;
import br.com.fiap.challenge.gateways.repository.UserRepository;
import br.com.fiap.challenge.service.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
        return new UserDetailsImpl(user);
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
