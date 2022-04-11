import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;

public class Stall 
{
    private String Ordinal_Number, Stall_Name, Seller_ID, Seller_Name, Gender;
    private ImageIcon Image;

    public Stall(String Ordinal_Number, String Stall_Name, String Seller_ID )
    {
        this.Ordinal_Number = Ordinal_Number;
        this.Stall_Name = Stall_Name;
        this.Seller_ID = Seller_ID;
    }

    public Stall(String Stall_Name, String Seller_ID, String Seller_Name, String Gender, ImageIcon Image)
    {
        this.Seller_Name = Seller_Name;
        this.Stall_Name = Stall_Name;
        this.Seller_ID = Seller_ID;
        this.Gender = Gender;
        this.Image = Image;
    }

	public Stall(){}

    //cài đặt getter 
	public String getOrdinal_Number()
	{ 
		return Ordinal_Number;
	}
	
	public String getSeller_ID()
	{
		return Seller_ID;
	}
	
	public String getStall_Name()
	{
		return Stall_Name;
	}

    public String getSeller_Name()
	{
		return Seller_Name;
	}

    public String getGender()
	{
		return Gender;
	}

    public ImageIcon getImage()
	{
		return Image;
	}

    //cài đặt setter
	public void setOrdinal_Number(String Ordinal_Number)
	{
		this.Ordinal_Number = Ordinal_Number;
	}
	
	public void setSeller_ID(String Seller_ID)
	{
		this.Seller_ID = Seller_ID;
	}
	
	public void setStall_Name(String Stall_Name)
	{
		this.Stall_Name = Stall_Name;
	}

    public void setSeller_Name(String Seller_Name)
	{
		this.Seller_Name = Seller_Name;
	}

    public void setGender(String Gender)
	{
		this.Gender = Gender;
	}

    public void setImage(ImageIcon Image)
	{
		this.Image = Image;
	}

    //đưa về 1 mảng object 
	public Object[] stallToArray()
	{
		return new Object[] {Ordinal_Number, Stall_Name, Seller_ID};
	}

	public Object[] sellersAtStallsToArray()
	{
		return new Object[]  {Stall_Name, Seller_ID, Seller_Name, Gender, Image};
	}


	public boolean stallIsExisted(String stallName)
	{
		Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String sql = "select Stall_Name from Stall where Stall_Name = '" + stallName + "'" ;

		try
		{
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next() == true)
			{
				this.Stall_Name = rs.getString("Stall_Name");
				return true;
			}
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}

		return false;
	}
}
