plugins {
  java
  checkstyle
  id("org.springframework.boot") version "4.0.4"
  id("io.spring.dependency-management") version "1.1.7"
  id("com.diffplug.spotless") version "7.2.1"
}

group = "com.example.serveroptvisualizer"

version = "0.0.1-SNAPSHOT"

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

configurations { compileOnly { extendsFrom(configurations.annotationProcessor.get()) } }

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
  implementation("org.flywaydb:flyway-core")
  implementation("org.flywaydb:flyway-mysql")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-webmvc")
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
  runtimeOnly("com.mysql:mysql-connector-j")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
  testRuntimeOnly("com.h2database:h2")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> { useJUnitPlatform() }

tasks.named("check") { dependsOn("spotlessCheck") }

tasks.withType<Checkstyle>().configureEach {
  reports {
    xml.required = false
    html.required = true
  }
}
