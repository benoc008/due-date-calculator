![travis-build](https://travis-ci.org/benoc008/due-date-calculator.svg?branch=master)

# due-date-calculator
Job application test task that implements a due date calculator. Written in Java.

## build
To build the project, you'll need JDK 8 and maven installed.
```
mvn clean install -DskipTests
```

## tests
To run the tests, with maven.
```
mvn clean test
```

## usage
There is a calculateDueDate method as required, within the DueDateCalculator class. Takes two arguments, the first one is the submit date, the second one is an integer of the turnaround time in hours.

## implementation
I've used the Java 8 Time api to work with date and time. 
The core of the calculation is a recursive method, which is leaping forward in the working hours and decreasing the remaining time until it gets zero.

There is a helper class to calculate the first and the last hours of a workday, the beginning of the next workday and to decide if a specific time is within a workday or not.

I've created validators with own exception classes for invalid inputs, such as null as submit date, non working hours as submit dates, and non positive values as turnaround hours. (The last one was not specified, but i think negative or zero turnaround time would not make any sense.)

There are unit tests for the validators and the helper methods, both valid and invalid cases, and end to end tests for the whole behaviour of the calculateDueDate method.

The program is using the working hours as fixed values as it was specified. Changing them could break the code.

I've tried to follow Uncle Bob's instructions about the clean code, so i've wrote the methods as small as possible, only focusing on one thing, and used submethods instead. Named them and the variables so it's obvious to use. Didn't write any comments to keep it maintainable. Didn't use too many arguments. And so on.
