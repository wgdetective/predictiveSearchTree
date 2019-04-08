package com.hematite.predictive.search.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class RabbitMQConfig {

    @Value("${request.queue.name}")
    String requestQueue;

    @Value("${response.queue.name}")
    String responseQueue;

    @Value("${request.exchange.name}")
    String requestExchange;

    @Value("${response.exchange.name}")
    String responseExchange;

    @Value("${request.routing.key}")
    private String requestRoutingKey;

    @Value("${response.routing.key}")
    private String responseRoutingKey;

    @Bean
    public Queue requestQueue() {
        return new Queue(requestQueue, false);
    }

    @Bean
    public Queue responseQueue() {
        return new Queue(responseQueue);
    }

    @Bean
    DirectExchange requestExchange() {
        return new DirectExchange(requestExchange);
    }

    @Bean
    DirectExchange responseExchange() {
        return new DirectExchange(responseExchange);
    }

    @Bean
    Binding bindingRequest(@Qualifier("requestQueue") Queue queue, @Qualifier("requestExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(requestRoutingKey);
    }

    @Bean
    Binding bindingResponse(@Qualifier("responseQueue") Queue queue, @Qualifier("responseExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(responseRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
