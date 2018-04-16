package com.netcracker.mediation.scripting.benchmark.runner.nashorn;

import com.netcracker.mediation.scripting.benchmark.function.nashorn.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;


public class NashornBenchmark extends AbstractBenchmark {
    private boolean optimisticTypesOption;

    public static void main(String[] args) throws Exception {
        new NashornBenchmark().run(args);
    }

    @Override
    protected void init(Map<String, String> argsMap) {
        String optimisticTypes = argsMap.get("optimistic_types");
        if (optimisticTypes != null) this.optimisticTypesOption = Boolean.valueOf(optimisticTypes);
        super.init(argsMap);
    }

    @Override
    protected Object runTests(int iterations) throws ScriptException {
        List<ScriptEngineFactory> engineFactories = new ScriptEngineManager().getEngineFactories();
        NashornScriptEngineFactory scriptEngineFactory = engineFactories.stream()
                .filter(factory -> factory instanceof NashornScriptEngineFactory)
                .map(factory -> (NashornScriptEngineFactory) factory)
                .findFirst()
                .get();
        ScriptEngine engine;
        engine = optimisticTypesOption
                ? scriptEngineFactory.getScriptEngine("--optimistic-types=true")
                : scriptEngineFactory.getScriptEngine();
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(iterations), ScriptContext.ENGINE_SCOPE);
        CompiledScript loadScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "runner.js');"
        );
        loadScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "benchmark.js');"
        );
        Object testScriptResult = testScriptCompiled.eval(context);
        System.out.println("Tests end");
        return testScriptResult;
    }


    private Bindings createBinding(int iterations) {
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("iterations", iterations);
        simpleBindings.put("writeToFile", new WriteToFile());
        simpleBindings.put("engine", optimisticTypesOption ? "nashorn-opt" : "nashorn");
        simpleBindings.put("resultPath", resultPath);
        return simpleBindings;
    }
}