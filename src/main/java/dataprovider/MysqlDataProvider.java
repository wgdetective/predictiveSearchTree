package dataprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class MysqlDataProvider implements DataProvider {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Connection connect() {
        try {
            final Properties properties = new Properties();
            final FileInputStream fileInputStream = new FileInputStream("db.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
            final String driver = properties.getProperty("jdbc.driver");
            if (driver != null) {
                Class.forName(driver) ;
            }
            final String url = properties.getProperty("jdbc.url");
            final String username = properties.getProperty("jdbc.username");
            final String password = properties.getProperty("jdbc.password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public void create(final String words) {
        connection = connect();

    }

    @Override
    public List<String> search(final String text) {
        connection = connect();
        return null;
    }

    @Override
    public void update(final String words) {
        connection = connect();
    }

    @Override
    public List<String> getAllData() {
        return null;
    }
}