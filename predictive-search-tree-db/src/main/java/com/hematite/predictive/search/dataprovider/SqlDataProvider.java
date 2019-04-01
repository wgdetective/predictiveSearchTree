package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SqlDataProvider implements DataProvider {

    private final TreeNodeRepository treeNodeRepository;

    private NodeDataConverter nodeDataConverter;

    @Override
    public List<NodeData<String>> getAllData() {
        return nodeDataConverter.convertToDto(treeNodeRepository.findAll());
    }
}
