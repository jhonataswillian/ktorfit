plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    // Suporte a datas modernas do Kotlin no Banco de Dados
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.58.0")

    // O Motor do Banco de Dados (H2 - Versão 2026 atualizada)
    implementation("com.h2database:h2:2.3.232")

    // Gerenciador de Conexões de Alta Performance
    implementation("com.zaxxer:HikariCP:6.0.0")

    // Logback (Geralmente já vem, mas garante logs SQL visíveis)
    implementation("ch.qos.logback:logback-classic:1.5.16")
}
