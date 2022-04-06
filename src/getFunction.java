import java.sql.CallableStatement;
import java.sql.Connection;

import javax.swing.JOptionPane;

public class getFunction 
{
    public static String checkHometownOfStaff(String staffID, String conclusion)
    {
        Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String funct = "{? = call foreignOrNationalStaff_function(?)}";

        try 
        {
            CallableStatement cs = connect.prepareCall(funct);
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.setString(2, staffID);
			cs.execute();
			conclusion = cs.getString(1);

            if (conclusion == null)
            {
                JOptionPane.showMessageDialog(null, "This supplier is non-exist");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return conclusion;
    }



    public static String checkNationalOrForeignSupplier(String suppName, String conclusion)
    {
        Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String funct = "{? = call nationalOrForeignSupplier_function(?)}";

        try 
        {
            CallableStatement cs = connect.prepareCall(funct);
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.setString(2, suppName);
			cs.execute();
			conclusion = cs.getString(1);

            if (conclusion == null)
            {
                JOptionPane.showMessageDialog(null, "This supplier is non-exist");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return conclusion;
    }



    public static String checkSellingPriceOfSelectedItem(String itemName, String conclusion)
    {
        Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String funct = "{? = call itmSellingPrice_function(?)}";

        try 
        {
            CallableStatement cs = connect.prepareCall(funct);
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.setString(2, itemName);
			cs.execute();
			conclusion = cs.getString(1);

            if (conclusion == null)
            {
                JOptionPane.showMessageDialog(null, "This Item is non-exist");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return conclusion;
    }

    public static String checkOriginalPriceOfSelectedItem(String itemName, String conclusion)
    {
        Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String funct = "{? = call itmOriginalPrice_function(?)}";

        try 
        {
            CallableStatement cs = connect.prepareCall(funct);
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.setString(2, itemName);
			cs.execute();
			conclusion = cs.getString(1);

            if (conclusion == null)
            {
                JOptionPane.showMessageDialog(null, "This Item is non-exist");
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return conclusion;
    }
}
