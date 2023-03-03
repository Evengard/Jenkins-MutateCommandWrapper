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
}