package com.netcracker.mediation.scripting.benchmark.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.file.Files.*;

@UtilityClass
public final class FileUtils {

    @SneakyThrows
    public void createAndWrite(String fileName, Object content) {
        Path path = Paths.get(fileName);
        createDirectories(path.getParent());
        write(path, content.toString().getBytes(), StandardOpenOption.CREATE_NEW);
    }
}