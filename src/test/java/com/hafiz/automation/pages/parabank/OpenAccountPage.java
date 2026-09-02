package com.hafiz.automation.pages.parabank;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        // The "from" account list is populated by an AJAX call that the ParaBank
        // demo is sometimes slow to answer - wait generously before proceeding.
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(d -> !new Select(fromAccount).getOptions().isEmpty());
        wait.until(ExpectedConditions.elementToBeClickable(accountType));
        new Select(accountType).selectByVisibleText(type);
        return this;
    }

    public String submit() {
        click(openButton);
        return textOf(newAccountId);
    }
}
