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
- **Resilient driver + navigation.** A cached chromedriver is used when present so a
  parallel run never stalls on Selenium Manager first-resolution; `pageLoadStrategy=NONE`
  plus a DOM-ready wait in `BasePage.open()` keeps navigation fast even when a target's
  `load` event never fires (the-internet embeds slow third-party analytics).
- **Two applications.**
  - *the-internet* — form auth, AJAX-rendered elements, dropdowns, dynamic DOM.
  - *ParaBank* — a full journey: register a unique customer, then open a second
    account and verify it appears in the overview.
- **Reporting + triage.** A TestNG `ITestListener` mirrors each test into an
  ExtentReports node and attaches a screenshot on failure.
- **Config as code.** `Configuration` resolves every key in order:
  `-Dkey` system property → environment variable → `config.properties` → default.
- **Grouped, profile-driven suites.** `./mvnw test -Pinternet` / `-Pparabank` / `-Psmoke`,
  or `-Dgroups=internet` to skip the ParaBank public demo if it is down.
- **Data-driven at scale.** ~102 `@Test` methods, most fed by a `@DataProvider`
  (8 add-remove counts, 10 key presses, 6 slider positions, 4 status codes, 4 table
  sorts, 2 file-upload fixtures, …).
- **Developed with an agentic-AI workflow.** `CLAUDE.md` plus `.claude/` subagents
  (`failure-triager`, `page-object-author`) and skills (`new-page-coverage`, `extent-triage`).

## Coverage — ~102 `@Test` methods

| Area | Tests | Highlights |
|---|---|---|
| the-internet — auth | 11 | valid login, 7-row bad-credentials data set, logout |
| the-internet — forms & inputs | 35 | checkboxes, dropdown, number input, 10 key presses, JS alert / confirm / prompt, file upload (2 fixtures) |
| the-internet — dynamic DOM | 13 | dynamic loading (1 & 2), AJAX enable/disable & add/remove, notification message, status codes |
| the-internet — interactions | 26 | hovers, horizontal slider, sortable tables, drag & drop, context menu, iframe editor, multiple windows |
| the-internet — resilience | 8 | disappearing elements, typos-that-settle, floating menu, forgot-password |
| ParaBank | 11 | register → open account (CHECKING/SAVINGS), transfer funds, bill pay, request loan, logout |

## Project structure

```
CLAUDE.md  .claude/{agents,skills}       # agentic-AI workflow config (repo root)
src/test/java/com/hafiz/automation/
├── config/Configuration.java           # layered configuration
├── driver/DriverFactory.java           # ThreadLocal WebDriver (chrome/firefox/remote)
├── pages/
│   ├── BasePage.java                   # PageFactory init + element/alert/select helpers
│   ├── internet/                       # one class per the-internet page
│   └── parabank/                       # register, overview, open account, transfer, bill pay, loan
├── listeners/                          # ExtentReports singleton + failure-screenshot listener
├── base/BaseTest.java                  # driver lifecycle (@BeforeMethod/@AfterMethod)
├── utils/TestData.java
└── tests/{internet,parabank}/          # one class per feature area, @DataProvider-driven
src/test/resources/
├── testng.xml  internet.xml  parabank.xml  smoke.xml
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
