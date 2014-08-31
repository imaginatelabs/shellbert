package com.imaginatelabs.jterminalexec;

import java.util.Locale;

public final class OperatingSystemUtils {

    ;

    protected static OperatingSystem cachedOperatingSystem;

    public static OperatingSystem detectOperatingSystem() {
        if (cachedOperatingSystem == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                cachedOperatingSystem = OperatingSystem.MacOSX;
            } else if (OS.indexOf("win") >= 0) {
                cachedOperatingSystem = OperatingSystem.Windows;
            } else if (OS.indexOf("nux") >= 0) {
                cachedOperatingSystem = OperatingSystem.Linux;
            } else {
                cachedOperatingSystem = OperatingSystem.Other;
            }
        }
        return cachedOperatingSystem;
    }
}
