package com.example.resources.dataProviders.bookStoreApplication;

import org.testng.annotations.DataProvider;

import com.example.utils.CSVFileReader;

public class BookStoreAppDataProvider {
    @DataProvider(name = "bookStoreAppE2EData", parallel = false)
    public String[][] bookStoreAppDataTest() {
        String path = "src/main/java/com/example/testData/bookStoreApplication/BookStoreAppE2EData.csv";
        String delimiter = ";";
        String[][] data = CSVFileReader.readCSV(path, delimiter);
        return data;
    }

    @DataProvider(name = "LoginValidData", parallel = false)
    public String[][] loginValidDataTest() {
        String path = "src/main/java/com/example/testData/bookStoreApplication/LoginValidData.csv";
        String delimiter = ";";
        String[][] data = CSVFileReader.readCSV(path, delimiter);
        return data;
    }

    @DataProvider(name = "LoginInvalidData", parallel = false)
    public String[][] loginInvalidDataTest() {
        String path = "src/main/java/com/example/testData/bookStoreApplication/LoginInvalidData.csv";
        String delimiter = ";";
        String[][] data = CSVFileReader.readCSV(path, delimiter);
        return data;
    }

}
