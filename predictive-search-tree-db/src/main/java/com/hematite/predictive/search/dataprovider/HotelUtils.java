package com.hematite.predictive.search.dataprovider;

import com.opencsv.CSVReader;

import java.io.*;

public class HotelUtils {
    public static void main(String[] args) {


        //refactor file

        /*BufferedReader br = null;

        try {

            String fileName = "./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.tourico.2019-03-26T23_00_01.csv";
            String line = "";

            br = new BufferedReader(new FileReader(fileName));

            final File newFile = new File("./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.tourico.2019-03-26T23_00_01New.csv");
            newFile.createNewFile();
            FileWriter fileWriter = new FileWriter(newFile, true);

            while ((line = br.readLine()) != null) {
                line = readFullLine(br, line);
                fileWriter.write(line + "\n");
                fileWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

        //write file to sql

        BufferedReader br = null;

        try {
            //final File newFile = new File("./predictive-search-tree-db/src/main/resources/sql/hotels.gta.2019-03-26T23_00_01.sql");
            final File newFile = new File("./predictive-search-tree-db/src/main/resources/sql/hotels.sql");
            newFile.createNewFile();

            FileWriter fileWriter = new FileWriter(newFile, false);
            fileWriter.write("CREATE PROCEDURE writeData()\n" +
                    "BEGIN\n" +
                    "INSERT INTO hotels(ids, location, star, phoneOne, phoneTwo, site, email, latitude, longitude, name, address, language) values\n");
            fileWriter.write("\n");

            //String[] fileNames = new String[1];
            //fileNames[0] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.gp.2019-03-26T23_00_01.csv";
            //fileNames[1] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.gta.2019-03-26T23_00_01.csv";
            //fileNames[2] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.hotelbeds.2019-03-26T23_00_01.csv";
            //fileNames[3] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.hotusa.2019-03-26T23_00_01.csv";
            //fileNames[4] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.metglobal.2019-03-26T23_00_01.csv";
            //[5] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.rooms.2019-03-26T23_00_01.csv";
            //fileNames[6] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.sunhotels.2019-03-26T23_00_01.csv";
            //fileNames[7] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.tourico.2019-03-26T23_00_01.csv";
            //fileNames[8] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.travco.2019-03-26T23_00_01.csv";
            //fileNames[0] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.w2m.2019-03-26T23_00_01.csv";


            String csvFile = "./predictive-search-tree-db/src/main/resources/csvFilesNew/hotels.gta.2019-03-26T23_00_01.csv";

            /*for (String fileName : fileNames) {*/
            //String line = "";

            /*br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",", -1);

                System.out.println(values[0]);

                fileWriter.write("(\"" + values[0] + "\", \"" + values[1] + "\", \"" + values[4] + "\", \"" + values[6] + "\", \"" + values[7] +
                        "\", \"" + values[8] + "\", \"" + values[11] + "\", \"" + values[12] + "\", \"" + values[13] + "\", \"" + values[15] + "\", \"" +
                        values[16] + "\", \"" + values[17] + "\"),\n");

                fileWriter.write("(" + values[0] + ", " + values[1] + ", " + values[4] + ", " + values[6] + ", " + values[7] +
                        ", " + values[8] + ", " + values[11] + ", " + values[12] + ", " + values[13] + ", " + values[15] + ", " +
                        values[16] + ", " + values[17] + "),\n");
            }*/







            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(csvFile));
                String[] values;
                while ((values = reader.readNext()) != null) {
                    values[16].replace("\'", "\\\'");
                    values[16].replace("\"", "\\\"");
                    fileWriter.write("(\"" + values[0] + "\", \"" + values[1] + "\", \"" + values[4] + "\", \"" + values[6] + "\", \"" + values[7] +
                            "\", \"" + values[8] + "\", \"" + values[11] + "\", \"" + values[12] + "\", \"" + values[13] + "\", \"" + values[15] + "\", \"" +
                            values[16] + "\", \"" + values[17] + "\"),\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileWriter.write(";\nEND");
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //String[] newFileNames = new String[1];
        //newFileNames[0] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.hotelbeds.2019-03-26T23_00_01.csv";
        //newFileNames[0] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.hotusa.2019-03-26T23_00_01.csv";
        //newFileNames[2] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.metglobal.2019-03-26T23_00_01.csv";
        //newFileNames[3] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.rooms.2019-03-26T23_00_01.csv";
        //newFileNames[4] = "./predictive-search-tree-db/src/main/resources/csvFiles/hotels.sunhotels.2019-03-26T23_00_01.csv";

        //String fileName = "./predictive-search-tree-db/src/main/resources/csvFiles/example.csv";


        //getNextLine(br, fileWriter);
        //fileWriter.flush();


    }

    /*public static void refactorFile(){

    }
*/



    public static String readFullLine(final BufferedReader br, final String line) throws IOException {
        String tmpLine = line;
        if (!tmpLine.endsWith("\"")) {
            final String str = br.readLine();
            if (tmpLine.length() > 1) {
                tmpLine = line.substring(0, line.length() - 1) + readFullLine(br, str);
            } else {
                tmpLine = readFullLine(br, str);
            }

        }
        return tmpLine;
    }}
