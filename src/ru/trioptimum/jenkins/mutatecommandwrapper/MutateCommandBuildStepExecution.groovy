package ru.trioptimum.jenkins.mutatecommandwrapper

import com.cloudbees.groovy.cps.NonCPS
import hudson.LauncherDecorator
import org.jenkinsci.plugins.workflow.steps.AbstractStepExecutionImpl
import org.jenkinsci.plugins.workflow.steps.BodyExecutionCallback
import org.jenkinsci.plugins.workflow.steps.BodyInvoker
import org.jenkinsci.plugins.workflow.steps.StepContext

public class MutateCommandBuildStepExecution extends AbstractStepExecutionImpl {
    MutateCommandBuildStepExecution(StepContext context) {
        super(context);
    }

    @Override
    @NonCPS
    public boolean start() throws Exception {
        getContext().newBodyInvoker().
                withContext(BodyInvoker.mergeLauncherDecorators(getContext().get(LauncherDecorator.class), new MutateCommandLauncherDecorator())).
                withCallback(BodyExecutionCallback.wrap(getContext())).
                start();
        return false;
    }

    @Override
    @NonCPS
    public void onResume() {}
}
