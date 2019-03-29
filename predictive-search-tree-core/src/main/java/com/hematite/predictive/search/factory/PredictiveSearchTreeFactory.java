package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PredictiveSearchTreeFactory {
    private static final int MAX_LIST_ITEMS_COUNT = 10;
    private static final String SPACE = " ";

    private final List<String> symbols = initSymbols();

    private final TextSearchService textSearchService = new TextSearchService();

    public TreeNode createTree(final List<NodeData> data) {
        final TreeNode rootNode = new TreeNode("", getSubList(data));

        data.forEach(d -> d.setPrefix(textSearchService.calculatePrefixFunction(d.getKey())));

        createChildNodes(data, rootNode);
        return rootNode;
    }

    private void createChildNodes(final List<NodeData> words, final TreeNode parentNode) {
        for (final String symbol : symbols) {
            final String key = parentNode.getKey() + symbol;
            final List<NodeData> filteredList = textSearchService.search(words, key);
            if (!filteredList.isEmpty()) {
                final TreeNode childNode = new TreeNode(key, getSubList(filteredList), parentNode);
                parentNode.addChildNode(key, childNode);
            }
        }
        if (!symbols.contains(SPACE)) {
            symbols.add(SPACE);
        }

        for (final Map.Entry<String, TreeNode> childNode : parentNode.getChildNodes().entrySet()) {
            createChildNodes(words, childNode.getValue());
        }
    }

    private List<NodeData> getSubList(final List<NodeData> list) {
        return list.subList(0, Math.min(MAX_LIST_ITEMS_COUNT, list.size()));
    }

    private List<String> initSymbols() {
        final List<String> symbols = new ArrayList<>();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            symbols.add(String.valueOf(letter));
        }
        for (int i = 0; i < 10; i++) {
            symbols.add(String.valueOf(i));
        }
        return symbols;
    }
}
