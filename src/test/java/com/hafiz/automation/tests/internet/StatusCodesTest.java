package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.StatusCodesPage;

public class StatusCodesTest extends BaseTest {

    @DataProvider(name = "codes")
    public Object[][] codes() {
        return new Object[][] {{200}, {301}, {404}, {500}};
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "codes")
    public void thePageEchoesTheStatusCodeItWasServed(int code) {
        assertEquals(new StatusCodesPage().open().openCode(code).reportedCode(),
                String.valueOf(code));
    }
}
