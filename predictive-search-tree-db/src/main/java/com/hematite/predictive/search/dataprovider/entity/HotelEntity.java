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
    private String hotelId;
    private String langId;
    private String hotelName;

}
