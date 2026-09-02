package com.hafiz.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.pages.BasePage;

/** The left-hand navigation panel shown to a logged-in ParaBank customer. */
public class ParaBankMenu extends BasePage {

    @FindBy(linkText = "Accounts Overview")
    private WebElement accountsOverview;

    @FindBy(linkText = "Transfer Funds")
    private WebElement transferFunds;

    @FindBy(linkText = "Bill Pay")
    private WebElement billPay;

    @FindBy(linkText = "Request Loan")
    private WebElement requestLoan;

    @FindBy(linkText = "Log Out")
    private WebElement logOut;

    public AccountOverviewPage openAccountsOverview() {
        click(accountsOverview);
        return new AccountOverviewPage();
    }

    public TransferFundsPage openTransferFunds() {
        click(transferFunds);
        return new TransferFundsPage();
    }

    public BillPayPage openBillPay() {
        click(billPay);
        return new BillPayPage();
    }

    public RequestLoanPage openRequestLoan() {
        click(requestLoan);
        return new RequestLoanPage();
    }

    public void logOut() {
        click(logOut);
        wait.until(d -> d.getCurrentUrl().contains("index.htm"));
    }

    public boolean isLoggedIn() {
        return !driver.findElements(org.openqa.selenium.By.linkText("Log Out")).isEmpty();
    }
}
