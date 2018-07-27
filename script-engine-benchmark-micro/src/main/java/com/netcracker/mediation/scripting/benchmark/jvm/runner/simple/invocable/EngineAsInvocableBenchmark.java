package com.netcracker.mediation.scripting.benchmark.jvm.runner.simple.invocable;

import com.netcracker.mediation.scripting.benchmark.common.runner.SimpleBenchmarkRunner;
import com.netcracker.mediation.scripting.benchmark.common.cases.impl.common.InvocableCase;
import de.christophkraemer.rhino.javascript.RhinoScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;
import org.python.jsr223.PyScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.util.concurrent.Callable;

/**
 * Runner for script engine as {@link Invocable} benchmark cases.
 */
public class EngineAsInvocableBenchmark extends SimpleBenchmarkRunner {
    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Rhino Java Script JSR-223 engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends InvocableCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new RhinoScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String getScriptFileName() {
            return "invocable.js";
        }

        @Override
        protected String getMethodName() {
            return "call";
        }

        @Override
        protected String getResultMethodName() {
            return "call";
        }

        @Override
        protected Class<?> getInterfaceForWrapping() {
            return Callable.class;
        }

        @Override
        protected Class<?> getResultInterfaceForWrapping() {
            return Callable.class;
        }
    }

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Nashorn Java Script JSR-223 engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends InvocableCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new NashornScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String getScriptFileName() {
            return "invocable.js";
        }

        @Override
        protected String getMethodName() {
            return "call";
        }

        @Override
        protected String getResultMethodName() {
            return "call";
        }

        @Override
        protected Class<?> getInterfaceForWrapping() {
            return Callable.class;
        }

        @Override
        protected Class<?> getResultInterfaceForWrapping() {
            return Callable.class;
        }
    }

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Nashorn Java Script JSR-223 engine
     * with optimistic types option.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes extends InvocableCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new NashornScriptEngineFactory().getScriptEngine("--optimistic-types=true");
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String getScriptFileName() {
            return "invocable.js";
        }

        @Override
        protected String getMethodName() {
            return "call";
        }

        @Override
        protected String getResultMethodName() {
            return "call";
        }

        @Override
        protected Class<?> getInterfaceForWrapping() {
            return Callable.class;
        }

        @Override
        protected Class<?> getResultInterfaceForWrapping() {
            return Callable.class;
        }
    }

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Groovy JSR-223 engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends InvocableCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new GroovyScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String getScriptFileName() {
            return "invocable.groovy";
        }

        @Override
        protected String getMethodName() {
            return "call";
        }

        @Override
        protected String getResultMethodName() {
            return "call";
        }

        @Override
        protected Class<?> getInterfaceForWrapping() {
            return Callable.class;
        }

        @Override
        protected Class<?> getResultInterfaceForWrapping() {
            return Callable.class;
        }
    }

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Jython Python JSR-223 engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends InvocableCase {
        @Override
        public ScriptEngine getScriptEngine() {
            return new PyScriptEngineFactory().getScriptEngine();
        }

        @Override
        public Class<?> getResourceClass() {
            return staticGetClass();
        }

        @Override
        protected String getScriptFileName() {
            return "invocable.py";
        }

        @Override
        protected String getMethodName() {
            return "call";
        }

        @Override
        protected String getResultMethodName() {
            return "call";
        }

        @Override
        protected Class<?> getInterfaceForWrapping() {
            return Callable.class;
        }

        @Override
        protected Class<?> getResultInterfaceForWrapping() {
            return Callable.class;
        }
    }

    /**
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return EngineAsInvocableBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new EngineAsInvocableBenchmark().run();
    }
}