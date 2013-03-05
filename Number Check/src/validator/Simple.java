package validator;

public class Simple implements Validator{
	
	/**
	 * Validates the given testNumber using a simple algorithm which adds up the individual digits of the
	 * testNumber, divides it by a given divisor and compares the remainder to an expected remainder.
	 * @param testNumber A String containing the number to be tested against
	 * @param args An array, containing a number to divide the input number by and the expected remainder.
	 * @return A boolean stating whether the testNumber was validated or not.
	 */
	public boolean validate(String testNumber, String[] args) {
		int total = 0;
		for(int i=0; i<testNumber.length(); i++){
			total += Integer.parseInt(testNumber.substring(i, i+1));
		}
		return (total % Integer.parseInt(args[0])) == Integer.parseInt(args[1]);
	}	
}