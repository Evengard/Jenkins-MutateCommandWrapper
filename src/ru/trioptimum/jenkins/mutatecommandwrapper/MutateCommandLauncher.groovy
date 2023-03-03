package ru.trioptimum.jenkins.mutatecommandwrapper;

import hudson.Launcher;
import hudson.FilePath;
import hudson.Proc;
import com.cloudbees.groovy.cps.NonCPS

import java.util.logging.Logger
import java.util.logging.Level


public class MutateCommandLauncher extends Launcher.DecoratedLauncher {
    private static final Logger LOGGER = Logger.getLogger(MutateCommandLauncher.class.getName());
    private Launcher outer;

    MutateCommandLauncher(Launcher launcher) {
        super(launcher)
        outer = launcher
        LOGGER.log(Level.WARNING, "Create mutator");
    }

    @NonCPS
    @Override
    public Proc launch(String[] cmd, boolean[] mask, String[] env, InputStream inp, OutputStream out, FilePath workDir) throws IOException {
        return launch(launch().cmds(cmd).masks(mask).envs(env).stdin(inp).stdout(out).pwd(workDir));
    }

    @NonCPS
    @Override
    public Proc launch(ProcStarter starter) throws IOException {

        List<String> args = [ '/srv/tools/tini', "-s", "--" ]
        LOGGER.log(Level.WARNING, "BeforeMutating");
        starter.cmds().each{ value ->
            LOGGER.log(Level.WARNING, value)
        }
        starter.cmds().addAll(0, args)
        LOGGER.log(Level.WARNING, "AfterMutating");
        starter.cmds().each{ value ->
            LOGGER.log(Level.WARNING, value)
        }
        return super.launch(starter)
    }
}
