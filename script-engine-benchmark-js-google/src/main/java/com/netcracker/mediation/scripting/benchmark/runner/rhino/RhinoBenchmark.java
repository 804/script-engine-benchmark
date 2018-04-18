package com.netcracker.mediation.scripting.benchmark.runner.rhino;

import com.netcracker.mediation.scripting.benchmark.function.rhino.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.tools.shell.Global;

import java.util.Map;

/**
 * Main class for Rhino JavaScript engine Google benchmarks.
 */
public class RhinoBenchmark extends AbstractBenchmark {
    private static final String OPTIMIZATION_LEVEL_ARG_STR = "optimization-level";
    private int optimizationLevel;

    public static void main(String[] args) throws Exception {
        new RhinoBenchmark().run(args);
    }

    @Override
    protected void init(Map<String, String> argsMap) {
        String optimizationLevel = argsMap.get(OPTIMIZATION_LEVEL_ARG_STR);
        if (optimizationLevel != null) this.optimizationLevel = Integer.parseInt(optimizationLevel);
        System.out.println("optimization-level=" + this.optimizationLevel);
        super.init(argsMap);
    }

    @Override
    public void runTests(int iterations) {
        Object result;
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(optimizationLevel);
            Global global = new Global(context);
            fillScope(global, iterations);
            Script extLibCompiled = context.compileString(
                "load('" + benchmarkJsPath + "runner.js');",
                "runner",
                1,
                null
            );
            extLibCompiled.exec(context, global);
            Script testScriptCompiled = context.compileString(
                "load('" + benchmarkJsPath + "benchmark.js');",
                "test",
                1,
                null
            );
            result = testScriptCompiled.exec(context, global);
        } finally {
            Context.exit();
        }
        System.out.println("Tests end. Result - " + result);
    }

    private void fillScope(Global global, int iterations) {
        global.put("iterations", global, iterations);
        global.put("engine", global, "rhino-" + optimizationLevel);
        global.put("writeToFile", global, new WriteToFile());
        global.put("resultPath", global, resultPath);
        global.put("benchmarkJsPath", global, benchmarkJsPath);
    }
}