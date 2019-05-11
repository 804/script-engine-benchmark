package org.eightofour.scripting.benchmark.common.cases.impl.common;

import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.*;
import java.io.InputStreamReader;

/**
 * Abstract class for JSR-223 compiled script
 * common evaluation (eval()) benchmark case.
 */
public abstract class CompiledEvalCase extends InterpretationEvalCase {
    /**
     * Cache with precompiled scripts.
     */
    protected CompiledScript[] compiledScripts;

    /**
     * Setup method for compiled script benchmark common evaluation case.
     *
     * @throws ScriptException if error occurred during script engine
     *                         preparation or scripts compilation
     */
    @Setup
    public void prepare() throws ScriptException {
        super.prepare();
        String[] scriptFileNames = getScriptFileNamesForIndexedCache();
        compiledScripts = new CompiledScript[scriptFileNames.length];
        for (int i = 0; i < scriptFileNames.length; i++) {
            String scriptFileName = scriptFileNames[i];
            compileAndPut(i, scriptFileName);
        }
    }

    /**
     * Common method for compiled script benchmark common evaluation case performing.
     *
     * @param index     - index of cached script (see passed file name index
     *                    from getScriptFileNamesForIndexedCache() method)
     * @param blackhole - {@link Blackhole} instance for
     *                    dead code elimination preventing
     * @throws ScriptException if error will be occurred during script evaluation
     */
    protected void evalScript(int index, Blackhole blackhole)
            throws ScriptException {
        Object result = compiledScripts[index].eval();
        blackhole.consume(result);
    }

    private void compileAndPut(int index, String scriptFileName)
            throws ScriptException {
        compiledScripts[index] =
            ((Compilable) scriptEngine).compile(
                new InputStreamReader(
                    getResourceClass().getResourceAsStream(scriptFileName)
                )
            );
    }

    /**
     * Getter for script file names for caching.
     *
     * @return script file names for caching
     */
    protected abstract String[] getScriptFileNamesForIndexedCache();
}