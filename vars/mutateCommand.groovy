import ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStepExecution
import org.jenkinsci.plugins.workflow.steps.StepExecution
import com.cloudbees.groovy.cps.NonCPS

@NonCPS
Closure<StepExecution> construct() {
    return { stepCtx ->
        new MutateCommandBuildStepExecution(stepCtx)
    }
}

def call(Closure body) {
    wrapStep(construct()) {
        body()
    }
}