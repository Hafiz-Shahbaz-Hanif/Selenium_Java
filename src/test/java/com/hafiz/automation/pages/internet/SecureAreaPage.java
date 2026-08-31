package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /secure (shown after a successful login). */
public class SecureAreaPage extends BasePage {

    @FindBy(id = "flash")
    private WebElement flash;

    @FindBy(css = "a.button")
    private WebElement logout;

    public String flashMessage() {
        return textOf(flash);
    }

    public boolean isLoggedIn() {
        return driver.getCurrentUrl().contains("/secure");
    }

    public LoginPage logout() {
        click(logout);
        return new LoginPage();
    }
}
