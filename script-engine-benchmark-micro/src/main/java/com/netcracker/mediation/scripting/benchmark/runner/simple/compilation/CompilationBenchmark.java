package com.netcracker.mediation.scripting.benchmark.runner.simple.compilation;

import com.netcracker.mediation.scripting.benchmark.SimpleBenchmarkRunner;
import com.netcracker.mediation.scripting.benchmark.cases.impl.common.CompilationCase;
import de.christophkraemer.rhino.javascript.RhinoScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;

/**
 * Runner for script compilation benchmark cases.
 */
public class CompilationBenchmark extends SimpleBenchmarkRunner {
    /**
     * Benchmark case for script compilation
     * for Rhino Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompilationCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new RhinoScriptEngine();
        }

        @Override
        protected String getScriptFileName() {
            return "tpk_rhino.js";
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for script compilation
     * for Nashorn Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompilationCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new NashornScriptEngineFactory().getScriptEngine();
        }

        @Override
        protected String getScriptFileName() {
            return "tpk_nashorn.js";
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for script compilation for Nashorn Java Script
     * engine with optimistic types option.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes extends CompilationCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new NashornScriptEngineFactory().getScriptEngine("--optimistic-types=true");
        }

        @Override
        protected String getScriptFileName() {
            return "tpk_nashorn.js";
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }
    }

    /**
     * Benchmark case for script compilation for Groovy engine.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompilationCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new GroovyScriptEngineFactory().getScriptEngine();
        }

        @Override
        protected String getScriptFileName() {
            return "tpk.groovy";
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
    private static Class<?> staticGetClass(){
        return CompilationBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new CompilationBenchmark().run();
    }
}