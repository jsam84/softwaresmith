package us.ExcellentIngenuity.Software_Smith;

public class Envioroment {
	private static String osName;
	private static String osVersion;
	private static String sysArch;
	
	Envioroment(){
		setEnv();
		
		
	}
	
	private static void setEnv() {
		osName = System.getProperty("os.name");
		System.out.println(osName);
		osVersion = System.getProperty("os.version");
		System.out.println(osVersion);
		sysArch = System.getProperty("os.arch");
		System.out.println(sysArch);
	
	}
	public String getOSName(){
		return osName;
	}
	public String getOSVersion(){
		return osVersion;
		
	}
	public String getSysArch(){
		return sysArch;
		
	}

}
