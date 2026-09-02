---
name: new-page-coverage
description: Add a new Page Object and TestNG test class for a the-internet or ParaBank page to this Selenium + PageFactory framework, data-driven where the behaviour varies.
---

# Add coverage for a page

## 1. Page Object

- `pages/internet/<Name>Page.java` (or `pages/parabank/`), extends `BasePage`.
- Controls: `private WebElement field;` with `@FindBy(css = "...")`. CSS first.
- `open()` → `open(Configuration.theInternetBaseUrl() + "/<path>")` then `visible(landmark)`.
- Actions return `this` / next page; queries return plain values.
- Only `BasePage` helpers for interaction. No `Thread.sleep`, no raw `By`.

## 2. Test class

```java
public class <Name>Test extends BaseTest {

    @Test(groups = {"smoke", "internet"})
    public void happyPath() { ... }

    @DataProvider(name = "cases")
    public Object[][] cases() { return new Object[][] {{...}, {...}}; }

    @Test(groups = {"internet"}, dataProvider = "cases")
    public void variation(String input, String expected) { ... }
}
```

- Extends `BaseTest` (thread-local driver per method).
- `groups`: site + `smoke` for one happy path.
- Assert with `org.testng.Assert`. Never touch a `WebElement` here.

## 3. Wire and verify

- `testng.xml` is package-scoped, so a new class in `tests/internet/` runs automatically.
  Add it to `smoke.xml` only if it has a `smoke` test.
- ```bash
  ./mvnw -q test-compile
  ./mvnw test -Dtest=<Name>Test
  ```

## Checklist

- [ ] `@FindBy` only in the page class; no `By` / `WebElement` in the test
- [ ] Variations are `@DataProvider` rows, not duplicated methods
- [ ] `groups` set; compiles; the new class runs green
- [ ] ParaBank test uses `TestData.uniqueUsername` and cleans up after itself
