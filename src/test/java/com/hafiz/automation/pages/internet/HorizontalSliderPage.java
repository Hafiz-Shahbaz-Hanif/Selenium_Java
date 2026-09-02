package com.hafiz.automation.pages.internet;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /horizontal_slider (range input 0..5 step 0.5). */
public class HorizontalSliderPage extends BasePage {

    @FindBy(css = "input[type='range']")
    private WebElement slider;

    @FindBy(id = "range")
    private WebElement value;

    public HorizontalSliderPage open() {
        open(Configuration.theInternetBaseUrl() + "/horizontal_slider");
        visible(slider);
        return this;
    }

    /** Move the slider to {@code target} (0..5, in 0.5 steps) using arrow keys. */
    public String moveTo(double target) {
        slider.click();
        // Reset to 0.
        for (int i = 0; i < 12; i++) {
            slider.sendKeys(Keys.ARROW_LEFT);
        }
        int steps = (int) Math.round(target / 0.5);
        for (int i = 0; i < steps; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        return textOf(value);
    }
}
