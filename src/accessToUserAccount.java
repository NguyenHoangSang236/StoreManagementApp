import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class accessToUserAccount 
{
    public static Staff getAccessToAccount(String role, String accountName, String ID, String name, String pos, String hometown, String gender, byte[] imageByte)
    {
        Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String manager = "{call managerInfoUsingIDInLoginManager_proc(?)}"; 
		String staff = "{call staffInfoUsingIDInLoginStaff_proc(?)}";
		Staff stf = null;
		
		try
		{
            PreparedStatement ps = null;
            if(role == "manager")
            {
                ps = connect.prepareStatement(manager);			
            }
			else if(role == "staff")
            {
                ps = connect.prepareStatement(staff);			
            }
            ps.setString(1, accountName);
            ResultSet rs = ps.executeQuery();
			
    		while(rs.next() == true)
    		{
    			ID = rs.getString("Staff_ID");
    			pos = rs.getString("Position");
    			name = rs.getString("Staff_Name");
    			hometown = rs.getString("Hometown");
				gender = rs.getString("Gender");
				Blob imgBlob = rs.getBlob("Image");
				imageByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
				ImageIcon Image = new ImageIcon(imageByte);
    			stf = new Staff(ID, pos, name, gender, hometown, Image);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail");
		}

		return stf;
    }
}
