package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.JavaScriptAlertsPage;

public class JavaScriptAlertsTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void acceptingAnAlert() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage().open();
        assertEquals(page.triggerAlertAndAccept(), "I am a JS Alert");
        assertEquals(page.resultText(), "You successfully clicked an alert");
    }

    @Test(groups = {"internet"})
    public void acceptingAConfirm() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage().open();
        page.triggerConfirmAndAccept();
        assertEquals(page.resultText(), "You clicked: Ok");
    }

    @Test(groups = {"internet"})
    public void dismissingAConfirm() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage().open();
        page.triggerConfirmAndDismiss();
        assertEquals(page.resultText(), "You clicked: Cancel");
    }

    @DataProvider(name = "promptAnswers")
    public Object[][] promptAnswers() {
        return new Object[][] {
            {"Hafiz"}, {"QA Engineer"}, {"12345"}, {"special !@#$%"}, {"a"},
        };
    }

    @Test(groups = {"internet"}, dataProvider = "promptAnswers")
    public void answeringAPrompt(String answer) {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage().open();
        page.triggerPromptWith(answer);
        assertTrue(page.resultText().equals("You entered: " + answer),
                "result was: " + page.resultText());
    }
}
