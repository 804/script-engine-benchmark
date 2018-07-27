package com.netcracker.mediation.scripting.benchmark.common.cases.impl.extention;

import com.netcracker.mediation.scripting.benchmark.common.cases.impl.common.InterpretationEvalCase;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.ScriptContext;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Abstract class for JSR-223 script interpretation with passed
 * default script context (eval(Reader, ScriptContext)) benchmark case.
 */
public abstract class InterpretationEvalWithContextCase
        extends InterpretationEvalCase {
    /**
     * Default script context for script evaluation.
     */
    private ScriptContext defaultContext = new SimpleScriptContext();

    @Override
    protected void evalScript(String scriptFileName, Blackhole blackhole)
            throws ScriptException {
        Object result = scriptEngine.eval(
                new InputStreamReader(
                    getResourceClass().getResourceAsStream(
                        scriptFileName
                    )
                ),
                defaultContext
        );
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