# SupplyFlow contributor guidance

## Project overview

- SupplyFlow is a Java 21 Spring Boot application built with Maven.
- The base Java package is `ir.mehdi.supplyflow`.
- The application entry point is `src/main/java/ir/mehdi/supplyflow/SupplyFlowApplication.java`.
- Persistence uses Spring Data JPA and PostgreSQL. Database schema changes are managed with Liquibase under `src/main/resources/db/changelog`.

## Package and code organization

- Put production Java code under `src/main/java/ir/mehdi/supplyflow` and tests under the matching package in `src/test/java`.
- Organize code primarily by domain/feature. Existing examples are `catalog` and `inventory`.
- Inside a feature, follow the existing subpackage pattern where appropriate: `controller`, `service`, `repository`, `entity`, `dto`, `mapper`, `exception`, and `enums`.
- When adding a new feature, create its package directly under `ir.mehdi.supplyflow` (for example, `ir.mehdi.supplyflow.order`) and put its related classes in that feature's subpackages. Do not create a new top-level Java package alongside `ir.mehdi.supplyflow`.
- Shared application-wide exceptions and error message constants currently live in `ir.mehdi.supplyflow.exception`.
- Keep API DTOs separate from JPA entities; controllers should use the existing service and DTO patterns.

## Implementation conventions

- Prefer the established Spring patterns and nearby code as the source of truth before introducing a new library or architecture.
- Use constructor injection; Lombok's `@RequiredArgsConstructor` is already used in services.
- Keep transaction boundaries on service methods where database operations need to be atomic, following the existing `@Transactional` usage.
- For persistent schema changes, add or update a Liquibase changelog and include it from `db.changelog-master.xml`; do not rely on automatic schema generation.
- Preserve existing error-code and exception handling conventions when adding API errors.
- Use descriptive names and keep each feature's implementation within its feature package unless it is genuinely shared.

## Build commands

- Build and run the test suite with `./mvnw test` (Windows: `mvnw.cmd test`).
- Package the application with `./mvnw package` (Windows: `mvnw.cmd package`).

## Change hygiene

- Keep changes focused on the requested behavior.
- Do not commit generated output from `target/`.
- Update or add tests when behavior changes, using the existing test package structure.
