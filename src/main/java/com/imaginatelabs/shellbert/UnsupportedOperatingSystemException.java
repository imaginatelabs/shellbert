package com.imaginatelabs.shellbert;

public class UnsupportedOperatingSystemException extends Exception {
    public UnsupportedOperatingSystemException() {
        super(String.format("There are no commands to run on the Operating System %s",
                OperatingSystemUtils.detectOperatingSystem().toString()));
    }
}
