package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.Actions;

public class ProfilePage extends Actions {

    @FindBy(css = "#books-wrapper>div>#submit")
    private WebElement logOutButton;

    @FindBy(id = "userName-value")
    private WebElement userNameLabel;

    @FindBy(id = "gotoStore")
    private WebElement goToStoreButton;

    @FindBy(xpath = "//div[@class='text-right button di']//button[@id='submit']")
    private WebElement deleteAllBooksButton;

    @FindBy(id = "closeSmallModal-ok")
    private WebElement closeSmallModalButton;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public BookStorePage clickGoToStoreButton() {
        click(goToStoreButton);
        return new BookStorePage(driver);
    }

    public String getUserName() {
        return getText(userNameLabel);
    }

    public void clickLogOutButton() {
        click(logOutButton);
    }

    public void clickDeleteAllBooksButton() {
        click(deleteAllBooksButton);
        clickCloseSmallModalButton();
        confirmAlert();
    }

    public void clickCloseSmallModalButton() {
        click(closeSmallModalButton);
    }
}
