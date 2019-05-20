package org.eightofour.scripting.benchmark.graal.runner.simple.eval;

import org.eightofour.scripting.benchmark.common.cases.impl.extention.InterpretationEvalWithBindingCase;
import org.eightofour.scripting.benchmark.jvm.runner.simple.eval.InterpretationEvalWithBindingsBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.*;

/**
 * Runner for script interpreting with passed bindings
 * (eval(Reader, Bindings)) benchmark cases with GraalVM.
 */
public class GraalInterpretationEvalWithBindingsBenchmark extends InterpretationEvalWithBindingsBenchmark {
    /**
     * Benchmark case for script interpreting with passed bindings
     * (eval(Reader, Bindings)) for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends InterpretationEvalWithBindingsBenchmark.Rhino {}

    /**
     * Benchmark case for script interpreting with passed bindings
     * (eval(Reader, Bindings)) for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends InterpretationEvalWithBindingsBenchmark.Nashorn {}

    /**
     * Benchmark case for script interpreting with passed bindings
     * (eval(Reader, Bindings)) for Nashorn Java Script engine
     * with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends InterpretationEvalWithBindingsBenchmark.NashornWithOptimisticTypes {}

    /**
     * Benchmark case for script interpreting with passed bindings
     * (eval(Reader, Bindings)) for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends InterpretationEvalWithBindingsBenchmark.Groovy {}

    /**
     * Benchmark case for script interpreting with passed bindings
     * (eval(Reader, Bindings)) for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends InterpretationEvalWithBindingsBenchmark.Python {}

    /**
     * Benchmark case for script interpreting with passed bindings
     * (eval(Reader, Bindings)) for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends InterpretationEvalWithBindingCase {
        /**
         * Benchmark method for script interpreting with
         * passed bindings (eval(Reader, Bindings)).
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
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
            Bindings bindings = engine.createBindings();
            bindings.put("polyglot.js.allowAllAccess", true);
            engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
            return engine;
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
        return GraalInterpretationEvalWithBindingsBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalInterpretationEvalWithBindingsBenchmark().run();
    }
}