package br.com.fiap.challenge.gateways.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FeedbackUpdateRequest {

    private Float avaliacao;

    @Size(max = 250, message = "O comentário deve ter no máximo 250 caracteres")
    private String comentario;
}
