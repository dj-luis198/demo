package com.example.test.gui.bookStoreApp;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.ProfilePage;
import com.example.resources.dataProviders.bookStoreApplication.BookStoreAppDataProvider;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.goToLoginPage();
    }

    @Test(dataProvider = "LoginValidData", dataProviderClass = BookStoreAppDataProvider.class)
    public void VerifyLoginWithValidDataTest(String username, String password) {
        ProfilePage profilePage = loginPage.login(username, password);
        Assert.assertEquals(profilePage.getUserName(), username);
        profilePage.clickLogOutButton();
    }

    @Test(dataProvider = "LoginInvalidData", dataProviderClass = BookStoreAppDataProvider.class)
    public void VerifyLoginWithInvalidDataTest(String username, String password) {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        Assert.assertEquals(loginPage.getMsgErrorLogin(), "Invalid username or password!");
    }
}
