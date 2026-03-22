# AGENTS.md

## First Step

- Read `README.md` before making changes.
- Read `PROJECT.md` when the task affects project scope, modeling, or experimentation direction.
- Follow the project scope and collaboration rules described there.

## Project Focus

- This project is a monolith-first server experiment project.
- Keep the scope minimal and focused on bottleneck reproduction and performance comparison.
- Treat modeling documentation as part of the implementation, not as optional follow-up work.
- Prioritize these flows:
  - concert list/detail lookup
  - seat lookup
  - reservation request

## Implementation Rules

- Avoid adding non-essential product features.
- Prefer changes that help visualize bottlenecks or compare before/after improvements.
- Keep the structure easy to explain and easy to run with the documented Docker-based local setup.
- Update modeling documents when domain structure, request flow, or optimization strategy changes.
- Treat Kafka and Grafana as later-stage experiment candidates, not default bootstrap requirements.

## Collaboration Rules

- Use commit message format: `prefix: summary`
- Allowed prefixes: `feat`, `fix`, `perf`, `refactor`, `docs`, `test`, `chore`, `style`
- Follow GitHub Flow for branch strategy.
- Follow Google Java Style for Java code.

## When Unsure

- Prefer simpler implementations.
- Prefer measurable improvements over broader feature scope.
- Use `README.md` for runtime and entry-point guidance.
- Use `PROJECT.md` as the source of truth for project direction, scope, and MDD-related intent.
