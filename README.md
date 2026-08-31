# Selenium + Java + TestNG — UI Automation Framework

[![CI](https://github.com/Hafiz-Shahbaz-Hanif/Selenium_Java/actions/workflows/ci.yml/badge.svg)](https://github.com/Hafiz-Shahbaz-Hanif/Selenium_Java/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.x-DE322F)
![Maven](https://img.shields.io/badge/Maven-wrapper-C71A36?logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-blue)

A UI automation framework built with **Selenium WebDriver**, **Java 21** and **TestNG**, on the
**Page Object Model** with **PageFactory** `@FindBy` elements, thread-safe parallel execution
and **ExtentReports** reporting with failure screenshots.

| | |
|---|---|
| **Applications under test** | [the-internet](https://the-internet.herokuapp.com) · [ParaBank](https://parabank.parasoft.com/parabank) |
| **Runner** | TestNG (`testng.xml`, `parallel="methods"`) |
| **Design** | Page Object Model + PageFactory (`@FindBy`) |
| **Driver management** | Selenium Manager — **no driver binaries** |
| **Reporting** | ExtentReports (Spark) + TestNG surefire reports + failure screenshots |
| **Build** | Maven (with wrapper — no local Maven needed) |
| **CI** | GitHub Actions |

---

## Highlights

- **Page Object Model + PageFactory.** Every page class declares its elements as
  `@FindBy` fields initialised by `PageFactory` in `BasePage`; tests call
  intent-revealing methods (`loginPage.loginAsValidUser()`), never locators.
- **Thread-safe parallelism.** `DriverFactory` hands out a `ThreadLocal<WebDriver>`,
  so `parallel="methods"` runs cleanly with a fresh browser per test.
- **Two applications.**
  - *the-internet* — form auth, AJAX-rendered elements, dropdowns, dynamic DOM.
  - *ParaBank* — a full journey: register a unique customer, then open a second
    account and verify it appears in the overview.
- **Reporting + triage.** A TestNG `ITestListener` mirrors each test into an
  ExtentReports node and attaches a screenshot on failure.
- **Config as code.** `Configuration` resolves every key in order:
  `-Dkey` system property → environment variable → `config.properties` → default.
- **Grouped suites.** TestNG groups (`smoke`, `internet`, `parabank`) allow
  `mvn test -Dgroups=internet` to skip the ParaBank public demo if it is down.

## Project structure

```
src/test/java/com/hafiz/automation/
├── config/Configuration.java        # layered configuration
├── driver/DriverFactory.java        # ThreadLocal WebDriver (chrome/firefox/remote)
├── pages/
│   ├── BasePage.java                # PageFactory init + element helpers
│   ├── internet/                    # the-internet page objects
│   └── parabank/                    # ParaBank page objects
├── listeners/
│   ├── ExtentManager.java           # ExtentReports singleton
│   └── TestListener.java            # per-test node + failure screenshots
├── base/BaseTest.java               # driver lifecycle (@BeforeMethod/@AfterMethod)
├── utils/TestData.java
└── tests/
    ├── internet/                    # AuthenticationTest, InteractionsTest
    └── parabank/                    # NewCustomerTest
src/test/resources/
├── testng.xml   smoke.xml           # suites
└── config.properties
```

## Running

```bash
./mvnw test                              # full suite (headless)
./mvnw test -Psmoke                       # smoke suite only
./mvnw test -Dgroups=internet             # skip the ParaBank demo
./mvnw test -Dheadless=false -Dbrowser=firefox
```

## Reports

- **ExtentReports:** `target/extent-report/index.html`
- **TestNG:** `target/surefire-reports/`
- **Failure screenshots:** `target/screenshots/`

## CI

`.github/workflows/ci.yml` runs the headless TestNG suite on every push and PR via the
Maven wrapper, and uploads the Extent report, surefire reports and screenshots.

---

## Author

**Hafiz Shahbaz Hanif** — Staff SQA Engineer / Test Automation Architect
[LinkedIn](https://www.linkedin.com/in/hafiz-shahbaz-hanif-70407417a) · [GitHub](https://github.com/Hafiz-Shahbaz-Hanif)

Licensed under the [MIT License](LICENSE).
