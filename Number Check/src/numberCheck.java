import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class numberCheck{	
	public static void main(String[] args){
		Properties prop = new Properties();
		try {
			//load a properties file
			prop.load(new FileInputStream("./src/cfg/class_mappings.properties"));
			
			//get the property value and print it out
			Set<String> props = prop.stringPropertyNames();
			for(String property:props){
				
				System.out.println(property);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}