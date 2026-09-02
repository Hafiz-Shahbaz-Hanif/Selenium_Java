package com.hafiz.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.pages.BasePage;

/** ParaBank &rarr; /billpay.htm. */
public class BillPayPage extends BasePage {

    @FindBy(css = "input[name='payee.name']")
    private WebElement payeeName;

    @FindBy(css = "input[name='payee.address.street']")
    private WebElement street;

    @FindBy(css = "input[name='payee.address.city']")
    private WebElement city;

    @FindBy(css = "input[name='payee.address.state']")
    private WebElement state;

    @FindBy(css = "input[name='payee.address.zipCode']")
    private WebElement zipCode;

    @FindBy(css = "input[name='payee.phoneNumber']")
    private WebElement phone;

    @FindBy(css = "input[name='payee.accountNumber']")
    private WebElement accountNumber;

    @FindBy(css = "input[name='verifyAccount']")
    private WebElement verifyAccount;

    @FindBy(css = "input[name='amount']")
    private WebElement amount;

    @FindBy(css = "input[value='Send Payment']")
    private WebElement sendPayment;

    @FindBy(css = "#billpayResult h1.title")
    private WebElement resultTitle;

    public BillPayPage payBill(String payee, String account, String value) {
        type(payeeName, payee);
        type(street, "1 Payee St");
        type(city, "Lahore");
        type(state, "Punjab");
        type(zipCode, "54000");
        type(phone, "0000000000");
        type(accountNumber, account);
        type(verifyAccount, account);
        type(amount, value);
        click(sendPayment);
        return this;
    }

    public String resultTitle() {
        return textOf(resultTitle);
    }
}
