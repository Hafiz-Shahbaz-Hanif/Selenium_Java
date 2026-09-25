# Changelog

Format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## 2026-09-25

### Added
- `ExtentReports` and `tests-108` badges to the README badge row (this repo had
  no reporting badge at all, unlike the Allure-based repos).

## 2026-09-24

### Fixed
- README headline test count was stale after the Redirector addition (~106 ->
  the actual ~108, recounted `@Test` methods + `@DataProvider` rows across every
  test class).

## 2026-09-23

### Added
- `RedirectorPage` + test: following the `/redirector` link lands on `/status_codes`
  (~105→~106 tests).

## 2026-09-22

### Added
- Cancel superseded CI runs on the same branch (`concurrency` group in the workflow).

## 2026-09-19

### Added
- Dependabot for `maven` and `github-actions`.

## 2026-09-16

### Added
- This changelog.

## 2026-09-13

### Added
- `RetryAnalyzer` + `RetryTransformer`: a 2-attempt auto-retry scoped only to the
  `parabank` group. the-internet tests never retry.

## 2026-09-11

### Added
- CI: publish TestNG results as a GitHub check via `mikepenz/action-junit-report`.

## 2026-09-08

### Added
- Cancelling a JS prompt is covered (`You entered: null`).

## 2026-09-07

### Added
- Infinite-scroll coverage against `/infinite_scroll`.

## 2026-09-06

### Added
- `.github/ISSUE_TEMPLATE/{bug_report,flaky_test}.md`,
  `.github/pull_request_template.md`.

## 2026-09-05

### Added
- File-upload coverage against `/upload` with 2 fixtures.

## 2026-09-03

### Added
- `CONTRIBUTING.md` and `.editorconfig`.

## 2026-09-02

### Added
- `.claude/` AI-assisted workflow: `failure-triager`, `page-object-author`
  subagents; `new-page-coverage`, `extent-triage` skills.
- `LocalDriverCache` (resolves chromedriver from `~/.cache/selenium`) and
  `pageLoadStrategy=NONE` to avoid Selenium Manager stalls and hanging-load
  pages; a DOM-ready wait in `BasePage.open()`; alert, select and
  multi-element helpers.
- 18 new the-internet page objects (checkboxes, alerts, hovers, slider, tables,
  frames, windows, …) and matching data-driven test suites; ParaBank menu,
  transfer-funds, bill-pay and request-loan pages and suites.
- Split TestNG suites (`internet` / `parabank`), Maven profiles, a
  forked-process time ceiling; CI gates on the-internet, ParaBank job is
  non-blocking.
- README coverage table and driver-resilience notes.

## 2026-08-31

### Added
- Initial scaffold: Selenium 4 + Java + TestNG + Maven wrapper.
- Layered `Configuration`, `ThreadLocal<WebDriver>` `DriverFactory`.
- `BasePage` (PageFactory) and the-internet page objects; ParaBank page
  objects (registration, overview, open account).
- ExtentReports manager and a TestNG listener with failure screenshots.
- the-internet and ParaBank suites, grouped for parallel execution.
- CI running the headless TestNG suite via the Maven wrapper.
- Framework overview and run instructions.
