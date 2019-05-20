package org.eightofour.scripting.benchmark.common.functions;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * Simple example for Java object which may be invoked
 * as function from Rhino Java Script engine.
 */
public class RhinoFunction extends BaseFunction {
    @Override
    public Object call(Context context, Scriptable scope, Scriptable thisObject, Object[] args) {
        return 1;
    }
}