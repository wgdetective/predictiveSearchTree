package com.hematite.predictive.search.mock;

import com.hematite.predictive.search.tree.NodeData;

import java.util.ArrayList;
import java.util.List;

public class NodeDataMock {

    public static List<NodeData> createList(final List<String> values) {
        final List<NodeData> data = new ArrayList<>();
        for (final String value : values) {
            data.add(new NodeData(value, value));
        }
        return data;
    }
}
