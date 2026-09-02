package com.hafiz.automation.tests.parabank;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.parabank.ParaBankMenu;
import com.hafiz.automation.pages.parabank.RegistrationPage;

public class SessionTest extends BaseTest {

    @Test(groups = {"parabank"})
    public void aNewlyRegisteredCustomerIsLoggedIn() {
        new RegistrationPage().registerRandomCustomer();
        assertTrue(new ParaBankMenu().isLoggedIn(), "registration should auto-login");
    }

    @Test(groups = {"parabank"})
    public void loggingOutEndsTheSession() {
        new RegistrationPage().registerRandomCustomer();
        ParaBankMenu menu = new ParaBankMenu();
        menu.logOut();
        assertFalse(menu.isLoggedIn(), "the Log Out link should be gone");
    }
}
