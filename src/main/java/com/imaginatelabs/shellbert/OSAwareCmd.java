package com.imaginatelabs.shellbert;

import java.util.HashMap;
import java.util.Map;

/**
 * Specify a command that handles the variations across multiple operating system.
 */
public class OSAwareCmd {

    private final Map<OS, Cmd> cmds;

    /**
     * Constructor
     *
     * @param osCmds array that specifies the command (value) against the operating system (key) it should be run against.
     */
    public OSAwareCmd(OSCmd... osCmds) {
        Map<OS, Cmd> tempCmds = new HashMap<OS, Cmd>();
        for (OSCmd osCmd : osCmds) {
            tempCmds.put(osCmd.getOs(), osCmd.getCmd());
        }
        cmds = tempCmds;
    }

    /**
     * Check if there is a command that supports a specified operating system.
     *
     * @param OS operating system to check for.
     * @return True - if command exist for specified operating system.
     *
     * False - if command doesn't exist for specified operating system.
     */
    public boolean hasSupportFor(OS OS) {
        return cmds.containsKey(OS);
    }

    /**
     * Get the command for a specified operating system.
     *
     * @param OS for which to retrieve the operating system specific command.
     * @return Cmd command for the specified operating system, providing one as been set in the constructor.
     */
    public Cmd getCmdFor(OS OS) {
        return cmds.get(OS);
    }
}
