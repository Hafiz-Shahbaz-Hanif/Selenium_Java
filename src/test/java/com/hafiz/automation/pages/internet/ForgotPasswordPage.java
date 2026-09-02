package com.hafiz.automation.pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /forgot_password. */
public class ForgotPasswordPage extends BasePage {

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "form_submit")
    private WebElement submit;

    public ForgotPasswordPage open() {
        open(Configuration.theInternetBaseUrl() + "/forgot_password");
        visible(email);
        return this;
    }

    /** Submit an address and return the text of the page that follows. */
    public String submitEmail(String address) {
        type(email, address);
        click(submit);
        wait.until(d -> {
            String source = d.getPageSource();
            return source.contains("been sent") || source.contains("Internal Server Error");
        });
        return driver.findElement(By.tagName("body")).getText().trim();
    }
}
