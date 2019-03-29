package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlDataProvider implements DataProvider {

    @Autowired
    private TreeNodeRepository treeNodeRepository;

    @Override
    public List<NodeData> getAllData() {
        return treeNodeRepository.findAll();
    }
}
