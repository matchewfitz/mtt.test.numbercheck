import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.HashSet;
import java.lang.Class;


public class numberCheck{
	public static boolean checkDecoderAvailable(String decoderName){		
		try {
			Class checkClass = Class.forName("decoder." + decoderName);
			
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
		
	public HashSet loadChunk(){
		return new HashSet();
	}
	
	public static void main(String[] args){
		Properties prop = new Properties();
		try {
			//load a properties file
			prop.load(new FileInputStream("./src/cfg/class_mappings.properties"));
			
			//get the property value and print it out
			Set<String> allPropNames = prop.stringPropertyNames();
			for(String propName:allPropNames){
				if(checkDecoderAvailable(prop.getProperty(propName))){
					System.out.println(propName + " decoding is supported");
				}
				else{
					System.out.println(propName + " decoding is not supported");
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}