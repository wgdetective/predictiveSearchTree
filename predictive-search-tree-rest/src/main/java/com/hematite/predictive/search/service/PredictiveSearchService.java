package com.hematite.predictive.search.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.neo4j.PredictiveSearchTreeNeoFactory;
import com.hematite.predictive.search.neo4j.entity.NodeDataNeo;
import com.hematite.predictive.search.neo4j.entity.TreeNodeNeo;
import com.hematite.predictive.search.neo4j.repository.HotelNeo4JRepository;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.SearchResult;

import com.hematite.predictive.search.tree.TreeNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PredictiveSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictiveSearchService.class);

    private final DataProvider dataProvider;
    private final PredictiveSearchTreeFactory treeFactory;

    private TreeNode rootNode;

    private final HotelNeo4JRepository hotelNeo4JRepository;

    private PredictiveSearchTreeNeoFactory factory;

    @PostConstruct
    public void init() {
        factory = new PredictiveSearchTreeNeoFactory(hotelNeo4JRepository);
        final List<NodeData> data = dataProvider.getAllData();
        rootNode = treeFactory.createTree(data);
        LOGGER.info("Data rows count: {}", data.size());
        LOGGER.info("Nodes count: {}", treeFactory.getNodeCount());

        final List<NodeDataNeo> nodeDataNeoList = new ArrayList<>();
        for (final NodeData nodeData : data) {
            final NodeDataNeo nodeDataNeo = new NodeDataNeo();
            nodeDataNeo.setKey(nodeData.getKey());
            nodeDataNeo.setData(nodeData.getData());
            nodeDataNeoList.add(nodeDataNeo);
        }
        factory.createTree(nodeDataNeoList);
        //searchInNeo();
    }

    public List<SearchResult> search(final String text) {
        return rootNode.search(text);
    }

    public void searchInNeo() {

        final NodeDataNeo recordOne = new NodeDataNeo();
        recordOne.setKey("a");
        recordOne.setData("a");

        final NodeDataNeo recordTwo = new NodeDataNeo();
        recordTwo.setKey("ab");
        recordTwo.setData("ab");

        final NodeDataNeo recordFour = new NodeDataNeo();
        recordFour.setKey("ac");
        recordFour.setData("ac");

        final NodeDataNeo recordFive = new NodeDataNeo();
        recordFive.setKey("ad");
        recordFive.setData("ad");

        final List<NodeDataNeo> nodeDataListRootNode = Arrays.asList(recordOne, recordTwo, recordFour, recordFive);

        final TreeNodeNeo treeNodeOne = new TreeNodeNeo();
        treeNodeOne.setKey("lala");
        treeNodeOne.setValues(nodeDataListRootNode);

        final List<NodeDataNeo> nodeDataListRootOne = Arrays.asList(recordOne, recordFour, recordFive);

        final TreeNodeNeo treeNodeTwo = new TreeNodeNeo();
        treeNodeTwo.setKey("a");
        treeNodeTwo.setValues(nodeDataListRootOne);


        hotelNeo4JRepository.save(treeNodeTwo);
        hotelNeo4JRepository.save(treeNodeOne);

    }
}
