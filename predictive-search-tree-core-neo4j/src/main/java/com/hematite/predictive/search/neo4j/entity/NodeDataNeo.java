package com.hematite.predictive.search.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@Data
@NodeEntity
public class NodeDataNeo<T> {

    @Id
    @GeneratedValue
    private Long id;
    private String key;
    private T data;
    private List<Integer> prefix;

    public NodeDataNeo(){}
}
