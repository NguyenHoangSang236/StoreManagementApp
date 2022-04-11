import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class getProcedure 
{
    public static String checkTheSellerNameInSelectedStall(String stallName, String conclusion)
    {
        Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call sellerWorkingAtStall_function(?,?)}", sellerName = null;

        try 
        {
            CallableStatement ps = connect.prepareCall(proc);
            ps.setString(1, stallName);
            ps.registerOutParameter(2, java.sql.Types.VARCHAR);
            ps.execute();
            sellerName = ps.getString(2);
            if(sellerName != null)
            {
                conclusion = "The Seller working at " + stallName + " is " + sellerName;
            }
            else conclusion = "This stall is non-existed";
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return conclusion;
    }



    public static ArrayList<Staff> showStfInfoUsingID(String StaffID)
    {
        ArrayList<Staff> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call stfInfoBasingOnStfID_proc(?)}";
        
        try
		{
			PreparedStatement ps = connect.prepareStatement(proc);
			ps.setString(1, StaffID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
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
    			
    			list.add(staff);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
    }

	public static ArrayList<Staff> showStfInfoUsingName(String nameOfStaff) 
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


    public static ArrayList<Staff> getStaffWorkingInSelectedPositonList(String selectedPos)
    {
        ArrayList<Staff> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call staffsWorkInPos_proc(?)}";
        
        try
		{
			PreparedStatement ps = connect.prepareStatement(proc);
			ps.setString(1, selectedPos);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
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
    			
    			list.add(staff);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
    }



	public static ArrayList<ItemInfoBaseOnSupplierName_proc> getItemInfoBaseOnSupplierNameList(String selectedSupp)
    {
        ArrayList<ItemInfoBaseOnSupplierName_proc> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call itemInfoBaseOnSuppName_proc(?)}";
        
        try
		{
			PreparedStatement ps = connect.prepareStatement(proc);
			ps.setString(1, selectedSupp);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				String Supplier_Name = rs.getString("Supplier_Name");
				String Stall_Name = rs.getString("Stall_Name");
				String Item_Code = rs.getString("Item_Code");
    			String Item_Name = rs.getString("Item_Name");
				int Selling_Price = rs.getInt("Selling_Price");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			ItemInfoBaseOnSupplierName_proc itm = new ItemInfoBaseOnSupplierName_proc(Supplier_Name, Stall_Name, Item_Code, Item_Name, Selling_Price, Image);
    			
    			list.add(itm);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
    }

	public static ArrayList<Supplier> getSupplierInfoBaseOnSupplierIDList(String suppID)
    {
        ArrayList<Supplier> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call suppInfoBasingOnSuppID_proc(?)}";
        
        try
		{
			PreparedStatement ps = connect.prepareStatement(proc);
			ps.setString(1, suppID);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
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



	public static ArrayList<Items> getTop3BestSellingItemsList()
	{
		ArrayList<Items> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call top3BestSelling_proc()}";
        
        try
		{
			PreparedStatement ps = connect.prepareStatement(proc);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
			{
				String Stall_Name = rs.getString("Stall_Name");
    			String Item_Name = rs.getString("Item_Name");
    			Float Selling_Price = rs.getFloat("Selling_Price");
				int Quantity = rs.getInt("Quantity");
				Blob imgBlob = rs.getBlob("Image");
				byte[] imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imgByte);
    			Items item = new Items(Stall_Name, Item_Name, Selling_Price, Quantity, Image);
    			
    			list.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Items> getItemInfoBaseOnItemCodeList(String itemCode)
    {
        ArrayList<Items> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call itemInfoBaseOnItemCode_proc(?)}";
        
        try
		{
			PreparedStatement ps = connect.prepareStatement(proc);
			ps.setString(1, itemCode);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
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
    			
    			list.add(item);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
    }

	public static ArrayList<Items> getItemInfoBaseOnItemNameList(String itemName)
    {
        ArrayList<Items> list = new ArrayList<>();
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String proc = "{call itemInfoBaseOnItemName_proc(?)}";
        
        try
		{
			PreparedStatement ps = connect.prepareStatement(proc);
			ps.setString(1, itemName);
			ResultSet rs = ps.executeQuery();

			while(rs.next())
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
