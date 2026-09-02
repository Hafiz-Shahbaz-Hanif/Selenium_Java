package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.AddRemoveElementsPage;

public class AddRemoveElementsTest extends BaseTest {

    @DataProvider(name = "counts")
    public Object[][] counts() {
        return new Object[][] {{1}, {2}, {3}, {4}, {5}, {6}, {8}, {10}};
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "counts")
    public void addingNElementsShowsNDeleteButtons(int n) {
        AddRemoveElementsPage page = new AddRemoveElementsPage().open().addElements(n);
        assertEquals(page.elementCount(), n);
    }

    @Test(groups = {"internet"})
    public void removingElementsDecrementsTheCount() {
        AddRemoveElementsPage page = new AddRemoveElementsPage().open().addElements(3);
        page.removeElement();
        assertEquals(page.elementCount(), 2);
        page.removeElement().removeElement();
        assertEquals(page.elementCount(), 0);
    }
}
