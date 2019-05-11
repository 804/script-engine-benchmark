package org.eightofour.scripting.benchmark.graal.runner.simple.eval;

import org.eightofour.scripting.benchmark.common.cases.impl.common.CompiledEvalCase;
import org.eightofour.scripting.benchmark.jvm.runner.simple.eval.CompiledEvalBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Runner for common compiled script evaluation (eval())
 * benchmark cases with GraalVM.
 */
public class GraalCompiledEvalBenchmark extends CompiledEvalBenchmark {
    /**
     * Benchmark case for common compiled script evaluation (eval())
     * for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompiledEvalBenchmark.Rhino {}

    /**
     * Benchmark case for common compiled script evaluation (eval())
     * for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompiledEvalBenchmark.Nashorn {}

    /**
     * Benchmark case for common compiled script evaluation (eval())
     * for Nashorn Java Script engine with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes extends CompiledEvalBenchmark.NashornWithOptimisticTypes {}

    /**
     * Benchmark case for common compiled script
     * evaluation (eval()) for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompiledEvalBenchmark.Groovy {}

    /**
     * Benchmark case for common compiled script evaluation (eval())
     * for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends CompiledEvalBenchmark.Python {}


    /**
     * Benchmark case for common compiled script evaluation (eval())
     * for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends CompiledEvalCase {
        /**
         * Benchmark method for common compiled script evaluation (eval()).
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error occurred during
         *                         script evaluation
         */
        @Benchmark
        public void evalScript(Blackhole blackhole) throws ScriptException {
            super.evalScript(0, blackhole);
        }

        @Override
        public ScriptEngine getScriptEngine() {
            return new ScriptEngineManager().getEngineByName("graal.js");
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String[] getScriptFileNamesForIndexedCache() {
            return new String[] { "tpk.js" };
        }
    }

    /**
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return GraalCompiledEvalBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalCompiledEvalBenchmark().run();
    }
}