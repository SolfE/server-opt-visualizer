# AGENTS.md

## First Step

- Read `README.md` before making changes.
- Follow the project scope and collaboration rules described there.

## Project Focus

- This project is a monolith-first server experiment project.
- Keep the scope minimal and focused on bottleneck reproduction and performance comparison.
- Prioritize these flows:
  - concert list/detail lookup
  - seat lookup
  - reservation request

## Implementation Rules

- Avoid adding non-essential product features.
- Prefer changes that help visualize bottlenecks or compare before/after improvements.
- Keep the structure easy to explain and easy to run locally.

## Collaboration Rules

- Use commit message format: `prefix: summary`
- Allowed prefixes: `feat`, `fix`, `perf`, `refactor`, `docs`, `test`, `chore`, `style`
- Follow GitHub Flow for branch strategy.
- Follow Google Java Style for Java code.

## When Unsure

- Prefer simpler implementations.
- Prefer measurable improvements over broader feature scope.
- Use `README.md` as the source of truth for project direction.
