package br.com.fiap.challenge.security;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserDetailsImpl(Dentista dentista) {
        this.id = dentista.getIdDentista();
        this.username = dentista.getEmail();
        this.password = dentista.getSenha();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + dentista.getRole().name()));
    }

    public UserDetailsImpl(Clinica clinica) {
        this.id = clinica.getIdClinica();
        this.username = clinica.getEmail();
        this.password = clinica.getSenha();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + clinica.getRole().name()));
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Role role) {
        return authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + role.name()));
    }
}
