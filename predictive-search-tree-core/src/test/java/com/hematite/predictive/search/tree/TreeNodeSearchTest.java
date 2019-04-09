package com.hematite.predictive.search.tree;

import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Wladimir Litvinov
 */
public class TreeNodeSearchTest {
    private TreeNode root;

    @Before
    public void init() throws URISyntaxException, IOException {
        final ArrayList<NodeData> data = new ArrayList<>();
        final List<String> lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("cities.txt").toURI()));
        lines.forEach(l -> data.add(new NodeData(l.toLowerCase(), l)));
        root = new PredictiveSearchTreeFactory().createTree(data);
    }

    @Test
    public void searchTest() {
        final List<SearchResult> search = root.search("Mu");
        assertEquals(search.size(), 3);
    }

    @Test
    public void searchTestWithEmptyResult() {
        final List<SearchResult> search = root.search("wrong_string");
        assertEquals(search.size(), 0);
    }
}
