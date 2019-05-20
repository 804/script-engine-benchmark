package org.eightofour.scripting.benchmark.common.cases.impl.extention;

import org.eightofour.scripting.benchmark.common.cases.impl.common.InterpretationEvalCase;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.Bindings;
import javax.script.ScriptException;
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
    private Bindings bindings;


    /**
     * Setup method for JSR-223 script interpretation
     * with passed bindings (eval(Reader, Bindings)) benchmark case.
     *
     * @throws ScriptException if error will be occurred during
     *                         script engine configuration
     */
    @Setup
    public void prepare() throws ScriptException {
        super.prepare();
        bindings = scriptEngine.createBindings();
    }

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