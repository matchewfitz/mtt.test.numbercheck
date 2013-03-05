package numberCheck;

import java.util.ArrayList;

import validator.Luhn;
import validator.Simple;

public class NumberCheck{

	/**
	 * Creates the FileManager instance.
	 * @param inPath A String containing the file path for the input file.
	 * @param outPath A String containing the file path for the output file.
	 */
	private static void getData(String inPath, String outPath){
		TSVInputManager inMgr = new TSVInputManager();
		ArrayList<Record[]> allRecords = new ArrayList<Record[]>();
		if(inMgr.init(inPath)){
			while(true){
				Record[] chunk = inMgr.getChunk();
				if(chunk.length > 0){
					allRecords.add(processChunk(chunk));
				}
				else{
					break;
				}
			}
		}
		XMLOutputManager.writeAllChunks(allRecords, outPath);
	}
		
	/**
	 * processChunk iterates over all members of a chunk and performs the respective encoding algorithm on that member.
	 * it then sorts the contents of the chunk.
	 * @param chunk an Array of Records to be processed
	 * @return the processed Array
	 */
	private static Record[] processChunk(Record[] chunk){
		Luhn luhnDecoder = new Luhn();
		Simple simpleDecoder = new Simple();
		for(int i=0; i<chunk.length; i++){
			try{	
				//delegate the Record to it's corresponding algorithm
				String function = chunk[i].getValidationFunction();
				Boolean success = false;
				if(function.equalsIgnoreCase("LUHN")){
					success = luhnDecoder.validate(chunk[i].getRecordNumber(), chunk[i].getFunctionArguments());
				}
				else if(function.equalsIgnoreCase("SIMPLE")){
					success = simpleDecoder.validate(chunk[i].getRecordNumber(), chunk[i].getFunctionArguments());
				}
				
				if(success){
					chunk[i].setStatus("PASS");
				}else{
					chunk[i].setStatus("FAIL");
				}
			}catch(NumberFormatException e){
				//if the input field isn't a number
				System.err.println("Error validating record: " + chunk[i].getRecordNumber() + " is not a numeric field");
				Record[] amendedRecords = new Record[chunk.length -1];
				for(int j=0; j<=amendedRecords.length; j++){
					if(j < i){
						amendedRecords[j] = chunk[j];
					}else if(j > i){
						amendedRecords[j-1] = chunk[j];
					}
				}
				chunk = amendedRecords;
				i--;
			}
		}
		RecordSorter sorter = new RecordSorter();
		return sorter.sortAllRecords(chunk);
	}	
	
	public static void main(String[] args){
		if(args.length == 2){
			getData(args[0], args[1]);
		}else{
			System.out.println("Usage: numberCheck INPUT_FILE OUTPUT_FILE");
		}
	}
}