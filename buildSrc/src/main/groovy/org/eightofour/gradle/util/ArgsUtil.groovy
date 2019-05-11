package org.eightofour.gradle.util

import groovy.transform.CompileStatic
import org.gradle.api.tasks.JavaExec

/**
 * Util class for arguments setting for {@link JavaExec} task.
 * Sets argument as project property value in case if passed values
 * is not applicable as argument value.
 * If project property value isn't applicable too,
 * then argument won't be set.
 */
final class ArgsUtil {
    @CompileStatic
    private ArgsUtil() {
        throw new IllegalStateException("This class isn't instantiated")
    }

    /**
     * Sets argument {@link JavaExec} task as {@link String} value.
     *
     * @param value   - value passed for argument setting
     * @param argName - set argument name (and fetched project property too)
     * @param task    - run {@link JavaExec} instance
     */
    @CompileStatic
    static void addArg(String value, String argName, JavaExec task) {
        value = value ?: task.project.properties[argName]
        if (value) task.args(argName + '=' + value)
    }

    /**
     * Sets argument {@link JavaExec} task as integer value.
     *
     * @param value        - value passed for argument setting
     * @param lowThreshold - excluded low threshold for integer value applicability
     *                       in case of argument setting
     * @param argName      - set argument name (and fetched project property too)
     * @param task         - run {@link JavaExec} instance
     */
    @CompileStatic
    static void addArg(int value, int lowThreshold, String argName, JavaExec task) {
        def property = task.project.properties[argName]
        value = value > lowThreshold ? value : (property != null ? property as int : lowThreshold)
        if (value > lowThreshold) task.args(argName + '=' + value)
    }

    /**
     * Sets argument {@link JavaExec} task as boolean value.
     *
     * @param value   - value passed for argument setting
     * @param argName - set argument name (and fetched project property too)
     * @param task    - run {@link JavaExec} instance
     */
    static void addArg(boolean value, String argName, JavaExec task) {
        def property = task.project.properties[argName]
        value = value ?: (property == null ? false : property.toBoolean())
        task.args(argName + '=' + value.toBoolean())
    }
}