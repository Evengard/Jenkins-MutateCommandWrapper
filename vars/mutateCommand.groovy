/*def call(Closure body) {
    //def ctx = new ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandLauncherDecorator()
    def step = new ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStepWrapper()
    wrap(step) {
        body()
    }
    /*withContext(ctx) {
        body()
    }*/
//}

import ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStep

def call(Object... args) {
    echo this.steps.class.getCanonicalName()
    echo this.class.getCanonicalName()
    echo args.class.getCanonicalName()
    def descriptor = MutateCommandBuildStep.getDescriptorInstance()
    descriptor.StepInvoker(steps, args)
    def list = org.jenkinsci.plugins.workflow.steps.StepDescriptor.all()
    echo "list all descriptor names"
    list.each {value ->
        echo value.getFunctionName()
    }
    echo "end list all descriptor names"
    //steps.invokeMethod("ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStep", args)
    //def descr = new MutateCommandBuildStep.DescriptorImpl()
    /*def list = StepDescriptor.all()
    list.add(descriptor.getClass())*/
    /*def stepInvoker = steps.class.getDeclaredMethod("invokeStep", org.jenkinsci.plugins.workflow.steps.StepDescriptor.class, Object.class);
    stepInvoker.invoke(steps, descriptor, args)*/
    //descriptor.StepInvoker(steps, args)
    //steps.invokeMethod("mutateCommandStep", args)
}