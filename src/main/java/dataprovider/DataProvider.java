package dataprovider;

import java.util.List;

public interface DataProvider {

    void create(String words);

    List<String> search(String text);

    void update(String words);

    List<String> getAllData();

}