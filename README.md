# CS-5800 Final: Digital Assistant

This repository contains an implementation of simulated digital assistant software for the final in CS-5800 (Advanced Software Engineering).
Essentially, the template design pattern is used to create a base command template that helps with handling the standard process for
executing a command in the "digital assistant." The actual commands themselves utilize the command design pattern to wrap receivers which actual execute the requests. The process utilized for command execution is essentially:
1. Validate user input
2. Log the command being executed
3. Notify the user of execution
4. Invoke the command receiver
The `driver` package implements a basic driver program demonstrating the instantiation and usage of the different classes implemented here. 
An example of its output can be found in the `output.png` file. Note that running the driver program will generate a log file 
`command.log` which contains log messages from the commands that were executed.

## Build & Run

To build with Maven, simply navigate to the project root in the command line and run:

```shell
mvn package
```

Alternatively, IDEs such as IntelliJ should be able to build the source files using their standard build utilities.

Once built, the project can be run using the `driver` package:

```shell
java -cp ./target/final-digital-assistant-1.0-SNAPSHOT.jar driver.Main
```