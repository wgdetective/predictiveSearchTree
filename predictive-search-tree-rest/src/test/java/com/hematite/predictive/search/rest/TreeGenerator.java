package com.hematite.predictive.search.rest;

import com.hematite.predictive.search.dataprovider.converter.HotelConverter;
import com.hematite.predictive.search.dataprovider.entity.HotelEntity;
import com.hematite.predictive.search.dataprovider.repository.HotelRepository;
import com.hematite.predictive.search.factory.PredictiveSearchTreeFactory;
import com.hematite.predictive.search.tree.NodeData;
import com.hematite.predictive.search.tree.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class TreeGenerator {

    private final PredictiveSearchTreeFactory predictiveSearchTreeFactory = new PredictiveSearchTreeFactory();

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void testFindInTree() {

        final List<HotelEntity> hotelEntities = hotelRepository.findAll();
        final List<NodeData> nodeDataList = HotelConverter.convertToDto(hotelEntities);

        final TreeNode treeNode = predictiveSearchTreeFactory.createTree(nodeDataList);

        final Instant start = Instant.now();

        try {
            final List<String> lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("requests.txt").toURI()));
            for (String value : lines) {
                treeNode.search(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        final Instant finish = Instant.now();
        final long timeElapsed = Duration.between(start, finish).toMillis();

    }

    @Test
    public void testFindInDatabase() {

        final Instant start = Instant.now();

        try {
            final List<String> lines = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("requests.txt").toURI()));
            for (String value : lines) {
                value = "^" + value;
                hotelRepository.findByNameRegex(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        final Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

    }

}
