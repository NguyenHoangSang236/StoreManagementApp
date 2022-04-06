import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.Event;

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

public class staffPanel implements ActionListener
{
    public DefaultTableCellRenderer centerTextInCell = new DefaultTableCellRenderer();
    public JButton chooseImageButt;
    public String conclusion, selectedStaffGender;
    public byte[] addedByteOfImg, initByteImg;
	public int rowPoint;
	public String sltItem;

    public JPanel stfPanel = new JPanel();
	public JScrollPane stfPanel_table = new JScrollPane();
	public JTable stfTable = new JTable();
	public DefaultTableModel stfTableModel;
	public JTextField stfPanel_textField = new JTextField();
	public JButton stfPanel_searchOrCheckButt = new JButton("Search");
	public JButton stfPanel_showTblButt = new JButton("Show full table");
	public JButton stfPanel_nationalStfButt = new JButton("National Staffs");
	public JButton stfPanel_foreignStfButt = new JButton("Foreign Staffs");
	public JButton stfPanel_staffsInPosButt = new JButton("Staffs in a Position");
	public JButton stfPanel_checkStfHtwnButt = new JButton("Hometown of a Staff");
	public JButton stfPanel_searchByIDButt = new JButton("Search by ID");
	public JButton stfPanel_searchByNameButt = new JButton("Search by Name");
	public JButton stfPanel_addButt = new JButton("Add");
	public JButton stfPanel_deleteButt = new JButton("Delete");
	public JButton stfPanel_editButt = new JButton("Edit");
	public String[] optionList = {"Show national Staffs", "Show foreign Staffs", "Show Staff in the selected Position", "Check the selected Staff's hometown", "Search using Staff's ID", "Search using Staff's name"}; 
	public popupResize stfPanel_comboBox = new popupResize(optionList);

	public JPanel showStfInfoPanel = new JPanel();
	public JLabel showStfInfo_IDLabel = new JLabel("Staff ID");
	public JLabel showStfInfo_nameLabel = new JLabel("Name");
	public JLabel showStfInfo_positionLabel = new JLabel("Position");
	public JLabel showStfInfo_hometownLabel = new JLabel("Hometown");
	public JLabel showStfInfo_genderLabel = new JLabel("Gender");
	public JLabel showStfInfo_IDShowLabel = new JLabel("");
	public JLabel showStfInfo_positionShowLabel = new JLabel("");
	public JLabel showStfInfo_hometownShowLabel = new JLabel("");
	public JLabel showStfInfo_nameShowLabel = new JLabel("");
	public JLabel showStfInfo_genderShowLabel = new JLabel("");
	public JLabel showStfInfo_staffImageLabel = new JLabel("");
	public ImageIcon showStfInfo_staffImage = new ImageIcon();

	public JPanel addStfInfoPanel = new JPanel();
	public JTextField addStfInfo_nameTextField = new JTextField();
	public JTextField addStfInfo_hometownTextField = new JTextField();
	public JTextField addStfInfo_IDTextField = new JTextField();
	public JTextField addStfInfo_imageTextField = new JTextField();
	public JTextField addStfInfo_positionTextField = new JTextField ();
	public JRadioButton addStfInfo_maleRadioButt = new JRadioButton("Male");
	public JRadioButton addStfInfo_femaleRadioButt = new JRadioButton("Female");
	public JLabel addStfInfo_IDLabel = new JLabel("ID");
	public JLabel addStfInfo_nameLabel = new JLabel("Name");
	public JLabel addStfInfo_positionLabel = new JLabel("Position");
	public JLabel addStfInfo_hometownLabel = new JLabel("Hometown");
	public JLabel addStfInfo_genderLabel = new JLabel("Gender");
	public JButton addStfInfo_completedButt;
	public JLabel addStfInfo_imageLabel = new JLabel("Image");
	public File stfImgFile;

	public JPanel editStfInfoPanel = new JPanel();
	public ImageIcon editStfInfo_imgIcon = new ImageIcon();
	public JLabel editStfInfo_staffImgLabel = new JLabel();
	public JTextField editStfInfo_nameTextField = new JTextField();
	public JTextField editStfInfo_hometownTextField = new JTextField();
	public JTextField editStfInfo_IDTextField = new JTextField();
	public JTextField editStfInfo_imageTextField = new JTextField();
	public JTextField editStfInfo_positionTextField = new JTextField ();
	public JRadioButton editStfInfo_maleRadioButt = new JRadioButton("Male");
	public JRadioButton editStfInfo_femaleRadioButt = new JRadioButton("Female");
	public JLabel editStfInfo_IDLabel = new JLabel("ID");
	public JLabel editStfInfo_nameLabel = new JLabel("Name");
	public JLabel editStfInfo_positionLabel = new JLabel("Position");
	public JLabel editStfInfo_hometownLabel = new JLabel("Hometown");
	public JLabel editStfInfo_genderLabel = new JLabel("Gender");
	public JButton editStfInfo_completedButt;

