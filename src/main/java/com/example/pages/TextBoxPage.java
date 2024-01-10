package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.Actions;

public class TextBoxPage extends Actions {

    @FindBy(id = "userName")
    private WebElement usernameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "name")
    private WebElement nameText;

    @FindBy(id = "email")
    private WebElement emailText;

    @FindBy(css = "#output>div>#currentAddress")
    private WebElement currentAddressText;

    @FindBy(css = "#output>div>#permanentAddress")
    private WebElement permanentAddressText;

    public TextBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterCurrentAddress(String currentAddress) {
        type(currentAddressInput, currentAddress);
    }

    public void enterPermanentAddress(String permanentAddress) {
        type(permanentAddressInput, permanentAddress);
    }

    public void clickSubmitButton() {
        click(submitButton);
    }

    public String getTextUserName() {
        return getText(nameText);
    }

    public String getTextEmail() {
        return getText(emailText);
    }

    public String getTextCurrentAddress() {
        return getText(currentAddressText);
    }

    public String getTextPermanentAddress() {
        return getText(permanentAddressText);
    }

    public Boolean verifyEmailMessage(String email) {
        if (getAttributeValidationMessage(emailInput).equals(
                "Incluye un signo \"@\" en la dirección de correo electrónico. La dirección \""+email+"\" no incluye el signo \"@\".")
                || getAttributeValidationMessage(emailInput).equals("Ingrese una dirección de correo electrónico.")) {
            return true;
        }
        return false;
    }

    public Boolean verifyBorderColorEmailInput() {
        if (getCssValueBorder(emailInput).equals("0.666667px solid rgb(255, 0, 0)")
                || getCssValueBorder(emailInput).equals("1px solid rgb(255, 0, 0)")) {
            return true;
        } else {
            return false;
        }
    }
}
