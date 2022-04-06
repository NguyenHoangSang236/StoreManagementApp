import java.sql.Statement;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class getView 
{
    public static ArrayList<Staff> getNationalStaffList()
	{
		ArrayList<Staff> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Staff_ID, Position, Staff_Name, Gender, Hometown, Image from [dbo].[nationalStaffs_view]";
		
		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next() == true)
			{
				String Staff_ID = rs.getString("Staff_ID");
				String Staff_Name = rs.getString("Staff_Name");
				String Position = rs.getString("Position");
				String Hometown = rs.getString("Hometown");
				String Gender = rs.getString("Gender");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
				
				Staff nationStf = new Staff(Staff_ID, Position, Staff_Name, Gender, Hometown, Image);
						
				list.add(nationStf);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}    

	public static ArrayList<Staff> getForeignStaffList()
	{
		ArrayList<Staff> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Staff_ID, Position, Staff_Name, Gender, Hometown, Image from [dbo].[foreignStaffs_view]";
		
		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next() == true)
			{
				String Staff_ID = rs.getString("Staff_ID");
				String Gender = rs.getString("Gender");
				String Staff_Name = rs.getString("Staff_Name");
				String Position = rs.getString("Position");
				String Hometown = rs.getString("Hometown");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
				
				Staff foreignStf = new Staff(Staff_ID, Position, Staff_Name, Gender, Hometown, Image);
						
				list.add(foreignStf);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}    



	public static ArrayList<Supplier> getNationalSupplierList()
	{
		ArrayList<Supplier> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Supplier_ID, Supplier_Name, Location, Phone_Number, Image from [dbo].[nationalSuppliers_view]";

		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
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
						
				list.add(supplier);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}	

	public static ArrayList<Supplier> getForeignSupplierList()
	{
		ArrayList<Supplier> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Supplier_ID, Supplier_Name, Location, Phone_Number, Image from [dbo].[foreignSuppliers_view]";

		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
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
						
				list.add(supplier);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}	



	public static ArrayList<Items> getCheapItemList()
	{
		ArrayList<Items> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Supplier_Name, Stall_Name, Item_Name, Selling_price, Image from [dbo].[cheapItems_view]";

		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next() == true)
			{
				String Stall_Name = rs.getString("Stall_Name");
    			String Supplier_Name = rs.getString("Supplier_Name");
    			String Item_Name = rs.getString("Item_Name");
				Float Selling_Price = rs.getFloat("Selling_Price");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			Items item = new Items(Supplier_Name, Stall_Name, Item_Name, Selling_Price, Image);
						
				list.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}	

	public static ArrayList<Items> getExpensiveItemList()
	{
		ArrayList<Items> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Supplier_Name, Stall_Name, Item_Name, Selling_price, Image from [dbo].[expensiveItems_view]";

		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next() == true)
			{
				String Stall_Name = rs.getString("Stall_Name");
    			String Supplier_Name = rs.getString("Supplier_Name");
    			String Item_Name = rs.getString("Item_Name");
				Float Selling_Price = rs.getFloat("Selling_Price");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			Items item = new Items(Supplier_Name, Stall_Name, Item_Name, Selling_Price, Image);
						
				list.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}	

	public static ArrayList<Items> getPriceDifferenceList()
	{
		ArrayList<Items> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Item_Name, Original_Price, Selling_price, (select Selling_price - Original_Price) as N'Price_Difference' from [dbo].[itemsPriceDifference_view]";

		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next() == true)
			{
    			String Item_Name = rs.getString("Item_Name");
				Float Selling_Price = rs.getFloat("Selling_Price");
				Float Original_Price = rs.getFloat("Original_Price");
				Float Price_Difference = rs.getFloat("Price_Difference");
    			Items item = new Items(Item_Name, Original_Price, Selling_Price, Price_Difference);
						
				list.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}	
}
