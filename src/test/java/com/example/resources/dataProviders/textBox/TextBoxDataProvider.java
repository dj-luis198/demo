package com.example.resources.dataProviders.textBox;

import org.testng.annotations.DataProvider;

import com.example.utils.CSVFileReader;

public class TextBoxDataProvider {
    @DataProvider(name = "ValidData",parallel = false)
    public String[][] validDataTest() {
        String path = "src/main/java/com/example/testData/textBox/TextBoxValidData.csv";
        String delimiter = ";";
        String[][] data = CSVFileReader.readCSV( path, delimiter);
        return data;
    }

    @DataProvider(name = "InvalidData",parallel = false)
    public String[][] InvalidDataTest() {
        String path = "src/main/java/com/example/testData/textBox/TextBoxInvalidEmail.csv";
        String delimiter = ";";
        String[][] data = CSVFileReader.readCSV( path, delimiter);
        return data;
    }
}
