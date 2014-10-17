package com.imaginatelabs.shellbert;

import java.util.Locale;

/**
 * Utility to handle different operating systems functions.
 */
public final class OSUtils {

    private static OS cachedOS;

    /**
     * Detects the operating system, caching the results for subsequent look-ups.
     *
     * @return OperatingSystem - an enum representation (Windows, MacOSX, Unix, Solaris, Other) detected at runtime.
     */
    public static OS detectOS() {
        if (cachedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ) {
                cachedOS = com.imaginatelabs.shellbert.OS.Unix;
            } else if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                cachedOS = com.imaginatelabs.shellbert.OS.MacOSX;
            } else if (OS.indexOf("win") >= 0) {
                cachedOS = com.imaginatelabs.shellbert.OS.Windows;
            } else if (OS.indexOf("sunos") >= 0) {
                cachedOS = com.imaginatelabs.shellbert.OS.Solaris;
            } else {
                cachedOS = com.imaginatelabs.shellbert.OS.Other;
            }
        }
        return cachedOS;
    }
}
