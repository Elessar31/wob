package wob;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyValues {
	
	private static final String propFileName = "prop.properties";
	InputStream inputStream;
	
	//public properties
	public String user;
	public String password;
	public String url;
	
	public String FtpPath;
	public String FtpUser;
	public String FtpPass;
	public int port;
	
	//public methods
	public void GetProperties() throws IOException
	{
		try {
			Properties prop = new Properties();			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// get the property values
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			url = prop.getProperty("url");
			
			FtpPass = prop.getProperty("ftppass");
			FtpPath = prop.getProperty("ftppath");
			FtpUser = prop.getProperty("ftpuser");
			port = Integer.parseInt(prop.getProperty("port"));
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
	 
}
