package com.netcracker.mediation.scripting.benchmark.common.functions;

/**
 * Simple example for Java object which may be invoked
 * as function from Jython Python engine.
 */
public class PythonFunction {
    public Object __call__(Object... args) {
        return 1;
    }
}