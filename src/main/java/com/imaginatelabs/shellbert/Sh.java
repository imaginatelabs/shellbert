package com.imaginatelabs.shellbert;

import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A library to easily run and process shell commands on the local operating system.
 */
public class Sh {

    /**
     * Private constructor to ensure default public is not used.
     */
    private Sh() {
    }

    ;

    /**
     * Run a command against the local operating system.
     *
     * @param cmd wraps the api of org.apache.commons.exec.CommandLine to create and run rich commands.
     * @return Output - encapsulates exit code, stdOut and stdErr from a run command.
     * @throws IOException
     */
    public static Std run(Cmd cmd) throws IOException {

        DefaultExecutor exec = new DefaultExecutor();
        ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
        ByteArrayOutputStream stdErr = new ByteArrayOutputStream();
        exec.setStreamHandler(new PumpStreamHandler(stdOut, stdErr));
        int exitCode;
        try {
            exitCode = exec.execute(cmd.getCommandLine());
        } catch (ExecuteException e) {
            exitCode = e.getExitValue();
        }
        return new Std(exitCode, stdOut, stdErr);
    }

    /**
     * Wraps Shellbert.run(Cmd cmd) to simplify running a command.
     *
     * @param cmdString native command to be run in the local shell (unix, mac) or cmd (windows).
     * @return Output - encapsulates exit code, stdOut and stdErr from a run command.
     * @throws IOException
     */
    public static Std run(String cmdString) throws IOException {
        return Sh.run(Cmd.parse(cmdString));
    }

    /**
     * Wraps Shellbert.run(Cmd Cmd) to run commands against a specific operating by using the OSAwareCmd object.
     *
     * @param OSAwareCmd encapsulates operating system aware commands.
     * @return Output - encapsulates exit code, stdOut and stdErr from a run command.
     * @throws UnsupportedOperatingSystemException
     * @throws IOException
     */
    public static Std run(OSAwareCmd OSAwareCmd) throws UnsupportedOperatingSystemException, IOException {

        OS os = OSUtils.detectOS();
        Cmd cmd;
        if (OSAwareCmd.hasSupportFor(os)) {
            cmd = OSAwareCmd.getCmdFor(os);
        } else if (OSAwareCmd.hasSupportFor(OS.Default)) {
            cmd = OSAwareCmd.getCmdFor(OS.Default);
        } else {
            throw new UnsupportedOperatingSystemException();
        }

        return Sh.run(cmd);
    }
}
