package wob;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Main {

	public static void main(String[] args) throws IOException
	{
		try {
			Connection conn = connections.GetInstance();
			String query = "SELECT " + 
					"    sku_data.sku," + 
					"    min(inventory.ItemReceivedDate) as ItemReceiveDate," + 
					"    sku_data.barcode," + 
					"    sku_data.RetailPrice," + 
					"    COUNT(inventory.sku) as quantity" + 
					" FROM" + 
					"    wob.inventory" + 
					"        INNER JOIN" + 
					"    wob.sku_data ON inventory.sku = sku_data.sku " + 
					" GROUP BY inventory.sku";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			try {
				CSV csv = new CSV();
				while(rs.next())
				{
					SkuItem sku = new SkuItem(rs.getString("sku") , 
							rs.getInt("quantity"), 
							rs.getString("barcode"), 
							rs.getDouble("RetailPrice"), 
							rs.getDate("ItemReceiveDate"));
					csv.Append(sku.GetCSVRow());					
				}
				csv.flush();
				System.out.println("csv generated");
				if (csv.HasRows)
				{
					FtpUpload ftp = new FtpUpload();
					if (ftp.Connnect())
					{
						if (ftp.Upload(csv.GetName()))
						{
							System.out.println("Upload ok");
						}
						else
						{
							System.out.println("Upload failed!");
						}
					}
					else throw new Exception("Can not connect to ftp");
				}
				else
				{
					System.out.println("Empty csv file. Not going to be updated");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

}
