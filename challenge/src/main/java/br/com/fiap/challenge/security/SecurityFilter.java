package br.com.fiap.challenge.security;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.domains.enums.Role;
import br.com.fiap.challenge.gateways.repository.ClinicaRepository;
import br.com.fiap.challenge.gateways.repository.DentistaRepository;
import br.com.fiap.challenge.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

//    @Autowired
//    private ClinicaRepository clinicaRepository;
//
//    @Autowired
//    private DentistaRepository dentistaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request);

        if (token != null && jwtUtils.isTokenValid(token)) {
            String username = jwtUtils.getUsernameFromToken(token);
            List<String> roles = jwtUtils.getRolesFromToken(token);

            UserDetails userDetails = null;

//            if (Objects.equals("ROLE_" + Role.CLINICA.name(), roles.getFirst())) {
//                Optional<Clinica> clinica = clinicaRepository.findByEmail(username);
//                if (clinica.isPresent()) {
//                    userDetails = new UserDetailsImpl(clinica.get());
//                }
//            } else if (Objects.equals("ROLE_" + Role.DENTISTA.name(), roles.getFirst())) {
//                Optional<Dentista> dentista = dentistaRepository.findByEmail(username);
//                if (dentista.isPresent()) {
//                    userDetails = new UserDetailsImpl(dentista.get());
//                }
//            }

            if (userDetails != null) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
}

