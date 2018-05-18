package com.netcracker.mediation.scripting.benchmark.runner.complex.integration.invokation;

import com.netcracker.mediation.scripting.benchmark.SimpleBenchmarkRunner;
import com.netcracker.mediation.scripting.benchmark.cases.impl.extention.CompiledEvalWithSimpleEngineBindingCase;
import com.netcracker.mediation.scripting.benchmark.runner.complex.integration.invokation.functions.GroovyFunction;
import com.netcracker.mediation.scripting.benchmark.runner.complex.integration.invokation.functions.NashornFunction;
import com.netcracker.mediation.scripting.benchmark.runner.complex.integration.invokation.functions.RhinoFunction;
import de.christophkraemer.rhino.javascript.RhinoScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * Main runner for Java object function style invokation
 * integration benchmark cases.
 */
public class InvokeObjectAsFunctionBenchmark extends SimpleBenchmarkRunner {
    /**
     * Main benchmark case for Java object function style invokation
     * for Rhino Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompiledEvalWithSimpleEngineBindingCase {
        /**
         * Custom function instance for binding.
         */
        private static final Object CUSTOM_FUNCTION = new RhinoFunction();

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
            return new RhinoScriptEngine();
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
     * Main benchmark case for Java object function style invokation
     * for Nashorn Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompiledEvalWithSimpleEngineBindingCase {
        /**
         * Custom function instance for binding.
         */
        private static final Object CUSTOM_FUNCTION = new NashornFunction();

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
            return new NashornScriptEngineFactory().getScriptEngine();
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
     * Main benchmark case for Java object function style invokation
     * for Nashorn Java Script engine with optimistic type option.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes extends CompiledEvalWithSimpleEngineBindingCase {
        /**
         * Custom function instance for binding.
         */
        private static final Object CUSTOM_FUNCTION = new NashornFunction();

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
            return new NashornScriptEngineFactory().getScriptEngine("--optimistic-types=true");
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
     * Main benchmark case for Java object
     * function style invokation for Groovy engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompiledEvalWithSimpleEngineBindingCase {
        /**
         * Custom function instance for binding.
         */
        private static final Object CUSTOM_FUNCTION = new GroovyFunction();

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
            return new GroovyScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String[] getScriptFileNamesForIndexedCache() {
            return new String[] { "function.groovy" };
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
        return InvokeObjectAsFunctionBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new InvokeObjectAsFunctionBenchmark().run();
    }
}