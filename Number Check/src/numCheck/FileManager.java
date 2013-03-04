package numCheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	private BufferedReader rawInput;
	
	/**
	 * Opens the stream
	 * @param path
	 * @return
	 */
	public FileManager(){
	}
	
	public boolean init(String path){
		File inFile = new File(path);
		try {
			rawInput = new BufferedReader(new FileReader(inFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<Record> getChunk(){
		ArrayList<Record> chunk = new ArrayList<Record>();
		try {
			for(int i=0; i<256; i++){
				if(rawInput.ready()){
					String line = rawInput.readLine();
					String[] rawRecord = line.split("/t");
					String[] subArrayLol = new String[rawRecord.length - 2];
					System.arraycopy(rawRecord, 2, subArrayLol, 0, subArrayLol.length);
					chunk.add(new Record(rawRecord[1], rawRecord[2], subArrayLol));
				}else{
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chunk;
	}
}
