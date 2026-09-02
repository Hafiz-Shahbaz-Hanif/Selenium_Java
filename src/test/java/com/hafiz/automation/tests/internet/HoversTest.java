package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.HoversPage;

public class HoversTest extends BaseTest {

    @DataProvider(name = "avatars")
    public Object[][] avatars() {
        return new Object[][] {{0, "user1"}, {1, "user2"}, {2, "user3"}};
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "avatars")
    public void hoveringAnAvatarRevealsItsCaption(int index, String expectedUser) {
        String caption = new HoversPage().open().captionAfterHover(index);
        assertTrue(caption.contains("name: " + expectedUser),
                "caption was: " + caption);
    }
}
