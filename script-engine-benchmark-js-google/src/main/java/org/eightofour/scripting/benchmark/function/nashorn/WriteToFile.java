package org.eightofour.scripting.benchmark.function.nashorn;

import org.eightofour.scripting.benchmark.util.FileUtils;
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
