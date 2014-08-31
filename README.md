JTerminalExec
=============

Users can specify multi platform commands to be executed on respective operating systems. Exit code, stdout and stderr are encapsulated so that they can be used in the logic of an application.


## Usage
### Execute String Command 
Running a simple command in the most simplest form.

```java
Results results   = JTerminalExec.exec("pwd");

int     exitCode  = results.getExitCode();                      // 0
String  stdOut    = results.stdOutToString();                   // /Projects/JTerminalExec
boolean hasString = results.stdOutContains("JTerminalExec");    // True

```

### Execute CommandLine Object
Running an [org.apache.commons.exec.CommandLine](http://commons.apache.org/proper/commons-exec/index.html) object. 
For how to build CommandLine objects see the Apache Commons Exec [tutorial page](http://commons.apache.org/proper/commons-exec/tutorial.html)

```java
CommandLine cl    = CommandLine.parse("pwd");
Results results   = JTerminalExec.exec(cl);

int     exitCode  = results.getExitCode();                      // 0
String  stdOut    = results.stdOutToString();                   // /Projects/JTerminalExec
boolean hasString = results.stdOutContains("JTerminalExec");    // True

```

### Execute MulitPlatformCommandLine Object
Building and running a Mulit Platform command.

```java
MultiPlatformCommandLine mpcl = JTerminalExec.exec(new MultiPlatformCommandLine(
            new HashMap<OperatingSystem,CommandLine>(){{
                put(OperatingSystem.Default,CommandLine.parse("ls"));
                put(OperatingSystem.Unix,CommandLine.parse("ls"));
                put(OperatingSystem.MacOSX,CommandLine.parse("ls"));
                put(OperatingSystem.Windows,CommandLine.parse("dir"));
                put(OperatingSystem.Other,CommandLine.parse("ls"));
            }}
        ));
Results results   = JTerminalExec.exec(mpcl);
```