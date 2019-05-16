package org.eightofour.scripting.benchmark.graal.runner.simple.compilation;

import org.eightofour.scripting.benchmark.common.cases.impl.common.CompilationCase;
import org.eightofour.scripting.benchmark.jvm.runner.simple.compilation.CompilationBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Runner for script compilation benchmark cases with GraalVM.
 */
public class GraalCompilationBenchmark extends CompilationBenchmark {
    /**
     * Benchmark case for script compilation
     * for Rhino Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends CompilationBenchmark.Rhino {}

    /**
     * Benchmark case for script compilation
     * for Nashorn Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends CompilationBenchmark.Nashorn {}

    /**
     * Benchmark case for script compilation for Nashorn Java Script
     * engine with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends CompilationBenchmark.NashornWithOptimisticTypes {}

    /**
     * Benchmark case for script compilation for Groovy engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends CompilationBenchmark.Groovy {}

    /**
     * Benchmark case for script compilation
     * for Jython Python engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends CompilationBenchmark.Python {}

    /**
     * Benchmark case for script compilation
     * for GraalJS Java Script engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime})
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends CompilationCase {
        @Override
        public ScriptEngine getScriptEngine() {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
            Bindings bindings = engine.createBindings();
            bindings.put("polyglot.js.allowAllAccess", true);
            engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
            return engine;
        }

        @Override
        protected String getScriptFileName() {
            return "tpk_graal.js";
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
        return GraalCompilationBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalCompilationBenchmark().run();
    }
}