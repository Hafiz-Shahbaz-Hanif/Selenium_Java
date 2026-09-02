package com.hafiz.automation.pages.internet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /tables (table #table1). */
public class SortableDataTablesPage extends BasePage {

    @FindBy(css = "#table1 thead th")
    private List<WebElement> headers;

    @FindBy(css = "#table1 tbody tr")
    private List<WebElement> rows;

    public SortableDataTablesPage open() {
        open(Configuration.theInternetBaseUrl() + "/tables");
        waitForAll(headers);
        return this;
    }

    public SortableDataTablesPage sortBy(String columnName) {
        headers.stream()
                .filter(h -> h.getText().trim().equalsIgnoreCase(columnName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No column " + columnName))
                .click();
        return this;
    }

    public List<String> columnValues(int columnIndex) {
        return rows.stream()
                .map(r -> r.findElements(By.tagName("td")).get(columnIndex).getText().trim())
                .toList();
    }
}
