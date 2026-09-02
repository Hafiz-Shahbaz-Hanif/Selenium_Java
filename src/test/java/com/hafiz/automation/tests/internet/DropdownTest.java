package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.DropdownPage;

public class DropdownTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void theDropdownOffersTwoRealOptions() {
        assertTrue(new DropdownPage().open().options().containsAll(
                java.util.List.of("Option 1", "Option 2")));
    }

    @DataProvider(name = "options")
    public Object[][] options() {
        return new Object[][] {{"Option 1"}, {"Option 2"}};
    }

    @Test(groups = {"internet"}, dataProvider = "options")
    public void selectingAnOptionSticks(String option) {
        DropdownPage page = new DropdownPage().open().select(option);
        assertEquals(page.selectedOption(), option);
    }
}
