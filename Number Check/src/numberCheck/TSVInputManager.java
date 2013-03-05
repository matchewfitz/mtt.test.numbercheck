package numberCheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TSVInputManager {

	private BufferedReader rawInput;
	
	/**
	 * TSVInputManager constructor
	 */
	public TSVInputManager(){
	}
	
	/**
	 * Opens the stream
	 * @param path
	 * @return A Boolean value, true if the file was successfully opened, false otherwise. 
	 */
	public boolean init(String path){
		File inFile = new File(path);
		try {
			rawInput = new BufferedReader(new FileReader(inFile));
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * getChunk queries the input for a chunk of data. Chunks are measured in numbers of lines.	
	 * @return An array of Records.
	 */
	public Record[] getChunk(){
		Record[] chunk = new Record[256];
		try {
			for(int i=0; i<256; i++){
				if(rawInput.ready()){
					String line = rawInput.readLine();
					String[] rawRecord = line.split("\t");
					if(rawRecord.length >= 2){
						String[] recordArguments = new String[rawRecord.length - 2];
						System.arraycopy(rawRecord, 2, recordArguments, 0, recordArguments.length);
						chunk[i] = new Record(rawRecord[0], rawRecord[1], recordArguments);
					}else{
						error(rawRecord);
						i--;
					}

				}else{
					Record[] endChunk = new Record[i];
					System.arraycopy(chunk, 0, endChunk, 0, endChunk.length);
					return endChunk;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chunk;
	}
	
	/**
	 * 
	 * @param error
	 */
	private static void error(String[] error){
		System.err.print("An error occurred processing a record ");
		for(String err:error){
			System.err.print(err);
		}
		System.err.println("The field may not have enough information");
	}
}
