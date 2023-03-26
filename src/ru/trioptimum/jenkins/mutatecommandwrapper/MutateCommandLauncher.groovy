package ru.trioptimum.jenkins.mutatecommandwrapper

import com.cloudbees.groovy.cps.NonCPS
import hudson.FilePath
import hudson.Launcher
import hudson.Proc

public class MutateCommandLauncher extends Launcher.DecoratedLauncher {
    private Launcher outer;

    MutateCommandLauncher(Launcher launcher) {
        super(launcher)
        outer = launcher
    }

    @NonCPS
    @Override
    public Proc launch(String[] cmd, boolean[] mask, String[] env, InputStream inp, OutputStream out, FilePath workDir) throws IOException {
        return launch(launch().cmds(cmd).masks(mask).envs(env).stdin(inp).stdout(out).pwd(workDir));
    }

    @NonCPS
    @Override
    public Proc launch(ProcStarter starter) throws IOException {

        List<String> args = [ '/srv/tools/tini', "-s", "-g", "--" ]
        starter.cmds().addAll(0, args)
        return super.launch(starter)
    }
}
