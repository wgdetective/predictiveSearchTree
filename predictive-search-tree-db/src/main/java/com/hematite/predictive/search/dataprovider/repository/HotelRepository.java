package com.hematite.predictive.search.dataprovider.repository;

import com.hematite.predictive.search.dataprovider.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    @Query(value = "select * from hotels where name LIKE %:value% limit 10", nativeQuery = true)
    List<HotelEntity> findByNameValue(String value);

}
