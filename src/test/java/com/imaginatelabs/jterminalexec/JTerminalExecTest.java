package com.imaginatelabs.jterminalexec;

import org.apache.commons.exec.CommandLine;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.regex.Pattern;

public class JTerminalExecTest {

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteUsingStringCommandLine() throws Exception {
        Results results = JTerminalExec.exec("pwd");

        Assert.assertEquals(results.getExitCode(), 0);
        Assert.assertTrue(results.stdOutMatches(".*JTerminalExec.*"));
        Assert.assertTrue(results.stdOutMatches(".*JTerminalExec.*", Pattern.DOTALL));
        Assert.assertTrue(results.stdOutMatches(".*JTerminalExec.*", Pattern.MULTILINE));
        Assert.assertTrue(results.stdOutContains("JTerminalExec"));
        Assert.assertFalse(results.stdOutContains("This Does Not Exist"));
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteUsingObjectCommandLine() throws Exception {
        CommandLine commandLine = CommandLine.parse("pwd");
        Results results = JTerminalExec.exec(commandLine);
        Assert.assertTrue(results.stdContains("JTerminalExec"));
        Assert.assertEquals(results.getExitCode(), 0);
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteMacOSXCommandUsingMultiPlatformCommandLine() throws Exception {

        Results results = JTerminalExec.exec(new MultiPlatformCommandLine(
            new HashMap<OperatingSystem,CommandLine>(){{
                put(OperatingSystem.MacOSX,CommandLine.parse("pwd"));
                put(OperatingSystem.Windows,CommandLine.parse("pwd"));
            }}
        ));

        Assert.assertTrue(results.stdOutContains("JTerminalExec"));
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteDefaultUsingMultiPlatformCommandLine() throws Exception {

        Results results = JTerminalExec.exec(new MultiPlatformCommandLine(
            new HashMap<OperatingSystem,CommandLine>(){{
                put(OperatingSystem.Default,CommandLine.parse("pwd"));
                put(OperatingSystem.Windows,CommandLine.parse("pwd"));
            }}
        ));

        Assert.assertTrue(results.stdOutContains("JTerminalExec"));
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldReadStdErr() throws Exception {
        Results results = JTerminalExec.exec("cd foo");
        Assert.assertTrue(results.stdErrContains("No such file or directory"));
        Assert.assertEquals(results.getExitCode(), 1);
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldReadStdAll() throws Exception {
        Results results = JTerminalExec.exec("cd foo");
        Assert.assertTrue(results.stdContains("No such file or directory"));
        Assert.assertEquals(results.getExitCode(), 1);
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldTrimStdOutToStringMethodsToHaveNoNewLineAtEnd() throws Exception {
        Results results = JTerminalExec.exec(new MultiPlatformCommandLine(
                new HashMap<OperatingSystem,CommandLine>(){{
                    put(OperatingSystem.Default,CommandLine.parse("ping -c 4 127.0.0.1"));
                    put(OperatingSystem.Windows,CommandLine.parse("ping 127.0.0.1"));
                }}
        ));
        
        Assert.assertFalse(Pattern.compile("\n$").matcher(results.stdToString()).find());
        Assert.assertFalse(Pattern.compile("\n$").matcher(results.stdOutToString()).find());
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldTrimStdErrToStringMethodsToHaveNoNewLineAtEnd() throws Exception {
        Results results = JTerminalExec.exec("ps foo");

        Assert.assertFalse(Pattern.compile("\n$").matcher(results.stdToString()).find());
        Assert.assertFalse(Pattern.compile("\n$").matcher(results.stdErrToString()).find());
    }
}
