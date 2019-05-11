package org.eightofour.gradle.task.common

import groovy.transform.CompileStatic
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.options.Option

import static org.eightofour.gradle.util.ArgsUtil.addArg

/**
 * Abstract task for JavaScript engine benchmark starting.
 */
@CompileStatic
abstract class AbstractBenchmarkTask extends JavaExec {
    // common options
    /**
     * File path to JavaScript benchmarks.
     */
    private String benchmarkJsPath
    /**
     * File path to benchmark result directory.
     */
    private String resultPath

    // options for google benchmarks
    /**
     * Iteration count.
     */
    private int iterations

    //options for statistic benchmarks
    /**
     * Flag for warming up performing before main test.
     */
    private boolean useWarmUp
    /**
     * Warming up iteration count.
     */
    private int warmUpIterations

    AbstractBenchmarkTask() {
        super()
        dependsOn 'compileJava'
        group = 'benchmark'
    }

    @Option(
        option = "result-path",
        description = "Option for setting file path to benchmark result directory (common)"
    )
    void setResultPath(String resultPath) {
        this.resultPath = resultPath
    }

    @Option(
        option = "benchmark-js-path",
        description = "Option for setting file path to JavaScript benchmarks (common)"
    )
    void setBenchmarkJsPath(String benchmarkJsPath) {
        this.benchmarkJsPath = benchmarkJsPath
    }

    @Option(
        option = "iterations",
        description = "Option for iteration count setting (Google)"
    )
    void setIterations(Object iterations) {
        this.iterations = iterations as int
    }

    @Option(
        option = "use-warm-up",
        description = "Option for warming up performing before main test (statistic)"
    )
    void setUseWarmUp(boolean useWarmUp) {
        this.useWarmUp = useWarmUp
    }

    @Option(
        option = "warm-up-iterations",
        description = "Option for warming up iteration count setting (statistic)"
    )
    void setWarmUpIterations(Object warmUpIterations) {
        this.warmUpIterations = warmUpIterations as int
    }

    void exec() {
        commonArgs()
        statisticArgs()
        googleArgs()
        super.exec()
    }

    private void commonArgs() {
        addArg(this.benchmarkJsPath, "benchmark-js-path", this)
        addArg(this.resultPath, "result-path", this)
    }

    private void statisticArgs() {
        addArg(this.useWarmUp, "use-warm-up", this)
        addArg(this.warmUpIterations, 0, "warm-up-iterations", this)
    }

    private googleArgs() {
        addArg(this.iterations, 0, "iterations", this)
    }
}