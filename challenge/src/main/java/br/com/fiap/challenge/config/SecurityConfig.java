package br.com.fiap.challenge.config;

import br.com.fiap.challenge.domains.enums.Role;
// import br.com.fiap.challenge.security.CustomAuthenticationFailureHandler;
// import br.com.fiap.challenge.security.SecurityFilter;
// import br.com.fiap.challenge.utils.JWTUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.AuthenticationFailureHandler;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // @Autowired
    // private SecurityFilter securityFilter;

    // @Autowired
    // private JWTUtils jwtUtils;

    // @Autowired
    // private AuthenticationFailureHandler failureHandler;

    // Pode manter se for usar depois
    private final String[] PUBLIC_ENDPOINTS = {
            "/auth/login",
            "/auth/register",
            "/clientes/**",
            "/dentistas/**",
            "/feedbacks/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity/*, AuthenticationManager authenticationManager*/) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll() // 🔓 Permite tudo
                        // .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        // .requestMatchers("/clinica/**").hasAuthority(Role.CLINICA.name())
                        // .requestMatchers("/dentista/**").hasAuthority(Role.DENTISTA.name())
                        // .requestMatchers("/feedback/**").hasAuthority(Role.CLINICA.name())
                        // .anyRequest().authenticated()
                )
                // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // .addFilterBefore(new JWTAuthenticationFilter(authenticationManager, jwtUtils, failureHandler), UsernamePasswordAuthenticationFilter.class)
                // .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
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

    // @Bean
    // public AuthenticationFailureHandler authenticationFailureHandler() {
    //     return new CustomAuthenticationFailureHandler();
    // }
}
