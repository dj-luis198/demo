package com.example.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.utils.Actions;

public class BookStorePage extends Actions {

    @FindBy(css = ".text-right>#addNewRecordButton")
    private WebElement addToCollectionButton;

    @FindBy(css = ".text-left>#addNewRecordButton")
    private WebElement backToBookStoreButton;

    @FindBy(xpath = "//span[normalize-space()='Profile']")
    private WebElement profileOption;

    public BookStorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectBooks(String books) {
        List<String> booksList = new ArrayList<String>(Arrays.asList(books.split(",,")));
        for (String book : booksList) {
            clickLinkText(book);
            this.clickAddToYouCollection();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            confirmAlert();
            this.clickBackToBookStore();
        }
    }

    public void clickAddToYouCollection() {
        click(addToCollectionButton);
    }

    public void clickBackToBookStore() {
        click(backToBookStoreButton);
    }

    public ProfilePage clickProfileOption() {
        click(profileOption);
        return new ProfilePage(driver);
    }
}
