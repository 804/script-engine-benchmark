package com.netcracker.mediation.scripting.benchmark.runner.rhino;

import com.netcracker.mediation.scripting.benchmark.function.rhino.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.tools.shell.Global;

import java.io.File;
import java.util.Arrays;

/**
 * Main class for Rhino JavaScript engine statistic benchmarks.
 */
public class RhinoBenchmark extends AbstractBenchmark {
    private static final String GET_RES_CLASS_CODE =
            "var resClass = new Packages.java.lang.Object().getClass();";

    public static void main(String[] args) throws Exception {
        new RhinoBenchmark().run(args);
    }

    @Override
    protected void warmUp() {
        Object[] res = new Object[warmUpIterations * 2];
        for (int i = 0; i < warmUpIterations; i++) {
            System.out.println("WarmUp: iteration " + i);
            try {
                Context context = Context.enter();
                Global global = new Global(context);
                fillScope(global, i);
                Script extLibCompiled = context.compileString(
                    loadJsResourcesCode(
                        "lodash.min.js",
                            "platform.js",
                            "benchmark.js"
                    ),
                    "benchmark",
                    1,
                    null
                );
                res[2 * i] = extLibCompiled.exec(context, global);
                Script testScriptCompiled = context.compileString(
                    loadJsResourcesCode("warmup.js"),
                    "test",
                    1,
                    null
                );
                res[2 * i + 1] = testScriptCompiled.exec(context, global);
            } finally {
                Context.exit();
            }
            System.out.println("WarmUp end. Result: " + Arrays.toString(res));
        }
    }

    @Override
    protected void runTests() {
        Object testScriptResult;
        try {
            Context context = Context.enter();
            Global global = new Global(context);
            fillScope(global, 0);
            Script extLibCompiled = context.compileString(
                loadJsResourcesCode(
                    "lodash.min.js",
                        "platform.js",
                        "benchmark.js"
                ),
                "benchmark",
                1,
                null
            );
            extLibCompiled.exec(context, global);
            Script testScriptCompiled = context.compileString(
                loadJsResourcesCode("runner.js"),
                "test",
                1,
                null
            );
            testScriptResult = testScriptCompiled.exec(context, global);
        } finally {
            Context.exit();
        }
        System.out.println("Tests end. Result: " + testScriptResult);
    }

    private void fillScope(Global global, int iterationNo) {
        global.put("iterNo", global, iterationNo);
        global.put("engine", global, "rhino");
        global.put("writeToFile", global, new WriteToFile());
        global.put("resultPath", global, resultPath);
    }

    private String loadJsResourcesCode(String... resourcesFileNames) {
        StringBuilder result = new StringBuilder(GET_RES_CLASS_CODE);
        for (String resourcesFileName: resourcesFileNames) {
            result.append(loadJsResourceCodeString(resourcesFileName));
        }
        return result.toString();
    }

    private String loadJsResourceCodeString(String fileName) {
        return "load(" + getJsResourceUrl(fileName) + ");";
    }

    private String getJsResourceUrl(String fileName) {
        return "resClass.getResource(" +
                "'" + getJsResourcePath(fileName) + "'" +
                ")";
    }

    private String getJsResourcePath(String fileName) {
        return File.separator + "js" +
                File.separator + fileName;
    }
}