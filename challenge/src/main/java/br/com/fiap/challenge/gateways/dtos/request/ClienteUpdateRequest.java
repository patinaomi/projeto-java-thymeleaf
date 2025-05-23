package br.com.fiap.challenge.gateways.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteUpdateRequest {

    @Size(max = 100, message = "O nome do cliente deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 100, message = "O sobrenome do cliente deve ter no máximo 100 caracteres")
    private String sobrenome;

    @Email(message = "O email do cliente não é válido")
    @Size(max = 100, message = "O e-mail do cliente deve ter no máximo 100 caracteres")
    private String email;

    @Size(max = 15, message = "O telefone do cliente deve ter no máximo 15 caracteres")
    private String telefone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNasc;

    @Size(max = 255, message = "O endereço do cliente deve ter no máximo 255 caracteres")
    private String endereco;

}
