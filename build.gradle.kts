plugins {
    kotlin("jvm") version "1.6.0"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

repositories {
    mavenCentral()
}

tasks.withType<Test> {

    useJUnitPlatform()
}

tasks {
    test {
        useJUnitPlatform()

        val failedTests = mutableListOf<TestDescriptor>()
        val skippedTests = mutableListOf<TestDescriptor>()

        // See https://github.com/gradle/kotlin-dsl/issues/836
        addTestListener(object : TestListener {
            override fun beforeSuite(suite: TestDescriptor) {}
            override fun beforeTest(testDescriptor: TestDescriptor) {}
            override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                when(result.resultType) {
                    TestResult.ResultType.FAILURE -> failedTests.add(testDescriptor)
                    TestResult.ResultType.SKIPPED -> skippedTests.add(testDescriptor)
                }
            }

            override fun afterSuite(suite: TestDescriptor, result: TestResult) {
                if (suite.parent == null) { // root suite
                    logger.lifecycle("----")
                    logger.lifecycle("Test result: ${result.resultType}")
                    logger.lifecycle("Test summary: ${result.testCount} tests, " +
                            "${result.successfulTestCount} succeeded, " +
                            "${result.failedTestCount} failed, " +
                            "${result.skippedTestCount} skipped")
                    if (failedTests.isNotEmpty()) {
                        logger.lifecycle("\tFailed Tests:")
                        failedTests.forEach {
                            parent?.let { parent ->
                                logger.lifecycle("\t\t${parent.name} - ${it.name}")
                            } ?: logger.lifecycle("\t\t${it.name}")
                        }
                    }

                    if (skippedTests.isNotEmpty()) {
                        logger.lifecycle("\tSkipped Tests:")
                        skippedTests.forEach {
                            parent?.let { parent ->
                                logger.lifecycle("\t\t${parent.name} - ${it.name}")
                            } ?: logger.lifecycle("\t\t${it.name}")
                        }
                    }
                }
            }
        })
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
