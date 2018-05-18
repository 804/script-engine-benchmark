package com.netcracker.mediation.scripting.benchmark.cases.impl.common;

import com.netcracker.mediation.scripting.benchmark.cases.api.ClassResourceDependent;
import com.netcracker.mediation.scripting.benchmark.cases.api.ScriptEngineHolder;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Abstract class for JSR-223 script common
 * interpretation (eval(Reader)) benchmark case.
 */
public abstract class InterpretationEvalCase
        implements ScriptEngineHolder, ClassResourceDependent {
    /**
     * JSR-223 script engine.
     */
    protected ScriptEngine scriptEngine;

    /**
     * Setup method for JSR-223 script
     * common interpretation (eval(Reader)) benchmark case.
     *
     * @throws ScriptException if error will be occurred during
     *                         script engine configuration
     */
    @Setup
    public void prepare() throws ScriptException {
        scriptEngine = getScriptEngine();
        ScriptContext context = scriptEngine.getContext();
        context.setWriter(
            new OutputStreamWriter(
                new OutputStream() {
                    public void write(int b) { /* NOP */ }
                }
            )
        );
        scriptEngine.setContext(context);
        postEngineCreationAction();
    }

    /**
     * Common method for script interpreting.
     *
     * @param scriptFileName - interpreted script file name
     * @param blackhole      - {@link Blackhole} instance for
     *                         dead code elimination preventing
     * @throws ScriptException if error will be occurred during
     *                         script evaluation
     */
    protected void evalScript(String scriptFileName, Blackhole blackhole)
            throws ScriptException {
        Object result = scriptEngine.eval(
                new InputStreamReader(
                    getResourceClass().getResourceAsStream(
                        scriptFileName
                    )
                )
        );
        blackhole.consume(result);
    }

    /**
     * Action-method performed after script engine creation.
     */
    protected void postEngineCreationAction() { /* NOP */ }
}