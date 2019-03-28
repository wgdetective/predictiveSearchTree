package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import com.hematite.predictive.search.util.NodeListUtil;

import java.util.List;
import java.util.Map;

public class PredictiveSearchTreeFactory {

    private static final int MAX_LIST_ITEMS_COUNT = 10;

    public TreeNode createTree(final List<NodeData<String>> data) {
        final TreeNode rootNode = new TreeNode("", NodeListUtil.getSubList(data, MAX_LIST_ITEMS_COUNT));
        createChildNodes(data, rootNode);
        return rootNode;
    }

    private void createChildNodes(final List<NodeData<String>> words, final TreeNode parentNode) {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            final String key = parentNode.getKey() + letter;
            final List<NodeData<String>> filteredList = NodeListUtil.filterList(words, key);
            if (!filteredList.isEmpty()) {
                final TreeNode childNode =
                        new TreeNode(key, NodeListUtil.getSubList(filteredList, MAX_LIST_ITEMS_COUNT), parentNode);
                parentNode.addChildNode(key, childNode);
            }
        }

        for (final Map.Entry<String, TreeNode> childNode : parentNode.getChildNodes().entrySet()) {
            createChildNodes(words, childNode.getValue());
        }
    }
}
