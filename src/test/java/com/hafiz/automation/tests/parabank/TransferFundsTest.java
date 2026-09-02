package com.hafiz.automation.tests.parabank;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.parabank.AccountOverviewPage;
import com.hafiz.automation.pages.parabank.ParaBankMenu;
import com.hafiz.automation.pages.parabank.RegistrationPage;

public class TransferFundsTest extends BaseTest {

    @DataProvider(name = "amounts")
    public Object[][] amounts() {
        return new Object[][] {{"10"}, {"100"}, {"250"}};
    }

    @Test(groups = {"smoke", "parabank"}, dataProvider = "amounts")
    public void transferringBetweenOwnAccountsCompletes(String amount) {
        new RegistrationPage().registerRandomCustomer();

        // A new customer needs a second account to transfer to.
        new AccountOverviewPage().open().goToOpenNewAccount().chooseType("SAVINGS").submit();

        String result = new ParaBankMenu().openTransferFunds().transfer(amount).resultTitle();
        assertEquals(result, "Transfer Complete!");
    }
}
