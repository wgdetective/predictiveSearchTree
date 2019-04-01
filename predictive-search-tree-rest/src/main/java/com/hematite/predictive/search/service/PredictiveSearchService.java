package com.hematite.predictive.search.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PredictiveSearchService {

    private final DataProvider dataProvider;
    private final PredictiveSearchTreeFactory treeFactory;
    private TreeNode rootNode;

    @Autowired
    private void init() {
        rootNode = treeFactory.createTree(dataProvider.getAllData());
    }

    public List<NodeData> search(final String text) {
        return processChild(text, rootNode);
    }

    private List<NodeData> processChild(final String text,
                                                final TreeNode treeNode) {
        if (text.equals(treeNode.getKey())) {
            return treeNode.getValues();
        }
        if (treeNode.hasChild()) {
            for (final Map.Entry<String, TreeNode> childNode : treeNode.getChildNodes().entrySet()) {
                if (text.startsWith(childNode.getKey())) {
                    processChild(text, childNode.getValue());
                }
            }
        }
        return Collections.emptyList();
    }
}
