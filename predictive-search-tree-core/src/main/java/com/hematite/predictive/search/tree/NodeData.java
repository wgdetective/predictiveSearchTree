package com.hematite.predictive.search.tree;

import lombok.Data;

/**
 * @author Wladimir Litvinov
 */
@Data
public class NodeData<T> {
    private final String key;
    private final T data;
}
