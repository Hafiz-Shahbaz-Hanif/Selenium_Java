package com.hafiz.automation.pages.parabank;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hafiz.automation.pages.BasePage;

/** ParaBank &rarr; /openaccount.htm. */
public class OpenAccountPage extends BasePage {

    @FindBy(id = "type")
    private WebElement accountType;

    @FindBy(id = "fromAccountId")
    private WebElement fromAccount;

    @FindBy(css = "input[value='Open New Account']")
    private WebElement openButton;

    @FindBy(id = "newAccountId")
    private WebElement newAccountId;

    public OpenAccountPage chooseType(String type) {
        // The "from" account list is populated by an AJAX call - wait for it
        // before interacting with the form.
        wait.until(d -> new Select(fromAccount).getOptions().size() > 0);
        wait.until(ExpectedConditions.elementToBeClickable(accountType));
        new Select(accountType).selectByVisibleText(type);
        return this;
    }

    public String submit() {
        click(openButton);
        return textOf(newAccountId);
    }
}
