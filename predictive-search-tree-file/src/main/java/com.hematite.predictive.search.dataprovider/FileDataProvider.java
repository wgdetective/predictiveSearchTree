package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileDataProvider implements DataProvider<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDataProvider.class);

    private final String fileName;

    public FileDataProvider(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<NodeData<String>> getAllData() {
        final List<NodeData<String>> dataList = new ArrayList<>();

        try {
            final List<String> lines = Files.readAllLines(new File(fileName).toPath());
            for (final String line : lines) {
                dataList.add(new NodeData<>(line, line));
            }
        } catch (final IOException e) {
            LOGGER.error("Error while reading from file", e);
        }
        return dataList;
    }

}