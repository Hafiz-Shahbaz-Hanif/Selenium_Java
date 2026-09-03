# Contributing

Thanks for looking at this project. It is a portfolio framework, but it is built
to real standards and PRs are welcome.

## Ground rules

The conventions in [`CLAUDE.md`](CLAUDE.md) are the contract — read it first. In
short:

- **Page Object Model, strictly.** Test classes never touch a `By`, `WebElement`,
  `WebDriverWait` or `driver`. They call page methods and assert with TestNG
  `Assert`.
- **PageFactory only** — every control is a `@FindBy private WebElement` field.
  CSS first; XPath only when there is no stable id/attribute.
- **Waits live in `BasePage`** (`visible`, `clickable`, `click`, `type`, …).
  No `Thread.sleep`.
- **Data-driven** — prefer a `@DataProvider` over copy-pasted `@Test` methods.
- **Thread-safe** — `DriverFactory` is `ThreadLocal`; a fresh browser per
  `@Test`, so `parallel="methods"` is safe.

## Getting set up

```bash
./mvnw -q test-compile            # no Android/browser SDK needed for this
./mvnw test -Psmoke               # needs Chrome; Selenium Manager fetches the driver
```

## Adding coverage

1. New page → a class under `pages/internet/` or `pages/parabank/` extending
   `BasePage`, controls as `@FindBy` fields.
2. New test → a class under `tests/<area>/` extending `BaseTest`; use a
   `@DataProvider` where the behaviour varies. Every `@Test` gets
   `groups = {...}`.
3. Register the class in `src/test/resources/testng.xml` (and `smoke.xml` if it
   has a `smoke` test).

## Before you open a PR

```bash
./mvnw -q test-compile
./mvnw test -Dgroups=<affected>    # note any public-demo flakiness in the PR
```

- [ ] `test-compile` clean
- [ ] No `By` / `WebElement` / `Thread.sleep` in a test class
- [ ] `groups` set; `testng.xml` / `smoke.xml` updated
- [ ] Formatting matches [`.editorconfig`](.editorconfig)
- [ ] Commit messages are conventional (`feat(pages): …`, `test(internet): …`, `docs: …`)

## AI-assisted workflow

`.claude/` contains the subagents and skills used to develop this repo
(`failure-triager`, `page-object-author`, and the `new-page-coverage` /
`extent-triage` skills). They encode the same rules as this document.
