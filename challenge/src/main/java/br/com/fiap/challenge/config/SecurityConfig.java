package br.com.fiap.challenge.config;

import br.com.fiap.challenge.domains.enums.Role;
import br.com.fiap.challenge.security.CustomAuthenticationFailureHandler;
import br.com.fiap.challenge.security.SecurityFilter;
import br.com.fiap.challenge.utils.JWTUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    private final String[] PUBLIC_ENDPOINTS = {
            "/auth/login",
            "/auth/register",
            "/access-denied",
            "/actuator/**",
            //TODO: Ver se o que é necessário liberar
            "/static/**",
            "/styles/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .requestMatchers("/clientes/**").hasAuthority(Role.CLINICA.name())
                        .requestMatchers("/dentistas/**").hasAuthority(Role.DENTISTA.name())
                        .requestMatchers("/feedbacks/**").hasAuthority(Role.CLINICA.name())
                        .anyRequest().authenticated()
                )

                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .addFilterBefore(new JWTAuthenticationFilter(authenticationManager, jwtUtils, failureHandler), UsernamePasswordAuthenticationFilter.class)
                 .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((req, res, e) -> {
                            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            res.setContentType("application/json");
                            res.getWriter().write("{\"error\":\"Acesso negado\"}");
                        })
                )

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

     @Bean
     public AuthenticationFailureHandler authenticationFailureHandler() {
         return new CustomAuthenticationFailureHandler();
     }
}
