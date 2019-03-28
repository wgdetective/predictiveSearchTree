package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;

import java.util.List;
import java.util.Map;

public class PredictiveSearchTreeFactory {
    private static final int MAX_LIST_ITEMS_COUNT = 10;

    private final TextSearchService textSearchService = new TextSearchService();

    public TreeNode createTree(final List<NodeData> data) {
        final TreeNode rootNode = new TreeNode("", getSubList(data));

        data.forEach(d -> d.setPrefix(textSearchService.calculatePrefixFunction(d.getKey())));

        createChildNodes(data, rootNode);
        return rootNode;
    }

    private void createChildNodes(final List<NodeData> words, final TreeNode parentNode) {
        //TODO spaces and numbers
        for (char letter = 'a'; letter <= 'z'; letter++) {
            final String key = parentNode.getKey() + letter;
            final List<NodeData> filteredList = textSearchService.search(words, key);
            if (!filteredList.isEmpty()) {
                final TreeNode childNode = new TreeNode(key, getSubList(filteredList));
                parentNode.addChildNode(key, childNode);
            }
        }

        for (final Map.Entry<String, TreeNode> childNode : parentNode.getChildNodes().entrySet()) {
            createChildNodes(words, childNode.getValue());
        }
    }

    private List<NodeData> getSubList(final List<NodeData> list) {
        return list.subList(0, Math.min(MAX_LIST_ITEMS_COUNT, list.size()));
    }
}
