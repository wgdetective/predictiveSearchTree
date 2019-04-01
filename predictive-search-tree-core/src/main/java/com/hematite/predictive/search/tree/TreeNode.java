package com.hematite.predictive.search.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
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

    public List<NodeData> search(final String text) {
        if (key.equals(text)) {
            return values;
        } else if (!childNodes.isEmpty()) {
            final TreeNode treeNode = childNodes.get(text.substring(0, key.length() + 1));
            if (treeNode != null) {
                return treeNode.search(text);
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
