package com.hematite.predictive.search.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictiveSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictiveSearchService.class);

    private final DataProvider dataProvider;
    private final PredictiveSearchTreeFactory treeFactory;

    private TreeNode rootNode;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${request.exchange.name}")
    String requestExchange;

    @Value("${request.routing.key}")
    private String requestRoutingKey;

    @Value("${response.exchange.name}")
    String responseExchange;

    @Value("${response.routing.key}")
    private String responseRoutingKey;

    @PostConstruct
    public void init() {
        rootNode = treeFactory.createTree(dataProvider.getAllData());
    }

    public List<NodeData> search(final String text) {
        return search(text, rootNode);
    }

    public List<NodeData> search(final String text,
                                 final TreeNode treeNode) {
        LOGGER.info("Sending request message: " + text);
        rabbitTemplate.convertAndSend(requestExchange, requestRoutingKey, text);

        final List<NodeData> nodeDataList = treeNode.search(text);
        rabbitTemplate.convertAndSend(responseExchange, responseRoutingKey, nodeDataList);

        return nodeDataList;
    }
}