    public ImageIcon searchIcon = new ImageIcon();
	public ImageIcon checkIcon = new ImageIcon();
	public ImageIcon editIcon = new ImageIcon();
	public ImageIcon deleteIcon = new ImageIcon();
	public ImageIcon addIcon = new ImageIcon();
	public ImageIcon showIcon = new ImageIcon();


    //method for setting up components in Staff Panel
	public void setStaffPanel()
	{
        images.readIcons(addIcon, deleteIcon, editIcon, searchIcon, checkIcon, showIcon);

        stfPanel.setBounds(0, 0, 1164, 594);
		stfPanel.setLayout(null);

		centerTextInCell.setHorizontalAlignment(JLabel.CENTER);

		stfTable.setShowVerticalLines(false);
		stfTable.addMouseListener(new MouseInputAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if(SwingUtilities.isLeftMouseButton(e))
					{
						rowPoint = stfTable.rowAtPoint(e.getPoint());

						String Staff_ID = null, Position = null, Staff_Name = null, Hometown = null, Gender = null;
						byte[] imgByte = null;
						
						Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
						String sql = "select Staff_ID, Position, Staff_Name, Gender, Hometown, Image from Staff where Staff_ID = '" + (String) stfTable.getValueAt(rowPoint, 0) + "'";
						try
						{
							Statement stm = connect.createStatement();
							ResultSet rs = stm.executeQuery(sql);
							while(rs.next() == true)
							{
								Staff_ID = rs.getString("Staff_ID");
								Position = rs.getString("Position");
								Staff_Name = rs.getString("Staff_Name");
								Hometown = rs.getString("Hometown");
								Gender = rs.getString("Gender");
								Blob imgBlob = rs.getBlob("Image");
								imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
							}
						}
						catch(Exception exc)
						{
							exc.printStackTrace();
						}
						setPanelShowStfInfo(Staff_ID, Staff_Name, Position, Hometown, Gender, imgByte);
						JOptionPane.showMessageDialog(null, showStfInfoPanel, "Staff's full information", JOptionPane.INFORMATION_MESSAGE);
					}

					if(SwingUtilities.isRightMouseButton(e))
					{
						rowPoint = stfTable.rowAtPoint(e.getPoint());
					}
				}
			}
			);
			
		stfPanel_searchOrCheckButt.setBounds(877, 42, 130, 34);
		stfPanel_searchOrCheckButt.setIcon(searchIcon);
		stfPanel_searchOrCheckButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stfPanel_searchOrCheckButt.addActionListener(this);
		stfPanel_searchOrCheckButt.setForeground(Color.red);
		stfPanel.add(stfPanel_searchOrCheckButt);
		
		stfPanel_table.setBounds(10, 145, 1130, 370);
		stfPanel_table.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		stfPanel_table.getVerticalScrollBar().setOpaque(false);
		stfPanel_table.setViewportView(stfTable);
		stfPanel.add(stfPanel_table);
		
		stfPanel_textField.setBounds(340, 42, 343, 34);
		stfPanel_textField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		stfPanel_textField.addMouseListener(new MouseInputAdapter() 
		{
			public void mouseClicked(MouseEvent e){
				stfPanel_textField.setText("");
				stfPanel_textField.setForeground(Color.BLACK);
			}
			}
		);
		stfPanel.add(stfPanel_textField);

		stfPanel_comboBox.setBounds(684, 42, 142, 34);
		stfPanel_comboBox.setPreferredSize(new Dimension(180, 20));
        stfPanel_comboBox.setWide(true);
		stfPanel_comboBox.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					sltItem = (String) stfPanel_comboBox.getSelectedItem();
					//JOptionPane.showMessageDialog(null,sltItem);

					switch (sltItem) {
						case "Show national Staffs":
							stfPanel_searchOrCheckButt.setText("Show");
							stfPanel_searchOrCheckButt.setIcon(showIcon);
							
							break;

						case "Show foreign Staffs":
							stfPanel_searchOrCheckButt.setText("Show");
							stfPanel_searchOrCheckButt.setIcon(showIcon);
							
							break;

						case "Show Staff in the selected Position":
							stfPanel_searchOrCheckButt.setText("Search");
							stfPanel_searchOrCheckButt.setIcon(searchIcon);

							break;

						case "Search using Staff's ID":
							stfPanel_searchOrCheckButt.setText("Search");
							stfPanel_searchOrCheckButt.setIcon(searchIcon);

							break;

						case "Search using Staff's Name":
							stfPanel_searchOrCheckButt.setText("Search");
							stfPanel_searchOrCheckButt.setIcon(searchIcon);

							break;

						case "Check the selected Staff's hometown":
							stfPanel_searchOrCheckButt.setText("Check");
							stfPanel_searchOrCheckButt.setIcon(checkIcon);

							break;

						default:
							break;
					}

					if(sltItem.equals("Show national Staffs") || sltItem.equals("Show foreign Staffs"))
					{
						stfPanel_searchOrCheckButt.setText("Show");
						stfPanel_searchOrCheckButt.setIcon(showIcon);
					}
					else if(sltItem == "Show Staff in the selected Position" || sltItem == "Search using Staff's ID" || sltItem == "Search using Staff's Name")
					{
						stfPanel_searchOrCheckButt.setText("Search");
						stfPanel_searchOrCheckButt.setIcon(searchIcon);
					}
					else if(sltItem == "Check the selected Staff's hometown")
					{
						stfPanel_searchOrCheckButt.setText("Check");
						stfPanel_searchOrCheckButt.setIcon(checkIcon);
					}
				}
			}
		);

		stfPanel.add(stfPanel_comboBox);
		
		stfPanel_showTblButt.setBounds(10, 113, 152, 34);
		stfPanel_showTblButt.setActionCommand("show staff table");
		stfPanel_showTblButt.setToolTipText("Show all information in the Staff table");
		stfPanel_showTblButt.addActionListener(this);
		stfPanel.add(stfPanel_showTblButt);
		
		stfPanel_nationalStfButt.setBounds(172, 113, 152, 34);
		stfPanel_nationalStfButt.setActionCommand("show national staffs");
		stfPanel_nationalStfButt.setToolTipText("Show all information of national Staffs");
		stfPanel_nationalStfButt.addActionListener(this);
		stfPanel.add(stfPanel_nationalStfButt);
		
		stfPanel_foreignStfButt.setBounds(340, 113, 152, 34);
		stfPanel_foreignStfButt.setActionCommand("show foreign staffs");
		stfPanel_foreignStfButt.setToolTipText("Show all information of foreign Staffs");
		stfPanel_foreignStfButt.addActionListener(this);
		stfPanel.add(stfPanel_foreignStfButt);
		
		stfPanel_staffsInPosButt.setBounds(502, 113, 152, 34);
		stfPanel_staffsInPosButt.setActionCommand("show staff in the selected position");
		stfPanel_staffsInPosButt.setToolTipText("Show conclusion about the Seller's name working in the selected Position basing on Position name");
		stfPanel_staffsInPosButt.addActionListener(this);
		stfPanel.add(stfPanel_staffsInPosButt);
		
		stfPanel_checkStfHtwnButt.setBounds(664, 113, 152, 34);
		stfPanel_checkStfHtwnButt.setActionCommand("check national or foreign staff");
		stfPanel_checkStfHtwnButt.setToolTipText("Show conclustion about the selected Staff is national or foreign basing on their ID");
		stfPanel_checkStfHtwnButt.addActionListener(this);
		stfPanel.add(stfPanel_checkStfHtwnButt);
		
		stfPanel_searchByIDButt.setBounds(826, 113, 152, 34);
		stfPanel_searchByIDButt.setActionCommand("search staff by ID _ option");
		stfPanel_searchByIDButt.setToolTipText("Show full information of the Staff basing on the selected Staff ID");
		stfPanel_searchByIDButt.addActionListener(this);
		stfPanel.add(stfPanel_searchByIDButt);
		
		stfPanel_searchByNameButt.setBounds(988, 113, 152, 34);
		stfPanel_searchByNameButt.setActionCommand("search staff by name");
		stfPanel_searchByNameButt.setToolTipText("Show full information of the Staff basing on the selected Staff Name");
		stfPanel_searchByNameButt.addActionListener(this);
		stfPanel.add(stfPanel_searchByNameButt);
		
		stfPanel_addButt.setBounds(172, 42, 101, 34);
        stfPanel_addButt.setIcon(addIcon);
		stfPanel_addButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stfPanel_addButt.setActionCommand("add staff");
		stfPanel_addButt.setToolTipText("Add one more Staff into the table");
		stfPanel_addButt.addActionListener(this);
		stfPanel.add(stfPanel_addButt);
		
		stfPanel_deleteButt.setBounds(61, 42, 101, 34);
        stfPanel_deleteButt.setIcon(deleteIcon);
		stfPanel_deleteButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stfPanel_deleteButt.setActionCommand("delete staff");
		stfPanel_deleteButt.setToolTipText("Delete a selected Staff in the table");
		stfPanel_deleteButt.addActionListener(this);
		stfPanel.add(stfPanel_deleteButt);

		stfPanel_editButt.setBounds(995, 42, 101, 34);
        stfPanel_editButt.setIcon(editIcon);
		stfPanel_editButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stfPanel_editButt.setActionCommand("edit staff");
		stfPanel_editButt.setToolTipText("Edit a selected Staff in the table");
		stfPanel_editButt.addActionListener(this);
		stfPanel.add(stfPanel_editButt);
	}



	//method for setting up components in Panel of showing Staff's detail information 
	public void setPanelShowStfInfo(String ID, String name, String position, String hometown, String gender, byte[] url)
	{
		showStfInfoPanel.setPreferredSize(new Dimension(864, 445));
		showStfInfoPanel.setLayout(null);
		
		showStfInfo_IDLabel.setBounds(24, 43, 62, 24);
		showStfInfoPanel.add(showStfInfo_IDLabel);
		
		showStfInfo_nameLabel.setBounds(24, 113, 62, 24);
		showStfInfoPanel.add(showStfInfo_nameLabel);
		
		showStfInfo_positionLabel.setBounds(24, 170, 62, 24);
		showStfInfoPanel.add(showStfInfo_positionLabel);
		
		showStfInfo_hometownLabel.setBounds(24, 234, 62, 24);
		showStfInfoPanel.add(showStfInfo_hometownLabel);
		
		showStfInfo_genderLabel.setBounds(24, 301, 62, 24);
		showStfInfoPanel.add(showStfInfo_genderLabel);
		
		showStfInfo_IDShowLabel.setText(ID);
		showStfInfo_IDShowLabel.setBounds(96, 43, 210, 24);
		showStfInfoPanel.add(showStfInfo_IDShowLabel);
		
		showStfInfo_positionShowLabel.setText(position);
		showStfInfo_positionShowLabel.setBounds(96, 170, 210, 24);
		showStfInfoPanel.add(showStfInfo_positionShowLabel);
		
		showStfInfo_hometownShowLabel.setText(hometown);
		showStfInfo_hometownShowLabel.setBounds(96, 234, 210, 24);
		showStfInfoPanel.add(showStfInfo_hometownShowLabel);
		
		showStfInfo_nameShowLabel.setText(name);
		showStfInfo_nameShowLabel.setBounds(96, 113, 210, 24);
		showStfInfoPanel.add(showStfInfo_nameShowLabel);
		
		showStfInfo_genderShowLabel.setText(gender);
		showStfInfo_genderShowLabel.setBounds(96, 301, 210, 24);
		showStfInfoPanel.add(showStfInfo_genderShowLabel);
		
		images.readAndResizeImageByBytes(showStfInfo_staffImage, url, 250, 320);
		showStfInfo_staffImageLabel.setIcon(showStfInfo_staffImage);
		showStfInfo_staffImageLabel.setBounds(573, 65, 250, 320);
		showStfInfoPanel.add(showStfInfo_staffImageLabel);
	}



	//method for setting up components in Panel of Adding a new Staff
	public void setPanelAddStfInfo()
	{
		addStfInfoPanel.setPreferredSize(new Dimension(864, 386));
		addStfInfoPanel.setLayout(null);
		
		addStfInfo_IDTextField.setBounds(96, 93, 266, 20);
		addStfInfoPanel.add(addStfInfo_IDTextField);
		addStfInfo_IDTextField.setColumns(10);
		
		addStfInfo_nameTextField.setColumns(10);
		addStfInfo_nameTextField.setBounds(96, 138, 266, 20);
		addStfInfoPanel.add(addStfInfo_nameTextField);
		
		addStfInfo_hometownTextField.setColumns(10);
		addStfInfo_hometownTextField.setBounds(569, 93, 266, 20);
		addStfInfoPanel.add(addStfInfo_hometownTextField);
		
		addStfInfo_positionTextField.setColumns(10);
		addStfInfo_positionTextField.setBounds(96, 192, 266, 20);
		addStfInfoPanel.add(addStfInfo_positionTextField);
		
		addStfInfo_maleRadioButt.setBounds(569, 137, 109, 23);
		addStfInfo_maleRadioButt.setActionCommand("male");
		addStfInfo_maleRadioButt.addActionListener(this);
		addStfInfoPanel.add(addStfInfo_maleRadioButt);
		
		addStfInfo_femaleRadioButt.setBounds(726, 137, 109, 23);
		addStfInfo_femaleRadioButt.setActionCommand("female");
		addStfInfo_femaleRadioButt.addActionListener(this);
		addStfInfoPanel.add(addStfInfo_femaleRadioButt);
		
		addStfInfo_IDLabel.setBounds(24, 91, 62, 24);
		addStfInfoPanel.add(addStfInfo_IDLabel);
		
		addStfInfo_nameLabel.setBounds(24, 136, 62, 24);
		addStfInfoPanel.add(addStfInfo_nameLabel);
		
		addStfInfo_positionLabel.setBounds(24, 190, 62, 24);
		addStfInfoPanel.add(addStfInfo_positionLabel);
		
		addStfInfo_hometownLabel.setBounds(497, 91, 62, 24);
		addStfInfoPanel.add(addStfInfo_hometownLabel);
		
		addStfInfo_genderLabel.setBounds(497, 136, 62, 24);
		addStfInfoPanel.add(addStfInfo_genderLabel);
		
		chooseImageButt = new JButton("Choose an image");
		chooseImageButt.setBounds(497, 186, 140, 33);
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
							stfImgFile = fc.getSelectedFile();
						}
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}
			}
		);
		addStfInfoPanel.add(chooseImageButt);
	}



	//method for setting up components in Panel of Editting a Staff Information
	public void setPanelEditStfInfo(String staffID, String staffName, String position, String hometown, String gender, byte[] iniStfImgByte, byte[] addedByteOfImg)
	{
		addedByteOfImg = null;

		editStfInfoPanel.setPreferredSize(new Dimension(864, 386));
		editStfInfoPanel.setLayout(null);
		
		editStfInfo_IDTextField.setBounds(96, 93, 266, 20);
		editStfInfo_IDTextField.setText(staffID);
		editStfInfoPanel.add(editStfInfo_IDTextField);
		editStfInfo_IDTextField.setColumns(10);
		
		editStfInfo_nameTextField.setColumns(10);
		editStfInfo_nameTextField.setText(staffName);
		editStfInfo_nameTextField.setBounds(96, 138, 266, 20);
		editStfInfoPanel.add(editStfInfo_nameTextField);
		
		editStfInfo_hometownTextField.setColumns(10);
		editStfInfo_hometownTextField.setText(hometown);
		editStfInfo_hometownTextField.setBounds(96, 243, 266, 20);
		editStfInfoPanel.add(editStfInfo_hometownTextField);
		
		editStfInfo_positionTextField.setColumns(10);
		editStfInfo_positionTextField.setText(position);
		editStfInfo_positionTextField.setBounds(96, 192, 266, 20);
		editStfInfoPanel.add(editStfInfo_positionTextField);
		
		editStfInfo_maleRadioButt.setBounds(96, 286, 109, 23);
		editStfInfo_maleRadioButt.setActionCommand("male");
		editStfInfo_maleRadioButt.addActionListener(this);
		editStfInfoPanel.add(editStfInfo_maleRadioButt);
		
		editStfInfo_femaleRadioButt.setBounds(253, 286, 109, 23);
		editStfInfo_femaleRadioButt.setActionCommand("female");
		editStfInfo_femaleRadioButt.addActionListener(this);
		editStfInfoPanel.add(editStfInfo_femaleRadioButt);

		if(gender.equals("Male"))
		{
			editStfInfo_maleRadioButt.setSelected(true);
		}
		else if(gender.equals("Female"))
		{
			editStfInfo_femaleRadioButt.setSelected(true);
		}
		
		editStfInfo_IDLabel.setBounds(24, 91, 62, 24);
		editStfInfoPanel.add(editStfInfo_IDLabel);
		
		editStfInfo_nameLabel.setBounds(24, 136, 62, 24);
		editStfInfoPanel.add(editStfInfo_nameLabel);
		
		editStfInfo_positionLabel.setBounds(24, 190, 62, 24);
		editStfInfoPanel.add(editStfInfo_positionLabel);
		
		editStfInfo_hometownLabel.setBounds(24, 241, 62, 24);
		editStfInfoPanel.add(editStfInfo_hometownLabel);
		
		editStfInfo_genderLabel.setBounds(24, 285, 62, 24);
		editStfInfoPanel.add(editStfInfo_genderLabel);

		images.readAndResizeImageByBytes(editStfInfo_imgIcon, iniStfImgByte, 256, 226);

		editStfInfo_staffImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editStfInfo_staffImgLabel.setIcon(editStfInfo_imgIcon);
		editStfInfo_staffImgLabel.setBounds(556, 70, 256, 226);
		editStfInfoPanel.add(editStfInfo_staffImgLabel);
		
		editStfInfo_completedButt = new JButton("COMPLETED");
		editStfInfo_completedButt.addActionListener(this);
		editStfInfo_completedButt.setActionCommand("completed edit staff");
		editStfInfo_completedButt.setForeground(Color.RED);
		editStfInfo_completedButt.setFont(new Font("Times New Roman", Font.BOLD, 15));
		editStfInfo_completedButt.setBounds(364, 327, 132, 48);
		editStfInfoPanel.add(editStfInfo_completedButt);
		
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
							stfImgFile = fc.getSelectedFile();
							byte[] addedByteOfImg = Files.readAllBytes(stfImgFile.toPath());
							ImageIcon newImg = new ImageIcon();
							images.readAndResizeImageByBytes(newImg, addedByteOfImg, 256, 226);
							editStfInfo_staffImgLabel.setIcon(newImg);
						}
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}
			}
		);
		editStfInfoPanel.add(chooseImageButt);
	}



    @Override
    public void actionPerformed(ActionEvent e) 
    {
		//when clicking into Show full table button
		if(e.getActionCommand().equals("show staff table"))
		{
			refreshTables.StaffTable(stfTable, stfTableModel, centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				stfPanel_editButt.setVisible(true);
			}
			//stfPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into National Staffs button
		if(e.getActionCommand() == "show national staffs")
		{
			stfTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 5) return ImageIcon.class;
					return Object.class;
				}
			};
			stfTableModel.setColumnCount(0);
			stfTable.setRowHeight(100);

			stfTableModel.addColumn("Staff ID");
			stfTableModel.addColumn("Position");
			stfTableModel.addColumn("Staff Name");
			stfTableModel.addColumn("Gender");
			stfTableModel.addColumn("Hometown");
			stfTableModel.addColumn("Image");
			stfTableModel.setRowCount(0);
			stfTableModel.setRowCount(0);

			ArrayList<Staff> list;
			list = getView.getNationalStaffList();
			for(Staff l : list)
			{
			    stfTableModel.addRow(l.staffToArray());
			}
			stfTable.setModel(stfTableModel);
			stfTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
		    stfTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);

			stfPanel_editButt.setVisible(false);
			//stfPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Foreign Staffs button
		if(e.getActionCommand() == "show foreign staffs")
		{
			stfTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 5) return ImageIcon.class;
					return Object.class;
				}
			};
			stfTableModel.setColumnCount(0);
			stfTable.setRowHeight(100);

			stfTableModel.addColumn("Staff ID");
			stfTableModel.addColumn("Position");
			stfTableModel.addColumn("Staff Name");
			stfTableModel.addColumn("Gender");
			stfTableModel.addColumn("Hometown");
			stfTableModel.addColumn("Image");
			stfTableModel.setRowCount(0);
			stfTableModel.setRowCount(0);

			ArrayList<Staff> list;
			list = getView.getForeignStaffList();
			for(Staff l : list)
			{
				stfTableModel.addRow(l.staffToArray());
			}
			stfTable.setModel(stfTableModel);
			stfTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);

			stfPanel_editButt.setVisible(false);
			//stfPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Staffs in a Position button
		if(e.getActionCommand() == "show staff in the selected position")
		{
			stfPanel_textField.setText("Please type the Position you want here...");
			stfPanel_textField.setForeground(Color.LIGHT_GRAY);

			stfPanel_searchOrCheckButt.setActionCommand("show staff info working in selected position");
			stfPanel_searchOrCheckButt.setEnabled(true);
			stfPanel_searchOrCheckButt.setText("Check");
			stfPanel_searchOrCheckButt.setIcon(checkIcon);

			stfPanel_editButt.setVisible(false);
		}
		//when clicking into Check button
		if(e.getActionCommand() == "show staff info working in selected position")
		{
			stfTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 5) return ImageIcon.class;
					return Object.class;
				}
			};
			stfTableModel.setColumnCount(0);
			stfTable.setRowHeight(100);

			stfTableModel.addColumn("Staff ID");
			stfTableModel.addColumn("Position");
			stfTableModel.addColumn("Staff Name");
			stfTableModel.addColumn("Gender");
			stfTableModel.addColumn("Hometown");
			stfTableModel.addColumn("Image");
			stfTableModel.setRowCount(0);
			stfTableModel.setRowCount(0);

			ArrayList<Staff> list;
			list = getProcedure.getStaffWorkingInSelectedPositonList(stfPanel_textField.getText());
			for(Staff l : list)
			{
				stfTableModel.addRow(l.staffToArray());
			}
			stfTable.setModel(stfTableModel);
			stfTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);

			if(stfTable.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "This Position is non-exist");
			}

			stfPanel_searchOrCheckButt.setText("Search");
			stfPanel_searchOrCheckButt.setIcon(searchIcon);
			//stfPanel_searchOrCheckButt.setEnabled(false);

			if(connectToDatabase.userRole == "manager")
			{
				stfPanel_editButt.setVisible(true);
			}
		}

		//when clicking into Hometown of a Staff button
		if(e.getActionCommand() == "check national or foreign staff")
		{
			stfPanel_textField.setText("Please type the Staff ID you want here...");
			stfPanel_textField.setForeground(Color.LIGHT_GRAY);

			stfPanel_searchOrCheckButt.setActionCommand("check and give conclusion whether selected staff is national or foreign");
			stfPanel_searchOrCheckButt.setEnabled(true);
			stfPanel_searchOrCheckButt.setText("Check");
			stfPanel_searchOrCheckButt.setIcon(checkIcon);

			stfPanel_editButt.setVisible(false);
		}
		//when clicking into Check button
		if(e.getActionCommand() == "check and give conclusion whether selected staff is national or foreign")
		{
			stfTableModel = new DefaultTableModel();
			stfTableModel.setColumnCount(0);
			stfTable.setRowHeight(100);

			conclusion = getFunction.checkHometownOfStaff(stfPanel_textField.getText(), conclusion);
			stfTableModel.addColumn("Conclusion");
			stfTableModel.setRowCount(0);
			stfTableModel.addRow(new Object[] {conclusion});

			stfTable.setModel(stfTableModel);
			stfTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);

			stfPanel_searchOrCheckButt.setText("Search");
			stfPanel_searchOrCheckButt.setIcon(searchIcon);
			//stfPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Search Staff by ID button
		if(e.getActionCommand() == "search staff by ID _ option")
		{
			stfPanel_textField.setText("Please type the Staff ID you want here...");
			stfPanel_textField.setForeground(Color.LIGHT_GRAY);

			stfPanel_searchOrCheckButt.setActionCommand("search staff by ID _ action");
			stfPanel_searchOrCheckButt.setEnabled(true);
			stfPanel_searchOrCheckButt.setText("Search");
			stfPanel_searchOrCheckButt.setIcon(searchIcon);

			stfPanel_editButt.setVisible(false);
		}
		//when clicking into Search button
		if(e.getActionCommand() == "search staff by ID _ action")
		{
			stfTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column==5) return ImageIcon.class;
					return Object.class;
				}
			};
			stfTableModel.setColumnCount(0);
			stfTable.setRowHeight(100);
		
			stfTableModel.addColumn("Staff ID");
			stfTableModel.addColumn("Position");
			stfTableModel.addColumn("Staff Name");
			stfTableModel.addColumn("Gender");
			stfTableModel.addColumn("Hometown");
			stfTableModel.addColumn("Image");
			stfTableModel.setRowCount(0);

			ArrayList<Staff> list;
			list = getProcedure.showStfInfoUsingID(stfPanel_textField.getText());     
			for(Staff stf:list)
			{
				stfTableModel.addRow(stf.staffToArray());
			}
			stfTable.setModel(stfTableModel);
			stfTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);

			if(stfTable.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "This Staff ID is non-exist");
			}

			if(connectToDatabase.userRole == "manager")
			{
				stfPanel_editButt.setVisible(true);
			}
			//stfPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Search Staff by Name button
		if(e.getActionCommand() == "search staff by name")
		{
			stfPanel_textField.setText("Please type the Staff Name you want here...");
			stfPanel_textField.setForeground(Color.LIGHT_GRAY);

			stfPanel_searchOrCheckButt.setActionCommand("search staff using name");
			stfPanel_searchOrCheckButt.setEnabled(true);
			stfPanel_searchOrCheckButt.setText("Search");
			stfPanel_searchOrCheckButt.setIcon(searchIcon);

			stfPanel_editButt.setVisible(false);
		}
		//when clicking into Search button
		if(e.getActionCommand().equals("search staff using name"))
		{
			stfTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column==5) return ImageIcon.class;
					return Object.class;
				}
			};
			stfTableModel.setColumnCount(0);
			stfTable.setRowHeight(100);
		
			stfTableModel.addColumn("Staff ID");
			stfTableModel.addColumn("Position");
			stfTableModel.addColumn("Staff Name");
			stfTableModel.addColumn("Gender");
			stfTableModel.addColumn("Hometown");
			stfTableModel.addColumn("Image");
			stfTableModel.setRowCount(0);

			ArrayList<Staff> list;
			list = getTable.searchStaffUsingName(stfPanel_textField.getText());
			for(Staff stf:list)
			{
				stfTableModel.addRow(stf.staffToArray());
			}
			stfTable.setModel(stfTableModel);
			stfTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			stfTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);

			if(stfTable.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "This Staff Name is non-exist");
			}

			if(connectToDatabase.userRole == "manager")
			{
				stfPanel_editButt.setVisible(true);
			}
			//stfPanel_searchOrCheckButt.setEnabled(false);
		}

		//if staff's gender is MALE
		if(e.getActionCommand() == "male")
		{
			addStfInfo_femaleRadioButt.setSelected(false);
			editStfInfo_femaleRadioButt.setSelected(false);
			selectedStaffGender = addStfInfo_maleRadioButt.getText();
		}
		//if staff's gender is FEMALE
		if(e.getActionCommand() == "female")
		{
			addStfInfo_maleRadioButt.setSelected(false);
			editStfInfo_maleRadioButt.setSelected(false);
			selectedStaffGender = addStfInfo_femaleRadioButt.getText();
		}

		//when clicking into Add button
		if(e.getActionCommand() == "add staff")
		{
			setPanelAddStfInfo();
			JOptionPane.showMessageDialog(null, addStfInfoPanel, "Add a new staff into table", JOptionPane.INFORMATION_MESSAGE);
			String ImagePath = null;

			try 
			{
				ImagePath = stfImgFile.getAbsolutePath();
			} 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "You have not chosen an image yet !!");
			}
			editTable.addIntoStaff(addStfInfo_IDTextField.getText(),addStfInfo_positionTextField.getText(), addStfInfo_nameTextField.getText(), selectedStaffGender, addStfInfo_hometownTextField.getText(), ImagePath);

			refreshTables.StaffTable(stfTable, stfTableModel, centerTextInCell);
		}

		//when clicking into Delete button
		if(e.getActionCommand() == "delete staff")
		{
			editTable.deleteOneRow("Staff", "Staff_ID", (String) stfTable.getValueAt(rowPoint, 0));

			refreshTables.StaffTable(stfTable, stfTableModel, centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				stfPanel_editButt.setVisible(true);
			}
		}

		//when clicking Edit button
		if(e.getActionCommand() == "edit staff")
		{
			String Staff_ID = null, Position = null, Staff_Name = null, Hometown = null, Gender = null;
			
			Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
			String sql = "select Staff_ID, Position, Staff_Name, Gender, Hometown, Image from Staff where Staff_ID = '" + (String) stfTable.getValueAt(rowPoint, 0) + "'";
			try
			{
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while(rs.next() == true)
				{
					Staff_ID = rs.getString("Staff_ID");
					Position = rs.getString("Position");
					Staff_Name = rs.getString("Staff_Name");
					Hometown = rs.getString("Hometown");
					Gender = rs.getString("Gender");
					Blob imgBlob = rs.getBlob("Image");
					initByteImg = imgBlob.getBytes(1, (int) imgBlob.length()); 
				}
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
			setPanelEditStfInfo(Staff_ID, Staff_Name, Position, Hometown, Gender, initByteImg, addedByteOfImg);
			JOptionPane.showMessageDialog(null, editStfInfoPanel, "Edit Staff information", JOptionPane.INFORMATION_MESSAGE);
		}
		//when clicking COMPLETED in edit staff panel
		if(e.getActionCommand() == "completed edit staff")
		{
			String Staff_Name = editStfInfo_nameTextField.getText();
			String Staff_ID = editStfInfo_IDTextField.getText();
			String Hometown = editStfInfo_hometownTextField.getText();
			String Position = editStfInfo_positionTextField.getText();
			String Gender = null;

			if(editStfInfo_maleRadioButt.isSelected())
			{
				Gender = "Male";
			}
			else if(editStfInfo_femaleRadioButt.isSelected())
			{
				Gender = "Female";
			}

			//if user doesn't choose any new Image to edit, it will take the byte[] of initial ImageIcon
			if(addedByteOfImg == null)
			{
				addedByteOfImg = initByteImg;
			}

			editTable.editOneCellInStaffTable(Staff_ID, Staff_Name, Hometown, Position, Gender, addedByteOfImg);

			refreshTables.StaffTable(stfTable, stfTableModel, centerTextInCell);
		}
    }
}
