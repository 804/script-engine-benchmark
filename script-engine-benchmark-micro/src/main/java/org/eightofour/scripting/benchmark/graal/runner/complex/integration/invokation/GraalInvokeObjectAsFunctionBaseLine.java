package org.eightofour.scripting.benchmark.graal.runner.complex.integration.invokation;

import org.eightofour.scripting.benchmark.common.cases.impl.extention.CompiledEvalWithSimpleEngineBindingCase;
import org.eightofour.scripting.benchmark.common.functions.GraalJSFunction;
import org.eightofour.scripting.benchmark.jvm.runner.complex.integration.invokation.InvokeObjectAsFunctionBaseLine;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.*;

/**
 * Base line runner for Java object function style invokation
 * integration benchmark cases with GraalVM.
 */
public class GraalInvokeObjectAsFunctionBaseLine extends InvokeObjectAsFunctionBaseLine {
    /**
     * Base line benchmark case for Java object function style invokation
     * for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends InvokeObjectAsFunctionBaseLine.Rhino {}

    /**
     * Base line benchmark case for Java object function style invokation
     * for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends InvokeObjectAsFunctionBaseLine.Nashorn {}

    /**
     * Base line benchmark case for Java object function style invokation
     * for Nashorn Java Script engine with optimistic type option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends InvokeObjectAsFunctionBaseLine.NashornWithOptimisticTypes {}

    /**
     * Base line benchmark case for Java object
     * function style invokation for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends InvokeObjectAsFunctionBaseLine.Groovy {}

    /**
     * Base line benchmark case for Java object function style invokation
     * for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends InvokeObjectAsFunctionBaseLine.Python {}

    /**
     * Base line benchmark case for Java object function style invokation
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
            return new String[] { "base.js" };
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
        return GraalInvokeObjectAsFunctionBaseLine.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalInvokeObjectAsFunctionBaseLine().run();
    }
}