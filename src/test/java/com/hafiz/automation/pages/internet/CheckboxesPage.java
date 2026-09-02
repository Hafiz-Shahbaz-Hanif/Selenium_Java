package com.hafiz.automation.pages.internet;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /checkboxes. */
public class CheckboxesPage extends BasePage {

    @FindBy(css = "#checkboxes input[type='checkbox']")
    private List<WebElement> checkboxes;

    public CheckboxesPage open() {
        open(Configuration.theInternetBaseUrl() + "/checkboxes");
        waitForAll(checkboxes);
        return this;
    }

    public int count() {
        return checkboxes.size();
    }

    public boolean isChecked(int index) {
        return checkboxes.get(index).isSelected();
    }

    public CheckboxesPage setChecked(int index, boolean checked) {
        if (checkboxes.get(index).isSelected() != checked) {
            checkboxes.get(index).click();
        }
        return this;
    }

    public CheckboxesPage toggle(int index) {
        checkboxes.get(index).click();
        return this;
    }
}
