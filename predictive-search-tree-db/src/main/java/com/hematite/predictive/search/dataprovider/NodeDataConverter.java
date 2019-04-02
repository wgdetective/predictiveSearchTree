package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
class NodeDataConverter {

    public List<NodeData<String>> convertToDto(final List<NodeDataEntity> nodeDataEntityList) {
        final List<NodeData<String>> dataList = new ArrayList<>();

        for (NodeDataEntity nodeDataEntity : nodeDataEntityList) {
            dataList.add(new NodeData<>(nodeDataEntity.getName(), nodeDataEntity.toString()));
        }
        return dataList;
    }

}
