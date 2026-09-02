package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.FramesPage;

public class FramesTest extends BaseTest {

    @DataProvider(name = "texts")
    public Object[][] texts() {
        return new Object[][] {{"Hafiz was here"}, {"Automated edit 123"}, {"iframe content"}};
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "texts")
    public void textCanBeTypedIntoTheIframeEditor(String text) {
        assertEquals(new FramesPage().open().replaceBodyTextWith(text), text);
    }
}
