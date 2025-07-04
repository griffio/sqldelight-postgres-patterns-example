plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.flyway)
    application
}

group = "griffio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.sqldelight.jdbc.driver)
    implementation(libs.postgresql.jdbc.driver)
}

sqldelight {
    databases {
        create("Sample") {
            deriveSchemaFromMigrations.set(true)
            migrationOutputDirectory = file("$buildDir/generated/migrations")
            migrationOutputFileFormat = ".sql" // Defaults to .sql
            packageName.set("griffio.queries")
            dialect(libs.sqldelight.postgresql.dialect)
        }
    }
}

tasks {
    ///sqldelight task generateMainSampleMigrations will output your .sqm files as valid SQL
    // in the output directory, with the output format.
    // Create a dependency from compileKotlin where flyway will have the files available on the classpath
    compileKotlin.configure {
        dependsOn("generateMainSampleMigrations")
    }
}

flyway {
    url = "jdbc:postgresql://localhost:5432/pattern-samples"
    user = "postgres"
    password = ""
    locations = arrayOf("filesystem:$buildDir/generated/migrations")
    baselineOnMigrate = true
    baselineVersion = "0"
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(24)
    
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}

application {
    mainClass.set("griffio.MainKt")
}

// https://documentation.red-gate.com/fd/gradle-task-184127407.html
//:( Without this you may see an error like the following: No database found to handle jdbc:...
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(libs.flyway.database.postgresql)
    }
}
