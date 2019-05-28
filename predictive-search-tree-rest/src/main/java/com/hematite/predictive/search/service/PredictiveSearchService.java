package com.hematite.predictive.search.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.SearchResult;
import com.hematite.predictive.search.tree.TreeNode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictiveSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictiveSearchService.class);

    private final DataProvider dataProvider;
    private final PredictiveSearchTreeFactory treeFactory;

    private TreeNode rootNode;

    @PostConstruct
    public void init() {
        final List<NodeData> data = dataProvider.getAllData();
        rootNode = treeFactory.createTree(data);
        LOGGER.info("Data rows count: {}", data.size());
        LOGGER.info("Nodes count: {}", treeFactory.getNodeCount());
    }

    public List<SearchResult> search(final String text) {
        return rootNode.search(text);
    }
}
