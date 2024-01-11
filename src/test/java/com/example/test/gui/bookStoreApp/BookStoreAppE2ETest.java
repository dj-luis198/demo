package com.example.test.gui.bookStoreApp;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.BookStorePage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.ProfilePage;
import com.example.resources.dataProviders.bookStoreApplication.LoginDataProvider;

public class BookStoreAppE2ETest extends BaseTest {
    private LoginPage loginPage;
    private String username;
    private String password;
    private String books;

    @BeforeMethod
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.goToLoginPage();
    }

    @Factory(dataProvider = "loginValidData", dataProviderClass = LoginDataProvider.class)
    public BookStoreAppE2ETest(String username, String password, String books) {
        this.username = username;
        this.password = password;
        this.books = books;
    }

    @Test(timeOut = 40000)
    public void bookStoreE2E() {
        ProfilePage profilePage = loginPage.login(username, password);
        Assert.assertEquals(profilePage.getUserName(), username);
        BookStorePage bookStorePage = profilePage.clickGoToStoreButton();
        bookStorePage.selectBooks(books);
        profilePage = bookStorePage.clickProfileOption();
        Assert.assertTrue(profilePage.verifySelectedBooks(books),"No se seleccionaron todos los libros");
        profilePage.clickDeleteAllBooksButton();
        Assert.assertTrue(profilePage.verifyDeleteAllBooks(books),"No fueron eliminados todos los libros");
        loginPage = profilePage.clickLogOutButton();
        Assert.assertTrue(loginPage.getTextMainHeader(),"No nos encontramos en el login");

    }
}