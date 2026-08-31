package com.hafiz.automation.pages.parabank;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** ParaBank &rarr; /overview.htm (Accounts Overview). */
public class AccountOverviewPage extends BasePage {

    @FindBy(css = "h1.title")
    private WebElement title;

    @FindBy(css = "#accountTable tbody tr td a")
    private List<WebElement> accountLinks;

    @FindBy(linkText = "Open New Account")
    private WebElement openNewAccountLink;

    public AccountOverviewPage open() {
        open(Configuration.paraBankBaseUrl() + "/overview.htm");
        visible(title);
        return this;
    }

    public String heading() {
        return textOf(title);
    }

    public int accountCount() {
        return waitForAll(accountLinks).size();
    }

    public List<String> accountNumbers() {
        return waitForAll(accountLinks).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    public OpenAccountPage goToOpenNewAccount() {
        click(openNewAccountLink);
        return new OpenAccountPage();
    }
}
