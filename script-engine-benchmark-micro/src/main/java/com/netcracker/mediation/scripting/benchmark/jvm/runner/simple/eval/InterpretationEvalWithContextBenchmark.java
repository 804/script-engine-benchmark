package com.netcracker.mediation.scripting.benchmark.jvm.runner.simple.eval;

import com.netcracker.mediation.scripting.benchmark.common.runner.SimpleBenchmarkRunner;
import com.netcracker.mediation.scripting.benchmark.common.cases.impl.extention.InterpretationEvalWithContextCase;
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
 * Runner for script interpreting with passed default script context
 * (eval(Reader, ScriptContext)) benchmark cases.
 */
public class InterpretationEvalWithContextBenchmark extends SimpleBenchmarkRunner {
    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Rhino Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends InterpretationEvalWithContextCase {
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
            return new RhinoScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Nashorn Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends InterpretationEvalWithContextCase {
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
            return new NashornScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Nashorn Java Script engine
     * with optimistic types option.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes extends InterpretationEvalWithContextCase {
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
            return new NashornScriptEngineFactory().getScriptEngine("--optimistic-types=true");
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Groovy engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends InterpretationEvalWithContextCase {
        /**
         * Benchmark method for script interpreting with passed default
         * script context (eval(Reader, ScriptContext))
         * with low level abstractions.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error occurred during
         *                         script evaluation
         */
        @Benchmark
        public void withForEvalScript(Blackhole blackhole) throws ScriptException {
            super.evalScript("tpk_for.groovy", blackhole);
        }

        /**
         * Benchmark method for script interpreting with passed default
         * script context (eval(Reader, ScriptContext))
         * with high level abstractions.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error occurred during
         *                         script evaluation
         */
        @Benchmark
        public void withRangeEvalScript(Blackhole blackhole) throws ScriptException {
            super.evalScript("tpk_range.groovy", blackhole);
        }

        @Override
        public ScriptEngine getScriptEngine() {
            return new GroovyScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for script interpreting with passed default script context
     * (eval(Reader, ScriptContext)) for Jython Python engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends InterpretationEvalWithContextCase {
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
            super.evalScript("tpk.py", blackhole);
        }

        @Override
        public ScriptEngine getScriptEngine() {
            return new PyScriptEngineFactory().getScriptEngine();
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
        return InterpretationEvalWithContextBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new InterpretationEvalWithContextBenchmark().run();
    }
}