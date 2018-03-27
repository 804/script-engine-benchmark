package com.netcracker.mediation.scripting.benchmark.nashorn;

import javax.script.*;

public class NashornBenchmark {
    private static Integer warmUpCounter = 2;

    public static void main(String[] args) throws ScriptException {
        warmUp();
        runTests();
    }

    public static Object warmUp() throws ScriptException {
        Object[] res = new Object[warmUpCounter * 2];
        for (int i = 0; i < warmUpCounter; i++) {
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
        return simpleBindings;
    }


}
