package dataprovider;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MysqlDataProvider implements DataProvider {

    private final TreeNodeRepository treeNodeRepository;

    @Override
    public List<Object> getAllData() {
        return treeNodeRepository.findAll();
    }
}