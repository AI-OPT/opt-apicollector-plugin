package com.ai.runner.gradle.plugin;

/**
 * Created by astraea on 2015/7/8.
 */
public enum MemberLevel {
    PRIVATE, PUBLIC, PROTECTED;

    public String getlevelString() {
        return "-" + this.name().toLowerCase();
    }
}
