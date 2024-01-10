package com.example.test.gui.textBox;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.TextBoxPage;
import com.example.resources.dataProviders.textBox.TextBoxDataProvider;

public class TextBoxTest extends BaseTest {
    TextBoxPage textBoxPage;

    @BeforeMethod
    public void preconditions() {
        HomePage homePage = new HomePage(driver);
        textBoxPage = homePage.goToTextBoxPage();
    }

    @Test(dataProvider = "ValidData" , dataProviderClass = TextBoxDataProvider.class)
    public void verifyTextBoxWithValidData(String username, String email, String currentAddress, String permanentAddress) {
        textBoxPage.enterUsername(username);
        textBoxPage.enterEmail(email);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        textBoxPage.clickSubmitButton();
        Assert.assertEquals(textBoxPage.getTextUserName(), "Name:"+username);
        Assert.assertEquals(textBoxPage.getTextEmail(), "Email:"+email);
        Assert.assertEquals(textBoxPage.getTextCurrentAddress(), "Current Address :"+currentAddress);
        Assert.assertEquals(textBoxPage.getTextPermanentAddress(), "Permananet Address :"+permanentAddress);
    }

    @Test(dataProvider = "InvalidData" , dataProviderClass = TextBoxDataProvider.class)
    public void verifyTextBoxWithInvalidEmail(String username, String email, String currentAddress, String permanentAddress) {
        textBoxPage.enterUsername(username);
        textBoxPage.enterEmail(email);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        textBoxPage.clickSubmitButton();
        String textRes= textBoxPage.verifyEmailMessage(email);
        Assert.assertEquals(textRes,"true","El mensaje de error no es el que se esperaba: "+textRes);
        Assert.assertTrue(textBoxPage.verifyBorderColorEmailInput(),"El color del borde del input email no es el que se esperaba");
    }
}
