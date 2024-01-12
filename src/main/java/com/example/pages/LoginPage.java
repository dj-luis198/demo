package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.Actions;

public class LoginPage extends Actions {

    @FindBy(id = "userName")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(className = "main-header")
    private WebElement mainHeader;

    @FindBy(id = "name")
    private WebElement MsgErrorLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public ProfilePage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new ProfilePage(driver);
    }

    public boolean getTextMainHeader() {
        String text = getText(mainHeader);
        if (text.equals("Login")) {
            return true;
        } else {
            return false;
        }
    }

    public String getMsgErrorLogin() {
        return getText(MsgErrorLogin);
    }
}
