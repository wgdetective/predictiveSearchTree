package com.hematite.predictive.search.service;

import com.hematite.predictive.search.tree.SearchResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    private final PredictiveSearchService searchService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${response.exchange.name}")
    private String responseExchange;

    @Value("${response.routing.key}")
    private String responseRoutingKey;

    @RabbitListener(queues = "${request.queue.name}")
    public void receiveMessageFromRequestQueue(final String value) {
        LOGGER.info("Received message from request queue: {}", value);
        final List<SearchResult> nodeDataList = searchService.search(value);
        rabbitTemplate.convertAndSend(responseExchange, responseRoutingKey, nodeDataList);
        LOGGER.info("Send response message to queue: {}", nodeDataList);
    }
}
