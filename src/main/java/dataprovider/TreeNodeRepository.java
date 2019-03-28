package dataprovider;

import com.hematite.predictive.search.tree.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeNodeRepository extends JpaRepository<Object, Long> {

}