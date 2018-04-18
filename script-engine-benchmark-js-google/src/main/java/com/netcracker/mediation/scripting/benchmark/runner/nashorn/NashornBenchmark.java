package com.netcracker.mediation.scripting.benchmark.runner.nashorn;

import com.netcracker.mediation.scripting.benchmark.function.nashorn.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;


/**
 * Main class for Nashorn JavaScript engine Google benchmarks.
 */
public class NashornBenchmark extends AbstractBenchmark {
    private boolean optimisticTypesOption;

    public static void main(String[] args) throws Exception {
        new NashornBenchmark().run(args);
    }

    @Override
    protected void init(Map<String, String> argsMap) {
        String optimisticTypes = argsMap.get("optimistic-types");
        if (optimisticTypes != null) this.optimisticTypesOption = Boolean.valueOf(optimisticTypes);
        System.out.println("optimistic-types=" + this.optimisticTypesOption);
        super.init(argsMap);
    }

    @Override
    protected void runTests(int iterations) throws ScriptException {
        ScriptEngine engine = getScriptEngine();
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(iterations), ScriptContext.ENGINE_SCOPE);
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
        List<ScriptEngineFactory> engineFactories = new ScriptEngineManager().getEngineFactories();
        NashornScriptEngineFactory scriptEngineFactory = engineFactories.stream()
                .filter(factory -> factory instanceof NashornScriptEngineFactory)
                .map(factory -> (NashornScriptEngineFactory) factory)
                .findFirst()
                .get();
        return optimisticTypesOption
                ? scriptEngineFactory.getScriptEngine("--optimistic-types=true")
                : scriptEngineFactory.getScriptEngine();
    }

    private Bindings createBinding(int iterations) {
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("iterations", iterations);
        simpleBindings.put("writeToFile", new WriteToFile());
        simpleBindings.put("engine", optimisticTypesOption ? "nashorn-opt" : "nashorn");
        simpleBindings.put("resultPath", resultPath);
        simpleBindings.put("benchmarkJsPath", benchmarkJsPath);
        return simpleBindings;
    }
}