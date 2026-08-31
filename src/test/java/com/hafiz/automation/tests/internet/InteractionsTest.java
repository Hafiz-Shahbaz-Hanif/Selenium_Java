package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.AddRemoveElementsPage;
import com.hafiz.automation.pages.internet.DropdownPage;
import com.hafiz.automation.pages.internet.DynamicLoadingPage;

public class InteractionsTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void dynamicElementIsWaitedFor() {
        String text = new DynamicLoadingPage().open().start().loadedText();
        assertEquals(text, "Hello World!");
    }

    @Test(groups = {"internet"}, dataProvider = "options")
    public void dropdownSelectionSticks(String option) {
        DropdownPage page = new DropdownPage().open();
        page.select(option);
        assertEquals(page.selectedOption(), option);
    }

    @org.testng.annotations.DataProvider(name = "options")
    public Object[][] options() {
        return new Object[][] {{"Option 1"}, {"Option 2"}};
    }

    @Test(groups = {"internet"})
    public void elementsCanBeAddedAndRemoved() {
        AddRemoveElementsPage page = new AddRemoveElementsPage().open();

        page.addElements(3);
        assertEquals(page.elementCount(), 3);

        page.removeElement();
        assertEquals(page.elementCount(), 2);
    }
}
