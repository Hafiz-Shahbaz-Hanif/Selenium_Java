package com.hafiz.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.pages.BasePage;

/** ParaBank &rarr; /transfer.htm. */
public class TransferFundsPage extends BasePage {

    @FindBy(id = "amount")
    private WebElement amount;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccount;

    @FindBy(id = "toAccountId")
    private WebElement toAccount;

    @FindBy(css = "input[value='Transfer']")
    private WebElement transferButton;

    @FindBy(css = "#showResult h1.title")
    private WebElement resultTitle;

    public TransferFundsPage transfer(String value) {
        wait.until(d -> !new org.openqa.selenium.support.ui.Select(fromAccount).getOptions().isEmpty());
        type(amount, value);
        click(transferButton);
        return this;
    }

    public String resultTitle() {
        return textOf(resultTitle);
    }
}
