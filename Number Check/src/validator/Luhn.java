package validator;

public class Luhn implements Validator{

	
	/**
	 * Validates the given testNumber using the LUHN algorithm
	 * @param testNumber A String containing the number to be tested against
	 * @param args An empty array, this is a requirement of the decoder interface and is not used in LUHN
	 * @return A boolean stating whether the testNumber was validated or not.
	 */
	public boolean validate(String testNumber, String[] args) throws NumberFormatException {	
		int total = 0;
		boolean isAlternate = false;
		// Iterate over the numbers, doubling every second digit (counting from the right hand side) 
		for (int i = testNumber.length() - 1; i >= 0; i--){
			//get the next digit
			int currentDigit = Integer.parseInt(testNumber.substring(i, i + 1));
			if (isAlternate){
				//double the digit
				currentDigit *= 2;
				// If the result is a double digit number, add the first digit to the second digit.
				// (note, the first digit will always be 1)			 
				if (currentDigit > 9){
					currentDigit = (currentDigit % 10) + 1;
				}
			}
			// Add the result (either transformed or not) to the total
			total += currentDigit;
			// Toggle the alternate digit detector 
			isAlternate = !isAlternate;

		}
		// Return whether or not the total result satisfies LUHN validation. 
		return (total % 10 == 0);
	}
}