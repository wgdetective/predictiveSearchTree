package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;

import java.util.List;

public interface DataProvider<T> {

    List<NodeData<T>> getAllData();

}