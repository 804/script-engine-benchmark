package com.netcraker.mediation.gradle.task.rhino

import com.netcraker.mediation.gradle.task.AbstractBenchmarkTask
import org.gradle.api.internal.tasks.options.Option
import org.gradle.api.tasks.TaskAction

import static com.netcraker.mediation.gradle.util.ArgsUtil.addArg

/**
 * Task for Rhino JavaScript engine benchmark starting.
 */
class RhinoBenchmarkTask extends AbstractBenchmarkTask {
    public static final String RHINO_BENCHMARK_MAIN_CLASS =
            'com.netcracker.mediation.scripting.benchmark.runner.rhino.RhinoBenchmark'

    // options for Google benchmarks
    /**
     * Value for 'optimization-level' parameter.
     */
    private int optimizationLevel = -1

    RhinoBenchmarkTask() {
        super()
        main = RHINO_BENCHMARK_MAIN_CLASS
    }

    int getOptimizationLevel() {
        return optimizationLevel
    }

    @Option(
        option = "optimization-level",
        description = "Option for 'optimization-level' argument setting"
    )
    void setOptimizationLevel(Object optimizationLevel) {
        this.optimizationLevel = optimizationLevel as int
    }

    @TaskAction
    void exec() {
        addArg(this.optimizationLevel, -1, "optimization-level", this)
        super.exec()
    }
}