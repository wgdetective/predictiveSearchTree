package com.hematite.predictive.search.service;

import com.hematite.predictive.search.tree.NodeData;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private final PredictiveSearchService searchService;

    /*@RabbitListener(queues = "${request.queue.name}")
    public void receiveMessageFromRequestQueue(String value) {
        searchService.searchFromQueue(value);
        LOGGER.info("Received message from request queue: {}", value);
    }*/

}
