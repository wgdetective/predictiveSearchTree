package com.hematite.predictive.search.rest;

import com.hematite.predictive.search.dataprovider.converter.HotelConverter;
import com.hematite.predictive.search.dataprovider.entity.HotelEntity;
import com.hematite.predictive.search.dataprovider.repository.HotelRepository;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProcessTimeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTimeTest.class);

    private final PredictiveSearchTreeFactory predictiveSearchTreeFactory = new PredictiveSearchTreeFactory();

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testFindInTree() {
        final List<HotelEntity> hotelEntities = hotelRepository.findAll();
        final List<NodeData> nodeDataList = HotelConverter.convertToDto(hotelEntities);

        final TreeNode treeNode = predictiveSearchTreeFactory.createTree(nodeDataList);

        try {
            final List<String> lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("hotelsQuery.txt").toURI()));
            final Instant start = Instant.now();
            for (final String value : lines) {
                treeNode.search(value);
            }
            final Instant finish = Instant.now();
            final long timeElapsed = Duration.between(start, finish).toMillis();
            LOGGER.info("Time for processing queries with tree: {}", timeElapsed);
        } catch (final IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindInDatabase() {
        try {
            final List<String> lines =
                    Files.readAllLines(Paths.get(ClassLoader.getSystemResource("hotelsQuery.txt").toURI())).subList(0, 26000);
            final Instant start = Instant.now();
            for (final String value : lines) {
                hotelRepository.findTop10ByNameContains(value);
            }
            final Instant finish = Instant.now();
            final long timeElapsed = Duration.between(start, finish).toMillis();
            LOGGER.info("Time for processing queries with database: {}", timeElapsed);
        } catch (final IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
