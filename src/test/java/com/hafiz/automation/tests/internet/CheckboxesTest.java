package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.CheckboxesPage;

public class CheckboxesTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void thePageHasTwoCheckboxes() {
        assertEquals(new CheckboxesPage().open().count(), 2);
    }

    @Test(groups = {"internet"})
    public void defaultStateIsUncheckedThenChecked() {
        CheckboxesPage page = new CheckboxesPage().open();
        assertFalse(page.isChecked(0), "checkbox 1 starts unchecked");
        assertTrue(page.isChecked(1), "checkbox 2 starts checked");
    }

    @DataProvider(name = "toggles")
    public Object[][] toggles() {
        return new Object[][] {
            {0, true}, {0, false}, {1, true}, {1, false},
        };
    }

    @Test(groups = {"internet"}, dataProvider = "toggles")
    public void settingACheckboxSticks(int index, boolean target) {
        CheckboxesPage page = new CheckboxesPage().open().setChecked(index, target);
        assertEquals(page.isChecked(index), target);
    }
}
