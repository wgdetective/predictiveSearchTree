package com.hematite.predictive.search.rest;

import com.hematite.predictive.search.tree.NodeData;
import org.apache.commons.text.RandomStringGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGenerator {

    public static void generateQueries(final List<NodeData> data) {
        final List<String> queries = generateData(data);

        try {
            final File file = new File(ClassLoader.getSystemResource("hotelsQuery.txt").getPath());
            final FileWriter fileWriter = new FileWriter(file, false);

            for (final String value : queries) {
                fileWriter.write(value + "\n");
            }

            fileWriter.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> generateData(final List<NodeData> data) {
        final List<String> result = new ArrayList<>();

        for (int i = 0; i < data.size(); i += 10) {
            result.add(data.get(i).getData().toString());
        }

        final char[][] range = {{'a', 'z'},
                                {' ',' '}};

        final RandomStringGenerator stringGenerator = new RandomStringGenerator.Builder()
                .withinRange(range).build();

        for (int i = 0; i < data.size(); i += 4) {
            final String currData = data.get(i).getData().toString();
            final int maxSize = currData.length();
            final int startIndex = maxSize != 0 ?
                    ThreadLocalRandom.current().nextInt(0, maxSize) : maxSize;
            final int endIndex = startIndex != maxSize ?
                    ThreadLocalRandom.current().nextInt(startIndex, maxSize) : maxSize;
            final String resultStr = currData.substring(startIndex, endIndex);

            result.add(resultStr.isEmpty() ? stringGenerator.generate(1, 10).trim() : resultStr.trim());
        }

        return result;
    }
}
