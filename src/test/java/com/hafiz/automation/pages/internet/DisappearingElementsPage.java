package com.hafiz.automation.pages.internet;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /disappearing_elements (a menu whose 5th item comes and goes). */
public class DisappearingElementsPage extends BasePage {

    @FindBy(css = "ul li a")
    private List<WebElement> menuItems;

    public DisappearingElementsPage open() {
        open(Configuration.theInternetBaseUrl() + "/disappearing_elements");
        waitForAll(menuItems);
        return this;
    }

    public List<String> menuLabels() {
        return textsOf(menuItems);
    }

    /** Reload until the (sometimes-present) "Gallery" item appears. */
    public boolean galleryEventuallyAppears(int attempts) {
        for (int i = 0; i < attempts; i++) {
            if (menuLabels().contains("Gallery")) {
                return true;
            }
            driver.navigate().refresh();
            waitForAll(menuItems);
        }
        return menuLabels().contains("Gallery");
    }
}
