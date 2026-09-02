package com.hafiz.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.pages.BasePage;

/** ParaBank &rarr; /requestloan.htm. */
public class RequestLoanPage extends BasePage {

    @FindBy(id = "amount")
    private WebElement amount;

    @FindBy(id = "downPayment")
    private WebElement downPayment;

    @FindBy(css = "input[value='Apply Now']")
    private WebElement applyNow;

    @FindBy(id = "loanStatus")
    private WebElement loanStatus;

    @FindBy(css = "#requestLoanResult h1.title")
    private WebElement resultTitle;

    public RequestLoanPage apply(String loanAmount, String down) {
        wait.until(d -> !new org.openqa.selenium.support.ui.Select(
                driver.findElement(org.openqa.selenium.By.id("fromAccountId"))).getOptions().isEmpty());
        type(amount, loanAmount);
        type(downPayment, down);
        click(applyNow);
        visible(resultTitle);
        return this;
    }

    public String resultTitle() {
        return textOf(resultTitle);
    }

    public String status() {
        return textOf(loanStatus);
    }
}
