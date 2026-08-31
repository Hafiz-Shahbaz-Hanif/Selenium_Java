package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /login (form authentication). */
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "button[type='submit']")
    private WebElement submit;

    @FindBy(id = "flash")
    private WebElement flash;

    public LoginPage open() {
        open(Configuration.theInternetBaseUrl() + "/login");
        visible(username);
        return this;
    }

    public SecureAreaPage loginAs(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(submit);
        return new SecureAreaPage();
    }

    public SecureAreaPage loginAsValidUser() {
        return loginAs("tomsmith", "SuperSecretPassword!");
    }

    public String flashMessage() {
        return textOf(flash);
    }
}
