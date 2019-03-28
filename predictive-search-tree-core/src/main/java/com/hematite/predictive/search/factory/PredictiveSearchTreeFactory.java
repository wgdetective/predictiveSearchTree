package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class PredictiveSearchTreeFactory {

    private static final int MAX_LIST_ITEMS_COUNT = 10;

    private final TextSearchService textSearchService;

    public TreeNode createTree(final List<NodeData<String>> data) {
        final TreeNode rootNode = new TreeNode("", getSubList(data));
        createChildNodes(data, rootNode);
        return rootNode;
    }

    private void createChildNodes(final List<NodeData<String>> words, final TreeNode parentNode) {
        //TODO spaces and numbers
        for (char letter = 'a'; letter <= 'z'; letter++) {
            final String key = parentNode.getKey() + letter;
            final List<NodeData<String>> filteredList = textSearchService.search(words, key);
            if (!filteredList.isEmpty()) {

                final TreeNode childNode = new TreeNode(key, getSubList(filteredList));
                parentNode.addChildNode(key, childNode);
            }
        }

        for (final Map.Entry<String, TreeNode> childNode : parentNode.getChildNodes().entrySet()) {
            createChildNodes(words, childNode.getValue());
        }
    }

    private List<NodeData<String>> getSubList(final List<NodeData<String>> list) {
        return list.subList(0, Math.min(MAX_LIST_ITEMS_COUNT, list.size()));
    }
}
