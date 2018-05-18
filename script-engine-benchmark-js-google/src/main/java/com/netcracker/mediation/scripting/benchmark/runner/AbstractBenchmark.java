package com.netcracker.mediation.scripting.benchmark.runner;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * Abstract class for JavaScript engine Google benchmarks.
 */
public abstract class AbstractBenchmark {
    private static final Integer DEFAULT_ITERATIONS = 500;
    private static final String ITER_ARG_STR = "iterations";
    private static final String BENCHMARK_JS_PATH_ARG_STR = "benchmark-js-path";
    private static final String RESULT_PATH_ARG_STR = "result-path";

    protected String resultPath = "out/";
    protected String benchmarkJsPath = "src/main/js/";

    /**
     * Method for benchmark running.
     *
     * @param args - arguments for benchmark run
     * @throws Exception in case of error occurring
     *                   during script execution
     */
    public void run(String[] args) throws Exception {
        Map<String, String> argsMap = parseArgs(args);
        int iterations = getIterations(argsMap);
        init(argsMap);
        System.out.println(
            "iterations=" + iterations +
                ", benchmark-js-path=" + benchmarkJsPath +
                ", result-path=" + resultPath
        );
        System.out.println("Start: " + Instant.now());
        runTests(iterations);
        System.out.println("Finish: " + Instant.now());
    }

    private int getIterations(Map<String, String> argsMap) {
        String iterations = argsMap.get(ITER_ARG_STR);
        return iterations != null ? Integer.parseInt(iterations) : DEFAULT_ITERATIONS;
    }

    protected void init(Map<String, String> argsMap) {
        String benchmarkJsPathArg = argsMap.get(BENCHMARK_JS_PATH_ARG_STR);
        String resultPathArg = argsMap.get(RESULT_PATH_ARG_STR);
        if (benchmarkJsPathArg != null) this.benchmarkJsPath = benchmarkJsPathArg;
        if (resultPathArg != null) this.resultPath = resultPathArg;
    }

    private Map<String, String> parseArgs(String[] args) {
        return stream(args).map(arg -> arg.split("="))
                .filter(pair -> pair.length == 2)
                .collect(
                    Collectors.toMap(
                        (String[] pair) -> pair[0],
                        (String[] pair) -> pair[1]
                    )
                );
    }

    /**
     * Method with engine-based main logic of benchmark script executing.
     *
     * @param iterations - benchmark iteration count
     * @throws Exception in case of error occurring
     *                   during script execution
     */
    protected abstract void runTests(int iterations) throws Exception;
}