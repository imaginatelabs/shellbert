package com.imaginatelabs.shellbert;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ShTest {

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteUsingStringCommandLine() throws Exception {
        Std std = Sh.run("pwd");

        Assert.assertEquals(std.getExitCode(), 0);
        Assert.assertTrue(std.outMatches(".*shellbert.*"));
        Assert.assertTrue(std.outMatches(".*shellbert.*", Pattern.DOTALL));
        Assert.assertTrue(std.outMatches(".*shellbert.*", Pattern.MULTILINE));
        Assert.assertTrue(std.outContains("shellbert"));
        Assert.assertFalse(std.outContains("This Does Not Exist"));
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteUsingObjectCommandLine() throws Exception {
        Std std = Sh.run(Cmd.parse("pwd"));
        Assert.assertTrue(std.contains("shellbert"));
        Assert.assertEquals(std.getExitCode(), 0);
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteMacOSXCommandUsingMultiPlatformCommandLine() throws Exception {

        Std std = Sh.run(
            new OSAwareCmd(
                new OSCmd(OS.MacOSX, Cmd.parse("pwd")),
                new OSCmd(OS.Windows, Cmd.parse("pwd"))
            )
        );
        System.out.println(std.toString());
        Assert.assertTrue(std.outContains("shellbert"));
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldExecuteDefaultUsingMultiPlatformCommandLine() throws Exception {

        Std std = Sh.run(new OSAwareCmd(
                new OSCmd(OS.Default, Cmd.parse("pwd")),
                new OSCmd(OS.Windows, Cmd.parse("pwd"))
            )
        );

        Assert.assertTrue(std.outContains("shellbert"));
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldReadStdErr() throws Exception {
        Std std = Sh.run("cd foo");
        Assert.assertTrue(std.errContains("No such file or directory"));
        Assert.assertEquals(std.getExitCode(), 1);
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldReadStdAll() throws Exception {
        Std std = Sh.run("cd foo");
        Assert.assertTrue(std.contains("No such file or directory"));
        Assert.assertEquals(std.getExitCode(), 1);
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldTrimStdOutToStringMethodsToHaveNoNewLineAtEnd() throws Exception {
        Std std = Sh.run(new OSAwareCmd(
                new OSCmd(OS.Default, Cmd.parse("ping -c 4 127.0.0.1")),
                new OSCmd(OS.Windows, Cmd.parse("ping 127.0.0.1"))
            )
        );
        
        Assert.assertFalse(Pattern.compile("\n$").matcher(std.toString()).find());
        Assert.assertFalse(Pattern.compile("\n$").matcher(std.outToString()).find());
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldUseOSCmdKePair() throws Exception {
        Std std = Sh.run(
                new OSAwareCmd(
                    new OSCmd(OS.Default, Cmd.parse("ping -c 4 127.0.0.1")),
                    new OSCmd(OS.Windows, Cmd.parse("ping 127.0.0.1"))
                )
        );

        Assert.assertFalse(Pattern.compile("\n$").matcher(std.toString()).find());
        Assert.assertFalse(Pattern.compile("\n$").matcher(std.outToString()).find());
    }

    @Test(groups = {TestUtils.INTEGRATION})
    public void shouldTrimStdErrToStringMethodsToHaveNoNewLineAtEnd() throws Exception {
        Std std = Sh.run("ps foo");

        Assert.assertFalse(Pattern.compile("\n$").matcher(std.toString()).find());
        Assert.assertFalse(Pattern.compile("\n$").matcher(std.errToString()).find());
    }
}
