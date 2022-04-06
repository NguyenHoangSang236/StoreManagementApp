import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;
import javax.swing.JOptionPane;

public class editTable 
{
    public static boolean error;
	public static String problem;



	public static void deleteOneRow(String tableName, String primKey, String pkVal)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);

		String deleteRow = "delete from " + tableName + " where " + primKey + " = ?";
		PreparedStatement ps;
		try
		{
			ps = connect.prepareStatement(deleteRow);
			ps.setString(1, pkVal);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Success deleting on " + tableName + " table !!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed!!");
			JOptionPane.showMessageDialog(null, e);
		}
	}


	
	public static void addIntoStaff(String Staff_ID, String Position, String Staff_Name, String Gender, String Hometown, String ImgFilePath)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String insert = "insert into Staff values(?,?,?,?,?,?)";
		PreparedStatement ps;
		InputStream image = null;

		try 
		{
			image = new FileInputStream(ImgFilePath);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "This file is not existed, please try again !!");
			e.printStackTrace();
		}
		
		try 
		{
			ps = connect.prepareStatement(insert);
			
			ps.setString(1, Staff_ID);
			ps.setString(2, Position);
			ps.setString(3, Staff_Name);
			ps.setString(4, Hometown);
			ps.setString(5, Gender);
			ps.setBlob(6, image);
			
			ps.executeUpdate();
		} 
		catch (Exception e) 
		{
			error = true;
			problem = e.getMessage();
		
			e.printStackTrace();
		}
	}

	public static void editOneCellInStaffTable(String Staff_ID, String Staff_Name, String Hometown, String Position, String Gender, byte[] Image)
	{

		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		Blob blobImg = null;

		try 
		{
			blobImg = new javax.sql.rowset.serial.SerialBlob(Image);
		} 
		catch (SerialException e1) 
		{
			e1.printStackTrace();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}

		String editTable = "UPDATE Staff SET Staff_ID = ?, Staff_Name = ?, Hometown = ?, Position = ?, Gender = ?, Image = ? WHERE Staff_ID = ?";
		try 
		{
			PreparedStatement ps = connect.prepareStatement(editTable);
			ps.setString(1, Staff_ID);
			ps.setString(2, Staff_Name);
			ps.setString(3, Hometown);
			ps.setString(4, Position);
			ps.setString(5, Gender);
			ps.setBlob(6, blobImg);
			ps.setString(7, Staff_ID);
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Success!!");
		} 
		catch (Exception e) 
		{
			error = true;
			problem = e.getMessage();
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Failed!!");
			JOptionPane.showMessageDialog(null, e);
		}
	}



	public static void addIntoSupplier(String Supplier_ID, String Supplier_Name, String Location, String Phone_Number, String ImgFilePath)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String insert = "insert into Supplier values(?,?,?,?,?)";
		PreparedStatement ps;
		InputStream image = null;

		try 
		{
			image = new FileInputStream(ImgFilePath);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "This file is not existed, please try again !!");
			e.printStackTrace();
		}
		
		try 
		{
			ps = connect.prepareStatement(insert);
			
			ps.setString(1, Supplier_ID);
			ps.setString(2, Supplier_Name);
			ps.setString(3, Location);
			ps.setString(4, Phone_Number);
			ps.setBlob(5, image);
			
			ps.executeUpdate();
		} 
		catch (Exception e) 
		{
			error = true;
			problem = e.getMessage();
		
			e.printStackTrace();
		}
	}

	public static void editOneCellInSupplierTable(String Supplier_ID, String Supplier_Name, String Location, String Phone_Number, byte[] Image)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		Blob blobImg = null;

		try 
		{
			blobImg = new javax.sql.rowset.serial.SerialBlob(Image);
		} 
		catch (SerialException e1) 
		{
			e1.printStackTrace();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}

		String editTable = "UPDATE Supplier SET Supplier_ID = ?, Supplier_Name = ?, Location = ?, Phone_Number = ?, Image = ? WHERE Supplier_ID = ?";
		try 
		{
			PreparedStatement ps = connect.prepareStatement(editTable);
			ps.setString(1, Supplier_ID);
			ps.setString(2, Supplier_Name);
			ps.setString(3, Location);
			ps.setString(4, Phone_Number);
			ps.setBlob(5, blobImg);
			ps.setString(6, Supplier_ID);
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Success!!");
		} 
		catch (Exception e) 
		{
			error = true;
			problem = e.getMessage();
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Failed!!");
			JOptionPane.showMessageDialog(null, e);
		}
	}



	public static void addIntoItems(String Item_Code, String Supplier_ID, String Item_Name, float Selling_Price, String Ordinal_Number_Of_Stall, java.sql.Date Import_Date, String ImgFilePath, boolean Availabe, float Original_Price, int Quantity_In_Stock)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String insertToItems = "insert into Items values(?,?,?,?,?,?,?,?)";
		String insertToItemManagement = "insert into Item_Management values(?,?,?)";
		PreparedStatement ps;
		InputStream image = null;

		try 
		{
			image = new FileInputStream(ImgFilePath);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "This file is not existed, please try again !!");
			e.printStackTrace();
		}
		
		try 
		{
			ps = connect.prepareStatement(insertToItemManagement);

			ps.setString(1, Item_Code);
			ps.setFloat(2, Original_Price);
			ps.setInt(3, Quantity_In_Stock);

			ps.executeUpdate();

			ps = connect.prepareStatement(insertToItems);
			
			ps.setString(1, Item_Code);
			ps.setString(2, Supplier_ID);
			ps.setString(3, Item_Name);
			ps.setFloat(4, Selling_Price);
			ps.setString(5, Ordinal_Number_Of_Stall);
			ps.setDate(6, Import_Date);
			ps.setBlob(7, image);
			ps.setBoolean(8, Availabe);
			
			ps.executeUpdate();
		} 
		catch (Exception e) 
		{
			error = true;
			problem = e.getMessage();
		
			e.printStackTrace();
		}
	}

	public static void editOneCellInItemsTable(String Item_Code, String Supplier_ID, String Item_Name, float Selling_Price, String Ordinal_Number_Of_Stall, java.sql.Date Import_Date, byte[] Image, boolean Availabe, float Original_Price, int Quantity_In_Stock)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		Blob blobImg = null;
		PreparedStatement ps;

		try 
		{
			blobImg = new javax.sql.rowset.serial.SerialBlob(Image);
		} 
		catch (SerialException e1) 
		{
			e1.printStackTrace();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}

		String editItemTable = "UPDATE Items SET Item_Code = ?, Supplier_ID = ?, Item_name = ?, Selling_Price = ?, Ordinal_Number_Of_Stall = ?, Import_Date = ?, Image = ?, Available = ? WHERE Item_Code = ?";
		String editItemManagementTable = "UPDATE Item_Management SET Item_Code = ?, Original_Price = ?, Quantity_In_Stock = ? where Item_Code = ?";

		try 
		{
			ps = connect.prepareStatement(editItemManagementTable);

			ps.setString(1, Item_Code);
			ps.setFloat(2, Original_Price);
			ps.setInt(3, Quantity_In_Stock);

			ps.executeUpdate();

			ps = connect.prepareStatement(editItemTable);

			ps.setString(1, Item_Code);
			ps.setString(2, Supplier_ID);
			ps.setString(3, Item_Name);
			ps.setFloat(4, Selling_Price);
			ps.setString(5, Ordinal_Number_Of_Stall);
			ps.setDate(6, Import_Date);
			ps.setBlob(7, blobImg);
			ps.setBoolean(8, Availabe);
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Success!!");
		} 
		catch (Exception e) 
		{
			error = true;
			problem = e.getMessage();
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Failed!!");
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
