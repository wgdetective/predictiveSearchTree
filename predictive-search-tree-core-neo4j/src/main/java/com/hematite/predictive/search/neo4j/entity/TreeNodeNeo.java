package com.hematite.predictive.search.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
//@ToString(exclude = {"childNodes"})
@NodeEntity
public class TreeNodeNeo {

    @Id
    @GeneratedValue
    private Long id;
    private String key;
    @Relationship(type="has")
    private List<NodeData> values;
    @Relationship(type="owns")
    private Map<String, TreeNodeNeo> childNodes;
    //@Relationship(type="of")
    //private final TreeNodeNeo parentNode;

    /*public TreeNodeNeo(final String key, final List<String> values) {
        this(key, values, null);
    }*/

    /*public TreeNodeNeo(final String key, final List<String> values, final TreeNodeNeo parentNode) {
        this(1L, key, values, new HashMap<>(), parentNode);
    }*/

    public TreeNodeNeo(){

    }

}
