package com.netcraker.mediation.gradle.task.jmh

import groovy.transform.CompileStatic
import org.gradle.api.internal.tasks.options.Option
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.Path

/**
 * Task for running JMH benchmarks and saving results as JSON file.
 */
@CompileStatic
class JmhBenchmarkRunnerTask extends JavaExec {
    public static final String JMH_MAIN_CLASS = "org.openjdk.jmh.Main"

    /**
     * File path to benchmark result directory.
     */
    private String resultPath = "out" + File.separator

    /**
     * Masked string for defining package for performed benchmark discovery.
     */
    private String include = ".*"

    JmhBenchmarkRunnerTask() {
        super()
        dependsOn 'compileJava'
        main = JMH_MAIN_CLASS
        group = 'benchmark'
        description = 'Task for JMH benchmarks running'
    }

    @Option(
        option = "result-path",
        description = "Option for file path to benchmark result directory."
    )
    void setResultPath(String resultPath) {
        this.resultPath = resultPath
    }

    void setIncludeString(String includeString) {
        this.include = includeString
    }

    @TaskAction
    void exec() {
        String path = buildResultPath()
        createDirRecursiveIfAbsent(path)
        args(include, "-rf", "json", "-rff", path)
        super.exec()
    }

    private String buildResultPath() {
        return resultPath + "result" + File.separator + "result-" + this.name + ".json"
    }

    private void createDirRecursiveIfAbsent(String path) {
        Path resultDirectoryPath = project.projectDir.toPath().resolve(path).getParent()
        if (!Files.exists(resultDirectoryPath)) {
            Files.createDirectories(resultDirectoryPath)
        }
    }
}