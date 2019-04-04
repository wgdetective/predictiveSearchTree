package com.hematite.predictive.search.dataprovider;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HotelUtils {
    public static void main(String[] args) {
        try {
            final File newFile = new File("./predictive-search-tree-db/src/main/resources/sql/hotels.sql");
            newFile.createNewFile();

            final FileWriter fileWriter = new FileWriter(newFile, false);
            fileWriter.write("CREATE PROCEDURE writeData()\n" +
                    "BEGIN\n" +
                    "INSERT INTO hotels(ids, star, name) values");

            final List<String> filesWithDefaultSeparator = new ArrayList<>();
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.gta.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.hotelbeds.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.hotusa.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.metglobal.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.rooms.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.sunhotels.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.tourico.2019-03-26T23_00_01.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.tourico.2019-03-26T23_00_01New.csv");
            filesWithDefaultSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.travco.2019-03-26T23_00_01.csv");

            final List<String> filesWithSpecialSeparator = new ArrayList<>();
            filesWithSpecialSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.gp.2019-03-26T23_00_01.csv");
            filesWithSpecialSeparator.add("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.w2m.2019-03-26T23_00_01.csv");

            for (final String file : filesWithDefaultSeparator) {
                processFile(file, fileWriter, false);
                fileWriter.write(",");
            }

            processFile(filesWithSpecialSeparator.get(0), fileWriter, true);
            fileWriter.write(",");

            processFile(filesWithSpecialSeparator.get(1), fileWriter, true);
            fileWriter.write(";\nEND");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(final String filePath,
                                    final FileWriter fileWriter,
                                    final boolean specialSeparator) throws IOException {
        final CSVReader reader = specialSeparator ?
                new CSVReader(new FileReader(filePath), ';') : new CSVReader(new FileReader(filePath));
        String[] values = reader.readNext();
        writeFile(fileWriter, values);
        while ((values = reader.readNext()) != null) {
            fileWriter.write(",");
            writeFile(fileWriter, values);
        }
    }

    private static void writeFile(final FileWriter fileWriter,
                                  final String[] values) throws IOException {
        fileWriter.write("\n(\"" + values[0] + "\", \"" + values[4] + "\", \"" + values[15] + "\")");
    }
}
