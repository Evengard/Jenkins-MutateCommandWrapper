package ru.trioptimum.jenkins.mutatecommandwrapper

import org.jenkinsci.plugins.workflow.steps.Step
import org.jenkinsci.plugins.workflow.steps.AbstractStepExecutionImpl
import org.jenkinsci.plugins.workflow.steps.StepDescriptor
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractSynchronousNonBlockingStepExecution;
import org.jenkinsci.plugins.workflow.steps.StepContextParameter;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.EnvVars;
import hudson.Extension;
import java.io.IOException
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import com.cloudbees.groovy.cps.NonCPS

public class MutateCommandBuildStep extends Step {
    @DataBoundConstructor
    public MutateCommandBuildStep() {
    }

    @NonCPS
    public static DescriptorImpl getDescriptorInstance() {
        return new DescriptorImpl()
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new Execution(context);
    }

    public static class Execution extends AbstractStepExecutionImpl {

        private static final long serialVersionUID = 1;

        Execution(StepContext context) {
            super(context);
        }

        @Override
        public boolean start() throws Exception {
            getContext().newBodyInvoker().
                    withContext(BodyInvoker.mergeLauncherDecorators(getContext().get(LauncherDecorator.class), new MutateCommandLauncherDecorator())).
                    withCallback(BodyExecutionCallback.wrap(getContext())).
                    start();
            return false;
        }

        @Override
        public void onResume() {}
    }

    @Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {
        public DescriptorImpl() {
            super(Execution.class)
        }


        @Override
        @NonCPS
        public String getFunctionName() {
            return "mutateCommandStep";
        }

        @NonNull
        @Override
        @NonCPS
        public String getDisplayName() {
            return "";
        }

        @Override
        @NonCPS
        public boolean takesImplicitBlockArgument() {
            return true;
        }

        @Override
        @NonCPS
        public boolean isAdvanced() {
            return true;
        }

        @NonCPS
        public void StepInvoker(org.jenkinsci.plugins.workflow.cps.DSL steps, Object args) {
            def list = StepDescriptor.all()
            list.add(this)
            steps.invokeMethod("mutateCommandStep", args)
            /*def stepInvoker = steps.class.getDeclaredMethod("invokeStep", org.jenkinsci.plugins.workflow.steps.StepDescriptor.class, Object.class);
            stepInvoker.setAccessible(true);
            stepInvoker.invoke(steps, this, args)*/
        }
    }
}
