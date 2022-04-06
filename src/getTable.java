import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class getTable 
{
    public static ArrayList<Staff> searchStaffUsingName(String nameOfStaff) 
    {
    	ArrayList<Staff> arrList = new ArrayList<>();
    	Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
    	String sql = "select Staff_ID, Position, Staff_Name, Gender, Hometown, Image from Staff where Staff_Name like ? ";

		try 
		{
			PreparedStatement pstm = connect.prepareStatement(sql);
			pstm.setString(1, "%" + nameOfStaff + "%");
			ResultSet rs = pstm.executeQuery();
			
    		while(rs.next() == true)
    		{
    			String Staff_ID = rs.getString("Staff_ID");
    			String Position = rs.getString("Position");
    			String Staff_Name = rs.getString("Staff_Name");
    			String Hometown = rs.getString("Hometown");
				String Gender = rs.getString("Gender");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			Staff staff = new Staff(Staff_ID, Position, Staff_Name, Gender, Hometown, Image);
    			
    			arrList.add(staff);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

    	return arrList;
    }

	public static ArrayList<Staff> getStaffListToShow() 
    {
		Statement stm = null;
		ResultSet rs = null;
    	ArrayList<Staff> arrList = new ArrayList<>();
    	Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
    	String sql = "select Staff_ID, Position, Staff_Name, Gender, Hometown, Image from Staff";
    	try
    	{
    		stm = connect.createStatement();
    		rs = stm.executeQuery(sql);
    		while(rs.next() == true)
    		{
    			String Staff_ID = rs.getString("Staff_ID");
    			String Position = rs.getString("Position");
    			String Staff_Name = rs.getString("Staff_Name");
    			String Hometown = rs.getString("Hometown");
				String Gender = rs.getString("Gender");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			Staff staff = new Staff(Staff_ID, Position, Staff_Name, Gender, Hometown, Image);
    			
    			arrList.add(staff);
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return arrList;
    }



	public static ArrayList<Supplier> getSupplierListToShow() 
    {
		Statement stm = null;
		ResultSet rs = null;
    	ArrayList<Supplier> arrList = new ArrayList<>();
    	Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
    	String sql = "select Supplier_ID, Supplier_Name, Location, Phone_Number, Image from Supplier";
    	try
    	{
    		stm = connect.createStatement();
    		rs = stm.executeQuery(sql);
    		while(rs.next() == true)
    		{
    			String Supplier_ID = rs.getString("Supplier_ID");
    			String Supplier_Name = rs.getString("Supplier_Name");
    			String Location = rs.getString("Location");
				String Phone_Number = rs.getString("Phone_Number");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			Supplier supplier = new Supplier(Supplier_ID, Supplier_Name, Location, Phone_Number, Image);
    			
    			arrList.add(supplier);
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return arrList;
    }



	public static ArrayList<Items> getItemListToShow() 
    {
		Statement stm = null;
		ResultSet rs = null;
    	ArrayList<Items> arrList = new ArrayList<>();
    	Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
    	String sql = "select Item_Code, Supplier_ID, Item_Name, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, Image from Items";
    	try
    	{
    		stm = connect.createStatement();
    		rs = stm.executeQuery(sql);
    		while(rs.next() == true)
    		{
				String Item_Code = rs.getString("Item_Code");
    			String Supplier_ID = rs.getString("Supplier_ID");
    			String Item_Name = rs.getString("Item_Name");
    			String Ordinal_Number_Of_Stall = rs.getString("Ordinal_Number_Of_Stall");
				Date Import_Date = rs.getDate("Import_Date");
				float Selling_Price = rs.getFloat("Selling_Price");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			Items item = new Items(Item_Code, Supplier_ID, Item_Name, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, Image);
    			
    			arrList.add(item);
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return arrList;
    }
}
