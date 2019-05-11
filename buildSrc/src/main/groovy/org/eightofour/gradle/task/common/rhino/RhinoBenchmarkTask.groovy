package org.eightofour.gradle.task.common.rhino

import org.eightofour.gradle.task.common.AbstractBenchmarkTask
import groovy.transform.CompileStatic
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

import static org.eightofour.gradle.util.ArgsUtil.addArg

/**
 * Task for Rhino JavaScript engine benchmark starting.
 */
@CompileStatic
class RhinoBenchmarkTask extends AbstractBenchmarkTask {
    public static final String RHINO_BENCHMARK_MAIN_CLASS =
            'org.eightofour.scripting.benchmark.runner.rhino.RhinoBenchmark'

    // options for Google benchmarks
    /**
     * Value for 'optimization-level' parameter.
     */
    private int optimizationLevel = -1

    RhinoBenchmarkTask() {
        super()
        main = RHINO_BENCHMARK_MAIN_CLASS
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