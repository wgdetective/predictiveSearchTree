package com.hematite.predictive.search.dataprovider.repository;

import com.hematite.predictive.search.dataprovider.entity.HotelEntity;
import com.hematite.predictive.search.tree.NodeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    List<HotelEntity> findByName(final String name);

}
