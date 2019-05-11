package org.eightofour.scripting.benchmark.runner.graaljs;

import org.eightofour.scripting.benchmark.function.graaljs.WriteToFile;
import org.eightofour.scripting.benchmark.runner.AbstractBenchmark;

import javax.script.*;
import java.io.File;


/**
 * Main class for GraalJS JavaScript engine Google benchmarks.
 */
public class GraalJSBenchmark extends AbstractBenchmark {
    private static final String GET_RES_CLASS_CODE =
            "var resClass = new (Java.type('java.lang.Object'))().getClass();";

    public static void main(String[] args) throws Exception {
        new GraalJSBenchmark().run(args);
    }

    @Override
    protected void runTests(int iterations) throws ScriptException {
        ScriptEngine engine = getScriptEngine();
        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(createBinding(engine, iterations), ScriptContext.ENGINE_SCOPE);
        CompiledScript loadScriptCompiled = ((Compilable) engine).compile(
            loadJsResourcesCode(
                "base.js", "richards.js",
                "deltablue.js", "crypto.js",
                "raytrace.js", "earley-boyer.js",
                "regexp.js", "splay.js",
                "navier-stokes.js", "runner.js"
            )
        );
        loadScriptCompiled.eval(context);
        CompiledScript testScriptCompiled = ((Compilable) engine).compile(
            loadJsResourcesCode("benchmark.js")
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