package wob;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connections {
	
	public static Connection GetInstance() throws IOException, SQLException
	{
		PropertyValues connInfos = new PropertyValues();
		connInfos.GetProperties();	
		Connection conn = DriverManager.getConnection(connInfos.url, connInfos.user, connInfos.password);
		return conn;
	}

}