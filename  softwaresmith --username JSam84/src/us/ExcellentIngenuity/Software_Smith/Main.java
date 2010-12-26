package us.ExcellentIngenuity.Software_Smith;

import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import us.ExcellentIngenuity.Software_Smith.XMLReader;
import us.ExcellentIngenuity.Software_Smith.Installer;
import us.ExcellentIngenuity.Software_Smith.Envioroment;

public class Main {

	/**
	 * @param args
	 */
	
	protected static File ini = new File(System.getProperty("user.dir")+"/jar/ini.xml");
	protected static XMLReader reader = new XMLReader();
	protected static Document parsedini = null;
	protected static String version;
	protected static Document parsedProject = null;
	protected static Envioroment Env = new Envioroment(); 
	protected static Boolean Compatible = false;
	protected static String OSFilePath = "";
	protected static Document parsedOSFile = null;
	protected static Installer Install = new Installer();
	
	
	
	public static void main(String[] args) {
		if(args.length > 0){
			if(args[0].equals("U")){
				Install.setUnattended(true);
				System.out.println(Install.getUnattended());
			}
		}
		
		System.out.print("Loading INI file:  " + ini + "\n");
		Main.ini();
		
		ProgramExit(0);
	}
	
	private static void ini(){
		
		parsedini = reader.Read(ini);
		NodeList nodes = parsedini.getElementsByTagName("Installer");
		for( int i = 0; i < nodes.getLength(); i++ ) {
		      Element node = (Element) nodes.item( i );
		      version = node.getAttribute("version");
		}
		
		System.out.println("Software Smith Installer Version " + version + " \n");
		ReadProject();
		
		VersionCheck();
		EnvCheck();
		LoadScript();
		
	}
	
	private static void ReadProject(){
		File projectFile = new File(System.getProperty("user.dir")+"/project/project.xml");
		parsedProject = reader.Read(projectFile);
		
	}
	private static void ReadOSFile(){
		File projectFile = new File(System.getProperty("user.dir") + OSFilePath);
		parsedOSFile = reader.Read(projectFile);
		
	}
	private static void VersionCheck(){
		String readversion = "";
//		System.out.println("version at evaluation " + projectinstallerversion);
//		System.out.println("version at evaluation " + version);
		NodeList nodes = parsedProject.getElementsByTagName("InstallerVersion");
		for( int i = 0; i < nodes.getLength(); i++ ) {
		      Element node = (Element) nodes.item( i );
		      readversion = node.getTextContent();
//		      System.out.println("version pulled from xml " + projectinstallerversion);
		}
		if(version.equals(readversion) == false) {
			System.out.println("Installer Version Incorrect");
			System.exit(1);
		}
	}
	private static void EnvCheck(){
		//ReadProject();
		String osname = "";
		String arch = "";
		NodeList nodes = parsedProject.getElementsByTagName("Platform");
		for( int i = 0; i < nodes.getLength(); i++ ) {
		      Element node = (Element) nodes.item( i );
		      //System.out.println(node.getNodeName());
		      osname = node.getAttribute("platform");
		      arch = node.getAttribute("processor");
		      if(osname.isEmpty() == false && arch.isEmpty() == false && Compatible != true){
				if(osname.equals(Env.getOSName()) && arch.equals(Env.getSysArch())){
					Compatible = true;
					//NodeList childnodes = parsedProject.getElementsByTagName("ScriptPath");
					NodeList childnodes = node.getChildNodes();
					//for( int v = 0; v < nodes.getLength(); v++ ) {
					      Element childnode = (Element) childnodes.item( 1 );
					      OSFilePath = childnode.getTextContent();
					      System.out.println(OSFilePath);
					//}
				}
				else {
					Compatible = false;
				}
		      }	
		}
		if(Compatible == false){
			System.out.println("OS or Processor incompatible");
			ProgramExit(2);
		}
							
	}
	private static void ProgramExit(int code){
		switch(code) {
		case 0: System.out.println("Program Exited normally");
				System.exit(0); 
				break;
		case 1: System.out.println("This program has encountered and error and will close");
				System.exit(1);
				break;
		case 2: System.out.println("The system is incompatible with the requirements and the program will close");
				System.exit(0);
				break;
		default: System.exit(0); 
		break;
		}
			
			
		
	}
	private static void LoadScript(){
		ReadOSFile();
		Install.LoadScript(parsedOSFile);
	}
}
