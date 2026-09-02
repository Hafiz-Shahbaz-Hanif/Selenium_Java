package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.KeyPressesPage;

public class KeyPressesTest extends BaseTest {

    @DataProvider(name = "keys")
    public Object[][] keys() {
        return new Object[][] {
            {"a", "A"}, {"b", "B"}, {"q", "Q"}, {"z", "Z"},
            {"0", "0"}, {"5", "5"}, {"9", "9"},
            {Keys.TAB.toString(), "TAB"},
            {Keys.SPACE.toString(), "SPACE"},
            {Keys.BACK_SPACE.toString(), "BACK_SPACE"},
        };
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "keys")
    public void pressingAKeyIsReported(String key, String expectedName) {
        assertEquals(
                new KeyPressesPage().open().pressAndReadResult(key),
                "You entered: " + expectedName);
    }
}
