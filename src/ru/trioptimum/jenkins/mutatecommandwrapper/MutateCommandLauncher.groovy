package ru.trioptimum.jenkins.mutatecommandwrapper

import com.cloudbees.groovy.cps.NonCPS
import hudson.FilePath
import hudson.Launcher
import hudson.Proc

public class MutateCommandLauncher extends Launcher.DecoratedLauncher {
    private Launcher outer;
    private Map params;

    MutateCommandLauncher(Launcher launcher, Map params) {
        super(launcher)
        outer = launcher
        this.params = params
    }

    @NonCPS
    @Override
    public Proc launch(String[] cmd, boolean[] mask, String[] env, InputStream inp, OutputStream out, FilePath workDir) throws IOException {
        return launch(launch().cmds(cmd).masks(mask).envs(env).stdin(inp).stdout(out).pwd(workDir));
    }

    @NonCPS
    @Override
    public Proc launch(ProcStarter starter) throws IOException {
        if (params.containsKey("prefix"))  {
            starter.cmds().addAll(0, params["prefix"])
        }
        if (params.containsKey("postfix")) {
            starter.cmds().addAll(params["postfix"])
        }
        if (params.containsKey("remove")) {
            starter.cmds().removeIf {it -> params["remove"].contains(it)}
        }
        return super.launch(starter)
    }
}
