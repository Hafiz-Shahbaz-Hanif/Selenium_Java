package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /drag_and_drop (HTML5 drag; needs a JS-based drag helper). */
public class DragAndDropPage extends BasePage {

    private static final String DND_HELPER = """
        function createEvent(typeOfEvent) {
          var e = document.createEvent('CustomEvent');
          e.initCustomEvent(typeOfEvent, true, true, null);
          e.dataTransfer = { data: {}, setData: function(k,v){this.data[k]=v;},
                             getData: function(k){return this.data[k];} };
          return e;
        }
        function dispatchEvent(el, t, e) {
          if (el.dispatchEvent) el.dispatchEvent(e);
          else if (el.fireEvent) el.fireEvent('on'+t, e);
        }
        var s = arguments[0], d = arguments[1];
        var dragStart = createEvent('dragstart'); dispatchEvent(s, 'dragstart', dragStart);
        var drop = createEvent('drop'); drop.dataTransfer = dragStart.dataTransfer;
        dispatchEvent(d, 'drop', drop);
        var dragEnd = createEvent('dragend'); dragEnd.dataTransfer = dragStart.dataTransfer;
        dispatchEvent(s, 'dragend', dragEnd);
        """;

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    public DragAndDropPage open() {
        open(Configuration.theInternetBaseUrl() + "/drag_and_drop");
        visible(columnA);
        return this;
    }

    public String columnAHeader() {
        return columnA.findElement(org.openqa.selenium.By.tagName("header")).getText().trim();
    }

    public String columnBHeader() {
        return columnB.findElement(org.openqa.selenium.By.tagName("header")).getText().trim();
    }

    public DragAndDropPage dragAOntoB() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(DND_HELPER, columnA, columnB);
        return this;
    }
}
