package ru.trioptimum.jenkins.mutatecommandwrapper;

import hudson.tasks.BuildWrapper;
import jenkins.tasks.SimpleBuildWrapper;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.EnvVars;
import hudson.FilePath;
import hudson.Launcher;
import com.cloudbees.groovy.cps.NonCPS

public class MutateCommandBuildStepWrapper extends SimpleBuildWrapper {
    @NonCPS
    @Override
    public void setUp(SimpleBuildWrapper.Context context,
                      Run<?, ?> run,
                      FilePath workspace,
                      Launcher launcher,
                      TaskListener listener,
                      EnvVars initialEnvironment) throws IOException, InterruptedException {
    }

    @Override
    @NonCPS
    public Launcher decorateLauncher(final AbstractBuild build, final Launcher launcher, final BuildListener listener)  throws IOException, InterruptedException, Run.RunnerAbortedException {
        def decoLauncher = new MutateCommandLauncher(launcher)
        return decoLauncher
    }
}