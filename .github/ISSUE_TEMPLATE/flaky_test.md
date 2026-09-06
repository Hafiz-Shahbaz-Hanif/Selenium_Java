---
name: Flaky test
about: A test that passes on rerun without a code change
title: "[flaky] "
labels: flaky
---

## Which test

<!-- Class#method (name the Examples row if it is a @DataProvider) -->

## Evidence it is flaky

- [ ] Passed on rerun with no code change
- [ ] Fails only with `parallel="methods"` / only in CI / only headless
- Rough failure rate: __ / 10 runs

## Failure detail

<!-- The assertion or WebDriver exception, and the page/step it happened on. -->

## Suspected cause

<!-- missing BasePage wait, the-internet load-event quirk, ParaBank demo reset,
     ThreadLocal leak, stale element, animation. -->

## Notes

Link the CI run and the screenshot. The `failure-triager` agent in `.claude/` is
built for this.
