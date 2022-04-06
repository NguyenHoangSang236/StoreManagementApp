import java.awt.Image;

import javax.swing.ImageIcon;

public class images 
{
    //method for reading and resizing Images
    public static void readAndResizeImage(ImageIcon ig, String fileName, int width, int height)
    {
        ImageIcon Imgicon = new ImageIcon(fileName);
        Image image = Imgicon.getImage();
        ig.setImage(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }



	//method for reading and resizing Bytes Images
	public static void readAndResizeImageByBytes(ImageIcon ig, byte[] fileName, int width, int height)
    {
        ImageIcon Imgicon = new ImageIcon(fileName);
        Image image = Imgicon.getImage();
        ig.setImage(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }


    //method for reading and resizing all icons that are used by different components
    public static void readIcons(ImageIcon addIcon, ImageIcon deleteIcon, ImageIcon editIcon, ImageIcon searchIcon, ImageIcon checkIcon, ImageIcon showIcon)
    {
        readAndResizeImage(addIcon, "F:\\study\\JAVA\\DBMS_Project\\icons\\addIcon.png", 27, 27);
		readAndResizeImage(deleteIcon, "F:\\study\\JAVA\\DBMS_Project\\icons\\deleteIcon.png", 27, 27);
		readAndResizeImage(editIcon, "F:\\study\\JAVA\\DBMS_Project\\icons\\editIcon.png", 27, 27);
		readAndResizeImage(searchIcon, "F:\\study\\JAVA\\DBMS_Project\\icons\\searchIcon.png", 27, 27);
		readAndResizeImage(checkIcon, "F:\\study\\JAVA\\DBMS_Project\\icons\\checkIcon.png", 27, 27);
        readAndResizeImage(showIcon, "F:\\study\\JAVA\\DBMS_Project\\icons\\showIcon.png", 27, 27);
    }
}
