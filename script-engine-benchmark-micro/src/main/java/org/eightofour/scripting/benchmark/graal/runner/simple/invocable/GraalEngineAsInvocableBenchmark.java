package org.eightofour.scripting.benchmark.graal.runner.simple.invocable;

import org.eightofour.scripting.benchmark.common.cases.impl.common.InvocableCase;
import org.eightofour.scripting.benchmark.jvm.runner.simple.invocable.EngineAsInvocableBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import javax.script.*;
import java.util.concurrent.Callable;

/**
 * Runner for script engine as {@link Invocable}
 * benchmark cases with GraalVM.
 */
public class GraalEngineAsInvocableBenchmark extends EngineAsInvocableBenchmark {
    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Rhino Java Script JSR-223 engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Rhino extends EngineAsInvocableBenchmark.Rhino {}

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Nashorn Java Script JSR-223 engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Nashorn extends EngineAsInvocableBenchmark.Nashorn {}

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Nashorn Java Script JSR-223 engine
     * with optimistic types option with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class NashornWithOptimisticTypes
            extends EngineAsInvocableBenchmark.NashornWithOptimisticTypes {}

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Groovy JSR-223 engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Groovy extends EngineAsInvocableBenchmark.Groovy {}

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Jython Python JSR-223 engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class Python extends EngineAsInvocableBenchmark.Python {}

    /**
     * Script engine as {@link Invocable} benchmark cases
     * for Jython Python JSR-223 engine with GraalVM.
     */
    @Fork(1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 10, time = 5)
    @Measurement(iterations = 10, time = 5)
    @State(Scope.Benchmark)
    static public class GraalJS extends InvocableCase {
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
     * Static method for getting current class {@link Class} instance.
     *
     * @return current class {@link Class} instance
     */
    private static Class<?> staticGetClass() {
        return GraalEngineAsInvocableBenchmark.class;
    }

    public static void main(String[] args) throws RunnerException {
        new GraalEngineAsInvocableBenchmark().run();
    }
}