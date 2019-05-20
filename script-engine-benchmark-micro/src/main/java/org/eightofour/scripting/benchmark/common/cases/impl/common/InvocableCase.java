package org.eightofour.scripting.benchmark.common.cases.impl.common;

import org.eightofour.scripting.benchmark.common.cases.api.ClassResourceDependent;
import org.eightofour.scripting.benchmark.common.cases.api.ScriptEngineHolder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.*;
import java.io.InputStreamReader;

/**
 * Abstract class for JSR-223 script engine as {@link Invocable} using benchmark case.
 */
public abstract class InvocableCase
        implements ScriptEngineHolder, ClassResourceDependent {
    private static final String RESULT_VARIABLE_NAME = "__result";

    /**
     * JSR-223 script engine.
     */
    private ScriptEngine scriptEngine;
    /**
     * Evaluated script result.
     */
    private Object retrievedObject;

    /**
     * Setup method for JSR-223 script engine
     * as {@link Invocable} using benchmark case.
     *
     * @throws ScriptException if error will be occurred during
     *                         script engine configuration
     */
    @Setup
    public void prepare() throws ScriptException {
        scriptEngine = getScriptEngine();
        scriptEngine.eval(
            new InputStreamReader(
                getResourceClass().getResourceAsStream(
                    getScriptFileName()
                )
            )
        );
        Bindings bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        retrievedObject = bindings.get(RESULT_VARIABLE_NAME);
    }

    /**
     * Benchmark method for top-level function invokation
     * from script engine context.
     *
     * @param blackhole - {@link Blackhole} instance for
     *                    dead code elimination preventing
     * @throws ScriptException if will be occurred during
     *                         function evaluating
     * @throws NoSuchMethodException if requested function doesn't
     *                               exist in engine context
     */
    @Benchmark
    public void invokeFunction(Blackhole blackhole)
            throws ScriptException, NoSuchMethodException {
        Object result = ((Invocable) scriptEngine).invokeFunction(getMethodName());
        blackhole.consume(result);
    }

    /**
     * Benchmark method for method invokation from passed
     * object got from script engine context.
     *
     * @param blackhole - {@link Blackhole} instance for
     *                    dead code elimination preventing
     * @throws ScriptException if will be occurred during
     *                         method evaluating
     * @throws NoSuchMethodException if requested method doesn't
     *                               exist in engine context
     */
    @Benchmark
    public void invokeMethod(Blackhole blackhole)
            throws ScriptException, NoSuchMethodException {
        Object result = ((Invocable) scriptEngine).invokeMethod(
            retrievedObject, getResultMethodName()
        );
        blackhole.consume(result);
    }

    /**
     * Benchmark method for top-level scope from script engine
     * context wrapping as interface implementation.
     *
     * @param blackhole - {@link Blackhole} instance for
     *                    dead code elimination preventing
     */
    @Benchmark
    public void getInterface(Blackhole blackhole) {
        Object runnable = ((Invocable) scriptEngine).getInterface(getInterfaceForWrapping());
        blackhole.consume(runnable);
    }

    /**
     * Benchmark method for script result wrapping as interface implementation.
     *
     * @param blackhole - {@link Blackhole} instance for
     *                    dead code elimination preventing
     */
    @Benchmark
    public void getInterfaceFromProperty(Blackhole blackhole) {
        Object runnable = ((Invocable) scriptEngine).getInterface(
                retrievedObject, getResultInterfaceForWrapping()
        );
        blackhole.consume(runnable);
    }

    /**
     * Getter for evaluated script file name.
     *
     * @return evaluated script file name
     */
    protected abstract String getScriptFileName();

    /**
     * Getter for invoked top-level function name.
     *
     * @return invoked top-level function name
     */
    protected abstract String getMethodName();

    /**
     * Getter for invoked result script method name.
     *
     * @return invoked result script method name
     */
    protected abstract String getResultMethodName();

    /**
     * Getter for interface for top-level scope wrapping.
     *
     * @return interface for top-level scope wrapping
     */
    protected abstract Class<?> getInterfaceForWrapping();

    /**
     * Getter for interface for script result wrapping.
     *
     * @return interface for script result wrapping
     */
    protected abstract Class<?> getResultInterfaceForWrapping();
}