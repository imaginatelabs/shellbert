package com.imaginatelabs.jterminalexec;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.*;

public class JTerminalExec {
    public static Results exec(String line) throws IOException {
        return JTerminalExec.exec(CommandLine.parse(line));
    }

    public static Results exec(CommandLine commandLine) throws IOException {

        DefaultExecutor exec = new DefaultExecutor();
        ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
        ByteArrayOutputStream stdErr = new ByteArrayOutputStream();
        exec.setStreamHandler(new PumpStreamHandler(stdOut,stdErr));
        int exitCode = -1;
        try {
            exitCode = exec.execute(commandLine);
        }catch (ExecuteException e){
            exitCode = e.getExitValue();
        }
        return new Results(exitCode, stdOut, stdErr);
    }

    public static Results exec(MultiPlatformCommandLine multiPlatformCommandLine) throws UnsupportedOperatingSystemException, IOException {

        OperatingSystem os = OperatingSystemUtils.detectOperatingSystem();
        CommandLine commandLine;
        if(multiPlatformCommandLine.contains(os)){
            commandLine = multiPlatformCommandLine.get(os);
        }else if(multiPlatformCommandLine.contains(OperatingSystem.Default)){
            commandLine = multiPlatformCommandLine.get(OperatingSystem.Default);
        }else{
            throw new UnsupportedOperatingSystemException();
        }

        return JTerminalExec.exec(commandLine);
    }
}
