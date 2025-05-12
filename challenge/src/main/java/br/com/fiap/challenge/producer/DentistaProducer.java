package br.com.fiap.challenge.producer;

import br.com.fiap.challenge.gateways.dtos.response.DentistaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static br.com.fiap.challenge.config.DentistaQueueConfig.DENTISTA_EXCHANGE;
import static br.com.fiap.challenge.config.DentistaQueueConfig.DENTISTA_ROUTING_KEY;

@Component
@RequiredArgsConstructor
public class DentistaProducer {
    private final RabbitTemplate template;

    public void enviarMensagem(DentistaResponse response) {
        template.convertAndSend(DENTISTA_EXCHANGE, DENTISTA_ROUTING_KEY, response);
    }
}
