<!-- See CONTRIBUTING.md and CLAUDE.md for the full conventions. -->

## What & why

<!-- One or two lines. Link the issue if there is one. -->

## Checklist

- [ ] `./mvnw -q test-compile` clean
- [ ] The affected group runs green (note any public-demo flakiness)
- [ ] New coverage is a new page class + a test class using `@DataProvider` where it varies
- [ ] No `By` / `WebElement` / `Thread.sleep` in a test class
- [ ] `groups` set; `testng.xml` / `smoke.xml` updated
- [ ] Formatting matches `.editorconfig`

## Notes for the reviewer

<!-- Anything non-obvious: a demo quirk worked around, a deliberate deviation, follow-ups. -->
