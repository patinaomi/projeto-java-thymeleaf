package br.com.fiap.challenge.config;

import br.com.fiap.challenge.gateways.dtos.request.AuthRequest;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.service.exception.DataIntegrityException;
import br.com.fiap.challenge.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            AuthRequest credentials = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new DataIntegrityException("Erro ao ler credenciais de login", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth) throws IOException, ServletException {
        var user = (UserDetailsImpl) auth.getPrincipal();
        String token = jwtUtils.generateToken(user.getUsername(), user.getAuthorities());

        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write("{\"token\": \"" + token + "\"}");
        response.getWriter().flush();
    }
}

