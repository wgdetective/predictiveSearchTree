package com.hematite.predictive.search.dataprovider;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tree_node")
public class NodeDataEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date date;
    private String description;

}
