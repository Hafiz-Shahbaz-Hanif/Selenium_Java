package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.InputsPage;

public class InputsTest extends BaseTest {

    @DataProvider(name = "numbers")
    public Object[][] numbers() {
        return new Object[][] {
            {"0"}, {"42"}, {"-17"}, {"1000000"}, {"999999999"}, {"7"},
        };
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "numbers")
    public void theNumberInputKeepsWhatIType(String value) {
        assertEquals(new InputsPage().open().enterAndReadBack(value), value);
    }
}
