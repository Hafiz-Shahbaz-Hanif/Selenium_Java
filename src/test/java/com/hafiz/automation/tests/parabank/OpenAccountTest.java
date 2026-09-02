package com.hafiz.automation.tests.parabank;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.parabank.AccountOverviewPage;
import com.hafiz.automation.pages.parabank.RegistrationPage;

public class OpenAccountTest extends BaseTest {

    @DataProvider(name = "accountTypes")
    public Object[][] accountTypes() {
        return new Object[][] {{"CHECKING"}, {"SAVINGS"}};
    }

    @Test(groups = {"smoke", "parabank"}, dataProvider = "accountTypes")
    public void aFreshCustomerCanOpenAnAdditionalAccount(String type) {
        new RegistrationPage().registerRandomCustomer();

        AccountOverviewPage overview = new AccountOverviewPage().open();
        int before = overview.accountCount();

        String newAccount = overview.goToOpenNewAccount().chooseType(type).submit();
        assertTrue(newAccount.matches("\\d+"), "a numeric account number is issued");

        assertEquals(new AccountOverviewPage().open().accountCount(), before + 1,
                "the new account shows in the overview");
    }
}
