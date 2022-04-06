import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class supplierPanel implements ActionListener
{
    public ImageIcon searchIcon = new ImageIcon();
	public ImageIcon checkIcon = new ImageIcon();
	public ImageIcon editIcon = new ImageIcon();
	public ImageIcon deleteIcon = new ImageIcon();
	public ImageIcon addIcon = new ImageIcon();
	public ImageIcon showIcon = new ImageIcon();

    public DefaultTableCellRenderer centerTextInCell = new DefaultTableCellRenderer();
    public JButton chooseImageButt;
    public String conclusion, selectedStaffGender;
    public byte[] addedByteOfImg, initByteImg;
    public int rowPoint;

	public JPanel suppPanel = new JPanel();
	public JButton suppPanel_searchOrCheckButt = new JButton("Search", searchIcon);
	public JTable suppTable = new JTable();
	public DefaultTableModel suppTableModel;
	public JScrollPane suppPanel_table = new JScrollPane();
	public JTextField suppPanel_textField = new JTextField();
	public JButton suppPanel_showTblButt = new JButton("Show full table");
	public JButton suppPanel_nationsupButt = new JButton("National suppliers");
	public JButton suppPanel_foreignsupButt = new JButton("Foreign supplier");
	public JButton suppPanel_iteminfoButt = new JButton("Supplier's items");
	public JButton suppPanel_searchSuppButt = new JButton("Search by ID");
	public JButton suppPanel_searchSuppByNameButt = new JButton("Search by Name");
	public JButton suppPanel_checknationButt = new JButton("Check nation");
	public JButton suppPanel_addButt = new JButton("Add", addIcon);
	public JButton suppPanel_deleteButt = new JButton("Delete", deleteIcon);
	public JButton suppPanel_editButt = new JButton("Edit", editIcon);

	public JPanel showSuppInfoPanel = new JPanel();
	public JLabel showSuppInfo_IDLabel = new JLabel("Supplier ID");
	public JLabel showSuppInfo_nameLabel = new JLabel("Name");
	public JLabel showSuppInfo_locationLabel = new JLabel("Location");
	public JLabel showSuppInfo_phoneNumberLabel = new JLabel("Phone Number");
	public JLabel showSuppInfo_IDShowLabel = new JLabel("");
	public JLabel showSuppInfo_locationShowLabel = new JLabel("");
	public JLabel showSuppInfo_phoneNumberShowLabel = new JLabel("");
	public JLabel showSuppInfo_nameShowLabel = new JLabel("");
	public JLabel showSuppInfo_supplierImageLabel = new JLabel("");
	public ImageIcon showSuppInfo_supplierImage = new ImageIcon();

	public JPanel addSuppInfoPanel = new JPanel();
	public JTextField addSuppInfo_nameTextField = new JTextField();
	public JTextField addSuppInfo_phoneNumberTextField = new JTextField();
	public JTextField addSuppInfo_IDTextField = new JTextField();
	public JTextField addSuppInfo_imageTextField = new JTextField();
	public JTextField addSuppInfo_locationTextField = new JTextField ();
	public JLabel addSuppInfo_IDLabel = new JLabel("ID");
	public JLabel addSuppInfo_nameLabel = new JLabel("Name");
	public JLabel addSuppInfo_locationLabel = new JLabel("Location");
	public JLabel addSuppInfo_phoneNumberLabel = new JLabel("Phone Number");
	public JButton addSuppInfo_completedButt;
	public JLabel addSuppInfo_imageLabel = new JLabel("Image");
	public File suppImgFile;

	public JPanel editSuppInfoPanel = new JPanel();
	public ImageIcon editSuppInfo_imgIcon = new ImageIcon();
	public JLabel editSuppInfo_supplierImgLabel = new JLabel();
	public JTextField editSuppInfo_nameTextField = new JTextField();
	public JTextField editSuppInfo_phoneNumberTextField = new JTextField();
	public JTextField editSuppInfo_IDTextField = new JTextField();
	public JTextField editSuppInfo_imageTextField = new JTextField();
	public JTextField editSuppInfo_locationTextField = new JTextField ();
	public JLabel editSuppInfo_IDLabel = new JLabel("ID");
	public JLabel editSuppInfo_nameLabel = new JLabel("Name");
	public JLabel editSuppInfo_locationLabel = new JLabel("Location");
	public JLabel editSuppInfo_phoneNumberLabel = new JLabel("Phone Numer");
	public JButton editSuppInfo_completedButt;



    //method for setting up components in Supplier Panel
	public void setSupplierPanel()
	{
        images.readIcons(addIcon, deleteIcon, editIcon, searchIcon, checkIcon, showIcon);

		suppPanel.setBounds(0, 0, 1194, 594);
		suppPanel.setLayout(null);

		centerTextInCell.setHorizontalAlignment(JLabel.CENTER);

		suppTable.setShowVerticalLines(false);
		suppTable.addMouseListener(new MouseInputAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if(SwingUtilities.isLeftMouseButton(e))
					{
						rowPoint = suppTable.rowAtPoint(e.getPoint());

						String Supplier_ID = null, Location = null, Supplier_Name = null, Phone_Number = null;
						byte[] imgByte = null;
						
						Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
						String sql = "select Supplier_ID, Supplier_Name, Location, Phone_Number, Image from Supplier where Supplier_ID = '" + (String) suppTable.getValueAt(rowPoint, 0) + "'";
						try
						{
							Statement stm = connect.createStatement();
							ResultSet rs = stm.executeQuery(sql);
							while(rs.next() == true)
							{
								Supplier_ID = rs.getString("Supplier_ID");
								Supplier_Name = rs.getString("Supplier_Name");
								Location = rs.getString("Location");
								Phone_Number = rs.getString("Phone_Number");
								Blob imgBlob = rs.getBlob("Image");
								imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
							}
						}
						catch(Exception exc)
						{
							exc.printStackTrace();
						}
						setPanelShowSuppInfo(Supplier_ID, Supplier_Name, Location, Phone_Number, imgByte);
						JOptionPane.showMessageDialog(null, showSuppInfoPanel, "Supplier's full information", JOptionPane.INFORMATION_MESSAGE);
					}

					if(SwingUtilities.isRightMouseButton(e))
					{
						rowPoint = suppTable.rowAtPoint(e.getPoint());
					}
				}
			}
		);
				
		suppPanel_table.setBounds(10, 145, 1130, 370);
		suppPanel_table.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		suppPanel_table.getVerticalScrollBar().setOpaque(false);
		suppPanel_table.setViewportView(suppTable);
		suppPanel.add(suppPanel_table);
		
		suppPanel_textField.setBounds(340, 42, 476, 34);
		suppPanel_textField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		suppPanel_textField.addMouseListener(new MouseInputAdapter() 
			{
				public void mouseClicked(MouseEvent e){
					suppPanel_textField.setText("");
					suppPanel_textField.setForeground(Color.BLACK);
				}
			}
		);
		suppPanel.add(suppPanel_textField);
		
		suppPanel_searchOrCheckButt.setBounds(877, 42, 130, 34);
		suppPanel_searchOrCheckButt.setActionCommand("search supplier");
		suppPanel_searchOrCheckButt.addActionListener(this);
		suppPanel.add(suppPanel_searchOrCheckButt);
		
		suppPanel_showTblButt.setBounds(10, 113, 152, 34);
		suppPanel_showTblButt.setToolTipText("Show full information of suppliers");
		suppPanel_showTblButt.setActionCommand("show supplier table");
		suppPanel_showTblButt.addActionListener(this);
		suppPanel.add(suppPanel_showTblButt);
		
		suppPanel_nationsupButt.setBounds(203, 113, 152, 34);
		suppPanel_nationsupButt.setToolTipText("Show information of national suppliers");
		suppPanel_nationsupButt.setActionCommand("show national suppliers");
		suppPanel_nationsupButt.addActionListener(this);
		suppPanel.add(suppPanel_nationsupButt);
		
		suppPanel_foreignsupButt.setBounds(401, 113, 152, 34);
		suppPanel_foreignsupButt.setToolTipText("Show information of foreign suppliers");
		suppPanel_foreignsupButt.setActionCommand("show foreign suppliers");
		suppPanel_foreignsupButt.addActionListener(this);
		suppPanel.add(suppPanel_foreignsupButt);

		suppPanel_iteminfoButt.setBounds(800, 113, 152, 34);
		suppPanel_iteminfoButt.setToolTipText("Show item's information of selected supplier\r\n");
		suppPanel_iteminfoButt.setActionCommand("show item info of a selected supplier");
		suppPanel_iteminfoButt.addActionListener(this);
		suppPanel.add(suppPanel_iteminfoButt);
		
		suppPanel_searchSuppButt.setBounds(988, 113, 152, 34);
		suppPanel_searchSuppButt.setToolTipText("Show supplier information by using supplier ID");
		suppPanel_searchSuppButt.setActionCommand("search supplier by ID _ option");
		suppPanel_searchSuppButt.addActionListener(this);
		suppPanel.add(suppPanel_searchSuppButt);
		
		suppPanel_checknationButt.setBounds(597, 113, 152, 34);
		suppPanel_checknationButt.setToolTipText("Check supplier is national or foreign");
		suppPanel_checknationButt.setActionCommand("check national or foreign supplier");
		suppPanel_checknationButt.addActionListener(this);
		suppPanel.add(suppPanel_checknationButt);
		
		suppPanel_addButt.setBounds(172, 42, 101, 34);
		suppPanel_addButt.setToolTipText("Add a supplier");
		suppPanel_addButt.setActionCommand("add supplier");
		suppPanel_addButt.addActionListener(this);
		suppPanel.add(suppPanel_addButt);
		
		suppPanel_deleteButt.setBounds(61, 42, 101, 34);
		suppPanel_deleteButt.setToolTipText("Delete a selected supplier");
		suppPanel_deleteButt.setActionCommand("delete supplier");
		suppPanel_deleteButt.addActionListener(this);
		suppPanel.add(suppPanel_deleteButt);

		suppPanel_editButt.setBounds(995, 42, 101, 34);
		suppPanel_editButt.setHorizontalTextPosition(SwingConstants.LEFT);
		suppPanel_editButt.setActionCommand("edit supplier");
		suppPanel_editButt.setToolTipText("Edit a selected Staff in the table");
		suppPanel_editButt.addActionListener(this);
		suppPanel.add(suppPanel_editButt);
	}



	//method for setting up components in Panel of showing Supplier's detail information
	public void  setPanelShowSuppInfo(String Supplier_ID, String Supplier_Name, String Location, String Phone_Number, byte[] url)
	{
		showSuppInfoPanel.setPreferredSize(new Dimension(864, 445));
		showSuppInfoPanel.setLayout(null);
		
		showSuppInfo_IDLabel.setBounds(24, 56, 87, 24);
		showSuppInfoPanel.add(showSuppInfo_IDLabel);
		
		showSuppInfo_nameLabel.setBounds(24, 113, 87, 24);
		showSuppInfoPanel.add(showSuppInfo_nameLabel);
		
		showSuppInfo_locationLabel.setBounds(24, 170, 87, 24);
		showSuppInfoPanel.add(showSuppInfo_locationLabel);
		
		showSuppInfo_phoneNumberLabel.setBounds(24, 234, 87, 24);
		showSuppInfoPanel.add(showSuppInfo_phoneNumberLabel);
		
		showSuppInfo_IDShowLabel.setText(Supplier_ID);
		showSuppInfo_IDShowLabel.setBounds(121, 56, 210, 24);
		showSuppInfoPanel.add(showSuppInfo_IDShowLabel);
		
		showSuppInfo_locationShowLabel.setText(Location);
		showSuppInfo_locationShowLabel.setBounds(121, 170, 210, 24);
		showSuppInfoPanel.add(showSuppInfo_locationShowLabel);
		
		showSuppInfo_phoneNumberShowLabel.setText(Phone_Number);
		showSuppInfo_phoneNumberShowLabel.setBounds(121, 234, 210, 24);
		showSuppInfoPanel.add(showSuppInfo_phoneNumberShowLabel);
		
		showSuppInfo_nameShowLabel.setText(Supplier_Name);
		showSuppInfo_nameShowLabel.setBounds(121, 113, 210, 24);
		showSuppInfoPanel.add(showSuppInfo_nameShowLabel);
		
		images.readAndResizeImageByBytes(showSuppInfo_supplierImage, url, 250, 320);
		showSuppInfo_supplierImageLabel.setIcon(showSuppInfo_supplierImage);
		showSuppInfo_supplierImageLabel.setBounds(573, 65, 250, 320);
		showSuppInfoPanel.add(showSuppInfo_supplierImageLabel);
	}



	//method for setting up components in Panel of Adding a new Supplier
	public void setPanelAddSuppInfo()
	{
		addSuppInfoPanel.setPreferredSize(new Dimension(864, 386));
		addSuppInfoPanel.setLayout(null);
		
		addSuppInfo_IDTextField.setBounds(96, 93, 266, 20);
		addSuppInfoPanel.add(addSuppInfo_IDTextField);
		addSuppInfo_IDTextField.setColumns(10);
		
		addSuppInfo_nameTextField.setColumns(10);
		addSuppInfo_nameTextField.setBounds(96, 138, 266, 20);
		addSuppInfoPanel.add(addSuppInfo_nameTextField);
		
		addSuppInfo_phoneNumberTextField.setColumns(10);
		addSuppInfo_phoneNumberTextField.setBounds(569, 93, 266, 20);
		addSuppInfoPanel.add(addSuppInfo_phoneNumberTextField);
		
		addSuppInfo_locationTextField.setColumns(10);
		addSuppInfo_locationTextField.setBounds(569, 138, 266, 20);
		addSuppInfoPanel.add(addSuppInfo_locationTextField);
		
		addSuppInfo_IDLabel.setBounds(24, 91, 62, 24);
		addSuppInfoPanel.add(addSuppInfo_IDLabel);
		
		addSuppInfo_nameLabel.setBounds(24, 136, 62, 24);
		addSuppInfoPanel.add(addSuppInfo_nameLabel);
		
		addSuppInfo_locationLabel.setBounds(479, 136, 62, 24);
		addSuppInfoPanel.add(addSuppInfo_locationLabel);
		
		addSuppInfo_phoneNumberLabel.setBounds(479, 91, 80, 24);
		addSuppInfoPanel.add(addSuppInfo_phoneNumberLabel);
		
		chooseImageButt = new JButton("Choose an image");
		chooseImageButt.setBounds(357, 192, 140, 33);
		chooseImageButt.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						JFileChooser fc = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
						fc.addChoosableFileFilter(filter);
						int dialog = fc.showOpenDialog(null);
						
						if(dialog == JFileChooser.APPROVE_OPTION)
						{
							suppImgFile = fc.getSelectedFile();
						}
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}
			}
		);
		addSuppInfoPanel.add(chooseImageButt);
	}



	//method for setting up components in Editting a Supplier information
	public void setPanelEditSuppInfo(String supplierID, String supplierName, String location, String phoneNumber, byte[] iniSuppImgByte)
	{
		addedByteOfImg = null;

		editSuppInfoPanel.setPreferredSize(new Dimension(864, 386));
		editSuppInfoPanel.setLayout(null);
		
		editSuppInfo_IDTextField.setBounds(115, 93, 266, 20);
		editSuppInfo_IDTextField.setText(supplierID);
		editSuppInfoPanel.add(editSuppInfo_IDTextField);
		editSuppInfo_IDTextField.setColumns(10);
		
		editSuppInfo_nameTextField.setColumns(10);
		editSuppInfo_nameTextField.setText(supplierName);
		editSuppInfo_nameTextField.setBounds(115, 138, 266, 20);
		editSuppInfoPanel.add(editSuppInfo_nameTextField);
		
		editSuppInfo_phoneNumberTextField.setColumns(10);
		editSuppInfo_phoneNumberTextField.setText(phoneNumber);
		editSuppInfo_phoneNumberTextField.setBounds(115, 243, 266, 20);
		editSuppInfoPanel.add(editSuppInfo_phoneNumberTextField);
		
		editSuppInfo_locationTextField.setColumns(10);
		editSuppInfo_locationTextField.setText(location);
		editSuppInfo_locationTextField.setBounds(115, 192, 266, 20);
		editSuppInfoPanel.add(editSuppInfo_locationTextField);

		editSuppInfo_IDLabel.setBounds(24, 91, 62, 24);
		editSuppInfoPanel.add(editSuppInfo_IDLabel);
		
		editSuppInfo_nameLabel.setBounds(24, 136, 62, 24);
		editSuppInfoPanel.add(editSuppInfo_nameLabel);
		
		editSuppInfo_locationLabel.setBounds(24, 190, 62, 24);
		editSuppInfoPanel.add(editSuppInfo_locationLabel);
		
		editSuppInfo_phoneNumberLabel.setBounds(24, 241, 81, 24);
		editSuppInfoPanel.add(editSuppInfo_phoneNumberLabel);

		images.readAndResizeImageByBytes(editSuppInfo_imgIcon, iniSuppImgByte, 256, 226);

		editSuppInfo_supplierImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editSuppInfo_supplierImgLabel.setIcon(editSuppInfo_imgIcon);
		editSuppInfo_supplierImgLabel.setBounds(556, 70, 256, 226);
		editSuppInfoPanel.add(editSuppInfo_supplierImgLabel);
		
		editSuppInfo_completedButt = new JButton("COMPLETED");
		editSuppInfo_completedButt.addActionListener(this);
		editSuppInfo_completedButt.setActionCommand("completed edit supplier");
		editSuppInfo_completedButt.setForeground(Color.RED);
		editSuppInfo_completedButt.setFont(new Font("Times New Roman", Font.BOLD, 15));
		editSuppInfo_completedButt.setBounds(364, 327, 132, 48);
		editSuppInfoPanel.add(editSuppInfo_completedButt);
		
		chooseImageButt = new JButton("Change image");
		chooseImageButt.setBounds(616, 304, 140, 33);
		chooseImageButt.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						JFileChooser fc = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
						fc.addChoosableFileFilter(filter);
						int dialog = fc.showOpenDialog(null);
						
						if(dialog == JFileChooser.APPROVE_OPTION)
						{
							suppImgFile = fc.getSelectedFile();
							addedByteOfImg = Files.readAllBytes(suppImgFile.toPath());
							ImageIcon newImg = new ImageIcon();
							images.readAndResizeImageByBytes(newImg, addedByteOfImg, 256, 226);
							editSuppInfo_supplierImgLabel.setIcon(newImg);
						}
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}
			}
		);
		editSuppInfoPanel.add(chooseImageButt);
	}



    @Override
    public void actionPerformed(ActionEvent e) 
	{
		//when clicking into Show full table button
		if(e.getActionCommand() == "show supplier table")
		{
			refreshTables.SupplierTable(suppTable, suppTableModel, centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				suppPanel_editButt.setVisible(true);
			}
			suppPanel_searchOrCheckButt.setEnabled(false);
		}  

		//when clicking into National Suppliers button
		if(e.getActionCommand() == "show national suppliers")
		{
			suppTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 4) return ImageIcon.class;
					return Object.class;
				}
			};
			suppTableModel.setColumnCount(0);
			suppTable.setRowHeight(100);
		
			suppTableModel.addColumn("Supplier ID");
			suppTableModel.addColumn("Supplier Name");
			suppTableModel.addColumn("Location");
			suppTableModel.addColumn("Phone Number");
			suppTableModel.addColumn("Image");
			suppTableModel.setRowCount(0);

			ArrayList<Supplier> list;
			list = getView.getNationalSupplierList();
			for(Supplier supplier:list)
			{
				suppTableModel.addRow(supplier.supplierToArray());
			}
			suppTable.setModel(suppTableModel);
			suppTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);

			suppPanel_editButt.setVisible(false);
			suppPanel_searchOrCheckButt.setEnabled(false);
		}  

		//when clicking into Foreign Suppliers button
		if(e.getActionCommand() == "show foreign suppliers")
		{
			suppTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 4) return ImageIcon.class;
					return Object.class;
				}
			};
			suppTableModel.setColumnCount(0);
			suppTable.setRowHeight(100);
		
			suppTableModel.addColumn("Supplier ID");
			suppTableModel.addColumn("Supplier Name");
			suppTableModel.addColumn("Location");
			suppTableModel.addColumn("Phone Number");
			suppTableModel.addColumn("Image");
			suppTableModel.setRowCount(0);

			ArrayList<Supplier> list;
			list = getView.getForeignSupplierList();
			for(Supplier supplier:list)
			{
				suppTableModel.addRow(supplier.supplierToArray());
			}
			suppTable.setModel(suppTableModel);
			suppTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);

			suppPanel_editButt.setVisible(false);
			suppPanel_searchOrCheckButt.setEnabled(false);
		}  

		//when clicking into Supplier's Items button
		if(e.getActionCommand() == "show item info of a selected supplier")
		{
			suppPanel_textField.setText("Please type the Supplier Name you want here...");
			suppPanel_textField.setForeground(Color.LIGHT_GRAY);

			suppPanel_searchOrCheckButt.setActionCommand("search selected supplier's item");
			suppPanel_searchOrCheckButt.setEnabled(true);
			suppPanel_searchOrCheckButt.setText("Search");
			suppPanel_searchOrCheckButt.setIcon(searchIcon);

			suppPanel_editButt.setVisible(false);
			suppPanel_searchOrCheckButt.setEnabled(true);
		}
		//when clicking into Search button
		if(e.getActionCommand() == "search selected supplier's item")
		{
			suppTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 5) return ImageIcon.class;
					return Object.class;
				}
			};
			suppTableModel.setColumnCount(0);
			suppTable.setRowHeight(100);
		
			suppTableModel.addColumn("Supplier Name");
			suppTableModel.addColumn("Stall Name");
			suppTableModel.addColumn("Item Code");
			suppTableModel.addColumn("Item Name");
			suppTableModel.addColumn("Selling Price");
			suppTableModel.addColumn("Image");
			suppTableModel.setRowCount(0);

			ArrayList<ItemInfoBaseOnSupplierName_proc> list;
			list = getProcedure.getItemInfoBaseOnSupplierNameList(suppPanel_textField.getText());
			for(ItemInfoBaseOnSupplierName_proc itm:list)
			{
				suppTableModel.addRow(itm.itemInfoBaseOnSupplierNameToArray());
			}
			suppTable.setModel(suppTableModel);
			suppTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);

			if(suppTable.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "This Supplier is non-exist");
			}

			if(connectToDatabase.userRole == "manager")
			{
				suppPanel_editButt.setVisible(true);
			}
		}  

		//when clicking into Check Nation button
		if(e.getActionCommand() == "check national or foreign supplier")
		{
			suppPanel_textField.setText("Please type the Supplier Name you want here...");
			suppPanel_textField.setForeground(Color.LIGHT_GRAY);

			suppPanel_searchOrCheckButt.setActionCommand("check and give conclusion whether selected supplier is national or foreign");
			suppPanel_searchOrCheckButt.setEnabled(true);
			suppPanel_searchOrCheckButt.setText("Check");
			suppPanel_searchOrCheckButt.setIcon(checkIcon);

			suppPanel_editButt.setVisible(false);
			suppPanel_searchOrCheckButt.setEnabled(true);
		}
		//when clicking into Check button
		if(e.getActionCommand() == "check and give conclusion whether selected supplier is national or foreign")
		{
			suppTableModel = new DefaultTableModel();
			suppTableModel.setColumnCount(0);
			suppTable.setRowHeight(100);

			conclusion = getFunction.checkNationalOrForeignSupplier(suppPanel_textField.getText(), conclusion);
			suppTableModel.addColumn("Conclusion");
			suppTableModel.setRowCount(0);
			suppTableModel.addRow(new Object[] {conclusion});

			suppTable.setModel(suppTableModel);
			suppTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);

			suppPanel_searchOrCheckButt.setText("Search");
			suppPanel_searchOrCheckButt.setIcon(searchIcon);
			suppPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Search Supplier by ID button
		if(e.getActionCommand() == "search supplier by ID _ option")
		{
			suppPanel_textField.setText("Please type the Supplier ID you want here...");
			suppPanel_textField.setForeground(Color.LIGHT_GRAY);

			suppPanel_searchOrCheckButt.setActionCommand("search supplier by ID _ action");
			suppPanel_searchOrCheckButt.setEnabled(true);
			suppPanel_searchOrCheckButt.setText("Search");
			suppPanel_searchOrCheckButt.setIcon(searchIcon);

			suppPanel_editButt.setVisible(false);
			suppPanel_searchOrCheckButt.setEnabled(true);
		}
		//when clicking into Search button
		if(e.getActionCommand() == "search supplier by ID _ action")
		{
			suppTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 4) return ImageIcon.class;
					return Object.class;
				}
			};
			suppTableModel.setColumnCount(0);
			suppTable.setRowHeight(100);
		
			suppTableModel.addColumn("Supplier ID");
			suppTableModel.addColumn("Supplier Name");
			suppTableModel.addColumn("Location");
			suppTableModel.addColumn("Phone Number");
			suppTableModel.addColumn("Image");
			suppTableModel.setRowCount(0);

			ArrayList<Supplier> list;
			list = getProcedure.getSupplierInfoBaseOnSupplierIDList(suppPanel_textField.getText());
			for(Supplier supplier:list)
			{
				suppTableModel.addRow(supplier.supplierToArray());
			}
			suppTable.setModel(suppTableModel);
			suppTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			suppTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);

			if(suppTable.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "This Supplier ID is non-exist");
			}

			if(connectToDatabase.userRole == "manager")
			{
				suppPanel_editButt.setVisible(true);
			}
			suppPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Add button
		if(e.getActionCommand() == "add supplier")
		{
			setPanelAddSuppInfo();
			JOptionPane.showMessageDialog(null, addSuppInfoPanel, "Add a new supplier into table", JOptionPane.INFORMATION_MESSAGE);
			String ImagePath = null;

			try 
			{
				ImagePath = suppImgFile.getAbsolutePath();
			} 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "You have not chosen an image yet !!");
			}
			editTable.addIntoSupplier(addSuppInfo_IDTextField.getText(), addSuppInfo_nameTextField.getText(), addSuppInfo_locationTextField.getText(), addSuppInfo_phoneNumberTextField.getText(), ImagePath);

			refreshTables.SupplierTable(suppTable, suppTableModel, centerTextInCell);
		}

		//when clicking into Delete button
		if(e.getActionCommand() == "delete supplier")
		{
			editTable.deleteOneRow("Supplier", "Supplier_ID", (String) suppTable.getValueAt(rowPoint, 0));

			refreshTables.SupplierTable(suppTable, suppTableModel, centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				suppPanel_editButt.setVisible(true);
			}
		}

		//when clicking Edit button
		if(e.getActionCommand() == "edit supplier")
		{
			String Supplier_ID = null, Location = null, Supplier_Name = null, Phone_Number = null;
			
			Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
			String sql = "select Supplier_ID, Supplier_Name, Location, Phone_Number, Image from Supplier where Supplier_ID = '" + (String) suppTable.getValueAt(rowPoint, 0) + "'";
			try
			{
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while(rs.next() == true)
				{
					Supplier_ID = rs.getString("Supplier_ID");
					Location = rs.getString("Location");
					Supplier_Name = rs.getString("Supplier_Name");
					Phone_Number = rs.getString("Phone_Number");
					Blob imgBlob = rs.getBlob("Image");
					initByteImg = imgBlob.getBytes(1, (int) imgBlob.length()); 
				}
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
			setPanelEditSuppInfo(Supplier_ID, Supplier_Name, Location, Phone_Number, initByteImg);
			JOptionPane.showMessageDialog(null, editSuppInfoPanel, "Edit Supplier information", JOptionPane.INFORMATION_MESSAGE);
		}
		//when clicking COMPLETED in edit supplier panel
		if(e.getActionCommand() == "completed edit supplier")
		{
			String Supplier_Name = editSuppInfo_nameTextField.getText();
			String Supplier_ID = editSuppInfo_IDTextField.getText();
			String Phone_Number = editSuppInfo_phoneNumberTextField.getText();
			String Location = editSuppInfo_locationTextField.getText();

			//if user doesn't choose any new Image to edit, it will take the byte[] of initial ImageIcon
			if(addedByteOfImg == null)
			{
				addedByteOfImg = initByteImg;
			}

			editTable.editOneCellInSupplierTable(Supplier_ID, Supplier_Name, Location, Phone_Number, addedByteOfImg);

			refreshTables.SupplierTable(suppTable, suppTableModel, centerTextInCell);
		}
        
    }
}
