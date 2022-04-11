import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class actionListener extends userInterface
{
    public String[] pw = new String[100];
	public String[] us = new String[100];
    public static String user, password;
	public String conclusion = null;
	public byte[] initByteImg = null;            //this byte[] is the Byte read from Image column of every table in database in editPanel of every table 



    public void actionPerformed(ActionEvent e)
    {
        //hiện giao diện login khi chọn 'Login' trong menu 
		if(e.getActionCommand() == "login")
		{
			cardLayout.show(mainPanel, "login");
		}



        //LOGIN AN ACCOUNT
		//
		//khi lựa chọn 'Staff' thì hệ thống sẽ truy cập vô database bằng account 'sa' và thực hiện truy vấn UserAccount, password trong bẳng LoginStaff
		// --> duyệt văn bản được nhập trong 2 thanh User và Passowrd xem chúng nó trong bẳng LoginStaff trong database không
		//nếu đúng thì cho phép người dùng sử dụng nút 'GO'
		if(e.getActionCommand() == "staff")
		{
			connectToDatabase.userRole = "staff";

			loginPanel_managerRaButt.setSelected(false);

			int count = 0;
			boolean status = false;
			String userInTextField = loginPanel_userTextField.getText();
			String passwordInTextField = String.valueOf(loginPanel_passwordTextField.getPassword());

			Connection connect = connectToDatabase.connectToSql("sa", "123");			
			String sql = "select UserAccount, password from LoginStaff";
			try
			{
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while(rs.next())
				{
					us[count] = rs.getString("UserAccount");
					pw[count] = rs.getString("password");
					if(us[count].equals(userInTextField))
					{
						if(pw[count].equals(passwordInTextField))
						{
							String currentUser;
							currentUser = us[count];
							loginPanel_loginButt.setEnabled(true);
							loginPanel_announceLabel.setText("Login success, welcome " + currentUser);
							status = true;
							break;
						}
					}
				}

				if(status == false)
				{
					loginPanel_announceLabel.setText("Login fail, can not find this account");
					loginPanel_staffRaButt.setSelected(false);
				}
				else if(status == true)
				{
					user = "staffAccount";
					password = "123";
				}
			}
			catch(Exception expt)
			{
				expt.printStackTrace();
			}
		}
		
		//khi lựa chọn 'Manager' thì hệ thống sẽ truy cập vô database bằng account 'sa' và thực hiện truy vấn UserAccount, password trong bẳng LoginManager
		// --> duyệt văn bản được nhập trong 2 thanh User và Passowrd xem chúng nó trong bẳng LoginManager trong database không
		//nếu đúng thì cho phép người dùng sử dụng nút 'GO'
		if(e.getActionCommand() == "manager")
		{
			connectToDatabase.userRole = "manager";

			loginPanel_staffRaButt.setSelected(false);

			int count = 0;
			boolean status = false;
			String userInTextBox = loginPanel_userTextField.getText();
			String passwordInTextBox = String.valueOf(loginPanel_passwordTextField.getPassword());

			Connection connect = connectToDatabase.connectToSql("sa", "123");
			String sql = "select UserAccount, password from LoginManager";
			try 
			{
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while(rs.next() == true)
				{
					us[count] = rs.getString("UserAccount");
					pw[count] = rs.getString("password");
					if(us[count].equals(userInTextBox))
					{
						if(pw[count].equals(passwordInTextBox))
						{
							String currentUser;
							currentUser = us[count];
							loginPanel_loginButt.setEnabled(true);
							loginPanel_announceLabel.setText("Login success, welcome " + currentUser);
							status = true;
							break;
						}
					}
				}

				if(status == false)
				{
					loginPanel_announceLabel.setText("Login fail, can not find this account");
					loginPanel_managerRaButt.setSelected(false);
				}
				else if(status == true)
				{
					user = "managerAccount";
					password = "123";
				}
			} 
			catch (Exception expt) 
			{
				expt.printStackTrace();
			}
		}
		
		//khi bám nút 'GO' thì đăng nhập thành công --> cho phép người dùng sử dụng 'Options'
		if(e.getActionCommand() == "lets go")
		{
			String id = null, name = null, pos = null, gender = null, hometown = null;
			byte[] imageByte = null;
			menuOptions.setEnabled(true);
			Staff stf = accessToUserAccount.getAccessToAccount(connectToDatabase.userRole, loginPanel_userTextField.getText(), id, name, pos, hometown, gender, imageByte);
			setAccountPanel(stf.getStaff_ID(), stf.getPosition(), stf.getStaff_Name(), stf.getGender(), stf.getHometown(), stf.getImage());
			accPanel.setVisible(true);
			cardLayout.show(mainPanel, "tab");
		}


		//LOGOUT AN ACCOUNT
		//
		//when clicking Log out option on Menu bar
		if(e.getActionCommand() == "logout")
		{
			cardLayout.show(mainPanel, "login");
			loginPanel_userTextField.setText(null);
			loginPanel_passwordTextField.setText(null);
			loginPanel_announceLabel.setText(null);
			loginPanel_loginButt.setEnabled(false);
			loginPanel_managerRaButt.setSelected(false);
			loginPanel_staffRaButt.setSelected(false);
			accPanel.setVisible(false);
			try 
			{
				connectToDatabase.connectToSql(user, password).close();
				JOptionPane.showMessageDialog(null, "Log out sucessfully !!");
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}


		//CREATE A NEW ACCOUNT
		//
		//when choosing Create a New Account in Menu
		if(e.getActionCommand() == "create account")
		{
			if(connectToDatabase.userRole == "manager")
			{
				setCreateNewAccountPanel();
				JOptionPane.showMessageDialog(null, creUsrPanel);
			}
			else if(connectToDatabase.userRole == "staff")
			{
				JOptionPane.showMessageDialog(null, "You do not have the right to create a new account !!");
			}
		}

		//make sure 2 radio buttons are not selected at the same time
		if(e.getActionCommand() == "create a manager login account")
		{
			creUsrPanel_staffRadioButt.setSelected(false);
		}
		if(e.getActionCommand() == "create a staff login account")
		{
			creUsrPanel_managerRadioButt.setSelected(false);
		}

		//when clicking into Create button
		if(e.getActionCommand() == "create this account")
		{
			if(!createOrDeleteUser.checkExistedManagerAccount(creUsrPanel_userTextField.getText()) && !createOrDeleteUser.checkExistedStaffAccount(creUsrPanel_userTextField.getText()))
			{
				if(creUsrPanel_managerRadioButt.isSelected())
				{
					createOrDeleteUser.createManagerLogin(creUsrPanel_userTextField.getText(), String.valueOf(creUsrPanel_passwordTextField.getPassword()), creUsrPanel_IDTextField.getText());
				}
				else if(creUsrPanel_staffRadioButt.isSelected())
				{
					createOrDeleteUser.createStaffLogin(creUsrPanel_userTextField.getText(), String.valueOf(creUsrPanel_passwordTextField.getPassword()), creUsrPanel_IDTextField.getText());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This account has already existed !!");
			}
		}

		//REQUEST TO CHANGE PASSWORD FROM STAFFS
		//
		//when clicking into Change Password in Menu
		if(e.getActionCommand() == "change password")
		{
			if(connectToDatabase.userRole == "staff")
			{
				setChangePassword();
				String[] options = new String[1];
				options[0] = "Request";
				options[1] = "Cancel";
				JOptionPane.showOptionDialog(null, changePassPanel, "Request to change password", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			}
		}
    }
} 