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

    protected void confirmAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()); // Esperar a que aparezca una alerta
            } catch (TimeoutException e) {
            throw new Error("No se encontró el alerta \n"+ e.getStackTrace());
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent(); // Devuelve el foco al contenido principal
        } catch (Exception e) {
            throw new Error("No se pudo aceptar el alerta \n"+ e.getStackTrace());
        }
    }

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
        jse.executeScript("arguments[0].click();", element);
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element).sendKeys(text);
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
}
