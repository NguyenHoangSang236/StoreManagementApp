import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class stallPanel implements ActionListener
{
    public DefaultTableCellRenderer centerTextInCell = new DefaultTableCellRenderer();
    public JButton chooseImageButt;
    public String conclusion, selectedStaffGender, stlStall;
    public byte[] addedByteOfImg, initByteImg;
	public int rowPoint;
	public String[] optionList = {"Show Sellers at all Stalls", "Show Items in selected Stall", "Check Seller name working in selected Stall", "Compare 2 different Stalls", "Search Stall using Name", "Search Stall using Ordinal Number"};

    public JPanel stlPanel = new JPanel();
    public JScrollPane stlPanel_table = new JScrollPane();
    public JTable stlTable = new JTable();
    public DefaultTableModel stlTableModel;
    public JTextField stlPanel_textField = new JTextField();
    public JButton stlPanel_searchOrCheckButt = new JButton("Search");
    public JButton stlPanel_showTblButt = new JButton("Show full table");
    public JButton stlPanel_addButt = new JButton("Add");
    public JButton stlPanel_deleteButt = new JButton("Delete");
    public JButton stlPanel_editButt = new JButton("Edit");
	public customedComboBox stlPanel_comboBox = new customedComboBox(optionList);

	public ImageIcon compareIcon = new ImageIcon();
	public ImageIcon nextIcon = new ImageIcon();

	public JPanel cmpPanel = new JPanel();
	public JLabel cmpPanel_firstStallLabel = new JLabel("First Stall");
	public JLabel cmpPanel_secondStallLabel = new JLabel("Second Stall");
	public JTextField cmpPanel_firstStallTextField = new JTextField("First Stall's name...");
	public JTextField cmpPanel_secondStallTextField = new JTextField("Second Stall's name...");

	
    public void setStallPanel()
    {
		images.readAndResizeImage(compareIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\compareIcon.png", 27, 27);
		images.readAndResizeImage(nextIcon, "F:\\study\\JAVA\\StoreManagementApp\\icons\\nextIcon.png", 27, 27);

        stlPanel.setBounds(0, 0, 1164, 594);
		stlPanel.setLayout(null);

		centerTextInCell.setHorizontalAlignment(JLabel.CENTER);

		stlTable.setShowVerticalLines(false);
		stlTable.addMouseListener(new MouseInputAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if(SwingUtilities.isLeftMouseButton(e))
					{
						rowPoint = stlTable.rowAtPoint(e.getPoint());

						String Ordinal_Number = null, Seller_ID = null, Stall_Name = null;
						byte[] imgByte = null;
						
						Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
						String sql = "select Ordinal_Number, Stall_Name, Seller_ID from Stall where Ordinal_Number = '" + (String) stlTable.getValueAt(rowPoint, 0) + "'";
						try
						{
							Statement stm = connect.createStatement();
							ResultSet rs = stm.executeQuery(sql);
							while(rs.next() == true)
							{
								Ordinal_Number = rs.getString("Ordinal_Number");
								Seller_ID = rs.getString("Seller_ID");
								Stall_Name = rs.getString("Stall_Name");
							}
						}
						catch(Exception exc)
						{
							exc.printStackTrace();
						}
						// setPanelShowStlInfo(Staff_ID, Staff_Name, Position, Hometown, Gender, imgByte);
						// JOptionPane.showMessageDialog(null, showStlInfoPanel, "Stall's full information", JOptionPane.INFORMATION_MESSAGE);
					}

					if(SwingUtilities.isRightMouseButton(e))
					{
						rowPoint = stlTable.rowAtPoint(e.getPoint());
					}
				}
			}
		);
		
		stlPanel_table.setBounds(10, 145, 1130, 370);
		stlPanel_table.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		stlPanel_table.getVerticalScrollBar().setOpaque(false);
		stlPanel_table.setViewportView(stlTable);
		stlPanel.add(stlPanel_table);
		
		stlPanel_textField.setBounds(340, 42, 343, 34);
		stlPanel_textField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		stlPanel_textField.addMouseListener(new MouseInputAdapter() 
			{
				public void mouseClicked(MouseEvent e){
					stlPanel_textField.setText("");
					stlPanel_textField.setForeground(Color.BLACK);
				}
			}
		);
		stlPanel.add(stlPanel_textField);
		
		stlPanel_searchOrCheckButt.setBounds(877, 42, 130, 34);
        stlPanel_searchOrCheckButt.setIcon(userInterface.searchIcon);
		stlPanel_searchOrCheckButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_searchOrCheckButt.addActionListener(this);
		stlPanel_searchOrCheckButt.setForeground(Color.red);
		stlPanel.add(stlPanel_searchOrCheckButt);

		stlPanel_comboBox.setBounds(684, 42, 142, 34);
		stlPanel_comboBox.setPreferredSize(new Dimension(180, 20));
        stlPanel_comboBox.setWide(true); 
		stlPanel_comboBox.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					stlStall = (String) stlPanel_comboBox.getSelectedItem();

					switch (stlStall) 
					{
						case "Show Sellers at all Stalls":
							stlPanel_searchOrCheckButt.setText("Show");
							stlPanel_searchOrCheckButt.setIcon(userInterface.showIcon);
							stlPanel_searchOrCheckButt.setActionCommand("show sellers at all stalls");

							stlPanel_editButt.setVisible(true);
							break;

						case "Show Items in selected Stall":
							stlPanel_searchOrCheckButt.setText("Show");
							stlPanel_searchOrCheckButt.setIcon(userInterface.showIcon);
							stlPanel_searchOrCheckButt.setActionCommand("show items in seletected stall");

							stlPanel_editButt.setVisible(true);
							break;

						case "Compare 2 different Stalls":
							stlPanel_searchOrCheckButt.setText("Compare");
							stlPanel_searchOrCheckButt.setIcon(compareIcon);
							setCompare2StallsPanel();
							Stall stall1 = new Stall();
							Stall stall2 = new Stall();

							JOptionPane.showConfirmDialog(null, cmpPanel, "Input name of 2 Stalls to compare", JOptionPane.OK_CANCEL_OPTION);

							while(!stall1.stallIsExisted(cmpPanel_firstStallTextField.getText()) || !stall2.stallIsExisted(cmpPanel_secondStallTextField.getText()))
							{
								if(stall1.stallIsExisted(cmpPanel_firstStallTextField.getText()) && stall2.stallIsExisted(cmpPanel_secondStallTextField.getText()))
								{
									break;
								}
								else if(!stall1.stallIsExisted(cmpPanel_firstStallTextField.getText()) && stall2.stallIsExisted(cmpPanel_secondStallTextField.getText()))
								{
									JOptionPane.showMessageDialog(null, "First Stall is not existed in the databae. Please input again !!");
									JOptionPane.showConfirmDialog(null, cmpPanel, "Input name of 2 Stalls to compare", JOptionPane.OK_CANCEL_OPTION);
								}
								else if(stall1.stallIsExisted(cmpPanel_firstStallTextField.getText()) && !stall2.stallIsExisted(cmpPanel_secondStallTextField.getText()))
								{
									JOptionPane.showMessageDialog(null, "Second Stall is not existed in the databae. Please input again !!");
									JOptionPane.showConfirmDialog(null, cmpPanel, "Input name of 2 Stalls to compare", JOptionPane.OK_CANCEL_OPTION);
								}
								else if(!stall1.stallIsExisted(cmpPanel_firstStallTextField.getText()) && !stall2.stallIsExisted(cmpPanel_secondStallTextField.getText()))
								{
									JOptionPane.showMessageDialog(null, "Both of Stalls are not existed in the databae. Please input again !!");
									JOptionPane.showConfirmDialog(null, cmpPanel, "Input name of 2 Stalls to compare", JOptionPane.OK_CANCEL_OPTION);
								}
							}
							
							stlPanel_textField.setText(stall1.getStall_Name() + " Stall VS " + stall2.getStall_Name() + " Stall");
							stlPanel_searchOrCheckButt.setActionCommand("compare 2 different stalls");

							stlPanel_editButt.setVisible(false);
							break;

						case "Search Stall using Ordinal Number":
							stlPanel_searchOrCheckButt.setText("Search");
							stlPanel_searchOrCheckButt.setIcon(userInterface.searchIcon);
							stlPanel_textField.setText("Please type the Staff ID you want here...");
							stlPanel_textField.setForeground(Color.LIGHT_GRAY);

							stlPanel_searchOrCheckButt.setActionCommand("search stall by ordinal number");
							stlPanel_searchOrCheckButt.setEnabled(true);
							stlPanel_searchOrCheckButt.setText("Search");
							stlPanel_searchOrCheckButt.setIcon(userInterface.searchIcon);

							stlPanel_editButt.setVisible(true);
							break;

						case "Search Stall using Name":
							stlPanel_searchOrCheckButt.setText("Search");
							stlPanel_searchOrCheckButt.setIcon(userInterface.searchIcon);
							stlPanel_textField.setText("Please type the Staff Name you want here...");
							stlPanel_textField.setForeground(Color.LIGHT_GRAY);
				
							stlPanel_searchOrCheckButt.setActionCommand("search stall using name");
							stlPanel_searchOrCheckButt.setEnabled(true);
							stlPanel_searchOrCheckButt.setText("Search");
							stlPanel_searchOrCheckButt.setIcon(userInterface.searchIcon);
				
							stlPanel_editButt.setVisible(true);
							break;

						case "Check Seller name working in selected Stall":
							stlPanel_searchOrCheckButt.setText("Check");
							stlPanel_searchOrCheckButt.setIcon(userInterface.checkIcon);
							stlPanel_textField.setText("Please type the Staff ID you want here...");
							stlPanel_textField.setForeground(Color.LIGHT_GRAY);

							stlPanel_searchOrCheckButt.setActionCommand("check and give conclusion whether selected staff is national or foreign");
							stlPanel_searchOrCheckButt.setEnabled(true);
							stlPanel_searchOrCheckButt.setText("Check");
							stlPanel_searchOrCheckButt.setIcon(userInterface.checkIcon);

							stlPanel_editButt.setVisible(false);
							break;

						default:
							break;
					}
				}
			}
		);

		stlPanel.add(stlPanel_comboBox);
		
		stlPanel_showTblButt.setBounds(10, 113, 152, 34);
		stlPanel_showTblButt.setActionCommand("show stall table");
		stlPanel_showTblButt.setToolTipText("Show all information in the Staff table");
		stlPanel_showTblButt.addActionListener(this);
		stlPanel.add(stlPanel_showTblButt);
		
		stlPanel_addButt.setBounds(172, 42, 101, 34);
        stlPanel_addButt.setIcon(userInterface.addIcon);
		stlPanel_addButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_addButt.setActionCommand("add stall");
		stlPanel_addButt.setToolTipText("Add one more Stall into the table");
		stlPanel_addButt.addActionListener(this);
		stlPanel.add(stlPanel_addButt);
		
		stlPanel_deleteButt.setBounds(61, 42, 101, 34);
        stlPanel_deleteButt.setIcon(userInterface.deleteIcon);
		stlPanel_deleteButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_deleteButt.setActionCommand("delete stall");
		stlPanel_deleteButt.setToolTipText("Delete a selected Stall in the table");
		stlPanel_deleteButt.addActionListener(this);
		stlPanel.add(stlPanel_deleteButt);

		stlPanel_editButt.setBounds(995, 42, 101, 34);
        stlPanel_editButt.setIcon(userInterface.editIcon);
		stlPanel_editButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_editButt.setActionCommand("edit stall");
		stlPanel_editButt.setToolTipText("Edit a selected Stall in the table");
		stlPanel_editButt.addActionListener(this);
		stlPanel.add(stlPanel_editButt);
    }



	public void setCompare2StallsPanel()
	{
		cmpPanel.setPreferredSize(new Dimension(410, 187));
		cmpPanel.setLayout(null);
		
		cmpPanel_firstStallLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cmpPanel_firstStallLabel.setBounds(29, 34, 97, 27);
		cmpPanel.add(cmpPanel_firstStallLabel);
		
		cmpPanel_secondStallLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cmpPanel_secondStallLabel.setBounds(29, 88, 97, 27);
		cmpPanel.add(cmpPanel_secondStallLabel);
		
		
		cmpPanel_firstStallTextField.setBounds(160, 34, 188, 27);
		cmpPanel_firstStallTextField.setForeground(Color.GRAY);
		cmpPanel_firstStallTextField.addMouseListener(new MouseInputAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					cmpPanel_firstStallTextField.setText("");
					cmpPanel_firstStallTextField.setForeground(Color.black);
				}
			}
		);
		cmpPanel.add(cmpPanel_firstStallTextField);
		
		cmpPanel_secondStallTextField.setBounds(160, 88, 188, 27);
		cmpPanel_secondStallTextField.setForeground(Color.GRAY);
		cmpPanel_secondStallTextField.addMouseListener(new MouseInputAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					cmpPanel_secondStallTextField.setText("");
					cmpPanel_secondStallTextField.setForeground(Color.black);
				}
			}
		);
		cmpPanel.add(cmpPanel_secondStallTextField);
	}



    @Override
    public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == "show stall table")
		{
			refreshTables.StallTable(stlTable, stlTableModel, centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				stlPanel_editButt.setVisible(true);
			}
		}

		if(e.getActionCommand() == "show sellers at all stalls")
		{
			stlTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 4) return ImageIcon.class;
					return Object.class;
				}
			};
			stlTableModel.setColumnCount(0);
			stlTable.setRowHeight(100);
			stlTableModel.setRowCount(0);

			stlTableModel.addColumn("Stall Name");
			stlTableModel.addColumn("Seller ID");
			stlTableModel.addColumn("Seller Name");
			stlTableModel.addColumn("Gender");
			stlTableModel.addColumn("Image");

			ArrayList<Stall> list;
			list = getView.getSellersAtStallsList();
			for(Stall l : list)
			{
			    stlTableModel.addRow(l.sellersAtStallsToArray());
			}
			stlTable.setModel(stlTableModel);
			stlTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			stlTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			stlTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
		    stlTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
		}

		if(e.getActionCommand() == "show items in seletected stall")
		{
			
		}

		if(e.getActionCommand() == "compare 2 different stalls")
		{
			stlTableModel = new DefaultTableModel();
			stlTableModel.setColumnCount(0);
			stlTable.setRowHeight(100);

			conclusion = getFunction.compare2Stalls(cmpPanel_firstStallTextField.getText(), cmpPanel_secondStallTextField.getText(), conclusion);
			stlTableModel.addColumn("Conclusion");
			stlTableModel.setRowCount(0);
			stlTableModel.addRow(new Object[] {conclusion});

			stlTable.setModel(stlTableModel);
			stlTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
		}

		if(e.getActionCommand() == "search stall by ordinal number")
		{
			
		}

		if(e.getActionCommand() == "search stall using name")
		{
			
		}

		if(e.getActionCommand() == "check and give conclusion whether selected staff is national or foreign")
		{
			
		}

		if(e.getActionCommand() == "")
		{
			
		}
    }
}
