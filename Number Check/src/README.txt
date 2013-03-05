
Number Check

Written by Matt Fitzpatrick, 2013.

This code and all accompanying files was constructed as an answer to a technical examination for MTT.

------------------------------------------------------------
Usage

Execute NumberCheck INPUT_FILE OUTPUT_FILE on the command line.

The sorted output will be written to the specified path.

------------------------------------------------------------
Input and Output

Number Check provides a simple command line interface, which accepts TSV files of the form:

1234567890123456	ALGORITHM	ARG_1	ARG_2	.....	ARG_N

It outputs a sorted XML file containing the results of the Number Check operation. 

------------------------------------------------------------
How does Number Check sort the output?

Number Check divides the input into manageable chunks which it then performs Quicksort on.
Once everything has been processed, it is encoded as XML and written to disk. 

------------------------------------------------------------
How to add new validation algorithms to Number Check

Number Check uses an interface which all validator classes must implement. 
To add a new validator, all you need to do is have your code follow the interface and check for the
algorithm in the numberCheck.java source code.
