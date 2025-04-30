package br.com.fiap.challenge.gateways.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

