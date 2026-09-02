package com.hafiz.automation.pages.internet;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /dynamic_controls (AJAX enable/disable and add/remove). */
public class DynamicControlsPage extends BasePage {

    @FindBy(css = "#input-example input")
    private WebElement textInput;

    @FindBy(css = "#input-example button")
    private WebElement enableDisableButton;

    @FindBy(css = "#checkbox-example button")
    private WebElement addRemoveButton;

    @FindBy(css = "#checkbox")
    private WebElement checkbox;

    @FindBy(css = "#loading")
    private WebElement loading;

    public DynamicControlsPage open() {
        open(Configuration.theInternetBaseUrl() + "/dynamic_controls");
        visible(enableDisableButton);
        return this;
    }

    private void waitForAjax() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.invisibilityOf(loading));
    }

    public DynamicControlsPage clickEnableDisable() {
        click(enableDisableButton);
        waitForAjax();
        return this;
    }

    public boolean isInputEnabled() {
        return textInput.isEnabled();
    }

    public DynamicControlsPage clickAddRemove() {
        click(addRemoveButton);
        waitForAjax();
        return this;
    }

    public boolean isCheckboxPresent() {
        return !driver.findElements(org.openqa.selenium.By.id("checkbox")).isEmpty();
    }
}
