package org.eightofour.scripting.benchmark.jvm.runner.complex.integration.invokation;

import org.eightofour.scripting.benchmark.common.functions.PythonFunction;
import org.eightofour.scripting.benchmark.common.runner.SimpleBenchmarkRunner;
import org.eightofour.scripting.benchmark.common.cases.impl.extention.CompiledEvalWithSimpleEngineBindingCase;
import org.eightofour.scripting.benchmark.common.functions.GroovyFunction;
import org.eightofour.scripting.benchmark.common.functions.NashornFunction;
import org.eightofour.scripting.benchmark.common.functions.RhinoFunction;
import de.christophkraemer.rhino.javascript.RhinoScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;
import org.python.jsr223.PyScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * Base line runner for Java object function style invokation
 * integration benchmark cases.
 */
public class InvokeObjectAsFunctionBaseLine extends SimpleBenchmarkRunner {
    /**
     * Base line benchmark case for Java object function style invokation
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
     * Base line benchmark case for Java object function style invokation
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
     * Base line benchmark case for Java object function style invokation
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
     * Base line benchmark case for Java object
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
            return new String[] { "base.groovy" };
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
     * Base line benchmark case for Java object function style invokation
     * for Jython Python engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends CompiledEvalWithSimpleEngineBindingCase {
        /**
         * Custom function instance for binding.
         */
        private static final Object CUSTOM_FUNCTION = new PythonFunction();

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
            return new PyScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String[] getScriptFileNamesForIndexedCache() {
            return new String[] { "base.py" };
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
        return InvokeObjectAsFunctionBaseLine.class;
    }

    public static void main(String[] args) throws RunnerException {
        new InvokeObjectAsFunctionBaseLine().run();
    }
}