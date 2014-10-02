package com.imaginatelabs.shellbert;

import java.util.Map;

/**
 * Maps a command against an operating system.
 */
public class OSCmd {

    private final OS os;
    private final Cmd cmd;

    public OSCmd(OS os, Cmd cmd) {
        this.os = os;
        this.cmd = cmd;
    }

    public OSCmd(OS os, String line) {
        this.os = os;
        this.cmd = Cmd.parse(line);
    }

    public OSCmd(OS os, String line, Map substitutionMap) {
        this.os = os;
        this.cmd = Cmd.parse(line,substitutionMap);
    }

    public OS getOs() {
        return os;
    }

    public Cmd getCmd() {
        return cmd;
    }
}
