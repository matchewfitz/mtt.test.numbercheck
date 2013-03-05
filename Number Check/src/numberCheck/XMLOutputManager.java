package numberCheck;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class XMLOutputManager{
	
	/**
	 * writeAllChunks formats a set of Chunks in XML and writes them to a given filepath.
	 * @param allChunks the set of chunks to be written.
	 * @param outPutPath the path to write the formatted Chunks to.
	 */
	public static void writeAllChunks(ArrayList<Record[]> allChunks, String outPutPath){
		Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    try {
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	        dom = builder.newDocument();
		
	        // create the root element
	        Element outFile = dom.createElement("outfile");
		
			// pick each Record out in order
			int[] indexes = new int[allChunks.size()];
			int min = 0;
			int full = 0;
			while(true){	
				for(int i=0; i<indexes.length; i++){
					// make sure we don't go out of bounds
					if(indexes[i] < allChunks.get(i).length){
						// comparing strings and numbers isn't quite the same
						String minRecordNumber = allChunks.get(min)[indexes[min]].getRecordNumber();
						long longMinRecordNumber = Long.parseLong(minRecordNumber);					
						String recordNumber = allChunks.get(i)[indexes[i]].getRecordNumber();
						long longRecordNumber = Long.parseLong(recordNumber);
						// update min if necessary
						if(longRecordNumber < longMinRecordNumber){
							min = i;
						}
					}else if(min == i){
						min++;
						full++;
					}else{
						// we've come to the end of a chunk, increment full
						full++;
					}
				}
				// if full equals the number of chunks we're done.
				if(full == indexes.length){
					break;
				}
				
				// build up the xml elements for all applicable Record fields				
				Element lineItem = dom.createElement("lineItem");
				
				// the record number
				e = dom.createElement("numValidated");
		        e.appendChild(dom.createTextNode(allChunks.get(min)[indexes[min]].getRecordNumber()));
		        lineItem.appendChild(e);

		        // the validation algorithm
		        e = dom.createElement("algorithm");
		        e.appendChild(dom.createTextNode(allChunks.get(min)[indexes[min]].getValidationFunction()));
		        lineItem.appendChild(e);
		        
		        // whether or not the number was valid
		        e = dom.createElement("status");
		        e.setAttribute("valid", allChunks.get(min)[indexes[min]].getStatus());
		        lineItem.appendChild(e);
		        
		        // any parameters which may have been included
		        String[] args = allChunks.get(min)[indexes[min]].getFunctionArguments();
		        Element parameters = dom.createElement("Parameters");
		        for(String arg: args){			        
			        e = dom.createElement("param");
			        e.appendChild(dom.createTextNode(arg));
			        parameters.appendChild(e);	
		        }
		        lineItem.appendChild(parameters);

		        // write the line to the root element and start again
		        outFile.appendChild(lineItem);
				indexes[min]++;
				min = 0;
				full = 0;
			}
			// write the root element to dom
	        dom.appendChild(outFile);
	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "./src/cfg/processedNumbers.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(outPutPath)));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }		
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
	
}