package com.netcracker.mediation.scripting.benchmark.cases.impl.extention;

import com.netcracker.mediation.scripting.benchmark.cases.impl.common.InterpretationEvalCase;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.InputStreamReader;

/**
 * Abstract class for JSR-223 script interpretation
 * with passed bindings (eval(Reader, Bindings)) benchmark case.
 */
public abstract class InterpretationEvalWithBindingCase
        extends InterpretationEvalCase {
    /**
     * Empty bindings for script evaluation.
     */
    private Bindings bindings = new SimpleBindings();

    @Override
    protected void evalScript(String scriptFileName, Blackhole blackhole)
            throws ScriptException {
        Object result = scriptEngine.eval(
                new InputStreamReader(
                    getResourceClass().getResourceAsStream(
                        scriptFileName
                    )
                ),
                bindings
        );
        blackhole.consume(result);
    }
}