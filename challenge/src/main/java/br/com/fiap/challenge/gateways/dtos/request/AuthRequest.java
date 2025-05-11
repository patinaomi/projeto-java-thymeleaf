package br.com.fiap.challenge.gateways.dtos.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

