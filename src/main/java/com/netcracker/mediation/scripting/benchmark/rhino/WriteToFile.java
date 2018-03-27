package com.netcracker.mediation.scripting.benchmark.rhino;

import jdk.nashorn.api.scripting.AbstractJSObject;
import org.mozilla.javascript.*;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile implements Callable{

    @Override
    public Object call(Context context, Scriptable scriptable, Scriptable scriptable1, Object[] args) {
        String fileName = args[0].toString();
        Object arg = args[1];
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append(arg.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
