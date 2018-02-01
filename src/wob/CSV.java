package wob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class CSV {
	private StringBuilder sb;
	public static final char separator = '\t';
	private final String filename = "test.csv";
	public Boolean HasRows = false;
	
    public CSV() throws Exception{
        sb = new StringBuilder();
        initHeaderLine();
    }
    
    private void initHeaderLine()
    {
    	sb.append("SKU");
        sb.append(separator);
        sb.append("Quantity");
        sb.append(separator);
        sb.append("SalePrice");
        sb.append(separator);
        sb.append("ImageURL");
        sb.append(separator);
        sb.append("Barcode");
        sb.append('\n');
    }
    
    public String GetName()
    {
    	return filename;
    }
    
    public void Append(String row)
    {
    	sb.append(row);
    	sb.append('\n');
    	if (row != "") HasRows = true;
    }
    
    public void flush() throws FileNotFoundException
    {
    	PrintWriter pw = new PrintWriter(new File(filename));
    	pw.write(sb.toString());
    	pw.close();
    }
}