package us.ExcellentIngenuity.Software_Smith;

import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * @author James Johnson
 *Installer should load the script
 *load a list of files and defaults
 *get user input if Unattended == false else use defaults
 *wait until ok/install button pressed
 *effect system changes and file copies
 *generate install log
 *return to main with install completion code
 */
public class Installer {
	private Boolean Unattended = false;
	protected String SourcePath;
	protected String DestinationPath;
	protected String FileArray[];
	
	
	public Boolean getUnattended(){
		return Unattended;
	}
	protected void setUnattended(Boolean newUnattended){
		Unattended = newUnattended;
	}
	
	protected void LoadScript(Document ParsedOSFile){
		//@TODO re-write including xml file to have a source and destination for each file
		NodeList nodes = ParsedOSFile.getElementsByTagName("Path");
		DestinationPath = (nodes.item(0).getTextContent());
		nodes = ParsedOSFile.getElementsByTagName("Source");
		SourcePath = (nodes.item(0).getTextContent());
		NodeList FileList = ParsedOSFile.getElementsByTagName("File");
		Integer count = FileList.getLength();
		/*for each count loop through and place into a list */
		System.out.println(FileList.item(0).getTextContent());
		FileArray = new String[count];

		for (int i = 0; i < count; i++){
			FileArray[i] = FileList.item(i).getTextContent();
			//System.out.println(FileArray[i].toString());
	
		}
		
	}
	protected Boolean ExecuteInstall(){
		Boolean installcode = false;
		MkDir();
		CopyFiles();
		return installcode;
	}
	private void CopyFiles() {
		
	}
	private void MkDir(){
		
	}

}
