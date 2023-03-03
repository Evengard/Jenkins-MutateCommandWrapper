package ru.trioptimum.jenkins.mutatecommandwrapper

import hudson.LauncherDecorator
import hudson.Launcher
import hudson.model.Node
import com.cloudbees.groovy.cps.NonCPS
import java.util.logging.Logger
import java.util.logging.Level

public class MutateCommandLauncherDecorator extends LauncherDecorator {
    private static final Logger LOGGER = Logger.getLogger(MutateCommandLauncherDecorator.class.getName());

    public MutateCommandLauncherDecorator() {
        LOGGER.log(Level.WARNING, "Create decorator");
    }

    @NonCPS
    @Override
    public Launcher decorate(Launcher launcher, Node node)
    {
        LOGGER.log(Level.WARNING, "Decorating");
        return new MutateCommandLauncher(launcher);
    }
}
