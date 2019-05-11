package org.eightofour.scripting.benchmark.common.cases.impl.extention;

import org.eightofour.scripting.benchmark.common.cases.impl.common.CompiledEvalCase;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.Bindings;
import javax.script.ScriptException;

/**
 * Abstract class for JSR-223 compiled script evaluation with
 * passed bindings (eval(Bindings)) benchmark case.
 */
public abstract class CompiledEvalWithBindingCase extends CompiledEvalCase {
    /**
     * Empty bindings for script evaluation.
     */
    private Bindings emptyBinding;

    /**
     * Setup method for JSR-223 compiled script evaluation with
     * passed bindings (eval(Bindings)) benchmark case.
     *
     * @throws ScriptException if error will be occurred during
     *                         script engine configuration
     */
    @Setup
    public void prepare() throws ScriptException {
        super.prepare();
        emptyBinding = scriptEngine.createBindings();
    }

    @Override
    protected void evalScript(int index, Blackhole blackhole)
            throws ScriptException {
        Object result = compiledScripts[index].eval(emptyBinding);
        blackhole.consume(result);
    }
}