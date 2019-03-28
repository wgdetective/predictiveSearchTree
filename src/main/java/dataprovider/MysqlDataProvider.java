package dataprovider;

import com.hematite.predictive.search.tree.NodeData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class MysqlDataProvider implements DataProvider {


    @Override
    public List<NodeData> getAllData() {
        return null;
    }
}