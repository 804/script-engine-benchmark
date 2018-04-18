package com.netcraker.mediation.gradle.task

import org.gradle.api.tasks.GradleBuild

/**
 * Task for sequential running all specified task in list order.
 * Passes all project properties to the run internal build.
 */
class SequentialGradleBuild extends GradleBuild {
    SequentialGradleBuild() {
        super()
        group = 'benchmark'
        startParameter.projectProperties = project.getGradle()
                .getStartParameter()
                .getProjectProperties()

        for (int i = 0; i < tasks.size - 1; i++) {
            project.tasks.findByName(tasks[i + 1]).mustRunAfter tasks[i]
        }
    }
}