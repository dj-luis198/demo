package com.example.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Actions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /*------------------------------ Waits------------------------------- */

    protected void waitTime(int time) {
        try {
            WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(time));
            ewait.until(ExpectedConditions.titleIs("titulo fake"));
        } catch (Exception e) {
        }
    }

    protected boolean waitTVisivilityElementBoolean(String Locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean waitTNotVisivilityElementBoolean(String Locator) {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.id(Locator))));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    protected WebElement waitToBeClickableLinkText(String linkText) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(linkText)));
        } catch (TimeoutException e) {
            throw new Error("El elemento no se volvió clickable dentro del tiempo de espera.\n" + e.getStackTrace());
        }
    }

    protected WebElement waitForVisibility(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new Error("El elemento no se volvió visible dentro del tiempo de espera.\n" + e.getStackTrace());
        }
    }

    protected WebElement waitToBeClickable(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new Error("El elemento no se volvió clickable dentro del tiempo de espera.\n" + e.getStackTrace());
        }
    }

    /*------------------------------ Alerts ------------------------------ */
    protected void confirmAlert() {
        Alert alert;
        try {
            WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(3));
            ewait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e) {
            throw new Error("ocurrio un problema con el alerta \n" + e.getStackTrace());
        }
    }

    /*------------------------------ Clicks------------------------------- */
    public void clickLinkText(String linkText) {
        WebElement element = waitToBeClickableLinkText(linkText);
        click(element);
    }

    protected void click(WebElement element) {
        try {
            waitToBeClickable(element).click();
        } catch (Exception e) {
            try {
                clickJS(element);
            } catch (Exception ex) {
                throw new Error("No se pudo hacer clic en el elemento: " + element + " : \n" + ex.getStackTrace());
            }
        }
    }

    protected void clickJS(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", waitToBeClickable(element));
    }

    /*------------------------------ Type ------------------------------ */
    protected void type(WebElement element, String text) {
        waitForVisibility(element).sendKeys(text);
    }

    /*------------------------------ Gets------------------------------- */

    protected String getTitle() {
        return driver.getTitle();
    }
    
    protected String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    protected String getAttributeValidationMessage(WebElement element) {
        return waitForVisibility(element).getAttribute("validationMessage").toString();
    }

    protected String getCssValueBorder(WebElement element) {
        return waitForVisibility(element).getCssValue("border");
    }

    /*------------------------------ Serchs------------------------------- */
    protected boolean serchLocatorId(String locator) {
        return waitTVisivilityElementBoolean(locator);
    }

    protected boolean serchNotVisivilityLocatorId(String locator) {
        return waitTNotVisivilityElementBoolean(locator);
    }
}
