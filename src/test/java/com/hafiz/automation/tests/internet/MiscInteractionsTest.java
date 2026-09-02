package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.ChallengingDomPage;
import com.hafiz.automation.pages.internet.ContextMenuPage;
import com.hafiz.automation.pages.internet.DisappearingElementsPage;
import com.hafiz.automation.pages.internet.DragAndDropPage;
import com.hafiz.automation.pages.internet.FloatingMenuPage;
import com.hafiz.automation.pages.internet.MultipleWindowsPage;
import com.hafiz.automation.pages.internet.TyposPage;

/** One-off checks for pages that do not warrant a data-driven class of their own. */
public class MiscInteractionsTest extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void contextMenuTriggersAJsAlert() {
        assertEquals(new ContextMenuPage().open().rightClickAndReadAlert(),
                "You selected a context menu");
    }

    @Test(groups = {"internet"})
    public void openingANewWindowExposesItsHeading() {
        assertEquals(new MultipleWindowsPage().open().openNewWindowAndReadHeading(),
                "New Window");
    }

    @Test(groups = {"internet"})
    public void theFloatingMenuStaysVisibleWhileScrolling() {
        assertTrue(new FloatingMenuPage().open().menuStaysVisibleAfterScroll());
    }

    @Test(groups = {"internet"})
    public void theTyposParagraphSettlesToItsCorrectText() {
        assertTrue(new TyposPage().open().settlesToCorrectText(8),
                "the paragraph never rendered without a typo in 10 reloads");
    }

    @Test(groups = {"internet"})
    public void disappearingMenuAlwaysHasTheCoreItems() {
        java.util.List<String> labels = new DisappearingElementsPage().open().menuLabels();
        assertTrue(labels.containsAll(java.util.List.of("Home", "About", "Contact Us")),
                "core menu items missing: " + labels);
    }

    @Test(groups = {"internet"})
    public void theGalleryMenuItemEventuallyAppears() {
        assertTrue(new DisappearingElementsPage().open().galleryEventuallyAppears(8));
    }

    @Test(groups = {"internet"})
    public void challengingDomExposesTenButtonsAndSevenColumns() {
        ChallengingDomPage page = new ChallengingDomPage().open();
        assertEquals(page.buttonCount(), 3);
        assertEquals(page.headerLabels().size(), 7);
        assertEquals(page.rowCount(), 10);
    }

    @Test(groups = {"internet"})
    public void dragAndDropSwapsTheColumnHeaders() {
        DragAndDropPage page = new DragAndDropPage().open();
        assertEquals(page.columnAHeader(), "A");
        page.dragAOntoB();
        assertEquals(page.columnAHeader(), "B");
        assertEquals(page.columnBHeader(), "A");
    }
}
