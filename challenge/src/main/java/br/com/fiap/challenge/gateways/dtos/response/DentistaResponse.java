package br.com.fiap.challenge.gateways.dtos.response;

import br.com.fiap.challenge.domains.Clinica;
import br.com.fiap.challenge.domains.Especialidade;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DentistaResponse {

    String nome;
    String sobrenome;
    String telefone;
    String email;
    Clinica clinica;
    Especialidade especialidade;
    Float avaliacao;
}
