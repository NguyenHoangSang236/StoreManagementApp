import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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

public class stallPanel implements ActionListener
{
    public DefaultTableCellRenderer centerTextInCell = new DefaultTableCellRenderer();
    public JButton chooseImageButt;
    public String conclusion, selectedStaffGender;
    public byte[] addedByteOfImg, initByteImg;
	public int rowPoint;

    public JPanel stlPanel = new JPanel();
    public JScrollPane stlPanel_table = new JScrollPane();
    public JTable stlTable = new JTable();
    public DefaultTableModel stlTableModel;
    public JTextField stlPanel_textField = new JTextField();
    public JButton stlPanel_searchOrCheckButt = new JButton("Search");
    public JButton stlPanel_showTblButt = new JButton("Show full table");
    public JButton stlPanel_stallAndSellersButt = new JButton("Stalls and Sellers");
    public JButton stlPanel_stallsItemsButt = new JButton("Stall's Items");
    public JButton stlPanel_stallsSellerButt = new JButton("Stall's Seller ");
    public JButton stlPanel_compare2StallsButt = new JButton("Compare 2 Stalls");
    public JButton stlPanel_searchByOrdNumButt = new JButton("Search by Ord Number");
    public JButton stlPanel_searchByNameButt = new JButton("Search by Name");
    public JButton stlPanel_addButt = new JButton("Add");
    public JButton stlPanel_deleteButt = new JButton("Delete");
    public JButton stlPanel_editButt = new JButton("Edit");

    public ImageIcon searchIcon = new ImageIcon();
	public ImageIcon checkIcon = new ImageIcon();
	public ImageIcon editIcon = new ImageIcon();
	public ImageIcon deleteIcon = new ImageIcon();
	public ImageIcon addIcon = new ImageIcon();


	
    public void setStallPanel()
    {
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
		
		stlPanel_textField.setBounds(340, 42, 476, 34);
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
        stlPanel_searchOrCheckButt.setIcon(searchIcon);
		stlPanel_searchOrCheckButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_searchOrCheckButt.addActionListener(this);
		stlPanel_searchOrCheckButt.setEnabled(false);
		stlPanel_searchOrCheckButt.setForeground(Color.red);
		stlPanel.add(stlPanel_searchOrCheckButt);
		
		stlPanel_showTblButt.setBounds(10, 113, 152, 34);
		stlPanel_showTblButt.setActionCommand("show stall table");
		stlPanel_showTblButt.setToolTipText("Show all information in the Staff table");
		stlPanel_showTblButt.addActionListener(this);
		stlPanel.add(stlPanel_showTblButt);
		
		stlPanel_stallAndSellersButt.setBounds(172, 113, 152, 34);
		stlPanel_stallAndSellersButt.setActionCommand("show stalls and sellers");
		stlPanel_stallAndSellersButt.setToolTipText("Show information of Seller in each Stall");
		stlPanel_stallAndSellersButt.addActionListener(this);
		stlPanel.add(stlPanel_stallAndSellersButt);
		
		stlPanel_stallsItemsButt.setBounds(340, 113, 152, 34);
		stlPanel_stallsItemsButt.setActionCommand("show stall's items");
		stlPanel_stallsItemsButt.setToolTipText("Show information of Items in the selected Stall");
		stlPanel_stallsItemsButt.addActionListener(this);
		stlPanel.add(stlPanel_stallsItemsButt);
		
		stlPanel_stallsSellerButt.setBounds(502, 113, 152, 34);
		stlPanel_stallsSellerButt.setActionCommand("check stall's seller");
		stlPanel_stallsSellerButt.setToolTipText("Show conclusion about the Seller's name of the selected Stall");
		stlPanel_stallsSellerButt.addActionListener(this);
		stlPanel.add(stlPanel_stallsSellerButt);
		
		stlPanel_compare2StallsButt.setBounds(664, 113, 152, 34);
		stlPanel_compare2StallsButt.setActionCommand("compare 2 stalls");
		stlPanel_compare2StallsButt.setToolTipText("Show conclustion about which Stall sells more products");
		stlPanel_compare2StallsButt.addActionListener(this);
		stlPanel.add(stlPanel_compare2StallsButt);
		
		stlPanel_searchByOrdNumButt.setBounds(826, 113, 152, 34);
		stlPanel_searchByOrdNumButt.setActionCommand("search staff by ID _ option");
		stlPanel_searchByOrdNumButt.setToolTipText("Show full information of the Stall basing on the selected Ordinal Number");
		stlPanel_searchByOrdNumButt.addActionListener(this);
		stlPanel.add(stlPanel_searchByOrdNumButt);
		
		stlPanel_searchByNameButt.setBounds(988, 113, 152, 34);
		stlPanel_searchByNameButt.setActionCommand("search staff by name");
		stlPanel_searchByNameButt.setToolTipText("Show full information of the Stall basing on the selected Stall Name");
		stlPanel_searchByNameButt.addActionListener(this);
		stlPanel.add(stlPanel_searchByNameButt);
		
		stlPanel_addButt.setBounds(172, 42, 101, 34);
        stlPanel_addButt.setIcon(addIcon);
		stlPanel_addButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_addButt.setActionCommand("add stall");
		stlPanel_addButt.setToolTipText("Add one more Stall into the table");
		stlPanel_addButt.addActionListener(this);
		stlPanel.add(stlPanel_addButt);
		
		stlPanel_deleteButt.setBounds(61, 42, 101, 34);
        stlPanel_deleteButt.setIcon(deleteIcon);
		stlPanel_deleteButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_deleteButt.setActionCommand("delete stall");
		stlPanel_deleteButt.setToolTipText("Delete a selected Stall in the table");
		stlPanel_deleteButt.addActionListener(this);
		stlPanel.add(stlPanel_deleteButt);

		stlPanel_editButt.setBounds(995, 42, 101, 34);
        stlPanel_editButt.setIcon(editIcon);
		stlPanel_editButt.setHorizontalTextPosition(SwingConstants.LEFT);
		stlPanel_editButt.setActionCommand("edit stall");
		stlPanel_editButt.setToolTipText("Edit a selected Stall in the table");
		stlPanel_editButt.addActionListener(this);
		stlPanel.add(stlPanel_editButt);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
	{
		
    }
}
