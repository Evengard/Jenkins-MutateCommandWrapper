def call(Closure body) {
    //def ctx = new ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandLauncherDecorator()
    def step = new ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStepWrapper()
    wrap(step) {
        body()
    }
    /*withContext(ctx) {
        body()
    }*/
}