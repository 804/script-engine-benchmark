package com.netcracker.mediation.scripting.benchmark.common.cases.impl.common;

import com.netcracker.mediation.scripting.benchmark.common.cases.api.ClassResourceDependent;
import com.netcracker.mediation.scripting.benchmark.common.cases.api.ScriptEngineHolder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptException;
import java.io.InputStreamReader;

/**
 * Abstract class for JSR-223 script compilation benchmark case.
 */
public abstract class CompilationCase
        implements ScriptEngineHolder, ClassResourceDependent {
    /**
     * JSR-223 script compiler.
     */
    private Compilable compiler;

    /**
     * Setup method for compilation script benchmark case.
     */
    @Setup
    public void prepare() {
        compiler = (Compilable) getScriptEngine();
    }

    /**
     * Main benchmark method compilation script benchmark case.
     *
     * @param blackhole - {@link Blackhole} instance for
     *                    dead code elimination preventing
     * @throws ScriptException if error will be occurred
     *                         during script compilation
     */
    @Benchmark
    public void scriptCompilation(Blackhole blackhole)
            throws ScriptException {
        CompiledScript script = compiler.compile(
                new InputStreamReader(
                    getResourceClass().getResourceAsStream(
                        getScriptFileName()
                    )
                )
        );
        blackhole.consume(script);
    }

    /**
     * Getter for compiled script file name.
     *
     * @return compiled script file name
     */
    protected abstract String getScriptFileName();
}