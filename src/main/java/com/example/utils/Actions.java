package com.example.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected WebElement waitToBeClickableLinkText(String linkText) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void confirmAlert() {
       try {
        wait.until(driver -> {
            try {
                driver.switchTo().alert();
                return true; // Se encontró la alerta
            } catch (NoAlertPresentException e) {
                return false; // No se encontró la alerta
            }
        });
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().defaultContent(); // Devuelve el foco al contenido principal
    } catch (TimeoutException e) {
        throw new Error("No se encontró la alerta");
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
                throw new Error("No se pudo hacer clic en el elemento: " + element + " : " + ex.getMessage());
            }
        }
    }

    protected void clickJS(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", waitToBeClickable(element));
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
