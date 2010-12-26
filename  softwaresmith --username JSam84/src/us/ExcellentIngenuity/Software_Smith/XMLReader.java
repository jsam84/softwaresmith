package us.ExcellentIngenuity.Software_Smith;
	import java.io.*;
	import org.w3c.dom.Document;
	import org.w3c.dom.*;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.DocumentBuilder;
	import org.xml.sax.SAXException;
	import org.xml.sax.SAXParseException;
	
	
public class XMLReader {
	
	/**
	 * @param args
	 */
XMLReader(){
		//TODO need a file variable for the installer script 

	}
	protected Document Read(File F){
		Document installScript = null;
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			 installScript = docBuilder.parse(F);
			
	        installScript.getDocumentElement ().normalize ();
	/*        System.out.println ("Root element of the doc is " + 
	        installScript.getDocumentElement().getNodeName());*/
	        
	        }catch (SAXParseException err) {
	            System.out.println ("** Parsing error" + ", line " 
	                 + err.getLineNumber () + ", uri " + err.getSystemId ());
	            System.out.println(" " + err.getMessage ());

	            }catch (SAXException e) {
	            Exception x = e.getException ();
	            ((x == null) ? e : x).printStackTrace ();

	            }catch (Throwable t) {
	            t.printStackTrace ();
	            }
	     return installScript;
	}

}
