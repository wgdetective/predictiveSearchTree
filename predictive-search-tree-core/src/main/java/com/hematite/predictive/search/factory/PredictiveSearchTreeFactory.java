package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PredictiveSearchTreeFactory {
    private static final int MAX_LIST_ITEMS_COUNT = 10;

    private final List<String> symbols = initSymbols();

    private final TextSearchService textSearchService = new TextSearchService();

    private long nodeCount = 0;

    public TreeNode createTree(final List<NodeData> data) {
        final TreeNode rootNode = new TreeNode("", getSubList(data));
        nodeCount++;

        data.forEach(d -> d.setPrefix(textSearchService.calculatePrefixFunction(d.getKey())));

        for (final String key : symbols) {
            final List<NodeData> filteredList = textSearchService.search(data, key);
            if (!filteredList.isEmpty()) {
                final TreeNode childNode = new TreeNode(key, new ArrayList<>(), rootNode);
                rootNode.addChildNode(key, childNode);
                nodeCount++;
                createChildNodes(filteredList, childNode);
            }
        }

        return rootNode;
    }

    private void createChildNodes(final List<NodeData> words, final TreeNode parentNode) {
        parentNode.getValues().addAll(getSubList(words));
        for (final NodeData nodeData : words) {
            final int startIndex = nodeData.getKey().indexOf(parentNode.getKey());
            final int endIndex = startIndex + parentNode.getKey().length() + 1;

            if (startIndex > -1 && endIndex < nodeData.getKey().length() + 1) {
                final String newKey = nodeData.getKey().substring(startIndex, endIndex);
                if (!parentNode.getChildNodes().containsKey(newKey)) {
                    final TreeNode childNode = new TreeNode(newKey, new ArrayList<>(), parentNode);
                    parentNode.addChildNode(newKey, childNode);
                }
            }
        }

        for (final Map.Entry<String, TreeNode> childNode : parentNode.getChildNodes().entrySet()) {
            final List<NodeData> filteredList = textSearchService.search(words, childNode.getKey());
            createChildNodes(filteredList, childNode.getValue());
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

    public long getNodeCount() {
        return nodeCount;
    }
}
