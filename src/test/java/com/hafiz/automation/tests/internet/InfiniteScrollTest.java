package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.InfiniteScrollPage;

public class InfiniteScrollTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void scrollingToTheBottomAppendsMoreContent() {
        InfiniteScrollPage page = new InfiniteScrollPage().open();
        int before = page.appendedBlockCount();

        page.scrollToBottom().waitForMoreThan(before);

        assertTrue(page.appendedBlockCount() > before, "a new block was appended");
    }

    @Test(groups = {"internet"})
    public void contentKeepsLoadingOnRepeatedScrolls() {
        InfiniteScrollPage page = new InfiniteScrollPage().open();
        int seen = page.appendedBlockCount();

        for (int i = 0; i < 3; i++) {
            page.scrollToBottom().waitForMoreThan(seen);
            int now = page.appendedBlockCount();
            assertTrue(now > seen, "scroll " + (i + 1) + " appended more content");
            seen = now;
        }
    }
}
