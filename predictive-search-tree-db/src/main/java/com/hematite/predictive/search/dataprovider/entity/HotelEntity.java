package com.hematite.predictive.search.dataprovider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "hotels")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String ids;
    private String location;
    private String star;
    private String phoneOne;
    private String phoneTwo;
    private String site;
    private String email;
    private String latitude;
    private String longitude;
    private String name;
    private String address;
    private String language;

}
