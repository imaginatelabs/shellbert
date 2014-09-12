Shellbert
=============

A library to run shell commands on the local operating system. 

A command can be setup to run on multiple platforms so that it can handle OS variations in the command.

The exit code, stdout and stderr are encapsulated so that they can be used in the logic of an application.


## Usage
### Execute String Command 
Running a simple command in the most simplest form.

```java
Results results   = Shellbert.run("pwd");

int     exitCode  = results.getExitCode();                      // 0
String  stdOut    = results.stdOutToString();                   // /Projects/Shellbert
boolean hasString = results.stdOutContains("Shellbert");    // True

```

### Execute CommandLine Object
Running an [org.apache.commons.exec.CommandLine](http://commons.apache.org/proper/commons-exec/index.html) object. 
For how to build CommandLine objects see the Apache Commons Exec [tutorial page](http://commons.apache.org/proper/commons-exec/tutorial.html)

```java
CommandLine cl    = CommandLine.parse("pwd");
Results results   = Shellbert.run(cl);

int     exitCode  = results.getExitCode();                      // 0
String  stdOut    = results.stdOutToString();                   // /Projects/Shellbert
boolean hasString = results.stdOutContains("Shellbert");    // True

```

### Execute MulitPlatformCommandLine Object
Building and running a Mulit Platform command.

```java
MultiPlatformCommandLine mpcl = new MultiPlatformCommandLine(
            new HashMap<OperatingSystem,CommandLine>(){{
                put(OperatingSystem.Default,CommandLine.parse("ls"));
                put(OperatingSystem.Unix,CommandLine.parse("ls"));
                put(OperatingSystem.MacOSX,CommandLine.parse("ls"));
                put(OperatingSystem.Windows,CommandLine.parse("dir"));
                put(OperatingSystem.Other,CommandLine.parse("ls"));
            }}
        ));
Results results   = Shellbert.run(mpcl);
```