package com.netcracker.mediation.scripting.benchmark.runner.nashorn;

import com.netcracker.mediation.scripting.benchmark.function.nashorn.WriteToFile;
import com.netcracker.mediation.scripting.benchmark.runner.AbstractBenchmark;

import javax.script.*;
import java.io.File;
import java.net.URL;
import java.util.Arrays;

/**
 * Main class for Nashorn JavaScript engine statistic benchmarks.
 */
public class NashornBenchmark extends AbstractBenchmark {
    private static final String GET_RES_CLASS_CODE =
            "var resClass = new (Java.type('java.lang.Object'))().getClass();";

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
                loadJsResourcesCode(
                    "lodash.min.js",
                            "platform.js",
                            "benchmark.js"
                )
            );
            SimpleScriptContext context = new SimpleScriptContext();
            context.setBindings(createBinding(i), ScriptContext.ENGINE_SCOPE);
            res[2 * i] = extScriptCompiled.eval(context);
            CompiledScript warmupScriptCompiled = ((Compilable) engine).compile(
                loadJsResourcesCode("warmup.js")
            );
            res[2 * i + 1] = warmupScriptCompiled.eval(context);
        }
        System.out.println("WarmUp end. Result: " + Arrays.toString(res));
    }

    @Override
    protected void runTests() throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        CompiledScript extScriptCompiled = ((Compilable) engine).compile(
            loadJsResourcesCode(
                "lodash.min.js",
                        "platform.js",
                        "benchmark.js"
            )
        );
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(0), ScriptContext.ENGINE_SCOPE);
        extScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
            loadJsResourcesCode("runner.js")
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