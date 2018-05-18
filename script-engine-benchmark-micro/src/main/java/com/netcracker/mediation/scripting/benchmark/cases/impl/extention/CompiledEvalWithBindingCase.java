package com.netcracker.mediation.scripting.benchmark.cases.impl.extention;

import com.netcracker.mediation.scripting.benchmark.cases.impl.common.CompiledEvalCase;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.Bindings;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * Abstract class for JSR-223 compiled script evaluation with
 * passed bindings (eval(Bindings)) benchmark case.
 */
public abstract class CompiledEvalWithBindingCase extends CompiledEvalCase {
    /**
     * Empty bindings for script evaluation.
     */
    private Bindings emptyBinding = new SimpleBindings();

    @Override
    protected void evalScript(int index, Blackhole blackhole)
            throws ScriptException {
        Object result = compiledScripts[index].eval(emptyBinding);
        blackhole.consume(result);
    }
}