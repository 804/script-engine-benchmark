package com.netcracker.mediation.scripting.benchmark.runner.graaljs;

import com.netcracker.mediation.scripting.benchmark.function.graaljs.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;

import javax.script.*;


/**
 * Main class for GraalJS JavaScript engine Google benchmarks.
 */
public class GraalJSBenchmark extends AbstractBenchmark {
    public static void main(String[] args) throws Exception {
        new GraalJSBenchmark().run(args);
    }

    @Override
    protected void runTests(int iterations) throws ScriptException {
        ScriptEngine engine = getScriptEngine();
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(engine, iterations), ScriptContext.ENGINE_SCOPE);
        CompiledScript loadScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "runner.js');"
        );
        loadScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "benchmark.js');"
        );
        Object result = testScriptCompiled.eval(context);
        System.out.println("Tests end. Result - " + result);
    }

    private ScriptEngine getScriptEngine() {
        return new ScriptEngineManager().getEngineByName("graal.js");
    }

    private Bindings createBinding(ScriptEngine engine, int iterations) {
        Bindings simpleBindings = engine.createBindings();
        simpleBindings.put("iterations", iterations);
        simpleBindings.put("writeToFile", new WriteToFile());
        simpleBindings.put("engine", "graal-js");
        simpleBindings.put("resultPath", resultPath);
        simpleBindings.put("benchmarkJsPath", benchmarkJsPath);
        return simpleBindings;
    }
}