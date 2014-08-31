package com.imaginatelabs.jterminalexec;

import org.apache.commons.exec.CommandLine;

import java.util.HashMap;
import java.util.Map;

public class MultiPlatformCommandLine {

    private Map<OperatingSystem, CommandLine> commands = new HashMap<>();

    public MultiPlatformCommandLine(HashMap<OperatingSystem, CommandLine> commands) {
        this.commands = commands;
    }

    public boolean contains(OperatingSystem os) {
        return commands.containsKey(os);
    }

    public CommandLine get(OperatingSystem os) {
        return commands.get(os);
    }
}
