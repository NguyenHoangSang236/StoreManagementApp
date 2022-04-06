import javax.swing.ImageIcon;

public class ItemInfoBaseOnSupplierName_proc 
{
    private String Stall_Name, Item_Code, Item_Name, Supplier_Name;
    private int Selling_Price;    
    private ImageIcon Image;

    public ItemInfoBaseOnSupplierName_proc(String Supplier_Name, String Stall_Name, String Item_Code, String Item_Name, int Selling_Price, ImageIcon Image)
    {
        this.Supplier_Name = Supplier_Name;
        this.Stall_Name = Stall_Name;
        this.Item_Code = Item_Code;
        this.Item_Name = Item_Name;
        this.Selling_Price = Selling_Price;
        this.Image = Image;
    }

    public String getSupplierName()
    {
        return Supplier_Name;
    }
    public void setSupplierName(String Supplier_Name)
    {
        this.Supplier_Name = Supplier_Name;
    }

    public String getStallName()
    {
        return Stall_Name;
    }
    public void setStallName(String Stall_Name)
    {
        this.Stall_Name = Stall_Name;
    }

    public String getItem_Code()
    {
        return Item_Code;
    }
    public void setItem_Code(String Item_Code)
    {
        this.Item_Code = Item_Code;
    }

    public String getItem_Name()
    {
        return Item_Name;
    }
    public void setItem_Name(String Item_Name)
    {
        this.Item_Name = Item_Name;
    }

    public int getSelling_Price()
    {
        return Selling_Price;
    }
    public void setSelling_Price(int Selling_Price)
    {
        this.Selling_Price = Selling_Price;
    }

    public ImageIcon getImage()
    {
        return Image;
    }
    public void setImage(ImageIcon Image)
    {
        this.Image = Image;
    }

    public Object[] itemInfoBaseOnSupplierNameToArray()
	{
		return new Object[] {Supplier_Name, Stall_Name, Item_Code, Item_Name, Selling_Price, Image};
	}
}
