package com.netcracker.mediation.scripting.benchmark.common.functions;

import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyExecutable;

/**
 * Simple example for Java object which may be invoked
 * as function from GraalJS engine.
 */
public class GraalJSFunction implements ProxyExecutable {
    @Override
    public Object execute(Value... arguments) {
        return 1;
    }
}