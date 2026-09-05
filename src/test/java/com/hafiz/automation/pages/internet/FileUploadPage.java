package com.hafiz.automation.pages.internet;

import java.nio.file.Path;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /upload. */
public class FileUploadPage extends BasePage {

    @FindBy(id = "file-upload")
    private WebElement fileInput;

    @FindBy(id = "file-submit")
    private WebElement submitButton;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFileName;

    public FileUploadPage open() {
        open(Configuration.theInternetBaseUrl() + "/upload");
        visible(fileInput);
        return this;
    }

    public FileUploadPage chooseFile(Path file) {
        visible(fileInput).sendKeys(file.toAbsolutePath().toString());
        return this;
    }

    public FileUploadPage submit() {
        click(submitButton);
        return this;
    }

    public String uploadedFileName() {
        return textOf(uploadedFileName);
    }
}
