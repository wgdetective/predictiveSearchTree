package dataprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class MysqlDataProvider implements DataProvider {

    private final TreeNodeRepository treeNodeRepository;

    @Override
    public List<Object> getAllData() {
        return treeNodeRepository.findAll();
    }
}