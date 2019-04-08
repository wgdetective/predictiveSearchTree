package com.hematite.predictive.search.service;

import com.hematite.predictive.search.tree.NodeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "${request.queue.name}")
    public void receiveMessageFromRequestQueue(String value) {
        LOGGER.info("Received message from request queue: {}", value);
    }

    @RabbitListener(queues = "${response.queue.name}")
    public void receiveMessageFromResponseQueue(List<NodeData> nodeDataList) {
        LOGGER.info("Received message from response queue: {}", nodeDataList.size());
    }
}
