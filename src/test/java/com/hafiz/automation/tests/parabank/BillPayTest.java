package com.hafiz.automation.tests.parabank;

import java.util.List;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.parabank.AccountOverviewPage;
import com.hafiz.automation.pages.parabank.ParaBankMenu;
import com.hafiz.automation.pages.parabank.RegistrationPage;

public class BillPayTest extends BaseTest {

    @Test(groups = {"smoke", "parabank"})
    public void payingABillCompletes() {
        new RegistrationPage().registerRandomCustomer();

        List<String> accounts = new AccountOverviewPage().open().accountNumbers();
        String payeeAccount = accounts.get(0);

        String result = new ParaBankMenu()
                .openBillPay()
                .payBill("City Utilities", payeeAccount, "25")
                .resultTitle();
        assertEquals(result, "Bill Payment Complete");
    }
}
