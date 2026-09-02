package com.hafiz.automation.tests.parabank;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.parabank.ParaBankMenu;
import com.hafiz.automation.pages.parabank.RegistrationPage;
import com.hafiz.automation.pages.parabank.RequestLoanPage;

public class RequestLoanTest extends BaseTest {

    @DataProvider(name = "loans")
    public Object[][] loans() {
        return new Object[][] {
            {"1000", "100"},
            {"100000", "0"},
        };
    }

    @Test(groups = {"parabank"}, dataProvider = "loans")
    public void aLoanApplicationIsProcessed(String amount, String down) {
        new RegistrationPage().registerRandomCustomer();

        RequestLoanPage loan = new ParaBankMenu().openRequestLoan().apply(amount, down);
        assertEquals(loan.resultTitle(), "Loan Request Processed");
        assertTrue(Set.of("Approved", "Denied", "Pending").contains(loan.status()),
                "unexpected loan status: " + loan.status());
    }
}
