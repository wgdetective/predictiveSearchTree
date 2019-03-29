package com.hematite.predictive.search.factory;

import com.hematite.predictive.search.mock.NodeDataMock;
import com.hematite.predictive.search.tree.NodeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextSearchServiceTest {

    private static final String TEST_VALUE = "abcabcd";
    private static final String WRONG_VALUE = "wrong";
    private static final String SEARCH_VALUE = "ca";

    private TextSearchService textSearchService = new TextSearchService();

    @Test
    public void prefixFunctionCalculationTest() {
        final List<Integer> expectedResult = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 2, 3, 0));

        final List<Integer> result = textSearchService.calculatePrefixFunction(TEST_VALUE);

        assertEquals(expectedResult, result);
    }

    @Test
    public void searchTest() {
        final List<NodeData> nodeData = NodeDataMock.createList(Arrays.asList(TEST_VALUE, WRONG_VALUE));
        final NodeData expectedNode = new NodeData(TEST_VALUE, TEST_VALUE);

        final List<NodeData> resultData = textSearchService.search(nodeData, SEARCH_VALUE);

        assertEquals(1, resultData.size());
        assertTrue(resultData.contains(expectedNode));
    }

    @Test
    public void testSearchEmptyResult() {
        final List<NodeData> nodeData = NodeDataMock.createList(Arrays.asList(WRONG_VALUE, WRONG_VALUE));

        final List<NodeData> resultData = textSearchService.search(nodeData, SEARCH_VALUE);

        assertTrue(resultData.isEmpty());
    }
}
