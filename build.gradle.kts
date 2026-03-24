plugins {
  java
  kotlin("jvm") version "2.2.21"
  kotlin("plugin.spring") version "2.2.21"
  kotlin("plugin.jpa") version "2.2.21"
  checkstyle
  id("org.springframework.boot") version "4.0.4"
  id("io.spring.dependency-management") version "1.1.7"
  id("com.diffplug.spotless") version "7.2.1"
}

group = "com.example.serveroptvisualizer"

version = "0.0.1-SNAPSHOT"

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

repositories { mavenCentral() }

checkstyle {
  toolVersion = "10.26.1"
  configDirectory = file("config/checkstyle")
}

spotless {
  java {
    target("src/*/java/**/*.java")
    googleJavaFormat("1.28.0")
    removeUnusedImports()
    endWithNewline()
  }
  kotlin {
    target("src/*/kotlin/**/*.kt")
    ktfmt("0.58").googleStyle()
  }
  kotlinGradle {
    target("*.gradle.kts")
    ktfmt("0.58").googleStyle()
  }
  format("misc") {
    target("*.md", ".gitignore", ".gitattributes", ".env.example")
    trimTrailingWhitespace()
    endWithNewline()
  }
}

dependencies {
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.springframework.boot:spring-boot-starter-flyway")
  implementation("org.flywaydb:flyway-core")
  implementation("org.flywaydb:flyway-mysql")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-webmvc")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  runtimeOnly("com.mysql:mysql-connector-j")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testRuntimeOnly("com.h2database:h2")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
  }
}

tasks.withType<Test> { useJUnitPlatform() }

tasks.named("check") { dependsOn("spotlessCheck") }

tasks.withType<Checkstyle>().configureEach {
  reports {
    xml.required = false
    html.required = true
  }
}
