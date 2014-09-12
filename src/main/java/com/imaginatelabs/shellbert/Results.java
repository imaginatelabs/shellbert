package com.imaginatelabs.shellbert;

import org.codehaus.plexus.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

public class Results {

    private final ByteArrayOutputStream stdOut;
    private final ByteArrayOutputStream stdErr;
    private int exitCode;

    public Results(int exitCode, ByteArrayOutputStream stdOut, ByteArrayOutputStream stdErr) {
        this.exitCode = exitCode;
        this.stdOut = stdOut;
        this.stdErr = stdErr;
    }

    public int getExitCode() {
        return exitCode;
    }

    public ByteArrayOutputStream getStdOut() {
        return stdOut;
    }

    public String stdOutToString(){
        return stdOut.toString().trim();
    }

    public boolean stdOutMatches(String pattern, int flag){
        return Pattern.compile(pattern, flag).matcher(stdOut.toString()).find();
    }

    public boolean stdOutMatches(String pattern){
        return stdOutMatches(pattern, Pattern.DOTALL);
    }

    public boolean stdOutContains(String searchString) {
        return StringUtils.contains(stdOut.toString(), searchString);
    }

    public ByteArrayOutputStream getStdErr() {
        return stdErr;
    }

    public String stdErrToString(){
        return stdErr.toString().trim();
    }

    public boolean stdErrMatches(String pattern, int flag){
        return Pattern.compile(pattern, flag).matcher(stdErr.toString()).find();
    }

    public boolean stdErrMatches(String pattern){
        return stdErrMatches(pattern, Pattern.DOTALL);
    }

    public boolean stdErrContains(String searchString) {
        return StringUtils.contains(stdErr.toString(), searchString);
    }

    public String stdToString(){
        return (stdOut.toString().trim() +"\n" +stdErr.toString().trim()).trim();
    }

    public boolean stdMatches(String pattern, int flag){
        return stdOutMatches(pattern, flag) || stdErrMatches(pattern, flag);
    }

    public boolean stdMatches(String pattern){
        return stdOutMatches(pattern) || stdErrMatches(pattern);
    }

    public boolean stdContains(String searchString) {
        return stdOutContains(searchString) || stdErrContains(searchString);
    }
}
