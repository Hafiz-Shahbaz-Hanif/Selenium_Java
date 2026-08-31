package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /dropdown. */
public class DropdownPage extends BasePage {

    @FindBy(id = "dropdown")
    private WebElement dropdown;

    public DropdownPage open() {
        open(Configuration.theInternetBaseUrl() + "/dropdown");
        visible(dropdown);
        return this;
    }

    public void select(String visibleText) {
        new Select(dropdown).selectByVisibleText(visibleText);
    }

    public String selectedOption() {
        return new Select(dropdown).getFirstSelectedOption().getText().trim();
    }
}
