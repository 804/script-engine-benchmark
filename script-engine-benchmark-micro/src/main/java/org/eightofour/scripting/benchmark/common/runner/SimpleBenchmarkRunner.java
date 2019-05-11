package org.eightofour.scripting.benchmark.common.runner;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Abstract class containing method for simple JMH benchmarks running
 * and saving results to the JSON file.
 * Benchmarks looking up is performed only inside of current class.
 */
public abstract class SimpleBenchmarkRunner {
    /**
     * Method for current class benchmarks running.
     *
     * @throws RunnerException if any exception will be occurred
     *                         during benchmark execution
     */
    protected void run() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*\\." + getClass().getSimpleName() + "\\.Nashorn\\..*")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opt).run();
    }
}