package com.imaginatelabs.shellbert;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.util.Map;

/**
 * Wraps the api of org.apache.commons.exec.CommandLine to create and run rich commands.
 */
public class Cmd {

    /**
     * Wrapped from org.apache.commons.exec to make use less verbose
     */
    private CommandLine commandLine;

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Create a command line without any arguments.
     *
     * @param executable the executable
     */
    public Cmd(String executable) {
        commandLine = new CommandLine(executable);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Create a command line without any arguments.
     *
     * @param executable the executable file
     */
    public Cmd(File executable) {
        commandLine = new CommandLine(executable);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Copy constructor.
     *
     * @param other the instance to copy
     */
    public Cmd(CommandLine other) {
        commandLine = new CommandLine(other);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Copy constructor.
     *
     * @param other the instance to copy
     */
    public Cmd(Cmd other) {
        commandLine = new CommandLine(other.getCommandLine());
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Create a command line from a string.
     *
     * @param line the first element becomes the executable, the rest the arguments
     * @return the parsed command line
     * @throws IllegalArgumentException If line is null or all whitespace
     */
    public static Cmd parse(String line) {
        return new Cmd(CommandLine.parse(line));
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Create a command line from a string.
     *
     * @param line            the first element becomes the executable, the rest the arguments
     * @param substitutionMap the name/value pairs used for substitution
     * @return the parsed command line
     * @throws IllegalArgumentException If line is null or all whitespace
     */
    public static Cmd parse(String line, Map substitutionMap) {
        return new Cmd(CommandLine.parse(line, substitutionMap));
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Returns the executable.
     *
     * @return The executable
     */
    public String getExecutable() {
        return commandLine.getExecutable();
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Was a file being used to set the executable?
     *
     * @return true if a file was used for setting the executable
     */
    public boolean isFile() {
        return commandLine.isFile();
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Add multiple arguments. Handles parsing of quotes and whitespace.
     *
     * @param addArguments An array of arguments
     * @return The command line itself
     */
    public CommandLine addArguments(String[] addArguments) {
        return commandLine.addArguments(addArguments);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Add multiple arguments.
     *
     * @param addArguments  An array of arguments
     * @param handleQuoting Add the argument with/without handling quoting
     * @return The command line itself
     */
    public CommandLine addArguments(String[] addArguments, boolean handleQuoting) {
        return commandLine.addArguments(addArguments, handleQuoting);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Add multiple arguments. Handles parsing of quotes and whitespace.
     * Please note that the parsing can have undesired side-effects therefore
     * it is recommended to build the command line incrementally.
     *
     * @param addArguments An string containing multiple arguments.
     * @return The command line itself
     */
    public CommandLine addArguments(String addArguments) {
        return commandLine.addArguments(addArguments);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Add multiple arguments. Handles parsing of quotes and whitespace.
     * Please note that the parsing can have undesired side-effects therefore
     * it is recommended to build the command line incrementally.
     *
     * @param addArguments  An string containing multiple arguments.
     * @param handleQuoting Add the argument with/without handling quoting
     * @return The command line itself
     */
    public CommandLine addArguments(String addArguments, boolean handleQuoting) {
        return commandLine.addArguments(addArguments, handleQuoting);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Add a single argument. Handles quoting.
     *
     * @param argument The argument to add
     * @return The command line itself
     * @throws IllegalArgumentException If argument contains both single and double quotes
     */
    public CommandLine addArgument(String argument) {
        return commandLine.addArgument(argument);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Add a single argument.
     *
     * @param argument      The argument to add
     * @param handleQuoting Add the argument with/without handling quoting
     * @return The command line itself
     */
    public CommandLine addArgument(String argument, boolean handleQuoting) {
        return commandLine.addArgument(argument, handleQuoting);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Returns the expanded and quoted command line arguments.
     *
     * @return The quoted arguments
     */
    public String[] getArguments() {
        return commandLine.getArguments();
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * @return the substitution map
     */
    public Map getSubstitutionMap() {
        return commandLine.getSubstitutionMap();
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Set the substitutionMap to expand variables in the
     * command line.
     *
     * @param substitutionMap the map
     */
    public void setSubstitutionMap(Map substitutionMap) {
        commandLine.setSubstitutionMap(substitutionMap);
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Returns the command line as an array of strings.
     *
     * @return The command line as an string array
     */
    public String[] toStrings() {
        return commandLine.toStrings();
    }

    /**
     * Specified in org.apache.commons.exec.CommandLine
     *
     * Stringify operator returns the command line as a string.
     * Parameters are correctly quoted when containing a space or
     * left untouched if the are already quoted.
     *
     * @return the command line as single string
     */
    public String toString() {
        return commandLine.toString();
    }

    /**
     * Returns the wrapped CommandLine object
     *
     * @return the wrapped CommandLine object
     */
    public CommandLine getCommandLine() {
        return commandLine;
    }
}
