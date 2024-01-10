package com.example.resources.dataProviders.bookStoreApplication;

import org.testng.annotations.DataProvider;

import com.example.utils.CSVFileReader;

public class LoginDataProvider {
    @DataProvider(name = "loginValidData",parallel = false)
    public String[][] loginValidDataTest() {
        String path = "src/main/java/com/example/testData/bookStoreApplication/BookStoreAppE2EData.csv";
        String delimiter = ";";
        String[][] data = CSVFileReader.readCSV( path, delimiter);
        return data;
    }
    
}
