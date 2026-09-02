---
name: extent-triage
description: Turn a target/ run (surefire + ExtentReports + screenshots) into a ranked failure summary for this TestNG framework, grouped by root cause.
---

# Triage a run

## 1. Gather

```bash
./mvnw test            # or the failing -Dtest / -Dgroups subset
```

- `target/surefire-reports/*.txt` — one per class, with the stack trace
- `target/surefire-reports/*.xml` — machine-readable pass/fail/skip + durations
- `target/screenshots/<method>-<ts>.png` — the page at failure (from `TestListener`)
- `target/extent-report/index.html` — the human view

## 2. Failure table

For every `<testcase>` in the surefire XML with a `<failure>` / `<error>`:

| field | source |
|---|---|
| test | `classname` + `name` |
| group | the `@Test(groups=...)` on the method |
| exception | first line of `<failure message=...>` |
| assertion / locator | the stack frame in the framework's own package |
| screenshot | `target/screenshots/<name>-*.png` |
| duration | `time` attribute (flag long ones) |

## 3. Group by cause

Cluster failures with the same exception/message or the same page class:
locator drift (one `@FindBy`), missing wait (`NoSuchElement` / `StaleElement`),
parallel interference (a `static` field), ParaBank data coupling, public-demo outage
(timeouts across many tests, long durations).

## 4. Rank

1. Real product bugs (deterministic wrong assertion value)
2. Framework defects hitting many tests (one page/helper)
3. Single flaky test (passed on rerun)
4. External (ParaBank / the-internet down)

## Output

Ranked clusters → tests affected → cause → fix owner, plus the one command to
reproduce the top item (`./mvnw test -Dtest=Class#method`). Hand fixes to
`failure-triager`.
