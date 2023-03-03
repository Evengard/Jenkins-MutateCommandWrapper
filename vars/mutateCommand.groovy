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
def call(Object... args) {
    echo this.steps.class.getCanonicalName()
    echo this.class.getCanonicalName()
    echo args.class.getCanonicalName()
    def descriptor = ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStep.DescriptorImpl.class
    def list = org.jenkinsci.plugins.workflow.steps.StepDescriptor.all()
    list.add(descriptor)
    steps.invokeMethod("ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStep", args)
    //def descr = new MutateCommandBuildStep.DescriptorImpl()
    /*def list = StepDescriptor.all()
    list.add(descriptor.getClass())
    def stepInvoker = steps.class.getDeclaredMethod("invokeStep", StepDescriptor.class, Object.class);
    stepInvoker.invoke(steps, descriptor, args)
    steps.invokeMethod("ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStep", args)*/
}