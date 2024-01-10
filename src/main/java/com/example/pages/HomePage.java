package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.Actions;

public class HomePage extends Actions {

    @FindBy(xpath = "//div[@class='card-body']/h5[text()='Elements']")
    private WebElement cardElements;

    @FindBy(xpath = "//span[normalize-space()='Text Box']")
    private WebElement textBoxOption;

    @FindBy(xpath = "//h5[normalize-space()='Book Store Application']")
    private WebElement cardBookStoreApplication;

    @FindBy(xpath = "//span[normalize-space()='Login']")
    private WebElement loginOption;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // elements

    public void clickCardElements() {
        click(cardElements);
    }

    public void clickTextBoxOption() {
        click(textBoxOption);
    }

    public TextBoxPage goToTextBoxPage() {
        clickCardElements();
        clickTextBoxOption();
        return new TextBoxPage(driver);
    }

    // login

    public void clickCardBookStoreApplication() {
        click(cardBookStoreApplication);
    }

    public void clickLoginOption() {
        click(loginOption);
    }

    public LoginPage goToLoginPage() {
        clickCardBookStoreApplication();
        clickLoginOption();
        return new LoginPage(driver);
    }
}
