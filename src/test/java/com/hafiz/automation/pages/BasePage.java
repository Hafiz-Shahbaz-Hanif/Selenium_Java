package com.hafiz.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.driver.DriverFactory;

/**
 * Base class for every Page Object.
 *
 * <p>Initialises {@link PageFactory} {@code @FindBy} fields, exposes a shared
 * {@link WebDriverWait} and provides the small set of element helpers the page
 * classes need. Test classes never see a locator or the raw driver.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.get();
        this.wait = new WebDriverWait(driver, Configuration.explicitWait());
        PageFactory.initElements(driver, this);
    }

    protected void open(String url) {
        driver.get(url);
        // The driver uses pageLoadStrategy=NONE (some targets never fire `load`),
        // so block here until the DOM is parsed before Page Objects look for
        // elements. `interactive` is DOMContentLoaded - it does not wait on the
        // slow third-party sub-resources that stall the `load` event.
        new WebDriverWait(driver, Duration.ofSeconds(25)).until(
                d -> !"loading".equals(((JavascriptExecutor) d)
                        .executeScript("return document.readyState")));
    }

    protected WebElement visible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement clickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        clickable(element).click();
    }

    protected void type(WebElement element, String text) {
        WebElement field = visible(element);
        field.clear();
        field.sendKeys(text);
    }

    protected String textOf(WebElement element) {
        return visible(element).getText().trim();
    }

    protected boolean isDisplayed(By locator, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    protected List<WebElement> waitForAll(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    protected void selectByText(WebElement dropdown, String visibleText) {
        new Select(visible(dropdown)).selectByVisibleText(visibleText);
    }

    protected String selectedOption(WebElement dropdown) {
        return new Select(visible(dropdown)).getFirstSelectedOption().getText().trim();
    }

    protected String attribute(WebElement element, String name) {
        return visible(element).getDomAttribute(name);
    }

    protected boolean isDisplayed(WebElement element, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    protected Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    protected String acceptAlert() {
        Alert alert = waitForAlert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    protected String dismissAlert() {
        Alert alert = waitForAlert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }

    protected String answerPrompt(String answer) {
        Alert alert = waitForAlert();
        String text = alert.getText();
        alert.sendKeys(answer);
        alert.accept();
        return text;
    }

    protected List<String> textsOf(List<WebElement> elements) {
        return elements.stream().map(WebElement::getText).map(String::trim).toList();
    }

    protected String currentUrl() {
        return driver.getCurrentUrl();
    }
}
