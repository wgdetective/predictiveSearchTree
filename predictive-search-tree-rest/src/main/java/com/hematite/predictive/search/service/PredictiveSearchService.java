package com.hematite.predictive.search.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class PredictiveSearchService {

    private final DataProvider dataProvider;
    private final PredictiveSearchTreeFactory treeFactory;

    private TreeNode rootNode;

    @PostConstruct
    public void init() {
        rootNode = treeFactory.createTree(dataProvider.getAllData());
    }

    public List<NodeData> search(final String text) {
        return search(text, rootNode);
    }

    public List<NodeData> search(final String text,
                                 final TreeNode treeNode) {
        return treeNode.search(text);
    }
}
