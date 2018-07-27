package com.netcraker.mediation.gradle.task.common.graaljs

import com.netcraker.mediation.gradle.task.common.AbstractBenchmarkTask
import groovy.transform.CompileStatic
import org.gradle.api.internal.tasks.options.Option
import org.gradle.api.tasks.TaskAction

import static com.netcraker.mediation.gradle.util.ArgsUtil.addArg

/**
 * Task for Nashorn JavaScript engine benchmark starting.
 */
@CompileStatic
class GraalJSBenchmarkTask extends AbstractBenchmarkTask {
    public static final String GRAALJS_BENCHMARK_MAIN_CLASS =
            'com.netcracker.mediation.scripting.benchmark.runner.graaljs.GraalJSBenchmark'

    GraalJSBenchmarkTask() {
        super()
        main = GRAALJS_BENCHMARK_MAIN_CLASS
    }
}