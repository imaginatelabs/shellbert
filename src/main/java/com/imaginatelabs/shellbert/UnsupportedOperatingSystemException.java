package com.imaginatelabs.shellbert;

/**
 * Throw to indicate that an operating system is not supported.
 */
public class UnsupportedOperatingSystemException extends Exception {
    public UnsupportedOperatingSystemException() {
        super(String.format("There are no commands to run on the operating system %s",
                OSUtils.detectOS().toString()));
    }
}
