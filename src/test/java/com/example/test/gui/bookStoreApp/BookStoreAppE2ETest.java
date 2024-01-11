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

    @Test
    public void bookStoreE2E() {
        ProfilePage profilePage = loginPage.login(username, password);
        Assert.assertEquals(profilePage.getUserName(), username);
        BookStorePage bookStorePage = profilePage.clickGoToStoreButton();
        System.out.println("ingreso seleccion libros");
        bookStorePage.selectBooks(books);
        System.out.println("Se seleccionaron los libros");
        profilePage = bookStorePage.clickProfileOption();
        System.out.println("verificando seleccion de libros");
        Assert.assertTrue(profilePage.verifySelectedBooks(books),"No se seleccionaron todos los libros");
        System.out.println("eliminando libros");
        profilePage.clickDeleteAllBooksButton();
        System.out.println("verificando eliminacion de libros");
        Assert.assertTrue(profilePage.verifyDeleteAllBooks(books),"No fueron eliminados todos los libros");
        System.out.println("saliendo de la app");
        loginPage = profilePage.clickLogOutButton();
        Assert.assertTrue(loginPage.getTextMainHeader(),"No nos encontramos en el login");

    }
}