package com.netcracker.mediation.scripting.benchmark.nashorn;

import jdk.nashorn.api.scripting.AbstractJSObject;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile  extends AbstractJSObject{
    @Override
    public boolean isFunction() {
        return true;
    }

    @Override
    public Object call(Object thiz, Object... args) {
        String fileName = (String) args[0];
        Object arg = args[1];
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append(arg.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
