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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

public class MutateCommandBuildStep extends Step {
    @DataBoundConstructor
    public MutateCommandBuildStep() {
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new Execution(context);
    }

    public static class Execution extends AbstractStepExecutionImpl {

        private static final long serialVersionUID = 1;

        @SuppressFBWarnings(value = "SE_TRANSIENT_FIELD_NOT_RESTORED", justification = "Only used when starting.")
        private transient final List<String> overrides;

        Execution(List<String> overrides, StepContext context) {
            super(context);
            this.overrides = overrides;
        }

        @Override
        public boolean start() throws Exception {
            Map<String, String> overridesM = new HashMap<>();
            for (String pair : overrides) {
                int split = pair.indexOf('=');
                assert split != -1;
                overridesM.put(pair.substring(0, split), pair.substring(split + 1));
            }
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
        @Override public String getFunctionName() {
            return "mutateCommandStep";
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return "";
        }

        @Override
        public boolean takesImplicitBlockArgument() {
            return true;
        }

        @Override
        public boolean isAdvanced() {
            return true;
        }
    }
}
