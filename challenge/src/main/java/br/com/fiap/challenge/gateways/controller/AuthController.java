package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.gateways.request.AuthRequest;
import br.com.fiap.challenge.security.UserDetailsImpl;
import br.com.fiap.challenge.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authentication = authenticationManager.authenticate(authToken);

        var user = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateToken(user.getUsername(), user.getAuthorities());

        return ResponseEntity.ok().body(Collections.singletonMap("token", token));
    }
}
