package com.netcracker.mediation.scripting.benchmark.runner.graaljs;

import com.netcracker.mediation.scripting.benchmark.function.graaljs.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;

import javax.script.*;

/**
 * Main class for GraalJS JavaScript engine statistic benchmarks.
 */
public class GraalJSBenchmark extends AbstractBenchmark {

    public static void main(String[] args) throws Exception {
        new GraalJSBenchmark().run(args);
    }

    @Override
    protected void warmUp() throws Exception {
        Object[] res = new Object[warmUpIterations * 2];
        for (int i = 0; i < warmUpIterations; i++) {
            System.out.println("WarmUp: iteration " + i);
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
            CompiledScript extScriptCompiled = ((Compilable) engine).compile(
                "load('" + benchmarkJsPath + "lodash.min.js');" +
                    "load('" + benchmarkJsPath + "platform.js');" +
                    "load('" + benchmarkJsPath + "benchmark.js');"
            );
            SimpleScriptContext context = new SimpleScriptContext();
            context.setBindings(createBinding(engine, i), ScriptContext.ENGINE_SCOPE);
            res[2 * i] = extScriptCompiled.eval(context);
            CompiledScript warmupScriptCompiled = ((Compilable) engine).compile(
                "load('" + benchmarkJsPath + "warmup.js');"
            );
            res[2 * i + 1] = warmupScriptCompiled.eval(context);
        }
        System.out.println("WarmUp end. Result hash code: " + res.hashCode());
    }

    @Override
    protected void runTests() throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
        CompiledScript extScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "lodash.min.js');" +
                "load('" + benchmarkJsPath + "platform.js');" +
                "load('" + benchmarkJsPath + "benchmark.js');"
        );
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(engine, 0), ScriptContext.ENGINE_SCOPE);
        extScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "runner.js');"
        );
        Object testScriptResult = testScriptCompiled.eval(context);
        System.out.println("Tests end. Result hash code: " + testScriptResult.hashCode());
    }

    private Bindings createBinding(ScriptEngine engine, int iterationNumber) {
        Bindings simpleBindings = engine.createBindings();
        simpleBindings.put("writeToFile", new WriteToFile());
        simpleBindings.put("iterNo", iterationNumber);
        simpleBindings.put("engine", "graal-js");
        simpleBindings.put("resultPath", resultPath);
        return simpleBindings;
    }
}