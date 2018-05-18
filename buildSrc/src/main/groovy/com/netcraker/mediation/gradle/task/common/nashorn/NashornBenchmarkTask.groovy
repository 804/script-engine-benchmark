package com.netcraker.mediation.gradle.task.common.nashorn

import com.netcraker.mediation.gradle.task.common.AbstractBenchmarkTask
import groovy.transform.CompileStatic
import org.gradle.api.internal.tasks.options.Option
import org.gradle.api.tasks.TaskAction

import static com.netcraker.mediation.gradle.util.ArgsUtil.addArg

/**
 * Task for Nashorn JavaScript engine benchmark starting.
 */
@CompileStatic
class NashornBenchmarkTask extends AbstractBenchmarkTask {
    public static final String NASHORN_BENCHMARK_MAIN_CLASS =
            'com.netcracker.mediation.scripting.benchmark.runner.nashorn.NashornBenchmark'

    // options for Google benchmarks
    /**
     * Flag for 'optimistic-types' parameter.
     */
    private boolean optimisticTypes

    NashornBenchmarkTask() {
        super()
        main = NASHORN_BENCHMARK_MAIN_CLASS
    }
    boolean getOptimisticTypes() {
        return optimisticTypes
    }

    @Option(
        option = "optimistic-types",
        description = "Option for 'optimistic-types' argument setting"
    )
    void setOptimisticTypes(boolean optimisticTypes) {
        this.optimisticTypes = optimisticTypes
    }

    @TaskAction
    void exec() {
        addArg(this.optimisticTypes, "optimistic-types", this)
        super.exec()
    }
}