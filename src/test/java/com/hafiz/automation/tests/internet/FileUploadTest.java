package com.hafiz.automation.tests.internet;

import static org.testng.Assert.assertEquals;

import java.nio.file.Path;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.FileUploadPage;

public class FileUploadTest extends BaseTest {

    @DataProvider(name = "fixtures")
    public Object[][] fixtures() {
        return new Object[][] {
            {Path.of("src/test/resources/fixtures/upload-sample.txt")},
            {Path.of("src/test/resources/fixtures/upload-data.csv")},
        };
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "fixtures")
    public void uploadingAFileShowsItsNameOnTheResultPage(Path file) {
        String shown = new FileUploadPage().open().chooseFile(file).submit().uploadedFileName();
        assertEquals(shown, file.getFileName().toString());
    }
}
