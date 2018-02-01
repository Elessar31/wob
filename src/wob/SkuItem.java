package wob;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SkuItem {

	public String SKU;
	public int Quantity;
	public double SalePrice;
	public double RetailPrice;
	public String Barcode;
	public Date ItemReceivedDate;
	private long dateDifference;
	
	//construct the sku item
	public SkuItem(String pSku, int pQuantity, String pBarcode, double pRetailPrice, Date pItemReceiveDate) throws Exception
	{
		SKU = pSku;
		Quantity = pQuantity;
		Barcode = pBarcode;
		RetailPrice = pRetailPrice;
		ItemReceivedDate = pItemReceiveDate;
		CalculateSalePrice();
	}
	
	public String GetImagesUrl()
	{
		if (SKU != "") return "http://testImageUrl.exercise/"+SKU+".png";
		return "http://testImageUrl.exercise/NoSKU.png";
	}
	
	//Calculate the SalePrice from the oldest SKU date
	public void CalculateSalePrice() throws Exception 
	{
		//If not all data are available throw exception
		if (ItemReceivedDate == null)
		{
			throw new Exception("ItemReceiveDate must be not null");
		}
		if (RetailPrice == 0.0)
		{
			SalePrice = 0.0;
			return;
		}
		
		//Calculate the difference
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY,0);
		
		dateDifference = getDifferenceDays(today.getTime(),ItemReceivedDate);
		
		Connection conn = connections.GetInstance();
		String Query = "select discount from wob.tresholds where "+ dateDifference + " between treshold_min and treshold_max";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
			
		rs.next();
		SalePrice = RetailPrice * rs.getDouble("discount") /100;
		stmt.close();
	}
	
	//Get the differences between two dates in DAYs
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return Math.abs(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
	}
	
	//Concatenate the sku items data as a csv row
	public String GetCSVRow()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(SKU);
		sb.append(CSV.separator);
		sb.append(Quantity);
		sb.append(CSV.separator);
		sb.append(SalePrice);
		sb.append(CSV.separator);
		sb.append(GetImagesUrl());
		sb.append(CSV.separator);
		sb.append(Barcode);
		
		return sb.toString(); 	
	}
}
