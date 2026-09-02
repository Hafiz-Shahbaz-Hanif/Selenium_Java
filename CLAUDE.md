# CLAUDE.md — working agreement for AI agents in this repo

This framework is developed with an **agentic-AI workflow**: Claude Code and the
subagents/skills in `.claude/` draft page objects and tests, triage failures, and
review diffs against the conventions below.

## What this project is

| | |
|---|---|
| Apps under test | [the-internet](https://the-internet.herokuapp.com) · [ParaBank](https://parabank.parasoft.com/parabank) |
| Runner | TestNG (`src/test/resources/*.xml`, `parallel="methods"`) |
| Design | Page Object Model + **PageFactory** (`@FindBy`) |
| Language / build | Java 21, Maven (with wrapper) |
| Driver | Selenium 4 + Selenium Manager — **no driver binaries** |
| Reporting | ExtentReports (Spark) + surefire + failure screenshots |

## Golden rules

1. **Page Object Model, strictly.** Test classes never touch a `By`, a `WebElement`,
   a `WebDriverWait`, or `driver`. They call intent-revealing page methods and assert
   with TestNG `Assert`.
2. **PageFactory only.** Every control is a `private WebElement` (or `List<WebElement>`)
   field with `@FindBy`. Initialised by `PageFactory.initElements` in `BasePage`.
   CSS first; XPath only when there is no stable id/attribute.
3. **Waits live in `BasePage`.** Use `visible`, `clickable`, `click`, `type`, `textOf`,
   `isDisplayed`, `waitForAll`. No `Thread.sleep`. No raw `WebDriverWait` in a page
   unless the wait is genuinely page-specific (then keep it in that page class).
4. **Thread-safe.** `DriverFactory` hands out a `ThreadLocal<WebDriver>`; a fresh
   browser per `@Test` method (`BaseTest`), so `parallel="methods"` is safe.
5. **Data-driven.** Prefer a `@DataProvider` over copy-pasted `@Test` methods. Each
   row is one real test case.
6. **Determinism.** Each test owns its data (ParaBank: a unique username per run via
   `TestData.uniqueUsername`). Tests pass in any order and in parallel.
7. **Groups.** Tag every `@Test` with `groups = {...}`: the site (`internet` /
   `parabank`), plus `smoke` for one happy path per area.
8. **Config via `Configuration`** — `-Dkey` → env var → `config.properties` → default.
   No literal URLs/timeouts in pages or tests.

## Layout

```
src/test/java/com/hafiz/automation/
├── config/Configuration.java
├── driver/DriverFactory.java            ThreadLocal WebDriver
├── pages/
│   ├── BasePage.java                    PageFactory init + helpers
│   ├── internet/                        one class per the-internet page
│   └── parabank/
├── listeners/                           ExtentReports + failure screenshots
├── base/BaseTest.java                   driver lifecycle
├── utils/
└── tests/{internet,parabank}/           one class per feature area
src/test/resources/{testng.xml,smoke.xml,config.properties}
```

## Commands

```bash
./mvnw test                       # full suite
./mvnw test -Psmoke               # smoke groups
./mvnw test -Dgroups=internet     # skip the ParaBank public demo
./mvnw test -Dheadless=false -Dbrowser=firefox
```

Reports: `target/extent-report/index.html`, `target/surefire-reports/`, `target/screenshots/`.

## Definition of done

- `./mvnw -q test-compile` clean
- The affected group runs green (note any public-demo flakiness)
- New coverage is a new page class + a test class using `@DataProvider` where it varies
- No `By` / `WebElement` / `Thread.sleep` in a test class
- `groups` set; `smoke.xml` still meaningful
