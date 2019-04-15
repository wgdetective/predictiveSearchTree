package com.hematite.predictive.search.dataprovider;

import com.opencsv.CSVReader;

import java.io.*;

public class HotelUtils {

    public static void main(String[] args) {
        try {
            int rowsCount = 1;
            final File newFile = new File("./predictive-search-tree-db/src/main/resources/sql/hotels.sql");
            newFile.createNewFile();

            final FileWriter fileWriter = new FileWriter(newFile, false);
            fileWriter.write("INSERT INTO hotels(ids, star, name) values");

            final CSVReader reader = new CSVReader(new FileReader(new File(
                    "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.sunhotels.2019-03-26T23_00_01.csv")));

            String[] values = reader.readNext();
            writeFile(fileWriter, values);
            while ((values = reader.readNext()) != null && rowsCount < 200000) {
                fileWriter.write(",");
                writeFile(fileWriter, values);
                rowsCount++;
            }

            fileWriter.write(";");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(final FileWriter fileWriter,
                                  final String[] values)
            throws IOException {
        values[15] = values[15].replaceAll("\"", "");
        fileWriter.write("\n(\"" + values[0] + "\", \"" + values[4] + "\", \"" + values[15] + "\")");
    }
}
