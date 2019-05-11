package org.eightofour.scripting.benchmark.runner.nashorn;

import org.eightofour.scripting.benchmark.function.nashorn.WriteToFile;
import org.eightofour.scripting.benchmark.runner.AbstractBenchmark;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * Main class for Nashorn JavaScript engine Google benchmarks.
 */
public class NashornBenchmark extends AbstractBenchmark {
    private static final String GET_RES_CLASS_CODE =
            "var resClass = new (Java.type('java.lang.Object'))().getClass();";

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
        List<ScriptEngineFactory> engineFactories = new ScriptEngineManager().getEngineFactories();
        NashornScriptEngineFactory scriptEngineFactory = engineFactories.stream()
                .filter(factory -> factory instanceof NashornScriptEngineFactory)
                .map(factory -> (NashornScriptEngineFactory) factory)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("NashornScriptEngineFactory is absence in ScriptEngineManager"));
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