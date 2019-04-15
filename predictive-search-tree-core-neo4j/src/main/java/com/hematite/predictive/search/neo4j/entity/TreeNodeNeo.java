package com.hematite.predictive.search.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@Data
@NodeEntity
public class TreeNodeNeo {

    @Id
    @GeneratedValue
    private Long id;
    private String key;
    @Relationship(type="has")
    private List<NodeDataNeo> values;

    public TreeNodeNeo(){ }
}
