import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("plugin.jpa") version "1.2.71"
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
}
allprojects {
    group = "com.haizhi"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_1_8

    repositories {
        maven(url = "http://download.osgeo.org/webdav/geotools/")
        maven(url = "http://maven.aliyun.com/nexus/content/groups/public/")
        maven(url = "http://uk.maven.org/maven2/")
        maven(url = "http://maven.geo-solutions.it/")
        maven(url = "https://repo.boundlessgeo.com/main/")
        maven(url = "http://maven.geomajas.org/")
    }
}

apply(plugin = "java")

dependencies {
    implementation("it.geosolutions:geoserver-manager:1.7.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("io.springfox:springfox-swagger2:2.8.0")
    implementation("io.springfox:springfox-swagger-ui:2.8.0")
    implementation("org.postgresql:postgresql")
    implementation("com.squareup.okhttp3:okhttp:3.5.0")
    implementation("org.xerial:sqlite-jdbc:3.7.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.geotools:gt-geojson:21.2")
    implementation("org.geotools:gt-epsg-hsql:21.2")
    implementation("org.projectlombok:lombok:1.18.4")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    implementation("org.hibernate:hibernate-core")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
