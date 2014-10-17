package com.imaginatelabs.shellbert;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.util.Map;

/**
 * Wraps the api of org.apache.commons.exec.CommandLine to create and run rich commands.
 */
public class Cmd{

    private CommandLine commandLine;

    public Cmd(String executable) {
        commandLine = new CommandLine(executable);
    }

    public Cmd(File executable) {
        commandLine = new CommandLine(executable);
    }

    public Cmd(CommandLine other) {
        commandLine = new CommandLine(other);
    }

    public Cmd(Cmd other) {
        commandLine = new CommandLine(other.getCommandLine());
    }

    public String getExecutable() {
        return commandLine.getExecutable();
    }

    public boolean isFile() {
        return commandLine.isFile();
    }

    public CommandLine addArguments(String[] addArguments) {
        return commandLine.addArguments(addArguments);
    }

    public CommandLine addArguments(String[] addArguments, boolean handleQuoting) {
        return commandLine.addArguments(addArguments, handleQuoting);
    }

    public CommandLine addArguments(String addArguments) {
        return commandLine.addArguments(addArguments);
    }

    public CommandLine addArguments(String addArguments, boolean handleQuoting) {
        return commandLine.addArguments(addArguments, handleQuoting);
    }

    public CommandLine addArgument(String argument) {
        return commandLine.addArgument(argument);
    }

    public CommandLine addArgument(String argument, boolean handleQuoting) {
        return commandLine.addArgument(argument, handleQuoting);
    }

    public String[] getArguments() {
        return commandLine.getArguments();
    }

    public Map getSubstitutionMap() {
        return commandLine.getSubstitutionMap();
    }

    public void setSubstitutionMap(Map substitutionMap) {
        commandLine.setSubstitutionMap(substitutionMap);
    }

    public String[] toStrings() {
        return commandLine.toStrings();
    }

    public String toString() {
        return commandLine.toString();
    }

    public static Cmd parse(String line){
        return new Cmd(CommandLine.parse(line));
    }

    public static Cmd parse(String line, Map substitutionMap) {
        return new Cmd(CommandLine.parse(line, substitutionMap));
    }

    public CommandLine getCommandLine() {
        return commandLine;
    }
}
