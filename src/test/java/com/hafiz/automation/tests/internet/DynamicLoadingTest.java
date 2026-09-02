package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.DynamicLoadingPage;

public class DynamicLoadingTest extends BaseTest {

    @DataProvider(name = "examples")
    public Object[][] examples() {
        return new Object[][] {{1}, {2}};
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "examples")
    public void theElementIsWaitedForAndRead(int example) {
        String text = new DynamicLoadingPage().open(example).start().loadedText();
        assertEquals(text, "Hello World!");
    }
}
