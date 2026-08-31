package com.hafiz.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** ParaBank &rarr; /register.htm. */
public class RegistrationPage extends BasePage {

    @FindBy(id = "customer.firstName")
    private WebElement firstName;

    @FindBy(id = "customer.lastName")
    private WebElement lastName;

    @FindBy(id = "customer.address.street")
    private WebElement street;

    @FindBy(id = "customer.address.city")
    private WebElement city;

    @FindBy(id = "customer.address.state")
    private WebElement state;

    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCode;

    @FindBy(id = "customer.phoneNumber")
    private WebElement phone;

    @FindBy(id = "customer.ssn")
    private WebElement ssn;

    @FindBy(id = "customer.username")
    private WebElement username;

    @FindBy(id = "customer.password")
    private WebElement password;

    @FindBy(id = "repeatedPassword")
    private WebElement confirmPassword;

    @FindBy(css = "input[value='Register']")
    private WebElement register;

    @FindBy(css = "#rightPanel p")
    private WebElement confirmation;

    public RegistrationPage open() {
        open(Configuration.paraBankBaseUrl() + "/register.htm");
        visible(firstName);
        return this;
    }

    public RegistrationPage registerNewCustomer(String user, String pass) {
        type(firstName, "Hafiz");
        type(lastName, "QA");
        type(street, "1 Test Street");
        type(city, "Lahore");
        type(state, "Punjab");
        type(zipCode, "54000");
        type(phone, "0000000000");
        type(ssn, "123-45-6789");
        type(username, user);
        type(password, pass);
        type(confirmPassword, pass);
        click(register);
        return this;
    }

    public String confirmationMessage() {
        return textOf(confirmation);
    }
}
