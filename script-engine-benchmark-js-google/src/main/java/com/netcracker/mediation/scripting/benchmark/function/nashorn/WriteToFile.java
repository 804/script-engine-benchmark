package com.netcracker.mediation.scripting.benchmark.function.nashorn;

import com.netcracker.mediation.scripting.benchmark.util.FileUtils;
import jdk.nashorn.api.scripting.AbstractJSObject;

public class WriteToFile extends AbstractJSObject{
    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        FileUtils.createAndWrite((String) args[0], args[1]);
        return null;
    }
}
