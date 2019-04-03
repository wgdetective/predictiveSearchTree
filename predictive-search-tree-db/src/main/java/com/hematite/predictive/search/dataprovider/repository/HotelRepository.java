package com.hematite.predictive.search.dataprovider.repository;

import com.hematite.predictive.search.dataprovider.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

}
