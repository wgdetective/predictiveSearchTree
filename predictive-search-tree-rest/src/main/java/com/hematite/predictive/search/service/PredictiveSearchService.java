package com.hematite.predictive.search.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictiveSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictiveSearchService.class);

    private final DataProvider dataProvider;
    private final PredictiveSearchTreeFactory treeFactory;
    private final RabbitTemplate rabbitTemplate;

    private TreeNode rootNode;

    @Value("${response.exchange.name}")
    private String responseExchange;

    @Value("${response.routing.key}")
    private String responseRoutingKey;

    @PostConstruct
    public void init() {
        rootNode = treeFactory.createTree(dataProvider.getAllData());
    }

    public List<NodeData> search(final String text) {
        return rootNode.search(text);
    }

    public void searchFromQueue(final String text) {
        final List<NodeData> nodeDataList = search(text);
        rabbitTemplate.convertAndSend(responseExchange, responseRoutingKey, nodeDataList);
        LOGGER.info("Send response message to queue: {}", nodeDataList);
    }
}
