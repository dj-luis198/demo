package com.example.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class WebDriverFactory {

    public static WebDriver getDriver(String browser) {
        File file = new File("files/downloadFiles");
        WebDriver driver;
        
        switch (browser.toLowerCase()) {
            case "chrome":
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> pref = new HashMap<String, Object>();
				pref.put("download.prompt_for_download", false);
				pref.put("download.default_directory", file.getAbsolutePath());
				chromeOptions.addArguments("--remote-allow-origins=*");
				//chromeOptions.addArguments("--headless=new");
				chromeOptions.addArguments("--window-size=1920,1080");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--no-sandbox");
				//chromeOptions.addArguments("--start-fullscreen");
				chromeOptions.addArguments("--no-proxy-server");
				chromeOptions.addArguments("--ignore-certificate-errors");
				chromeOptions.setExperimentalOption("prefs", pref);
				chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                // Agrega aquí las opciones adicionales que desees
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("pdfjs.disabled", true);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg");
				profile.setPreference("browser.download.dir", file.getAbsolutePath());
				profile.setPreference("browser.download.folderList", 2);
				firefoxOptions.addArguments("--headless");
				firefoxOptions.setProfile(profile);
                // Agrega aquí las opciones adicionales que desees
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                
                EdgeOptions edgeOptions = new EdgeOptions();
                Map<String, Object> prefEdge = new HashMap<String, Object>();
				prefEdge.put("download.prompt_for_download", false);
				prefEdge.put("download.default_directory", file.getAbsolutePath());
				edgeOptions.addArguments("--remote-allow-origins=*");
				edgeOptions.addArguments("--headless=new");
				edgeOptions.addArguments("--window-size=1920,1080");
				edgeOptions.addArguments("--disable-extensions");
				edgeOptions.addArguments("--disable-dev-shm-usage");
				edgeOptions.addArguments("--disable-gpu");
				edgeOptions.addArguments("--no-sandbox");
				edgeOptions.addArguments("--start-fullscreen");
				edgeOptions.setExperimentalOption("prefs", prefEdge);
				edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                // Agrega aquí las opciones adicionales que desees
                driver = new EdgeDriver(edgeOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Navegador no válido: " + browser);
        }
        
        return driver;
    }
    
}
