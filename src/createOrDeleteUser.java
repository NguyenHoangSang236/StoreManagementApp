import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class createOrDeleteUser 
{
	//check wheather this Manager's account is existed or not
	public static boolean checkExistedManagerAccount(String creUser)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String check = "select UserAccount from LoginManager where UserAccount = ?";
		PreparedStatement ps;

		try
		{
			ps = connect.prepareStatement(check);
			ps.setString(1, creUser);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			if(rs.next() == true)
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}


	
	//check wheather this Staff's account is existed or not
	public static boolean checkExistedStaffAccount(String creUser)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String check = "select UserAccount from LoginStaff where UserAccount = ?";
		PreparedStatement ps;

		try
		{
			ps = connect.prepareStatement(check);
			ps.setString(1, creUser);
			ps.execute();
			ResultSet rs = ps.executeQuery();
			if(rs.next() == true)
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}


	
	//method for creating a new Login of Manager
	public static void createManagerLogin(String creUser, String crePass, String ID)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String insert = "insert into LoginManager values (?,?,?)";
		
		try
		{
			PreparedStatement psInsert = connect.prepareStatement(insert);			//insert tên user và pass vô bảng LoginManager trong database
			psInsert.setString(1, creUser);											//mục đích: để lần sau check xem tài khoản này đã tồn tại hay chưa
			psInsert.setString(2, crePass);
			psInsert.setString(3, ID);
			psInsert.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail");
		
		}
	}


	
	//method for creating a new Login of Staff
	public static void createStaffLogin(String creUser, String crePass, String ID)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String insert = "insert into LoginStaff values (?,?,?)";
		
		try
		{
			PreparedStatement psInsert = connect.prepareStatement(insert);			//insert tên user và pass vô bảng LoginStaff trong database
			psInsert.setString(1, creUser);											//mục đích: để lần sau check xem tài khoản này đã tồn tại hay chưa
			psInsert.setString(2, crePass);
			psInsert.setString(3, ID);
			psInsert.execute();
			
			JOptionPane.showMessageDialog(null, "Success !!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail !!");
		}
	}



	//method for deleting a Login
	public static void deleteLoginAndUser(String loginName)
	{
		Connection connect = connectToDatabase.connectToSql(actionListener.user, actionListener.password);
		String delLogin = "{call deleteLogin_proc(?)}";
		String delUser = "{call deleteUser_proc(?)}";
		String delInLoginManagerTbl = "{call deleteOneRowInLoginManager_proc(?)}";
		String delInLoginStaffTbl = "{call deleteOneRowInLoginStaff_proc(?)}";

		try
		{
			PreparedStatement psDelLog = connect.prepareStatement(delLogin);		
			psDelLog.setString(1, loginName);
			psDelLog.execute();
			
			PreparedStatement psUser = connect.prepareStatement(delUser);				
			psUser.setString(1, loginName);
			psUser.execute();

			PreparedStatement psDelMng = connect.prepareStatement(delInLoginManagerTbl);		
			psDelMng.setString(1, loginName);
			psDelMng.execute();
			
			PreparedStatement psDelStf = connect.prepareStatement(delInLoginStaffTbl);				
			psDelStf.setString(1, loginName);
			psDelStf.execute();
			
			JOptionPane.showMessageDialog(null, "Success !!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fail !!");
		}
	}
}
