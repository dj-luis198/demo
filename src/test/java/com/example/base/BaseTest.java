package com.example.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.example.utils.WebDriverFactory;

public class BaseTest {
    private static Properties propA = new Properties();

    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    @Parameters(value = { "browser" })
    public void setUp(@Optional("chrome") String browser) {
        // Carga de propiedades
        propA = initProperties("aplication");
        // Configuración del WebDriver
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(propA.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        // Cierre del WebDriver
        driver.quit();
    }

    protected static Properties initProperties(String name) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(
                "src/main/java/com/example/properties/" + name + ".properties")) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String takesScreenshot(String testName, WebDriver driver) throws IOException {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "/ExtentReports/Screenshots/" + testName + ".png");
		FileUtils.copyFile(sourceFile, destFile);
		return "Screenshots/" + destFile.getName();
	}
}
