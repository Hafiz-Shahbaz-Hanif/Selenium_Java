package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

    @Test(groups = {"internet"})
    public void invalidPasswordIsRejected() {
        LoginPage login = new LoginPage().open();
        login.loginAs("tomsmith", "wrong-password");

        assertTrue(login.flashMessage().contains("Your password is invalid!"));
    }

    @Test(groups = {"internet"})
    public void logoutReturnsToLogin() {
        SecureAreaPage secure = new LoginPage().open().loginAsValidUser();
        LoginPage login = secure.logout();

        assertTrue(login.flashMessage().contains("You logged out of the secure area!"));
        assertFalse(new SecureAreaPage().isLoggedIn());
    }
}
