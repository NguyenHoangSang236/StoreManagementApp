import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class userInterface implements ActionListener, MouseListener
{
	staffPanel staff = new staffPanel();
	supplierPanel supplier = new supplierPanel();
	itemPanel item = new itemPanel();
	stallPanel stall = new stallPanel();

	public int imgCol, rowPoint;
	public String selectedStaffGender;
	public byte[] addedByteOfImg;										//this byte[] is the one from ImageIcon which is added by user

    public JFrame userFrame = new JFrame("Store Management");
    public JPanel mainPanel = new JPanel();
	public JMenuBar menuBar = new JMenuBar();
	public JMenu menuOptions = new JMenu("Menu");

	public DefaultTableCellRenderer centerTextInCell = new DefaultTableCellRenderer();

	public JTabbedPane tabPanel = new JTabbedPane();

	public CardLayout cardLayout = new CardLayout();
	
	public ImageIcon stfIcon = new ImageIcon();
	public ImageIcon itmIcon = new ImageIcon();
	public ImageIcon stlIcon = new ImageIcon();
	public ImageIcon cusIcon = new ImageIcon();
	public ImageIcon ordIcon = new ImageIcon();
	public ImageIcon deliIcon = new ImageIcon();
	public ImageIcon recpIcon = new ImageIcon();
	public ImageIcon suppIcon = new ImageIcon();
	public static ImageIcon searchIcon = new ImageIcon();
	public static ImageIcon checkIcon = new ImageIcon();
	public static ImageIcon editIcon = new ImageIcon();
	public static ImageIcon deleteIcon = new ImageIcon();
	public static ImageIcon addIcon = new ImageIcon();
	public ImageIcon notiOnIcon = new ImageIcon();
	public ImageIcon notiOffIcon = new ImageIcon();
	public static ImageIcon showIcon = new ImageIcon();
	public JButton chooseImageButt;


	//ACCOUNT PANEL'S COMPONENTS
	public JPanel accPanel = new JPanel();
	public ImageIcon accPanel_accImage;
	public JLabel accPanel_accImgLabel = new JLabel();
	public JLabel accPanel_accNameLabel = new JLabel();
	public JLabel accPanel_accIDLabel = new JLabel();
	public JLabel accPanel_accPositionLabel = new JLabel();
	public JLabel accPanel_accHometownLabel = new JLabel();
	public JLabel accPanel_accGenderLabel = new JLabel();
	JButton accPanel_notificationButton = new JButton();
	
		
	//LOGIN PANEL'S COMPONENTS
	public JPanel loginPanel = new JPanel();
	public JLabel loginPanel_loginLabel, noticeLabel, loginPanel_announceLabel, loginPanel_userLabel, loginPanel_passLabel, labelChooseTable;
	public static JPasswordField loginPanel_passwordTextField, creloginPanel_passwordTextField, creCfmloginPanel_passwordTextField;
	public static JTextField loginPanel_userTextField;
	public JRadioButton loginPanel_staffRaButt, loginPanel_managerRaButt, creMngRaButt, creStfRaButt;
	public JButton loginPanel_loginButt;

	//CREATE A NEW ACCOUNT'S COMPONENTS
	public JPanel creUsrPanel = new JPanel();
	public JLabel creUsrPanel_userNameLabel = new JLabel("User");
	public JLabel creUsrPanel_passwordLabel = new JLabel("Password");
	public JLabel creUsrPanel_confirmloginPanel_passLabel = new JLabel("Confirm password");
	public JLabel creUsrPanel_titleLabel = new JLabel("CREATE A NEW LOGIN ACCOUNT");
	public JLabel creUsrPanel_IDLabel = new JLabel("ID");
	public JLabel creUsrPanel_roleLabel = new JLabel("Role");
	public JButton crecreUsrPanel_createButton = new JButton("CREATE");
	public JButton creUsrPanel_createButton = new JButton("CREATE");
	public JTextField creUsrPanel_userTextField = new JTextField();
	public JTextField creUsrPanel_IDTextField = new JTextField();
	public JPasswordField creUsrPanel_passwordTextField = new JPasswordField();
	public JPasswordField creUsrPanel_confirmPassTextField = new JPasswordField();
	public JRadioButton creUsrPanel_managerRadioButt = new JRadioButton("Manager");
	public JRadioButton creUsrPanel_staffRadioButt = new JRadioButton("Staff");


	//CHANGE PASSWORD PANEL'S COMPONENTS
	public JPanel changePassPanel = new JPanel();
	public JLabel changePassPanel_titleLabel = new JLabel("REQUEST TO CHANGE A NEW PASSWORD");
	public JLabel changePassPanel_oldPassLabel = new JLabel("Old Password");
	public JLabel changePassPanel_newPassLabel = new JLabel("New Password");
	public JLabel changePassPanel_cfmNewPassLabel = new JLabel("Confirm Password");
	public JLabel changePassPanel_reasonLabel = new JLabel("Reason");
	public JTextArea changePassPanel_reasonTextArea = new JTextArea();
	public JTextField changePassPanel_oldPassTextField = new JTextField();
	public JTextField changePassPanel_newPassTextField = new JTextField();
	public JTextField changePassPanel_cfmNewPassTextField = new JTextField();

	
	//ORDER PANEL'S COMPONENTS
	public JPanel ordPanel = new JPanel();


	//CUSTOMER PANEL'S COMPONENTS
	public JPanel cusPanel = new JPanel();


	//STALL PANEL'S COMPONENTS
	public JPanel stlPanel = new JPanel();


	//DELIVERY PANEL'S COMPONENTS
	public JPanel deliPanel = new JPanel();


	//RECEIPT PANEL'S COMPONENTS
	public JPanel recpPanel = new JPanel();




	//method for setting up Menu 
    public void setMenu()
	{
		menuBar.setBounds(0, 0, 1200, 30);
		
		menuOptions.setEnabled(false);
		menuBar.add(menuOptions);
		
		JMenuItem login = new JMenuItem("Login");
		login.setActionCommand("login");
		login.addActionListener(this);
		menuOptions.add(login);
		
		JMenuItem createAcc = new JMenuItem("Create an account");
		createAcc.setActionCommand("create account");
		createAcc.addActionListener(this);
		menuOptions.add(createAcc); 

		JMenuItem changePass = new JMenuItem("Change password");
		changePass.setActionCommand("change password");
		changePass.addActionListener(this);
		menuOptions.add(changePass);
		
		JMenuItem logout = new JMenuItem("Logout");
		logout.setActionCommand("logout");
		logout.addActionListener(this);
		menuOptions.add(logout);
	}



	//method for setting up components in Account panel
	public void setAccountPanel(String accID, String accPosition, String accName, String accGender, String accHometown, ImageIcon accImg)
	{
		accPanel.setBounds(10, 38, 1174, 67);
		accPanel.setLayout(null);
	
		Image img = accImg.getImage();
		accPanel_accImage = new ImageIcon(img.getScaledInstance(70, 67, Image.SCALE_DEFAULT));
		accPanel_accImgLabel.setIcon(accPanel_accImage);
		accPanel_accImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accPanel_accImgLabel.setBounds(10, 0, 70, 67);
		accPanel.add(accPanel_accImgLabel);
		
		accPanel_accNameLabel.setText(accName);
		accPanel_accNameLabel.setBounds(90, 11, 198, 22);
		accPanel.add(accPanel_accNameLabel);
		
		accPanel_accIDLabel.setText(accID);
		accPanel_accIDLabel.setBounds(90, 34, 77, 22);
		accPanel.add(accPanel_accIDLabel);
		
		accPanel_accPositionLabel.setText(accPosition);
		accPanel_accPositionLabel.setBounds(900, 45, 120, 22);
		accPanel.add(accPanel_accPositionLabel);

		accPanel_accHometownLabel.setText(accHometown);
		accPanel_accHometownLabel.setBounds(900, 0, 200, 20);
		accPanel.add(accPanel_accHometownLabel);

		accPanel_accGenderLabel.setText(accGender);
		accPanel_accGenderLabel.setBounds(900, 23, 120, 20);
		accPanel.add(accPanel_accGenderLabel);

		accPanel_notificationButton.setBounds(1127, 0, 45, 40);
		accPanel_notificationButton.setIcon(notiOffIcon);
		accPanel_notificationButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		accPanel_notificationButton.setOpaque(true);
		accPanel_notificationButton.setActionCommand("notification");
		accPanel_notificationButton.addActionListener(this);
		accPanel.add(accPanel_notificationButton);
	}



	//method for setting up components in Login panel
    public void setLogin()
	{
		loginPanel.setBounds(200, 300, 885, 630);
		loginPanel.setLayout(null);
		
		loginPanel_passwordTextField = new JPasswordField();
		loginPanel_passwordTextField.setBounds(328, 222, 194, 25);
		loginPanel.add(loginPanel_passwordTextField);
		
		loginPanel_userTextField = new JTextField();
		loginPanel_userTextField.setBounds(328, 186, 194, 25);
		loginPanel_userTextField.setColumns(1);
		loginPanel.add(loginPanel_userTextField);
		
		loginPanel_loginLabel = new JLabel("LOGIN");
		loginPanel_loginLabel.setForeground(Color.RED);
		loginPanel_loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		loginPanel_loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel_loginLabel.setBounds(262, 113, 284, 72);
		loginPanel.add(loginPanel_loginLabel);
		
		loginPanel_userLabel = new JLabel("User");
		loginPanel_userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel_userLabel.setBounds(238, 186, 80, 25);
		loginPanel.add(loginPanel_userLabel);
		
		loginPanel_passLabel = new JLabel("Password");
		loginPanel_passLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel_passLabel.setBounds(238, 222, 80, 25);
		loginPanel.add(loginPanel_passLabel);
		
		loginPanel_announceLabel = new JLabel("Welcome!!");
		loginPanel_announceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel_announceLabel.setBounds(286, 336, 252, 36);
		loginPanel.add(loginPanel_announceLabel);
		
		loginPanel_managerRaButt = new JRadioButton("Manager");
		loginPanel_managerRaButt.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel_managerRaButt.setBounds(298, 281, 125, 23);
		loginPanel_managerRaButt.setSelected(false);
		loginPanel_managerRaButt.setActionCommand("manager");
		loginPanel_managerRaButt.addActionListener(this);
		loginPanel.add(loginPanel_managerRaButt);
		
		loginPanel_staffRaButt = new JRadioButton("Staff");
		loginPanel_staffRaButt.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel_staffRaButt.setBounds(437, 281, 109, 23);
		loginPanel_staffRaButt.setSelected(false);
		loginPanel_staffRaButt.setActionCommand("staff");
		loginPanel_staffRaButt.addActionListener(this);
		loginPanel.add(loginPanel_staffRaButt);
		
		loginPanel_loginButt = new JButton("GO");
		loginPanel_loginButt.setForeground(Color.RED);
		loginPanel_loginButt.setFont(new Font("Times New Roman", Font.BOLD, 15));
		loginPanel_loginButt.setBounds(536, 186, 89, 61);
		loginPanel_loginButt.setActionCommand("lets go");
		loginPanel_loginButt.addActionListener(this);
		loginPanel_loginButt.setEnabled(false);
		loginPanel.add(loginPanel_loginButt);
	}



	//method for setting up Create a New Account panel
	public void setCreateNewAccountPanel()
	{
		creUsrPanel.setPreferredSize(new Dimension(907, 598));
		creUsrPanel.setLayout(null);
		
		creUsrPanel_userNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		creUsrPanel_userNameLabel.setBounds(171, 101, 111, 24);
		creUsrPanel.add(creUsrPanel_userNameLabel);
		
		creUsrPanel_passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		creUsrPanel_passwordLabel.setBounds(171, 151, 113, 24);
		creUsrPanel.add(creUsrPanel_passwordLabel);
		
		creUsrPanel_confirmloginPanel_passLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		creUsrPanel_confirmloginPanel_passLabel.setBounds(171, 208, 111, 24);
		creUsrPanel.add(creUsrPanel_confirmloginPanel_passLabel);
		
		creUsrPanel_userTextField.setBounds(351, 101, 386, 24);
		creUsrPanel.add(creUsrPanel_userTextField);
		creUsrPanel_userTextField.setColumns(10);
		
		creUsrPanel_passwordTextField.setColumns(10);
		creUsrPanel_passwordTextField.setBounds(351, 152, 386, 24);
		creUsrPanel.add(creUsrPanel_passwordTextField);
		
		creUsrPanel_confirmPassTextField.setColumns(10);
		creUsrPanel_confirmPassTextField.setBounds(351, 209, 386, 24);
		creUsrPanel.add(creUsrPanel_confirmPassTextField);
		
		creUsrPanel_titleLabel.setForeground(Color.RED);
		creUsrPanel_titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		creUsrPanel_titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creUsrPanel_titleLabel.setBounds(195, 26, 509, 45);
		creUsrPanel.add(creUsrPanel_titleLabel);
		
		creUsrPanel_IDLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		creUsrPanel_IDLabel.setBounds(171, 260, 111, 24);
		creUsrPanel.add(creUsrPanel_IDLabel);
		
		creUsrPanel_IDTextField.setColumns(10);
		creUsrPanel_IDTextField.setBounds(351, 261, 386, 24);
		creUsrPanel.add(creUsrPanel_IDTextField);
		
		creUsrPanel_createButton.setForeground(Color.RED);
		creUsrPanel_createButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		creUsrPanel_createButton.setBounds(381, 422, 137, 38);
		creUsrPanel_createButton.setActionCommand("create this account");
		creUsrPanel_createButton.addActionListener(this);
		creUsrPanel.add(creUsrPanel_createButton);

		creUsrPanel_managerRadioButt.setHorizontalAlignment(SwingConstants.CENTER);
		creUsrPanel_managerRadioButt.setBounds(351, 341, 109, 23);
		creUsrPanel_managerRadioButt.setActionCommand("create a manager login account");
		creUsrPanel_managerRadioButt.addActionListener(this);
		creUsrPanel.add(creUsrPanel_managerRadioButt);

		creUsrPanel_staffRadioButt.setHorizontalAlignment(SwingConstants.CENTER);
		creUsrPanel_staffRadioButt.setBounds(628, 341, 109, 23);
		creUsrPanel_staffRadioButt.setActionCommand("create a staff login account");
		creUsrPanel_staffRadioButt.addActionListener(this);
		creUsrPanel.add(creUsrPanel_staffRadioButt);

		creUsrPanel_roleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		creUsrPanel_roleLabel.setBounds(171, 340, 111, 24);
		creUsrPanel.add(creUsrPanel_roleLabel);
	}



	//method for setting up Change Password Panel
	public void setChangePassword()
	{
		changePassPanel.setPreferredSize(new Dimension(600, 320));;
		changePassPanel.setLayout(null);
		
		changePassPanel_oldPassLabel.setBounds(81, 53, 91, 25);
		changePassPanel.add(changePassPanel_oldPassLabel);
		
		changePassPanel_newPassLabel.setBounds(81, 107, 91, 25);
		changePassPanel.add(changePassPanel_newPassLabel);
		
		changePassPanel_cfmNewPassLabel.setBounds(81, 156, 91, 25);
		changePassPanel.add(changePassPanel_cfmNewPassLabel);
		
		changePassPanel_reasonLabel.setBounds(81, 212, 91, 25);
		changePassPanel.add(changePassPanel_reasonLabel);
		
		changePassPanel_reasonTextArea.setBounds(202, 212, 282, 72);
		changePassPanel.add(changePassPanel_reasonTextArea);
		
		changePassPanel_oldPassTextField.setBounds(202, 55, 282, 20);
		changePassPanel.add(changePassPanel_oldPassTextField);
		changePassPanel_oldPassTextField.setColumns(10);
		
		changePassPanel_newPassTextField.setColumns(10);
		changePassPanel_newPassTextField.setBounds(202, 107, 282, 20);
		changePassPanel.add(changePassPanel_newPassTextField);
		
		changePassPanel_cfmNewPassTextField.setColumns(10);
		changePassPanel_cfmNewPassTextField.setBounds(202, 158, 282, 20);
		changePassPanel.add(changePassPanel_cfmNewPassTextField);

		changePassPanel_titleLabel.setForeground(Color.RED);
		changePassPanel_titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		changePassPanel_titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		changePassPanel_titleLabel.setBounds(129, 11, 297, 33);
		changePassPanel.add(changePassPanel_titleLabel);
	}



	//method for setting up Tabbed Panel
	public void setTabbedPane()
	{
		images.readAndResizeImage(notiOnIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\notificationOnIcon.png", 30, 30);
		images.readAndResizeImage(notiOffIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\notificationOffIcon.png", 30, 30);
		images.readAndResizeImage(stfIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\staffIcon.png", 35, 35);
		images.readAndResizeImage(itmIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\itemIcon.png", 35, 35);
		images.readAndResizeImage(suppIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\supplierIcon.png", 35, 35);
		images.readAndResizeImage(cusIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\customerIcon.png", 35, 35);
		images.readAndResizeImage(stlIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\stallIcon.png", 35, 35);
		images.readAndResizeImage(ordIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\orderIcon.png", 35, 35);
		images.readAndResizeImage(deliIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\deliveryIcon.png", 35, 35);
		images.readAndResizeImage(recpIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\receiptIcon.png", 35, 35);
		staff.setStaffPanel();
		supplier.setSupplierPanel();
		item.setItemPanel();
		stall.setStallPanel();
		tabPanel.setBounds(10, 154, 1164, 594);
		tabPanel.addTab("Staff", stfIcon, staff.stfPanel);
		tabPanel.addTab("Item", itmIcon, item.itmPanel);
		tabPanel.addTab("Supplier", suppIcon, supplier.suppPanel);
		tabPanel.addTab("Customer", cusIcon, cusPanel);
		tabPanel.addTab("Stall", stlIcon, stall.stlPanel);
		tabPanel.addTab("Order", ordIcon, ordPanel);
		tabPanel.addTab("Delivery", deliIcon, deliPanel);
		tabPanel.addTab("Receipt", recpIcon, recpPanel);
	}



	//method for setting up Main Panel
	public void setMainPanel()
	{
		mainPanel.setBounds(10, 130, 1165, 594);
		mainPanel.setLayout(cardLayout);
		mainPanel.add(loginPanel, "login");
		mainPanel.add(tabPanel, "tab");
	}


	
	//method for setting up the Application Frame
    public void setUserFrame()
	{
		userFrame.setSize(1200, 800);
		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userFrame.setLocationRelativeTo(null);
		userFrame.setLayout(null);

		images.readIcons(addIcon, deleteIcon, editIcon, searchIcon, checkIcon, showIcon);

		setMenu();
		setLogin();
		setTabbedPane();
		setMainPanel();
		
		userFrame.add(menuBar);
		userFrame.getContentPane().add(mainPanel);
		userFrame.getContentPane().add(accPanel);
		userFrame.setVisible(true);
	}



    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}