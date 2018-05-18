package com.netcracker.mediation.scripting.benchmark.runner;

import java.time.Instant;

/**
 * Abstract class for JavaScript engine statistic benchmarks.
 */
public abstract class AbstractBenchmark {
    protected String benchmarkJsPath = "src/main/js/";
    protected String resultPath = "out/";
    protected Integer warmUpIterations = 5;
    private boolean useWarmUp;

    /**
     * Method for benchmark running.
     *
     * @param args - arguments for benchmark run
     * @throws Exception in case of error occurring
     *                   during script execution
     */
    public void run(String[] args) throws Exception {
        parseArguments(args);
        System.out.println(
            "use-warm-up=" + useWarmUp +
                ", warm-up-iterations=" + warmUpIterations +
                ", benchmark-js-path=" + benchmarkJsPath +
                ", result-path=" + resultPath
        );
        System.out.println("Start: " + Instant.now());
        if (useWarmUp) {
            warmUp();
        }
        runTests();
        System.out.println("Finish: " + Instant.now());
    }

    private void parseArguments(String[] args) {
        for (String arg : args) {
            String[] pair = arg.split("=");
            if (pair.length == 2) {
                switch (pair[0]) {
                    case "use-warm-up":
                        useWarmUp = Boolean.valueOf(pair[1]);
                        break;
                    case "warm-up-iterations":
                        warmUpIterations = Integer.parseInt(pair[1]);
                        break;
                    case "benchmark-js-path":
                        benchmarkJsPath = pair[1];
                        break;
                    case "result-path":
                        resultPath = pair[1];
                        break;
                }
            }
        }
    }

    /**
     * Method for benchmark warming up performing.
     *
     * @throws Exception in case of error occurring
     *                   during script execution
     */
    protected abstract void warmUp() throws Exception ;

    /**
     * Method for benchmark main test performing.
     *
     * @throws Exception in case of error occurring
     *                   during script execution
     */
    protected abstract void runTests() throws Exception ;
}