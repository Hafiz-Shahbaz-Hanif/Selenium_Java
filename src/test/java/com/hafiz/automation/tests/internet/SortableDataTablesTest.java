package com.hafiz.automation.tests.internet;

import java.util.List;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.automation.base.BaseTest;
import com.hafiz.automation.pages.internet.SortableDataTablesPage;

public class SortableDataTablesTest extends BaseTest {

    @DataProvider(name = "columns")
    public Object[][] columns() {
        return new Object[][] {
            {"Last Name", 0}, {"First Name", 1}, {"Email", 2}, {"Web Site", 4},
        };
    }

    @Test(groups = {"smoke", "internet"}, dataProvider = "columns")
    public void sortingByAColumnOrdersTheRowsAscending(String column, int columnIndex) {
        List<String> values = new SortableDataTablesPage()
                .open()
                .sortBy(column)
                .columnValues(columnIndex);

        List<String> sorted = values.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
        assertEquals(values, sorted, "column \"" + column + "\" is not sorted ascending");
    }
}
