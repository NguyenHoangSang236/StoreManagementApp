import javax.swing.ImageIcon;

public class Supplier 
{
	private String Supplier_ID;
	private String Supplier_Name;
	private String Location;
	private String Phone_Number;
	private ImageIcon Image;
	
	public Supplier(String Supplier_ID, String Supplier_Name, String Location, String Phone_Number, ImageIcon Image)
	{
		this.Supplier_ID = Supplier_ID;
		this.Supplier_Name = Supplier_Name;
		this.Location = Location;
		this.Phone_Number = Phone_Number;
		this.Image = Image;
	}
	
	//cài đặt getter
	public String getSupplier_ID()
	{
		return Supplier_ID;
	}
	
	public String getSupplier_Name()
	{
		return Supplier_Name;
	}
	
	public String getLocation()
	{
		return Location;
	}
	
	public String getPhone_Number()
	{
		return Phone_Number;
	}

	public ImageIcon getImage()
	{
		return Image;
	}
	
	//cài đặt setter
	public void setSupplier_ID(String Supplier_ID)
	{
		this.Supplier_ID = Supplier_ID;
	}
	
	public void setSupplier_Name(String Supplier_Name)
	{
		this.Supplier_Name = Supplier_Name;
	}
	
	public void setLocation(String Location)
	{
		this.Location = Location;
	}
	
	public void setPhone_Number(String Phone_Number)
	{
		this.Phone_Number = Phone_Number;
	}
	
	public void setImage(ImageIcon Image)
	{
		this.Image = Image;
	}

	//đưa về mảng object
	public Object[] supplierToArray()
	{
		return new Object[] {Supplier_ID, Supplier_Name, Location, Phone_Number, Image};
	}
}
