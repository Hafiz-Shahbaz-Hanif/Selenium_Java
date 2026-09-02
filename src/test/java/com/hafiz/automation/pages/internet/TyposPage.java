package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /typos (the second paragraph occasionally has a typo). */
public class TyposPage extends BasePage {

    private static final String EXPECTED =
            "Sometimes you'll see a typo, other times you won't.";

    @FindBy(css = ".example p:nth-of-type(2)")
    private WebElement paragraph;

    public TyposPage open() {
        open(Configuration.theInternetBaseUrl() + "/typos");
        visible(paragraph);
        return this;
    }

    /** Reload up to {@code attempts} times until the paragraph is typo-free. */
    public boolean settlesToCorrectText(int attempts) {
        for (int i = 0; i < attempts; i++) {
            if (textOf(paragraph).equals(EXPECTED)) {
                return true;
            }
            driver.navigate().refresh();
            visible(paragraph);
        }
        return textOf(paragraph).equals(EXPECTED);
    }
}
