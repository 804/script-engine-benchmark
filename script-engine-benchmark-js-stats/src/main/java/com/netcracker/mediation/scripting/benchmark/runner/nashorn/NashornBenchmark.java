package com.netcracker.mediation.scripting.benchmark.runner.nashorn;

import com.netcracker.mediation.scripting.benchmark.function.nashorn.WriteToFile;

import javax.script.*;
import java.util.Date;


public class NashornBenchmark {
    private static String benchmarkJsPath = "script-engine-benchmark-js-stats/src/main/js/";
    private static String resultPath = "script-engine-benchmark-js-stats/target/";
    private static Integer warmUpIterations = 5;
    private static boolean useWarmUp = true;

    public static void main(String[] args) throws ScriptException {
        parseArguments(args);
        System.out.println(
            "useWarmUp=" + useWarmUp +
            ", warmUpIterations=" + warmUpIterations +
            ", benchmarkJsPath=" + benchmarkJsPath +
            ", resultPath" + resultPath
        );
        System.out.println("Start: " + new Date());
        if (useWarmUp) {
            warmUp();
        }
        runTests();
        System.out.println("Finish: " + new Date());
    }

    public static void parseArguments(String[] args) {
        for (String arg : args) {
            String[] pair = arg.split("=");
            if (pair.length == 2) {
                switch (pair[0]) {
                    case "warmup":
                        useWarmUp = Boolean.valueOf(pair[1]);
                        break;
                    case "warmup_iter":
                        warmUpIterations = Integer.parseInt(pair[1]);
                        break;
                    case "benchmark_js_path":
                        benchmarkJsPath = pair[1];
                        break;
                    case "result_path":
                        resultPath = pair[1];
                        break;
                }
            }
        }
    }

    public static Object warmUp() throws ScriptException {
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
        System.out.println("WarmUp: end");
        return res; //return result to avoid code elimination
    }

    public static Object runTests() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        CompiledScript extScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "lodash.min.js');" +
                "load('" + benchmarkJsPath + "platform.js');" +
                "load('" + benchmarkJsPath + "benchmark.js');"
        );
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(0), ScriptContext.ENGINE_SCOPE);
        Object extScriptRes = extScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
            "load('" + benchmarkJsPath + "runner.js');"
        );
        Object testScriptResult = testScriptCompiled.eval(context);
        System.out.println("Tests end");
        return testScriptResult;
    }

    private static Bindings createBinding(int iterationNumber) {
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("writeToFile", new WriteToFile());
        simpleBindings.put("iterNo", iterationNumber);
        simpleBindings.put("engine", "nashorn");
        simpleBindings.put("resultPath", resultPath);
        return simpleBindings;
    }
}