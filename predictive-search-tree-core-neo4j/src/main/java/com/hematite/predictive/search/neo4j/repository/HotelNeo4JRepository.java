package com.hematite.predictive.search.neo4j.repository;

import com.hematite.predictive.search.neo4j.entity.TreeNodeNeo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelNeo4JRepository extends Neo4jRepository<TreeNodeNeo, Long> {

    //List<HotelEntity> findTop10ByNameContains(final String name);

}
