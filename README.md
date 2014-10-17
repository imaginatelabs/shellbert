Shellbert
=============

A small java library to easily run and process shell commands on the local operating system.

## Features
- Brief and Simple API and Syntax for running commands.
- Encapsulation of the exit code, stdOut and stdErr in a single object.
- Commands configurable to handle various operating systems that are determined at runtime.


## Usage
### Running String Command 
Running a simple string command in the most simplest form.

```java
Std std   = Sh.run("pwd");

int     exitCode  = std.getExitCode();               // 0
String  out       = std.outToString();               // /Projects/Shellbert
boolean hasString = std.outContains("Shellbert");    // True

```

### Running Cmd Object
The Cmd object wraps the [org.apache.commons.exec.CommandLine](http://commons.apache.org/proper/commons-exec/index.html) object, giving users
the ability to build complex commands with a rich familiar api.

Simply build a Cmd object and pass it to the run command.
 
See the Apache Commons Exec [tutorial page](http://commons.apache.org/proper/commons-exec/tutorial.html) for more on building complex Cmd objects.

```java
Cmd cmd   = Cmd.parse("pwd");
Std std   = Sh.run(cmd);

int     exitCode  = std.getExitCode();               // 0
String  out       = std.outToString();               // /Projects/Shellbert
boolean hasString = std.outContains("Shellbert");    // True

```

### Running OSAwareCmd Object
An OSAwareCmd takes multiple Cmd objects configured to run for specific operating systems such as Unix, Mac OSX, Solaris and Windows.
This provides an easy way to write platform independent code that interacts with respective differences of each operating systems' shell.

```java
Cmd ls = Cmd.parse("ls");
Cmd dir = Cmd.parse("dir");

OSAwareCmd osAwareCmd = new OSAwareCmd(
                new OSCmd(OS.Default,ls),
                new OSCmd(OS.Unix,ls),
                new OSCmd(OS.MacOSX,ls),
                new OSCmd(OS.Solaris,ls),
                new OSCmd(OS.Windows,dir),
                new OSCmd(OS.Other,ls)
            );
Std std   = Sh.run(osAwareCmd);
```

In this case we could simplify this even further, notice how all the operating systems but windows use the "ls" command (oh windows).
We can simply set the default command to be "ls" and this will achieve the same the result.
 
 ```java
 Cmd ls = Cmd.parse("ls");
 Cmd dir = Cmd.parse("dir");
 
 OSAwareCmd osAwareCmd = new OSAwareCmd(
                 new OSCmd(OS.Default,ls),
                 new OSCmd(OS.Windows,dir),
             );
 Std std   = Sh.run(osAwareCmd);
 ```
 
 # Compatibility
 Runs with Java 1.6+
 
 # Contributing 
 See [Contributing](CONTRIBUTING.md) 