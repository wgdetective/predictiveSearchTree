package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileDataProviderTest {

    @Test
    public void getAllData() {

        final FileDataProvider fileDataProvider = new FileDataProvider("./src/main/resources/fileExample.txt");
        List<NodeData<String>> dataList = fileDataProvider.getAllData();
        assertNotNull(dataList);
        assertEquals(506880, dataList.size());

    }
}