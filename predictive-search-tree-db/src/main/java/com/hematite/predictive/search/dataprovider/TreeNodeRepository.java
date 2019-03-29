package com.hematite.predictive.search.dataprovider;

import com.hematite.predictive.search.tree.NodeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeNodeRepository extends JpaRepository<NodeData, Long> {

}
