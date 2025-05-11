package br.com.fiap.challenge.gateways.dtos.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DentistaUpdateRequest {

    @Size(max = 100, message = "O nome do dentista deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 100, message = "O sobrenome do dentista deve ter no máximo 100 caracteres")
    private String sobrenome;

    @Size(max = 15, message = "O telefone do dentista deve ter no máximo 15 caracteres")
    private String telefone;

    private Float avaliacao;
}
