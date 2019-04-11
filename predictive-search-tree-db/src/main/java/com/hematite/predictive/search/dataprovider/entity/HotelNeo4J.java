package com.hematite.predictive.search.dataprovider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
@NodeEntity
@NoArgsConstructor
public class HotelNeo4J {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String hotelId;
    private String star;
    private String name;

}
