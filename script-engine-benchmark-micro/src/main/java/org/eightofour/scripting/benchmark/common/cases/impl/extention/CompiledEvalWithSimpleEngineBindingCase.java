package org.eightofour.scripting.benchmark.common.cases.impl.extention;

import org.eightofour.scripting.benchmark.common.cases.impl.common.CompiledEvalCase;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.ScriptException;

/**
 * Abstract class for JSR-223 compiled script evaluation with
 * passed script context (eval(ScriptContext)) benchmark case.
 */
public abstract class CompiledEvalWithSimpleEngineBindingCase
        extends CompiledEvalCase {
    @Override
    protected void evalScript(int index, Blackhole blackhole)
            throws ScriptException {
        Object result = compiledScripts[index].eval();
        blackhole.consume(result);
    }

    @Override
    protected void postEngineCreationAction() {
        scriptEngine.put(getBindingName(), getBindingValue());
    }

    /**
     * Getter for binding name.
     *
     * @return binding name
     */
    protected abstract String getBindingName();

    /**
     * Getter for binding value.
     *
     * @return binding value
     */
    protected abstract Object getBindingValue();
}
