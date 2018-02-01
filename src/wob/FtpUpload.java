package wob;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpUpload {
	
	private FTPClient ftpClient;
	public FtpUpload()
	{
		ftpClient = new FTPClient();		
		
	}
	
	public Boolean Connnect()
	{
		PropertyValues connInfos = new PropertyValues();
		try {
			connInfos.GetProperties();
			ftpClient.connect(connInfos.FtpPath, connInfos.port);
            ftpClient.login(connInfos.FtpUser, connInfos.FtpPass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (Exception e) {
			return false;
		}
		return ftpClient.isConnected();
	}
	
	public Boolean Upload(String Filename)
	{
		try {
			if (!ftpClient.isConnected())
			{
				throw new Exception ("Please log in first!");
			}
	           // APPROACH #1: uploads first file using an InputStream
		 File firstLocalFile = new File(Filename);
		
		 InputStream inputStream = new FileInputStream(firstLocalFile);
		
		 System.out.println("Start uploading first file");
		 boolean done = ftpClient.storeFile(Filename, inputStream);
		 inputStream.close();
		 if (done) {
		     System.out.println("The first file is uploaded successfully.");
		 }
		 else
		 {
			 return false;
		 }
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
