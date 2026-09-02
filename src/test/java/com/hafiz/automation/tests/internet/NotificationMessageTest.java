package com.hafiz.automation.tests.internet;

import java.util.Set;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.NotificationMessagePage;

public class NotificationMessageTest extends BaseTest {

    private static final Set<String> EXPECTED = Set.of(
            "Action successful",
            "Action unsuccesful, please try again");

    @DataProvider(name = "attempts")
    public Object[][] attempts() {
        return new Object[][] {{1}, {2}, {3}, {4}, {5}};
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "attempts")
    public void theFlashMessageIsAlwaysOneOfTheKnownStrings(int attempt) {
        String message = new NotificationMessagePage().open().clickHereAndReadMessage();
        assertTrue(EXPECTED.contains(message),
                "attempt " + attempt + " got unexpected message: \"" + message + "\"");
    }
}
