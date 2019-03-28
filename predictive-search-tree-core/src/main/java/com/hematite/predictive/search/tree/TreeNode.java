package com.hematite.predictive.search.tree;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TreeNode {
    private final String key;
    private final List<String> values;
    private final Map<String, TreeNode> childNodes;
    private final TreeNode parentNode;


    public TreeNode(final String key,
                    final List<String> values,
                    final Map<String, TreeNode> childNodes,
                    final TreeNode parentNode) {
        this.key = key;
        this.values = values;
        this.childNodes = childNodes;
        this.parentNode = parentNode;
    }

    public void addChildNode(final String key, final TreeNode node) {
        childNodes.put(key, node);
    }
}
