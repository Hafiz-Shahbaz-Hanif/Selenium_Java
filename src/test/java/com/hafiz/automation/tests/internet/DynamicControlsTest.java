package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.DynamicControlsPage;

public class DynamicControlsTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void theInputIsEnabledAndDisabledViaAjax() {
        DynamicControlsPage page = new DynamicControlsPage().open();
        assertFalse(page.isInputEnabled(), "input starts disabled");
        page.clickEnableDisable();
        assertTrue(page.isInputEnabled(), "input becomes enabled");
        page.clickEnableDisable();
        assertFalse(page.isInputEnabled(), "input is disabled again");
    }

    @Test(groups = {"internet"})
    public void theCheckboxIsRemovedAndAddedViaAjax() {
        DynamicControlsPage page = new DynamicControlsPage().open();
        assertTrue(page.isCheckboxPresent(), "checkbox starts present");
        page.clickAddRemove();
        assertFalse(page.isCheckboxPresent(), "checkbox is removed");
        page.clickAddRemove();
        assertTrue(page.isCheckboxPresent(), "checkbox is added back");
    }
}
