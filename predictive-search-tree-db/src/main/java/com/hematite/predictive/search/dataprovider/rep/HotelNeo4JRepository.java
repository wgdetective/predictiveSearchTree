package com.hematite.predictive.search.dataprovider.rep;

import com.hematite.predictive.search.dataprovider.ent.HotelNeo4J;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelNeo4JRepository extends Neo4jRepository<HotelNeo4J, Long> {

    //List<HotelEntity> findTop10ByNameContains(final String name);

}
