package org.eightofour.gradle.task.jmh

import groovy.transform.CompileStatic
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

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
    private String resultPath

    /**
     * Masked string for defining package for performed benchmark discovery.
     */
    private String include

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

    @Option(
        option = "include",
        description = "Option for defining package for performed benchmark discovery."
    )
    void setInclude(String include) {
        this.include = include
    }

    @TaskAction
    void exec() {
        configureParameters()
        String path = buildResultPath()
        createDirRecursiveIfAbsent(path)
        args(include, "-rf", "json", "-rff", path)
        super.exec()
    }

    private void configureParameters() {
        resultPath = getFromPropertyIfNull(
            resultPath,
            "result-path",
            "out" + File.separator
        )
        include = getFromPropertyIfNull(
            include,
            "include",
            ".*"
        )
    }

    private Object getFromPropertyIfNull(Object value, String propertyName,
                                         Object defaultValue) {
        return (value ?: project.properties.get(propertyName)) ?: defaultValue
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