package com.hematite.predictive.search.rest;

import com.hematite.predictive.search.neo4j.PredictiveSearchTreeNeoFactory;
import com.hematite.predictive.search.neo4j.entity.NodeDataNeo;
import com.hematite.predictive.search.neo4j.repository.HotelNeo4JRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataNeo4jTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Neo4JProcessTimeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Neo4JProcessTimeTest.class);

    @Autowired
    private HotelNeo4JRepository hotelNeo4JRepository;

    @Test
    public void testFindInNeo4J() throws URISyntaxException, IOException {
        final PredictiveSearchTreeNeoFactory predictiveSearchTreeNeoFactory = new PredictiveSearchTreeNeoFactory(hotelNeo4JRepository);
        final List<String> nodes = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("hotels.txt").toURI()));

        final List<NodeDataNeo> nodeDataNeoList = new ArrayList<>();
        for (final String nodeData : nodes) {
            final NodeDataNeo nodeDataNeo = new NodeDataNeo();
            nodeDataNeo.setKey(nodeData.toLowerCase());
            nodeDataNeo.setData(nodeData);
            nodeDataNeoList.add(nodeDataNeo);
        }

        final Instant startTree = Instant.now();
        predictiveSearchTreeNeoFactory.createTree(nodeDataNeoList);
        final Instant finishTree = Instant.now();
        final long timeElapsedTree = Duration.between(startTree, finishTree).toMillis();
        LOGGER.info("Time for create tree with Neo4J: {}", timeElapsedTree);

        final List<String> lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("hotelsQuery.txt").toURI()));
        final Instant start = Instant.now();
        for (final String value : lines) {
            hotelNeo4JRepository.findByKey(value);
        }
        final Instant finish = Instant.now();
        final long timeElapsed = Duration.between(start, finish).toMillis();
        LOGGER.info("Time for processing queries with tree: {}", timeElapsed);
    }
}
