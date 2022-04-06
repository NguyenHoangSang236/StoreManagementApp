import java.sql.Connection;
import java.sql.DriverManager;

public class connectToDatabase 
{
    public static String userRole;

    public static Connection connectToSql(String user, String password)
    {
        try 
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection("jdbc:sqlserver://DESKTOP-TH84DKJ:1433;databaseName = ProjectDBMS", user, password);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}
