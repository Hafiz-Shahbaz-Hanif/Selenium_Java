package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.RedirectorPage;

public class RedirectorTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void followingTheLinkRedirectsToStatusCodes() {
        RedirectorPage page = new RedirectorPage().open().followRedirect();
        assertTrue(page.redirectedToStatusCodes());
    }
}
