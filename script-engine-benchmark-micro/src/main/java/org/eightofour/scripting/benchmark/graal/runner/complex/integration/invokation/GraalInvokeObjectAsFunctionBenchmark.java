package org.eightofour.scripting.benchmark.graal.runner.complex.integration.invokation;

import org.eightofour.scripting.benchmark.common.cases.impl.extention.CompiledEvalWithSimpleEngineBindingCase;
import org.eightofour.scripting.benchmark.common.functions.GraalJSFunction;
import org.eightofour.scripting.benchmark.jvm.runner.complex.integration.invokation.InvokeObjectAsFunctionBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Main runner for Java object function style invokation
 * integration benchmark cases with GraalVM.
 */
public class GraalInvokeObjectAsFunctionBenchmark extends InvokeObjectAsFunctionBenchmark {
    /**
     * Main benchmark case for Java object function style invokation
     * for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends InvokeObjectAsFunctionBenchmark.Rhino {}

    /**
     * Main benchmark case for Java object function style invokation
     * for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends InvokeObjectAsFunctionBenchmark.Nashorn {}

    /**
     * Main benchmark case for Java object function style invokation
     * for Nashorn Java Script engine with optimistic type option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends InvokeObjectAsFunctionBenchmark.NashornWithOptimisticTypes {}

    /**
     * Main benchmark case for Java object
     * function style invokation for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends InvokeObjectAsFunctionBenchmark.Groovy {}

    /**
     * Main benchmark case for Java object function style invokation
     * for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends InvokeObjectAsFunctionBenchmark.Python {}

    /**
     * Main benchmark case for Java object function style invokation
     * for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends CompiledEvalWithSimpleEngineBindingCase {
        /**
         * Custom function instance for binding.
         */
        private static final Object CUSTOM_FUNCTION = new GraalJSFunction();

        /**
         * Benchmark method for Java object function style invokation.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error will be occurred
         *                         during script evaluation
         */
        @Benchmark
        public void invoke(Blackhole blackhole) throws ScriptException {
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
            return new String[] { "function.js" };
        }

        @Override
        protected String getBindingName() {
            return "myFunction";
        }

        @Override
        protected Object getBindingValue() {
            return CUSTOM_FUNCTION;
        }
    }

    /**
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return GraalInvokeObjectAsFunctionBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalInvokeObjectAsFunctionBenchmark().run();
    }
}