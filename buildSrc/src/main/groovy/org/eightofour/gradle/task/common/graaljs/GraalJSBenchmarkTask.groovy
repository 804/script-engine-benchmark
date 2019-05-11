package org.eightofour.gradle.task.common.graaljs

import org.eightofour.gradle.task.common.AbstractBenchmarkTask
import groovy.transform.CompileStatic

/**
 * Task for Nashorn JavaScript engine benchmark starting.
 */
@CompileStatic
class GraalJSBenchmarkTask extends AbstractBenchmarkTask {
    public static final String GRAALJS_BENCHMARK_MAIN_CLASS =
            'org.eightofour.scripting.benchmark.runner.graaljs.GraalJSBenchmark'

    GraalJSBenchmarkTask() {
        super()
        main = GRAALJS_BENCHMARK_MAIN_CLASS
    }
}