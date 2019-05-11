package org.eightofour.scripting.benchmark.function.graaljs;

import org.eightofour.scripting.benchmark.util.FileUtils;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyExecutable;

public class WriteToFile implements ProxyExecutable {
    @Override
    public Object execute(Value... arguments) {
        FileUtils.createAndWrite(arguments[0].toString(), arguments[1].toString());
        return null;
    }
}
