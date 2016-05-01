package com.ai.runner.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class APICollectorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {
        target.getTasks().create("APICollect", APICollectorTask.class);
    }
}
