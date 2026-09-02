package com.hafiz.automation.pages.internet;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /windows. */
public class MultipleWindowsPage extends BasePage {

    @FindBy(css = ".example a[href='/windows/new']")
    private WebElement clickHere;

    @FindBy(css = ".example h3")
    private WebElement heading;

    public MultipleWindowsPage open() {
        open(Configuration.theInternetBaseUrl() + "/windows");
        visible(clickHere);
        return this;
    }

    public String openNewWindowAndReadHeading() {
        String original = driver.getWindowHandle();
        click(clickHere);
        wait.until(d -> d.getWindowHandles().size() > 1);
        List<String> handles = List.copyOf(driver.getWindowHandles());
        String other = handles.stream().filter(h -> !h.equals(original)).findFirst().orElseThrow();
        driver.switchTo().window(other);
        try {
            return textOf(heading);
        } finally {
            driver.close();
            driver.switchTo().window(original);
        }
    }
}
