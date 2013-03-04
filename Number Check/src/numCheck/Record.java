package numCheck;

public class Record {
	private String RECORD_NUMBER;
	private String VALIDATION_FUNC;
	private String[] FUNC_ARGS;	
	private String STATUS;
	
	/**
	 * Simple record object
	 * @param recordNumber A String, usually a credit card number, the most important value in a record.
	 * @param validationFunction A String, the class name of the validation algorithm to be used.
	 * @param functionArguments An Array of Strings, any arguments which may be required for the validation function.
	 */	
	public Record(String recordNumber, String validationFunction, String[] functionArguments){
		RECORD_NUMBER = recordNumber;
		VALIDATION_FUNC = validationFunction;
		FUNC_ARGS = functionArguments;
	}
	
	/**
	 * getRecordNumber
	 * @return a String containing the record number.
	 */	
	public String getRecordNumber(){
		return RECORD_NUMBER;
	}

	
	/**
	 * getValidationFunction
	 * @return a String containing the class name of the validation function.
	 */	
	public String getValidationFunction(){
		return VALIDATION_FUNC;
	}
	
	
	/**
	 * getFunctionArguments
	 * @return an array of Strings containing any arguments which may be required for the validation function.
	 */	
	public String[] getFunctionArguments(){
		return FUNC_ARGS;
	}
	
	/**
	 * setStatus
	 * @param status A String describing the validation status of the record.
	 */
	public void setStatus(String status){
		STATUS = status;
	}
}
