/*
package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SqlDataProviderTest {

    @InjectMocks
    private SqlDataProvider sqlDataProvider;

    @Mock
    private TreeNodeRepository treeNodeRepository;
    @Spy
    private NodeDataConverter nodeDataConverter;

    @Test
    public void getAllData() {
        final List<NodeDataEntity> nodeDataEntities = new ArrayList<>();
        final NodeDataEntity nodeDataEntity = new NodeDataEntity();
        nodeDataEntity.setId(1L);
        nodeDataEntity.setName("name");
        nodeDataEntity.setDate(new Date(2018,02,02));
        nodeDataEntity.setDescription("description");
        nodeDataEntities.add(nodeDataEntity);
        doReturn(nodeDataEntities).when(treeNodeRepository).findAll();


        final List<NodeData<String>> dataList = sqlDataProvider.getAllData();
        assertNotNull(dataList);
    }
}*/
