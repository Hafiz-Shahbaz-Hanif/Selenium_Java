package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.HorizontalSliderPage;

public class HorizontalSliderTest extends BaseTest {

    @DataProvider(name = "positions")
    public Object[][] positions() {
        return new Object[][] {
            {0.0, "0"}, {0.5, "0.5"}, {1.5, "1.5"},
            {2.5, "2.5"}, {3.0, "3"}, {5.0, "5"},
        };
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "positions")
    public void theSliderReportsWhereItIsMoved(double target, String expected) {
        assertEquals(new HorizontalSliderPage().open().moveTo(target), expected);
    }
}
