import ru.trioptimum.jenkins.mutatecommandwrapper.MutateCommandBuildStepExecution
import org.jenkinsci.plugins.workflow.steps.StepExecution
import com.cloudbees.groovy.cps.NonCPS

@NonCPS
Closure<StepExecution> construct(Map params=[:]) {
    return { stepCtx ->
        new MutateCommandBuildStepExecution(stepCtx, params)
    }
}

def call(Map params=[:], Closure body) {
    wrapStep(construct(params)) {
        body()
    }
}