package com.netcracker.mediation.scripting.benchmark.common.cases.api;

import javax.script.ScriptEngine;

/**
 * Interface for entity which holds
 * {@link ScriptEngine} instance.
 */
public interface ScriptEngineHolder {
    /**
     * Getter for used script engine.
     *
     * @return hold {@link ScriptEngine} instance
     */
    ScriptEngine getScriptEngine();
}