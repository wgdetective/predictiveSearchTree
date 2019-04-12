package com.hematite.predictive.search.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Wladimir Litvinov
 */


@Data
@NodeEntity
@AllArgsConstructor
public class NodeData<T> {

    @Id
    @GeneratedValue
    private Long id;
    private String key;
    private T data;

    public NodeData(){

    }

}
