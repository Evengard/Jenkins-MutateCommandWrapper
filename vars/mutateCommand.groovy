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
}