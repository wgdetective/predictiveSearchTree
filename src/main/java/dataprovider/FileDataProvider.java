package dataprovider;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataProvider implements DataProvider {

    private final File fileName;

    public FileDataProvider(final File fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Object> getAllData() {
        List<Object> dataList = new ArrayList<>();

        try {
            final FileInputStream fileInputStream = new FileInputStream(fileName);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null){
                dataList.add(strLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

}