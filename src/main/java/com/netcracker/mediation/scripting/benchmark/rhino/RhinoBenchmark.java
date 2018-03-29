package com.netcracker.mediation.scripting.benchmark.rhino;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.tools.shell.Global;

import java.io.IOException;
import java.util.Date;

public class RhinoBenchmark {
    private static Integer warmUpIterations = 5;
    private static boolean useWarmUp = true;

    public static void main(String[] args) throws IOException {
        parseArguments(args);
        System.out.println("useWarmUp=" + useWarmUp + ", warmUpIterations=" + warmUpIterations);
        System.out.println("Start: " + new Date());
        if (useWarmUp) {
            warmup();
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

    public static Object warmup() {
        Object[] res = new Object[warmUpIterations * 2];
        for (int i = 0; i < warmUpIterations; i++) {
            System.out.println("WarmUp: iteration " + i);
            try {
                Context context = Context.enter();
                Global global = new Global(context);
                fillScope(global, i);
                Script extLibCompiled = context.compileString("load('js/lodash.min.js');" +
                        "load('js/platform.js');" +
                        "load('js/benchmark.js');", "benchmark", 1, null);
                res[2 * i] = extLibCompiled.exec(context, global);
                Script testScriptCompiled = context.compileString("load('js/warmup.js');", "test", 1, null);
                res[2 * i + 1] = testScriptCompiled.exec(context, global);
            } finally {
                Context.exit();
            }
        }
        return res;
    }

    public static Object runTests() {
        Object result = null;
        try {
            Context context = Context.enter();
            Global global = new Global(context);
            fillScope(global, 0);
            Script extLibCompiled = context.compileString("load('js/lodash.min.js');" +
                    "load('js/platform.js');" +
                    "load('js/benchmark.js');", "benchmark", 1, null);
            extLibCompiled.exec(context, global);
            Script testScriptCompiled = context.compileString("load('js/runner.js');", "test", 1, null);
            result = testScriptCompiled.exec(context, global);
        } finally {
            Context.exit();
        }
        return result;
    }


    public static void fillScope(Global global, int iterationNo) {
        global.put("iterNo", global, iterationNo);
        global.put("engine", global, "rhino");
        global.put("writeToFile", global, new WriteToFile());
    }

}
