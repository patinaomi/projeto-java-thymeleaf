package br.com.fiap.challenge.consumer;

import br.com.fiap.challenge.gateways.dtos.response.DentistaResponse;
import br.com.fiap.challenge.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.fiap.challenge.config.DentistaQueueConfig.DENTISTA_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class DentistaConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = DENTISTA_QUEUE)
    public void listener(DentistaResponse response) {
        log.info("Dentista received: {}", response);
        emailService.enviarEmail(response.getEmail(), "Cadastro confirmado",
                "Olá " + response.getNome() + ", seu cadastro foi realizado com sucesso!");
    }
}
