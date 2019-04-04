package com.hematite.predictive.search.dataprovider;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HotelUtils {
    public static void main(String[] args) {
        try {
            final List<String> filesWithDefaultSeparator = new ArrayList<>();
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.gta.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.hotelbeds.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.hotusa.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.metglobal.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.rooms.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.sunhotels.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.tourico.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.travco.2019-03-26T23_00_01.csv");

            final List<String> filesWithSpecialSeparator = new ArrayList<>();
            filesWithSpecialSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.gp.2019-03-26T23_00_01.csv");
            filesWithSpecialSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.w2m.2019-03-26T23_00_01.csv");

            int fileNumber = 1;

            for (final String file : filesWithDefaultSeparator) {
                processFile(file, false, String.valueOf(fileNumber));
                fileNumber++;
            }

            for (final String file : filesWithSpecialSeparator) {
                processFile(file, true, String.valueOf(fileNumber));
                fileNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(final String filePath,
                                    final boolean specialSeparator,
                                    final String newFileNumber) throws IOException {
        final File newFile = new File("./predictive-search-tree-db/src/main/resources/sql/hotels" + newFileNumber + ".sql");
        newFile.createNewFile();

        final FileWriter fileWriter = new FileWriter(newFile, false);
        fileWriter.write("INSERT INTO hotels(ids, star, name) values");

        final CSVReader reader = specialSeparator ?
                new CSVReader(new FileReader(filePath), ';') : new CSVReader(new FileReader(filePath));

        String[] values = reader.readNext();
        writeFile(fileWriter, values);
        while ((values = reader.readNext()) != null) {
            fileWriter.write(",");
            writeFile(fileWriter, values);
        }

        fileWriter.write(";");
        fileWriter.flush();
    }

    private static void writeFile(final FileWriter fileWriter,
                                  final String[] values) throws IOException {
        values[15] = values[15].replaceAll("\"", "");
        fileWriter.write("\n(\"" + values[0] + "\", \"" + values[4] + "\", \"" + values[15] + "\")");
    }
}
