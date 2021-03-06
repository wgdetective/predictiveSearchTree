package com.hematite.predictive.search.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@Data
@AllArgsConstructor
@ToString(exclude = {"childNodes"})
public class TreeNode {
    private final String key;
    private final List<NodeData> values;

    private final Map<String, TreeNode> childNodes;
    private final TreeNode parentNode;

    public TreeNode(final String key, final List<NodeData> values) {
        this(key, values, null);
    }

    public TreeNode(final String key, final List<NodeData> values, final TreeNode parentNode) {
        this(key, values, new HashMap<>(), parentNode);
    }

    public List<SearchResult> search(final String text) {
        final String normalizedText = text.toLowerCase();
        if (key.equals(normalizedText)) {
            final List<SearchResult> result = new ArrayList<>();
            for (final NodeData value : values) {
                result.add(new SearchResult(value.getKey(), value.getData().toString()));
            }
            return result;
        } else if (!childNodes.isEmpty()) {
            final TreeNode treeNode = childNodes.get(normalizedText.substring(0, key.length() + 1));
            if (treeNode != null) {
                return treeNode.search(normalizedText);
            }
        }
        return Collections.emptyList();
    }

    public void addChildNode(final String key, final TreeNode node) {
        childNodes.put(key, node);
    }

    public boolean hasChild() {
        return !childNodes.isEmpty();
    }
}
