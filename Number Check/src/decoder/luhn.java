package decoder;

public class luhn implements decoder{

	public boolean decode(String[] args) {	
		int total = 0;
		boolean isAlternate = false;
		String testNum = args[0];
		
		// Iterate over the numbers, doubling every second digit (counting from the right hand side) 
		for (int i = testNum.length() - 1; i >= 0; i--){
			//get the next digit
			int currentDigit = Integer.parseInt(testNum.substring(i, i + 1));
			if (isAlternate){
				//double the digit
				currentDigit *= 2;
				/* If the result is a double digit number, add the first digit to the second digit.
				 * (note, the first digit will always be 1) */				 
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