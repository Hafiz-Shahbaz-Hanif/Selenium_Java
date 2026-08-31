package com.hafiz.automation.pages.internet;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /add_remove_elements/. */
public class AddRemoveElementsPage extends BasePage {

    @FindBy(css = "button[onclick='addElement()']")
    private WebElement addButton;

    @FindBy(css = ".added-manually")
    private List<WebElement> deleteButtons;

    public AddRemoveElementsPage open() {
        open(Configuration.theInternetBaseUrl() + "/add_remove_elements/");
        visible(addButton);
        return this;
    }

    public AddRemoveElementsPage addElements(int count) {
        for (int i = 0; i < count; i++) {
            click(addButton);
        }
        return this;
    }

    public AddRemoveElementsPage removeElement() {
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
        }
        return this;
    }

    public int elementCount() {
        return deleteButtons.size();
    }
}
