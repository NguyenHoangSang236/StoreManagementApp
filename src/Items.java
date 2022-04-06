import javax.swing.ImageIcon;
import java.sql.Date;

public class Items 
{
    private String Item_Code, Item_Name, Supplier_ID, Ordinal_Number_Of_Stall, Stall_Name, Supplier_Name;
    private Date Import_Date;
    private float Selling_Price, Original_Price, Price_Difference;
	private ImageIcon Image;
    private int Quantity;

    public Items(String Item_Code, String Supplier_ID, String Item_Name, float Selling_Price, String Ordinal_Number_Of_Stall, Date Import_Date, ImageIcon Image)
    {
        this.Item_Code = Item_Code;
        this.Supplier_ID = Supplier_ID;
        this.Item_Name = Item_Name;
        this.Selling_Price = Selling_Price;
        this.Import_Date = Import_Date;
        this.Ordinal_Number_Of_Stall = Ordinal_Number_Of_Stall;
        this.Image = Image;
    }



    public Items(String Supplier_Name, String Stall_Name, String Item_Name, float Selling_Price, ImageIcon Image)
    {
        this.Supplier_Name = Supplier_Name;
        this.Stall_Name = Stall_Name;
        this.Item_Name = Item_Name;
        this.Selling_Price = Selling_Price;
        this.Image = Image;
    }



    public Items(String Item_Name, float Original_Price, float Selling_Price, float Price_Difference)
    {
        this.Item_Name = Item_Name;
        this.Selling_Price = Selling_Price;
        this.Original_Price = Original_Price;
        this.Price_Difference = Price_Difference;
    }



    public Items(String Stall_Name, String Item_Name, float Selling_Price, int Quantity, ImageIcon Image)
    {
        this.Quantity = Quantity;
        this.Stall_Name = Stall_Name;
        this.Item_Name = Item_Name;
        this.Selling_Price = Selling_Price;
        this.Image = Image;
    }



    //cài đặt getter 
	public String getItem_Code()
	{ 
		return Item_Code;
	}

    public String getItem_Name()
	{ 
		return Item_Name;
	}

    public String getSupplier_ID()
	{ 
		return Supplier_ID;
	}

    public String getOrdinal_Number_Of_Stall()
	{ 
		return Ordinal_Number_Of_Stall;
	}

    public Date getImport_Date()
    {
        return Import_Date;
    }

    public float getSelling_Price()
    {
        return Selling_Price;
    }

    public float getOriginal_Price()
    {
        return Original_Price;
    }

    public float getPrice_Difference()
    {
        return Price_Difference;
    }

    public ImageIcon getImage()
	{
		return Image;
	}

    public String getSupplier_Name()
	{ 
		return Supplier_Name;
	}

    public String getStall_Name()
	{ 
		return Stall_Name;
	}

    public int getQuantity()
    {
        return Quantity;
    }



    //cài đặt setter
	public void setItem_Code(String Item_Code)
	{
		this.Item_Code = Item_Code;
	}

    public void setSupplier_ID(String Supplier_ID)
	{
		this.Supplier_ID = Supplier_ID;
	}

    public void setOrdinal_Number_Of_Stall(String Ordinal_Number_Of_Stall)
	{
		this.Ordinal_Number_Of_Stall = Ordinal_Number_Of_Stall;
	}

    public void setItem_Name(String Item_Name)
	{
		this.Item_Name = Item_Name;
	}

    public void setSelling_Price(float Selling_Price)
    {
        this.Selling_Price = Selling_Price;
    }

    public void setOriginal_Price(float Original_Price)
    {
        this.Original_Price = Original_Price;
    }

    public void setPrice_Difference(float Price_Difference)
    {
        this.Price_Difference = Price_Difference;
    }

    public void setImport_Date(Date Import_Date)
    {
        this.Import_Date = Import_Date;
    }

    public void setSupplier_Name(String Supplier_Name)
	{
		this.Supplier_Name = Supplier_Name;
	}

    public void setStall_Name(String Stall_Name)
	{
		this.Stall_Name = Stall_Name;
	}

    public void setImage(ImageIcon Image)
	{
		this.Image = Image;
	}

    public void setQuantity(int Quantity)
    {
        this.Quantity = Quantity;
    }



    //đưa về 1 mảng object 
	public Object[] itemToArray()
	{
		return new Object[] {Item_Code, Supplier_ID, Item_Name, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, Image};
	}

    public Object[] cheapAndExpensiveItemsToArray()
	{
		return new Object[] {Supplier_Name, Stall_Name, Item_Name, Selling_Price, Image};
	}

    public Object[] itemPriceDiffToArray()
    {
        return new Object[] {Item_Name, Original_Price, Selling_Price, Price_Difference};
    }

    public Object[] top3BestSellingItemsToArray()
	{
		return new Object[] {Stall_Name, Item_Name, Selling_Price, Quantity, Image};
	}
}
