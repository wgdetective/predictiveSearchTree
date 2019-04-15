package com.hematite.predictive.search.neo4j;

import com.hematite.predictive.search.neo4j.entity.NodeDataNeo;
import com.hematite.predictive.search.neo4j.entity.TreeNodeNeo;
import com.hematite.predictive.search.neo4j.repository.HotelNeo4JRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PredictiveSearchTreeNeoFactory {

    private static final int MAX_LIST_ITEMS_COUNT = 10;

    private List<String> symbols = initSymbols();

    private TextSearchServiceNeo textSearchServiceNeo = new TextSearchServiceNeo();

    private final HotelNeo4JRepository hotelNeo4JRepository;

    private long nodeCount = 0;

    public void createTree(final List<NodeDataNeo> data) {
        final TreeNodeNeo rootNode = new TreeNodeNeo();
        rootNode.setKey("");
        rootNode.setValues(getSubList(data));
        nodeCount++;

        data.forEach(d -> d.setPrefix(textSearchServiceNeo.calculatePrefixFunction(d.getKey())));

        for (final String key : symbols) {
            final List<NodeDataNeo> filteredList = textSearchServiceNeo.search(data, key);
            if (!filteredList.isEmpty()) {
                final TreeNodeNeo childNode = new TreeNodeNeo();
                childNode.setKey(key);
                childNode.setValues(new ArrayList<>());
                nodeCount++;
                createChildNodes(filteredList, childNode);
            }
        }
        hotelNeo4JRepository.save(rootNode);
    }

    private void createChildNodes(final List<NodeDataNeo> words, final TreeNodeNeo parentNode) {
        parentNode.setValues(getSubList(words));
        final List<TreeNodeNeo> childNodes = new ArrayList<>();
        for (final NodeDataNeo nodeData : words) {
            int startIndex = 0;
            while ((startIndex = nodeData.getKey().indexOf(parentNode.getKey(), startIndex)) != -1) {
                final int endIndex = startIndex + parentNode.getKey().length() + 1;

                if (endIndex < nodeData.getKey().length() + 1) {
                    final String newKey = nodeData.getKey().substring(startIndex, endIndex);
                    final TreeNodeNeo childNode = new TreeNodeNeo();
                    childNode.setKey(newKey);
                    childNode.setValues(new ArrayList<>());
                    if (!childNodes.contains(childNode)) {
                        childNodes.add(childNode);
                        nodeCount++;
                    }
                }
                startIndex++;
            }
        }

        hotelNeo4JRepository.save(parentNode);

        for (final TreeNodeNeo childNode : childNodes) {
            final List<NodeDataNeo> filteredList = textSearchServiceNeo.search(words, childNode.getKey());
            createChildNodes(filteredList, childNode);
        }
    }

    private List<NodeDataNeo> getSubList(final List<NodeDataNeo> list) {
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
