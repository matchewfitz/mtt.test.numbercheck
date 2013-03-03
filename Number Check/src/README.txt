
Number Check

Written by Matt Fitzpatrick, 2013.

This code and all accompanying files was constructed as an answer to a technical examination for MTT.

------------------------------------------------------------
How to add new functionality to Number Check:

Number Check uses an interface which all decoder classes must implement. This allows for easy extensibility
of the system. To add a new decoder, all you need to do is have your code follow the interface and define
the class mapping in the class_mappings.properties file outlined below.


------------------------------------------------------------
Description of class_mappings.properties:

This file contains mappings for all available encoding utilities, 
adding new functionality requires a new mapping to be added to this file.

New mappings have the form: 

EXAMPLE_INPUT_ENCODING=correspondingClass

And it's as simple as that!