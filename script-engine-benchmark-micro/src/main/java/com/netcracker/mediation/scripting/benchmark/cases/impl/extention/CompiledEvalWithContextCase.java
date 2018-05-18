package com.netcracker.mediation.scripting.benchmark.cases.impl.extention;

import com.netcracker.mediation.scripting.benchmark.cases.impl.common.CompiledEvalCase;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.*;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Abstract class for JSR-223 compiled script evaluation with
 * passed script context (eval(ScriptContext)) benchmark case.
 */
public abstract class CompiledEvalWithContextCase extends CompiledEvalCase {
    /**
     * Default script context for script evaluation.
     */
    private ScriptContext defaultContext = new SimpleScriptContext();

    @Override
    protected void evalScript(int index, Blackhole blackhole)
            throws ScriptException {
        Object result = compiledScripts[index].eval(defaultContext);
        blackhole.consume(result);
    }

    @Override
    protected void postEngineCreationAction() {
        defaultContext.setWriter(
            new OutputStreamWriter(
                new OutputStream() {
                    public void write(int b) { /* NOP */ }
                }
            )
        );
    }
}