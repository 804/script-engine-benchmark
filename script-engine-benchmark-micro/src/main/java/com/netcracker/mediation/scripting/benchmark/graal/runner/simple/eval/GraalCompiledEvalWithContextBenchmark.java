package com.netcracker.mediation.scripting.benchmark.graal.runner.simple.eval;

import com.netcracker.mediation.scripting.benchmark.common.cases.impl.extention.CompiledEvalWithContextCase;
import com.netcracker.mediation.scripting.benchmark.jvm.runner.simple.eval.CompiledEvalWithContextBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Runner for compiled script evaluation with passed default
 * script context (eval(ScriptContext)) benchmark cases with GraalVM.
 */
public class GraalCompiledEvalWithContextBenchmark extends CompiledEvalWithContextBenchmark {
    /**
     * Benchmark case for compiled script evaluation with passed default
     * script context (eval(ScriptContext)) for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompiledEvalWithContextBenchmark.Rhino {}

    /**
     * Benchmark case for compiled script evaluation with passed default
     * script context (eval(ScriptContext)) for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompiledEvalWithContextBenchmark.Nashorn {}

    /**
     * Benchmark case for compiled script evaluation with passed default
     * script context (eval(ScriptContext)) for Nashorn Java Script engine
     * with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends CompiledEvalWithContextBenchmark.NashornWithOptimisticTypes {}

    /**
     * Benchmark case for compiled script evaluation with passed
     * default script context (eval(ScriptContext)) for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompiledEvalWithContextBenchmark.Groovy {}

    /**
     * Benchmark case for compiled script evaluation with passed default
     * script context (eval(ScriptContext)) for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends CompiledEvalWithContextBenchmark.Python {}

    /**
     * Benchmark case for compiled script evaluation with passed default
     * script context (eval(ScriptContext)) for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends CompiledEvalWithContextCase {
        /**
         * Benchmark method for compiled script evaluation with
         * passed default script context (eval(ScriptContext)).
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
        return GraalCompiledEvalWithContextBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalCompiledEvalWithContextBenchmark().run();
    }
}