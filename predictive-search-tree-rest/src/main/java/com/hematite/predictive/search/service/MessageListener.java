package com.hematite.predictive.search.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    private final PredictiveSearchService searchService;

    @RabbitListener(queues = "${request.queue.name}")
    public void receiveMessageFromRequestQueue(final String value) {
        LOGGER.info("Received message from request queue: {}", value);
        searchService.searchFromQueue(value);
    }
}
