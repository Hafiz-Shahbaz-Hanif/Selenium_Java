package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.LoginPage;
import com.hafiz.automation.pages.internet.SecureAreaPage;

public class AuthenticationTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void validLoginReachesSecureArea() {
        SecureAreaPage secure = new LoginPage().open().loginAsValidUser();
        assertTrue(secure.isLoggedIn(), "should be on the secure area");
        assertTrue(secure.flashMessage().contains("You logged into a secure area!"));
    }

    @DataProvider(name = "badCredentials")
    public Object[][] badCredentials() {
        return new Object[][] {
            {"tomsmith", "wrong-password", "Your password is invalid!"},
            {"tomsmith", "", "Your password is invalid!"},
            {"wronguser", "SuperSecretPassword!", "Your username is invalid!"},
            {"", "SuperSecretPassword!", "Your username is invalid!"},
            {"", "", "Your username is invalid!"},
            {"TomSmith", "SuperSecretPassword!", "Your username is invalid!"},
            {"tomsmith ", "SuperSecretPassword!", "Your username is invalid!"},
        };
    }

    @Test(groups = {"internet"}, dataProvider = "badCredentials")
    public void invalidCredentialsAreRejected(String user, String pass, String message) {
        LoginPage login = new LoginPage().open();
        login.loginAs(user, pass);
        assertTrue(login.flashMessage().contains(message),
                "expected \"" + message + "\" but was \"" + login.flashMessage() + "\"");
    }

    @Test(groups = {"internet"})
    public void logoutReturnsToLogin() {
        LoginPage login = new LoginPage().open().loginAsValidUser().logout();
        assertTrue(login.flashMessage().contains("You logged out of the secure area!"));
        assertFalse(new SecureAreaPage().isLoggedIn());
    }
}
