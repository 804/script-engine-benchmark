package org.eightofour.scripting.benchmark.jvm.runner.complex.integration.lookup;

import org.eightofour.scripting.benchmark.common.runner.SimpleBenchmarkRunner;
import org.eightofour.scripting.benchmark.common.cases.impl.common.CompiledEvalCase;
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
 * Base line runner for Java class looking up
 * integration benchmark cases.
 */
public class LookUpClassBaseLine extends SimpleBenchmarkRunner {
    /**
     * Base line benchmark case for Java class looking up
     * for Rhino Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompiledEvalCase {
        /**
         * Base line benchmark method for Java class looking up.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error will be occurred
         *                         during script evaluation
         */
        @Benchmark
        public void lookUp(Blackhole blackhole) throws ScriptException {
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
    }

    /**
     * Base line benchmark case for Java class looking up
     * for Nashorn Java Script engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompiledEvalCase {
        /**
         * Base line benchmark method for Java class looking up.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error will be occurred
         *                         during script evaluation
         */
        @Benchmark
        public void lookUp(Blackhole blackhole) throws ScriptException {
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
    }

    /**
     * Base line benchmark case for Java class looking up for Nashorn
     * Java Script engine with optimistic types option.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes extends CompiledEvalCase {
        /**
         * Base line benchmark method for Java class looking up.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error will be occurred
         *                         during script evaluation
         */
        @Benchmark
        public void lookUp(Blackhole blackhole) throws ScriptException {
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
    }

    /**
     * Base line benchmark case for Java class looking up
     * for Groovy engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompiledEvalCase {
        /**
         * Base line benchmark method for Java class looking up.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error will be occurred
         *                         during script evaluation
         */
        @Benchmark
        public void lookUp(Blackhole blackhole) throws ScriptException {
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
    }

    /**
     * Base line benchmark case for Java class looking up
     * for Jython Python engine.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends CompiledEvalCase {
        /**
         * Base line benchmark method for Java class looking up.
         *
         * @param blackhole - {@link Blackhole} instance for
         *                    dead code elimination preventing
         * @throws ScriptException if error will be occurred
         *                         during script evaluation
         */
        @Benchmark
        public void lookUp(Blackhole blackhole) throws ScriptException {
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
    }

    /**
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return LookUpClassBaseLine.class;
    }

    public static void main(String[] args) throws RunnerException {
        new LookUpClassBaseLine().run();
    }
}