package dataprovider;

import com.hematite.predictive.search.tree.NodeData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileDataProvider implements DataProvider<String> {

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
            //TODO logger
            e.printStackTrace();
        }
        return dataList;
    }

}