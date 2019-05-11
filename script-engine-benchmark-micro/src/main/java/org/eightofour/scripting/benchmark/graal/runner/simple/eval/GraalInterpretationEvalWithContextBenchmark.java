package org.eightofour.scripting.benchmark.graal.runner.simple.eval;

import org.eightofour.scripting.benchmark.common.cases.impl.extention.InterpretationEvalWithContextCase;
import org.eightofour.scripting.benchmark.jvm.runner.simple.eval.InterpretationEvalWithContextBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Runner for script interpreting with passed default script context
 * (eval(Reader, ScriptContext)) benchmark cases with GraalVM.
 */
public class GraalInterpretationEvalWithContextBenchmark extends InterpretationEvalWithContextBenchmark {
    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends InterpretationEvalWithContextBenchmark.Rhino {}

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends InterpretationEvalWithContextBenchmark.Nashorn {}

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Nashorn Java Script engine
     * with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends InterpretationEvalWithContextBenchmark.NashornWithOptimisticTypes {}

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends InterpretationEvalWithContextBenchmark.Groovy {}

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends InterpretationEvalWithContextBenchmark.Python {}

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends InterpretationEvalWithContextCase {
        /**
         * Benchmark method for script interpreting with passed
         * default script context (eval(Reader, ScriptContext)).
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
        return GraalInterpretationEvalWithContextBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalInterpretationEvalWithContextBenchmark().run();
    }
}