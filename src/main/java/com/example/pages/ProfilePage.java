package com.example.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @FindBy (xpath = "//span[contains(@id,'see-book-')]")
    private WebElement gridElements;

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

    public LoginPage clickLogOutButton() {
        click(logOutButton);
        return new LoginPage(driver);
    }

    public void clickDeleteAllBooksButton() {
        click(deleteAllBooksButton);
        clickCloseSmallModalButton();
        confirmAlert();
    }

    public void clickCloseSmallModalButton() {
        click(closeSmallModalButton);
    }

    public boolean verifySelectedBooks(String books) {
        List<String> booksList = new ArrayList<String>(Arrays.asList(books.split(",,")));
        for (String book : booksList) {
            String locator = "see-book-" + book;
            if (!serchLocatorId(locator)) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyDeleteAllBooks(String books) {
        long startTime = System.currentTimeMillis();
        List<String> booksList = new ArrayList<>(Arrays.asList(books.split(",,")));
        int count = 0;
        for (String book : booksList) {
            String locator = "see-book-" + book;
            if (!serchNotVisivilityLocatorId(locator)) {
                count++;
            }
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("El método tardó " + duration + " milisegundos en ejecutarse.");
        return count == booksList.size();
    }
}
