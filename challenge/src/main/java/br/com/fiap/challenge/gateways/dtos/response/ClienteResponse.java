package br.com.fiap.challenge.gateways.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ClienteResponse {

    String nome;
    String sobrenome;
    String email;
    String telefone;
    LocalDate dataNasc;
    String endereco;
}
