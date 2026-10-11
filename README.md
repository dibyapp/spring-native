# Spring Boot 3 Native Image and Kubernetes: Historical Learning Experiment

A historical **Spring Boot 3.0.0 / Java 17** study project with GraalVM build configuration and Kubernetes manifests. Use it to inspect early Spring AOT/native-image conventions and identify the difference between build configuration and a working application.

**Current state:** the controller and service are commented out, and the `Customer` record is not a valid managed JPA entity. The project does not currently supply the `/api/customers` HTTP service described in its original tutorial. The manifests are historical examples, not a verified deployment.

## Attribution and license scope

The original README credits [nyakaz73/spring-native](https://github.com/nyakaz73/spring-native). That attribution is preserved. The new MIT license covers dibyapp's original additions only; inherited source and tutorial material retain their upstream terms. See [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md). The original tutorial remains in Git history.

## What is in the source?

| File | What it shows |
| --- | --- |
| [pom.xml](pom.xml) | Boot 3.0.0, Java 17, AOT/native plugins, and a historical buildpack configuration |
| [SpringNativeApplication.java](src/main/java/com/stackdev/springnative/SpringNativeApplication.java) | Boot entry point |
| [Customer.java](src/main/java/com/stackdev/springnative/models/Customer.java) | Record with Spring Data `@Id`, not a JPA `@Entity` |
| [CustomerRepository.java](src/main/java/com/stackdev/springnative/repositories/CustomerRepository.java) | JPA repository expecting a managed entity |
| [CustomerController.java](src/main/java/com/stackdev/springnative/controllers/CustomerController.java) | Commented HTTP example |
| [CustomerService.java](src/main/java/com/stackdev/springnative/services/CustomerService.java) | Commented reactive example |
| [k8s](k8s) | Historical Kubernetes manifests |

## Start by inspecting the build

```sh
git clone https://github.com/dibyapp/spring-native.git
cd spring-native
```

Read the POM and source map before running anything. Java 17 is the configured baseline. For native compilation, inspect the project's historical GraalVM requirements and the native C/C++ toolchain requirements for your OS.

The root Maven wrapper is present. Duplicate Lombok and Boot-plugin declarations have been merged while preserving the buildpack, AOT, and packaging configuration. `sh ./mvnw -DskipTests compile` on macOS/Linux or `.\mvnw.cmd -DskipTests compile` on Windows is a source-compilation check; it is not proof of JPA startup, HTTP service availability, or a native binary.

The database settings now read environment variables. Use [DATABASE.md](docs/DATABASE.md) only with a disposable local database. Fix the application model and choose either JPA with a proper entity or an appropriate reactive persistence stack before expecting startup. A database credential does not fix a missing managed entity.

## AOT is different from JIT

GraalVM Native Image compiles an application **ahead of time** into a platform-specific executable. JIT compilation happens during JVM execution. Spring's AOT step generates assets needed by the native build; it is not a replacement name for JIT.

Spring Boot 3 integrated native-image support and superseded the experimental Spring Native project. The repository name is historical. See the [official migration explanation](https://spring.io/blog/2023/02/23/from-spring-native-to-spring-boot-3/) before following older Spring Native commands.

## Useful next contributions

1. Select and implement one coherent persistence model.
2. Add a real local endpoint and a synthetic database fixture.
3. Verify the historical plugin lifecycle and prove JVM runtime behavior.
4. Verify a native build and record reproducible toolchain details.
5. Review image names, secrets handling, probes, and manifests before any Kubernetes deployment.

No Kubernetes resources are deployed by these documentation changes. No startup-time, memory, or production-readiness benchmark is claimed. For a new application, start with a supported configuration from [Spring Initializr](https://start.spring.io).

## What has been checked?

The JVM source compilation only check passed locally. [Validation scope](docs/VALIDATION.md) · [CI](https://github.com/dibyapp/spring-native/actions/workflows/starter-check.yml). This is a limited check, not certification of the whole repository.

## Continue learning

| What you want to understand | Repository |
| --- | --- |
| IoC, dependency injection, bean lifecycle, AOP | [Spring Core examples](https://github.com/dibyapp/spring-core-all-concepts-tutorial) |
| Controllers, JSP forms, validation, CRUD, PRG | [Spring MVC examples](https://github.com/dibyapp/spring-mvc-all-concepts-tutorial) |
| Requests, sessions, cookies, dispatching, JDBC | [Java Servlet examples](https://github.com/dibyapp/java-servlets-all-concepts) |
| Spring Boot 3.1 JPA/MySQL native-image demo | [Spring Boot GraalVM demo](https://github.com/dibyapp/spring-boot-graal-demo) |
| Explicit native Maven profile experiment | [GraalVM application](https://github.com/dibyapp/graalvm-app) |
| Historical Boot 3.0 AOT/Kubernetes experiment | [Spring native study project](https://github.com/dibyapp/spring-native) |

## Contribute or report a problem

Found a broken example? Include its folder, Java/tool versions, a minimal reproduction, and a sanitized error. See [CONTRIBUTING.md](CONTRIBUTING.md). A small fix with a repeatable before/after result is useful; unverified performance claims are not.

Maintained by [dibyapp](https://github.com/dibyapp). [MIT](LICENSE) applies to the author's original contributions; inherited material and bundled third-party files retain their own terms. Read [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md). If an example helped you understand a concept, a star or a link to that specific example helps other learners find it.
