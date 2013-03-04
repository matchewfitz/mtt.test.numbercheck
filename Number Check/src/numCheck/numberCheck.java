package numCheck;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.HashSet;
import java.lang.Class;


public class numberCheck{
	private String PROPERTY_MAPPING_FILE = "./src/cfg/class_mappings.properties";
	private FileManager fm;
	
	/**
	 * Checks to see if a given encoding algorithm is available.
	 * @param encodingName the readable name for the encoding algorithm
	 * @return Boolean stating whether or not the specified algorithm is supported
	 */
	public static boolean checkEncoding(String encodingName){
		try {
			Class.forName("decoder." + encodingName);
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets all supported encodings.
	 * @return An ArrayList containing all supported encoding algorithms.
	 */
	public ArrayList<String> getEncodings(){
		Properties prop = new Properties();
		ArrayList<String> supportedEncodings = new ArrayList<String>();
		try {
			//load a properties file 
			prop.load(new FileInputStream(PROPERTY_MAPPING_FILE));
			
			//get the property value and print it out
			Set<String> allEncodings = prop.stringPropertyNames();
			for(String encName:allEncodings){
				if(checkEncoding(prop.getProperty(encName))){
					supportedEncodings.add(encName);
				}
			}
		} catch (IOException e) {
			//report error properly
			e.printStackTrace();
		}
		return supportedEncodings;
	}
	
	/**
	 * Creates the FileManager instance.
	 */
	public boolean startFM(String path){
		fm = new FileManager();
		if(fm.init(path)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	* Loads a chunk of information from the input file.
	*/
	public ArrayList<Record> loadChunk(){
		return new HashSet();
		
	}
	
	public static void main(String[] args){
		
	}
}