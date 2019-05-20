package org.eightofour.scripting.benchmark.graal.runner.simple.eval;

import org.eightofour.scripting.benchmark.common.cases.impl.extention.CompiledEvalWithBindingCase;
import org.eightofour.scripting.benchmark.jvm.runner.simple.eval.CompiledEvalWithBindingsBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.*;

/**
 * Runner for compiled script evaluation with passed bindings
 * (eval(Bindings)) benchmark cases with GraalVM.
 */
public class GraalCompiledEvalWithBindingsBenchmark extends CompiledEvalWithBindingsBenchmark {
    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompiledEvalWithBindingsBenchmark.Rhino {}

    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompiledEvalWithBindingsBenchmark.Nashorn {}

    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Nashorn Java Script engine
     * with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends CompiledEvalWithBindingsBenchmark.NashornWithOptimisticTypes {}

    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompiledEvalWithBindingsBenchmark.Groovy {}

    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends CompiledEvalWithBindingsBenchmark.Python {}

    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends CompiledEvalWithBindingCase {
        /**
         * Benchmark method for compiled script evaluation
         * with passed bindings (eval(Bindings)).
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
        return GraalCompiledEvalWithBindingsBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalCompiledEvalWithBindingsBenchmark().run();
    }
}