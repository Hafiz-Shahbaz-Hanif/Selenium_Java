package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.ForgotPasswordPage;

public class ForgotPasswordTest extends BaseTest {

    @DataProvider(name = "emails")
    public Object[][] emails() {
        return new Object[][] {
            {"hafiz@example.com"},
            {"another.user@example.org"},
            {"qa+test@example.net"},
        };
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "emails")
    public void submittingAnEmailLeavesTheFormWithAServerResponse(String email) {
        String body = new ForgotPasswordPage().open().submitEmail(email);
        // the-internet's endpoint either confirms the send or returns its
        // deliberate 500 page; either way we should have left the form.
        assertTrue(
                body.contains("e-mail's been sent")
                        || body.contains("email's been sent")
                        || body.contains("Internal Server Error"),
                "unexpected response body: " + body);
    }
}
