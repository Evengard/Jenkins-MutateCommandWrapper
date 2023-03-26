package ru.trioptimum.jenkins.mutatecommandwrapper

import com.cloudbees.groovy.cps.NonCPS
import hudson.Launcher
import hudson.LauncherDecorator
import hudson.model.Node

public class MutateCommandLauncherDecorator extends LauncherDecorator {
    private Map params;

    public MutateCommandLauncherDecorator(Map params) {
        this.params = params;
    }

    @NonCPS
    @Override
    public Launcher decorate(Launcher launcher, Node node)
    {
        return new MutateCommandLauncher(launcher, params);
    }
}
