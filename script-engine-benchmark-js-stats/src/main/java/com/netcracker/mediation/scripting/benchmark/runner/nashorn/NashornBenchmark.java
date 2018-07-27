package com.netcracker.mediation.scripting.benchmark.runner.nashorn;

import com.netcracker.mediation.scripting.benchmark.function.nashorn.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;

import javax.script.*;
import java.util.Arrays;

/**
 * Main class for Nashorn JavaScript engine statistic benchmarks.
 */
public class NashornBenchmark extends AbstractBenchmark {

    public static void main(String[] args) throws Exception {
        new NashornBenchmark().run(args);
    }

    @Override
    protected void warmUp() throws Exception {
        Object[] res = new Object[warmUpIterations * 2];
        for (int i = 0; i < warmUpIterations; i++) {
            System.out.println("WarmUp: iteration " + i);
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            CompiledScript extScriptCompiled = ((Compilable) engine).compile(
                "load('" + benchmarkJsPath + "lodash.min.js');" +
                    "load('" + benchmarkJsPath + "platform.js');" +
                    "load('" + benchmarkJsPath + "benchmark.js');"
            );
            SimpleScriptContext context = new SimpleScriptContext();
            context.setBindings(createBinding(i), ScriptContext.ENGINE_SCOPE);
            res[2 * i] = extScriptCompiled.eval(context);
            CompiledScript warmupScriptCompiled = ((Compilable) engine).compile(
                "load('" + benchmarkJsPath + "warmup.js');"
            );
            res[2 * i + 1] = warmupScriptCompiled.eval(context);
        }
        System.out.println("WarmUp end. Result: " + Arrays.toString(res));
    }

    @Override
    protected void runTests() throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        CompiledScript extScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "lodash.min.js');" +
                "load('" + benchmarkJsPath + "platform.js');" +
                "load('" + benchmarkJsPath + "benchmark.js');"
        );
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(0), ScriptContext.ENGINE_SCOPE);
        extScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "runner.js');"
        );
        Object testScriptResult = testScriptCompiled.eval(context);
        System.out.println("Tests end. Result: " + testScriptResult);
    }

    private Bindings createBinding(int iterationNumber) {
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("writeToFile", new WriteToFile());
        simpleBindings.put("iterNo", iterationNumber);
        simpleBindings.put("engine", "nashorn");
        simpleBindings.put("resultPath", resultPath);
        return simpleBindings;
    }
}