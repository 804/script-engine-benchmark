package com.netcracker.mediation.scripting.benchmark.nashorn;

import javax.script.*;
import java.util.Date;


public class NashornBenchmark {
    private static Integer warmUpIterations = 5;
    private static boolean useWarmUp = true;

    public static void main(String[] args) throws ScriptException {
        parseArguments(args);
        System.out.println("useWarmUp=" + useWarmUp + ", warmUpIterations=" + warmUpIterations);
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
                    "load('js/lodash.min.js');" +
                            "load('js/platform.js');" +
                            "load('js/benchmark.js');");
            SimpleScriptContext context = new SimpleScriptContext();
            context.setBindings(createBinding(i), ScriptContext.ENGINE_SCOPE);
            res[2 * i] = extScriptCompiled.eval(context);
            CompiledScript warmupScriptCompiled = ((Compilable) engine).compile(
                    "load('js/warmup.js');");
            res[2 * i + 1] = warmupScriptCompiled.eval(context);
        }
        System.out.println("WarmUp: end");
        return res; //return result to avoid code elimination
    }

    public static Object runTests() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        CompiledScript extScriptCompiled = ((Compilable) engine).compile(
                "load('js/lodash.min.js');" +
                        "load('js/platform.js');" +
                        "load('js/benchmark.js');");
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(0), ScriptContext.ENGINE_SCOPE);
        Object extScriptRes = extScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
                "load('js/runner.js');");
        Object testScriptResult = testScriptCompiled.eval(context);
        System.out.println("Tests end");
        return testScriptResult;
    }


    private static Bindings createBinding(int iterationNumber) {
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("writeToFile", new WriteToFile());
        simpleBindings.put("iterNo", iterationNumber);
        simpleBindings.put("engine", "nashorn");
        return simpleBindings;
    }


}
