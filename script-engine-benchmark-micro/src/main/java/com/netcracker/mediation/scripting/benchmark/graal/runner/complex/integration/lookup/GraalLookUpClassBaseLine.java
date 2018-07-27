package com.netcracker.mediation.scripting.benchmark.graal.runner.complex.integration.lookup;

import com.netcracker.mediation.scripting.benchmark.common.cases.impl.common.CompiledEvalCase;
import com.netcracker.mediation.scripting.benchmark.jvm.runner.complex.integration.lookup.LookUpClassBaseLine;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Base line runner for Java class looking up
 * integration benchmark cases with GraalVM.
 */
public class GraalLookUpClassBaseLine extends LookUpClassBaseLine {
    /**
     * Base line benchmark case for Java class looking up
     * for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends LookUpClassBaseLine.Rhino {}

    /**
     * Base line benchmark case for Java class looking up
     * for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends LookUpClassBaseLine.Nashorn {}

    /**
     * Base line benchmark case for Java class looking up for Nashorn
     * Java Script engine with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends LookUpClassBaseLine.NashornWithOptimisticTypes {}

    /**
     * Base line benchmark case for Java class looking up
     * for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends LookUpClassBaseLine.Groovy {}

    /**
     * Base line benchmark case for Java class looking up
     * for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends LookUpClassBaseLine.Python {}

    /**
     * Base line benchmark case for Java class looking up
     * for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends CompiledEvalCase {
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
            return new ScriptEngineManager().getEngineByName("graal.js");
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
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return GraalLookUpClassBaseLine.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalLookUpClassBaseLine().run();
    }
}