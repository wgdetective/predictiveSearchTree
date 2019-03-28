package dataprovider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileDataProvider implements DataProvider {

    private final File fileName;

    public FileDataProvider(final File fileName) {
        this.fileName = fileName;
    }

    @Override
    public void create(final String words) {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(words);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing bufferedWriter or fileWriter");
            }
        }

    }

    @Override
    public List<String> search(final String text) {
        return null;
    }

    @Override
    public void update(final String words) {

    }

    @Override
    public List<String> getAllData() {
        return null;
    }
}