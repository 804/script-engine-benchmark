package com.netcracker.mediation.scripting.benchmark.jvm.runner.simple.eval;

import com.netcracker.mediation.scripting.benchmark.common.runner.SimpleBenchmarkRunner;
import com.netcracker.mediation.scripting.benchmark.common.cases.impl.extention.CompiledEvalWithBindingCase;
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
 * Runner for compiled script evaluation with passed bindings
 * (eval(Bindings)) benchmark cases.
 */
public class CompiledEvalWithBindingsBenchmark extends SimpleBenchmarkRunner {
    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Rhino Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompiledEvalWithBindingCase {
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
            return new RhinoScriptEngine();
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
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Nashorn Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompiledEvalWithBindingCase {
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
        protected String[] getScriptFileNamesForIndexedCache() {
            return new String[] { "tpk.js" };
        }

        @Override
        public ScriptEngine getScriptEngine() {
            return new NashornScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Nashorn Java Script engine
     * with optimistic types option.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes extends CompiledEvalWithBindingCase {
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
            return new NashornScriptEngineFactory().getScriptEngine("--optimistic-types=true");
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
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Groovy engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompiledEvalWithBindingCase {
        /**
         * Benchmark method for compiled script evaluation with passed
         * bindings (eval(Bindings)) with low abstraction level.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error occurred during
         *                         script evaluation
         */
        @Benchmark
        public void withForEvalScript(Blackhole blackhole) throws ScriptException {
            super.evalScript(0, blackhole);
        }

        /**
         * Benchmark method for compiled script evaluation with passed
         * bindings (eval(Bindings)) with high abstraction level.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error occurred during
         *                         script evaluation
         */
        @Benchmark
        public void withRangeEvalScript(Blackhole blackhole) throws ScriptException {
            super.evalScript(1, blackhole);
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
            return new String[] {
                "tpk_for.groovy",
                "tpk_range.groovy"
            };
        }
    }

    /**
     * Benchmark case for compiled script evaluation with passed bindings
     * (eval(Bindings)) for Jython Python engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends CompiledEvalWithBindingCase {
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
            return new PyScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String[] getScriptFileNamesForIndexedCache() {
            return new String[] { "tpk.py" };
        }
    }

    /**
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return CompiledEvalWithBindingsBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new CompiledEvalWithBindingsBenchmark().run();
    }
}