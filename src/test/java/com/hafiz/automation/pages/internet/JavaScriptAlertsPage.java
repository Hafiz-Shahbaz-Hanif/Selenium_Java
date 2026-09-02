package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /javascript_alerts. */
public class JavaScriptAlertsPage extends BasePage {

    @FindBy(css = "button[onclick='jsAlert()']")
    private WebElement alertButton;

    @FindBy(css = "button[onclick='jsConfirm()']")
    private WebElement confirmButton;

    @FindBy(css = "button[onclick='jsPrompt()']")
    private WebElement promptButton;

    @FindBy(id = "result")
    private WebElement result;

    public JavaScriptAlertsPage open() {
        open(Configuration.theInternetBaseUrl() + "/javascript_alerts");
        visible(alertButton);
        return this;
    }

    public String triggerAlertAndAccept() {
        click(alertButton);
        return acceptAlert();
    }

    public String triggerConfirmAndAccept() {
        click(confirmButton);
        return acceptAlert();
    }

    public String triggerConfirmAndDismiss() {
        click(confirmButton);
        return dismissAlert();
    }

    public String triggerPromptWith(String answer) {
        click(promptButton);
        return answerPrompt(answer);
    }

    public String resultText() {
        return textOf(result);
    }
}
