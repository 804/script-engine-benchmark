package com.netcracker.mediation.scripting.benchmark.common.cases.api;

/**
 * Interface which define that entity
 * uses Java class'es resources.
 */
public interface ClassResourceDependent {
    /**
     * Getter for class for resource getting.
     *
     * @return class for resource getting
     */
    Class<?> getResourceClass();
}