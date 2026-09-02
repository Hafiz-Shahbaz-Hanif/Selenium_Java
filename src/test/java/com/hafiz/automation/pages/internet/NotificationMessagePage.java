package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /notification_message_rendered. */
public class NotificationMessagePage extends BasePage {

    @FindBy(css = ".example a[href='/notification_message']")
    private WebElement clickHereLink;

    @FindBy(id = "flash")
    private WebElement flash;

    public NotificationMessagePage open() {
        open(Configuration.theInternetBaseUrl() + "/notification_message_rendered");
        visible(clickHereLink);
        return this;
    }

    public String clickHereAndReadMessage() {
        click(clickHereLink);
        return textOf(flash).replace("×", "").trim();
    }
}
