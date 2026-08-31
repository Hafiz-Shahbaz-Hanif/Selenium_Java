package com.hafiz.automation.tests.parabank;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.parabank.AccountOverviewPage;
import com.hafiz.automation.pages.parabank.RegistrationPage;
import com.hafiz.automation.utils.TestData;

/**
 * ParaBank end-to-end: register a brand-new customer (unique username per run)
 * and open a second account from the overview page.
 *
 * <p>Tagged {@code parabank} so it can be excluded when that public demo is
 * unavailable: {@code mvn test -Dgroups=internet}.
 */
public class NewCustomerTest extends BaseTest {

    @Test(groups = {"smoke", "parabank"})
    public void registerCustomerAndOpenAccount() {
        String username = TestData.uniqueUsername("hafiz");

        RegistrationPage registration =
                new RegistrationPage().open().registerNewCustomer(username, "Password123!");
        assertTrue(registration.confirmationMessage().contains("Your account was created successfully"),
                "registration should succeed and auto-login");

        AccountOverviewPage overview = new AccountOverviewPage().open();
        int before = overview.accountCount();
        assertTrue(before >= 1, "a new customer starts with one account");

        String newAccountNumber = overview.goToOpenNewAccount()
                .chooseType("SAVINGS")
                .submit();
        assertTrue(newAccountNumber.matches("\\d+"), "a numeric account number is issued");

        AccountOverviewPage refreshed = new AccountOverviewPage().open();
        assertEquals(refreshed.accountCount(), before + 1, "the new account appears in the overview");
    }
}
