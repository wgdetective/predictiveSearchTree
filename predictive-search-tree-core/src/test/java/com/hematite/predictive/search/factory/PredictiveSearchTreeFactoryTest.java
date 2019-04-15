package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.mock.NodeDataMock;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PredictiveSearchTreeFactoryTest {

    private PredictiveSearchTreeFactory factory = new PredictiveSearchTreeFactory();

    @Test
    public void testCreation() {
        final List<String> valuesList = new ArrayList<>(Arrays.asList("ab","cd"));
        final List<NodeData> nodeData = NodeDataMock.createList(valuesList);

        final TreeNode node = factory.createTree(nodeData);

        assertNotNull(node);
        assertTrue(node.hasChild());
        for (final NodeData data : nodeData) {
            assertTrue(node.getValues().contains(data));
        }

        assertEquals(4 , node.getChildNodes().size());
        for (final Map.Entry<String, TreeNode> childNode : node.getChildNodes().entrySet()) {
            assertEquals(node, childNode.getValue().getParentNode());
            assertEquals(1, childNode.getValue().getValues().size());
        }
    }

    @Test
    public void testCreationWithSpace() {
        final List<String> valuesList = new ArrayList<>(Collections.singleton("a b"));
        final List<NodeData> nodeData = NodeDataMock.createList(valuesList);

        final TreeNode node = factory.createTree(nodeData);

        assertNotNull(node);
        assertTrue(node.hasChild());
        assertEquals(2, node.getChildNodes().size());

        final TreeNode childNodeWithSpace = node.getChildNodes().get("a");

        assertEquals(1, childNodeWithSpace.getChildNodes().size());
        assertTrue(childNodeWithSpace.getChildNodes().containsKey("a "));
    }

    @Test
    public void testCreationWithMultipleMatching() {
        final List<NodeData> nodeData = NodeDataMock.createList(Collections.singletonList("aabad"));

        final TreeNode node = factory.createTree(nodeData);
        assertNotNull(node);

        final TreeNode childNode = node.getChildNodes().get("a");

        assertEquals(3, childNode.getChildNodes().size());
        assertTrue(childNode.getChildNodes().containsKey("aa"));
        assertTrue(childNode.getChildNodes().containsKey("ab"));
        assertTrue(childNode.getChildNodes().containsKey("ad"));
    }
}
