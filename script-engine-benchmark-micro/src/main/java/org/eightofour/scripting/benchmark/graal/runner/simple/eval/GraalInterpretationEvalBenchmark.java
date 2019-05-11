package org.eightofour.scripting.benchmark.graal.runner.simple.eval;

import org.eightofour.scripting.benchmark.common.cases.impl.common.InterpretationEvalCase;
import org.eightofour.scripting.benchmark.jvm.runner.simple.eval.InterpretationEvalBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Runner for common script interpreting (eval(Reader))
 * benchmark cases with GraalVM.
 */
public class GraalInterpretationEvalBenchmark extends InterpretationEvalBenchmark {
    /**
     * Benchmark case for common script interpreting (eval(Reader))
     * for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends InterpretationEvalBenchmark.Rhino {}

    /**
     * Benchmark case for common script interpreting (eval(Reader))
     * for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends InterpretationEvalBenchmark.Nashorn {}

    /**
     * Benchmark case for common script interpreting (eval(Reader))
     * for Nashorn Java Script engine with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends InterpretationEvalBenchmark.NashornWithOptimisticTypes {}

    /**
     * Benchmark case for common script interpreting (eval(Reader))
     * for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends InterpretationEvalBenchmark.Groovy {}

    /**
     * Benchmark case for common script interpreting (eval(Reader))
     * for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends InterpretationEvalBenchmark.Python {}

    /**
     * Benchmark case for common script interpreting (eval(Reader))
     * for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends InterpretationEvalCase {
        /**
         * Benchmark method for common script interpreting (eval(Reader)).
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error occurred during
         *                         script evaluation
         */
        @Benchmark
        public void evalScript(Blackhole blackhole) throws ScriptException {
            super.evalScript("tpk.js", blackhole);
        }

        @Override
        public ScriptEngine getScriptEngine() {
            return new ScriptEngineManager().getEngineByName("graal.js");
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return GraalInterpretationEvalBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalInterpretationEvalBenchmark().run();
    }
}