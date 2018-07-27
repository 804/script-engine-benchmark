package com.netcracker.mediation.scripting.benchmark.common.functions;

import jdk.nashorn.api.scripting.AbstractJSObject;

/**
 * Simple example for Java object which may be invoked
 * as function from Nashorn Java Script engine.
 */
public class NashornFunction extends AbstractJSObject {
    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thisObject, Object... args) {
        return 1;
    }
}