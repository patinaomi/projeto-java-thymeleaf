package br.com.fiap.challenge.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DentistaQueueConfig {
    public static final String DENTISTA_QUEUE = "dentistaQueue";
    public static final String DENTISTA_EXCHANGE = "dentistaExchange";
    public static final String DENTISTA_ROUTING_KEY = "dentistaRoutingKey";

    @Bean
    public Queue dentistaQueue() {
        return QueueBuilder.durable(DENTISTA_QUEUE).build();
    }

    @Bean
    public Exchange dentistaExchange() {
        return ExchangeBuilder.directExchange(DENTISTA_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Binding dentistaBinding(Queue dentistaQueue, Exchange dentistaExchange) {
        return BindingBuilder.bind(dentistaQueue)
                .to(dentistaExchange)
                .with(DENTISTA_ROUTING_KEY)
                .noargs();
    }
}
