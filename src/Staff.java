import javax.swing.ImageIcon;

public class Staff 
{
	private String Staff_ID, Position, Staff_Name, Hometown, Gender;
	private ImageIcon Image;
	
	public Staff(String Staff_ID, String Position, String Staff_Name, String Gender, String Hometown, ImageIcon Image)
	{
		this.Staff_ID = Staff_ID;
		this.Position = Position;
		this.Staff_Name = Staff_Name;
		this.Gender = Gender;
		this.Hometown = Hometown;
		this.Image = Image;
	}
	
	//cài đặt getter 
	public String getStaff_ID()
	{ 
		return Staff_ID;
	}
	
	public String getPosition()
	{
		return Position;
	}
	
	public String getStaff_Name()
	{
		return Staff_Name;
	}
	
	public String getHometown()
	{
		return Hometown;
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
	public void setStaff_ID(String Staff_ID)
	{
		this.Staff_ID = Staff_ID;
	}
	
	public void setPosition(String Position)
	{
		this.Position = Position;
	}
	
	public void setStaff_Name(String Staff_Name)
	{
		this.Staff_Name = Staff_Name;
	}
	
	public void setHometown(String Hometown)
	{
		this.Hometown = Hometown;
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
	public Object[] staffToArray()
	{
		return new Object[] {Staff_ID, Position, Staff_Name, Gender, Hometown, Image};
	}
}
