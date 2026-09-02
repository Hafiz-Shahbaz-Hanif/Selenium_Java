package com.hafiz.automation.pages.internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /iframe (a TinyMCE editor inside an iframe). */
public class FramesPage extends BasePage {

    @FindBy(id = "mce_0_ifr")
    private WebElement editorFrame;

    public FramesPage open() {
        open(Configuration.theInternetBaseUrl() + "/iframe");
        visible(editorFrame);
        return this;
    }

    public String replaceBodyTextWith(String text) {
        driver.switchTo().frame(editorFrame);
        try {
            WebElement body = wait.until(
                    org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
                            By.id("tinymce")));
            var js = (org.openqa.selenium.JavascriptExecutor) driver;
            // #tinymce is a contenteditable <body>: replace its content directly
            // and fire input so the editor's model updates, then read it back.
            js.executeScript(
                    "arguments[0].innerHTML = '<p>' + arguments[1] + '</p>';"
                            + "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));",
                    body, text);
            return ((String) js.executeScript("return arguments[0].textContent;", body)).trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
