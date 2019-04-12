package com.hematite.predictive.search.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.neo4j.entity.NodeData;
import com.hematite.predictive.search.neo4j.entity.TreeNodeNeo;
import com.hematite.predictive.search.neo4j.repository.HotelNeo4JRepository;
import com.hematite.predictive.search.tree.SearchResult;

import com.hematite.predictive.search.tree.TreeNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PredictiveSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictiveSearchService.class);

    private final DataProvider dataProvider;
    private final PredictiveSearchTreeFactory treeFactory;
    private final RabbitTemplate rabbitTemplate;

    private TreeNode rootNode;

    @Autowired
    private HotelNeo4JRepository hotelNeo4JRepository;

    /*@Value("${response.exchange.name}")
    private String responseExchange;

    @Value("${response.routing.key}")
    private String responseRoutingKey;*/

    @PostConstruct
    public void init() {
        rootNode = treeFactory.createTree(dataProvider.getAllData());
    }

    public List<SearchResult> search(final String text) {
        return rootNode.search(text);
    }

    public void searchInNeo() {

        final NodeData recordOne = new NodeData();
        recordOne.setKey("a");
        recordOne.setData("a");

        final NodeData recordTwo = new NodeData();
        recordTwo.setKey("ab");
        recordTwo.setData("ab");


        final NodeData recordFour = new NodeData();
        recordFour.setKey("ac");
        recordFour.setData("ac");

        final NodeData recordFive = new NodeData();
        recordFive.setKey("ad");
        recordFive.setData("ad");

        final List<NodeData> nodeDataListRootNode = Arrays.asList(recordOne, recordTwo, recordFour, recordFive);

        final TreeNodeNeo treeNodeOne = new TreeNodeNeo();
        treeNodeOne.setKey("lala");
        treeNodeOne.setValues(nodeDataListRootNode);

        final List<NodeData> nodeDataListRootOne = Arrays.asList(recordOne, recordFour, recordFive);

        final TreeNodeNeo treeNodeTwo = new TreeNodeNeo();
        treeNodeTwo.setKey("a");
        treeNodeTwo.setValues(nodeDataListRootOne);
        Map<String, TreeNodeNeo> hashMap = new HashMap<>();
        hashMap.put("a", treeNodeTwo);
        treeNodeTwo.setChildNodes(hashMap);


        hotelNeo4JRepository.save(treeNodeOne);
        hotelNeo4JRepository.save(treeNodeTwo);

    }

    /*public void searchFromQueue(final String text) {
        final List<SearchResult> nodeDataList = search(text);
        rabbitTemplate.convertAndSend(responseExchange, responseRoutingKey, nodeDataList);
        LOGGER.info("Send response message to queue: {}", nodeDataList);
    }*/
}
