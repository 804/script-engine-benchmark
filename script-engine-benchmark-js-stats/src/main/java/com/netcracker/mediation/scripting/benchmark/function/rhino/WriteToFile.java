package com.netcracker.mediation.scripting.benchmark.function.rhino;

import com.netcracker.mediation.scripting.benchmark.util.FileUtils;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class WriteToFile implements Callable {

    @Override
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable1, Object[] args) {
        FileUtils.createAndWrite(args[0].toString(), args[1]);
        return null;
    }
}