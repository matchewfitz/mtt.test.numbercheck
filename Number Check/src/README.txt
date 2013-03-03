
Number Check

Written by Matt Fitzpatrick, 2013.

This code and all accompanying files was constructed as an answer to a technical examination for MTT.

------------------------------

Description of class_mappings.json

This file contains mappings for all available encoding utilities, 
adding new functionality requires a new mapping to be added to this file.

New mappings have the form: 

{
	//"EXAMPLE_INPUT_ENCODING":"correspondingClass",
	
	"LUHN":"luhn",
	"CHECKBIT":"checkBit"
}