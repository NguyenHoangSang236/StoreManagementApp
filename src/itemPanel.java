import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;


public class itemPanel implements ActionListener
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
	public boolean avai;
	public int day, month, year;

	public JPanel itmPanel = new JPanel();
	public JScrollPane itmPanel_table = new JScrollPane();
	public JTable itmTable = new JTable();
	public DefaultTableModel itmTableModel;
	public JTextField itmPanel_textField = new JTextField();
	public JButton itmPanel_searchOrCheckButt = new JButton("Search", searchIcon);
	public JButton itmPanel_showTblButt = new JButton("Show full table");
	public JButton itmPanel_cheapItemsButt = new JButton("Low Cost");
	public JButton itmPanel_foreignitmButt = new JButton("Expensive");
	public JButton itmPanel_priceDifferencesButt = new JButton("Sell vs Org");
	public JButton itmPanel_top3BestSellButt = new JButton("Top 3 best sell");
	public JButton itmPanel_sellingPriceButt = new JButton("Selling Price");
	public JButton itmPanel_originalPriceButt = new JButton("Original Price");
	public JButton itmPanel_searchByItmCodeButt = new JButton("Search by Code");
	public JButton itmPanel_searchByItmNameButt = new JButton("Search by Name");
	public JButton itmPanel_addButt = new JButton("Add", addIcon);
	public JButton itmPanel_deleteButt = new JButton("Delete", deleteIcon);
	public JButton itmPanel_editButt = new JButton("Edit", editIcon);

	public JPanel showItmInfoPanel = new JPanel();
	public JLabel showItmInfo_itemCodeLabel = new JLabel("Item Code");
	public JLabel showItmInfo_itemNameLabel = new JLabel("Item Name");
	public JLabel showItmInfo_sellingPriceLabel = new JLabel("Selling Price");
	public JLabel showItmInfo_supplierIDLabel = new JLabel("Supplier ID");
	public JLabel showItmInfo_importDateLabel = new JLabel("Import Date");
	public JLabel showItmInfo_itemCodeShowLabel = new JLabel("");
	public JLabel showItmInfo_sellingPriceShowLabel = new JLabel("");
	public JLabel showItmInfo_supplierIDShowLabel = new JLabel("");
	public JLabel showItmInfo_itemNameShowLabel = new JLabel("");
	public JLabel showItmInfo_importDateShowLabel = new JLabel("");
	public JLabel showItmInfo_itemImageLabel = new JLabel("");
	public ImageIcon showItmInfo_imageImage = new ImageIcon();
	public JLabel showItmInfo_stallOrdNumberLabel = new JLabel("<html>Stall Ordinal<br/>Number<html/>\r\n");
	public JLabel showItmInfo_stallOrdNumberShowLabel = new JLabel("");

	public JPanel addItmInfoPanel = new JPanel();
	public JTextField addItmInfo_itemNameTextField = new JTextField();
	public JTextField addItmInfo_supplierIDTextField = new JTextField();
	public JTextField addItmInfo_itemCodeTextField = new JTextField();
	public JTextField addItmInfo_imageTextField = new JTextField();
	public JTextField addItmInfo_stallOrdNumberTextField = new JTextField();
	public JTextField addItmInfo_sellingPriceTextField = new JTextField();
	public JTextField addItmInfo_originalPriceTextField = new JTextField();
	public JTextField addItmInfo_importDateTextField = new JTextField();
	public JTextField addItmInfo_quantityInStockTextField = new JTextField();
	public JLabel addItmInfo_itemCodeLabel = new JLabel("Item Code");
	public JLabel addItmInfo_itemNameLabel = new JLabel("Item Name");
	public JLabel addItmInfo_originalPriceLabel = new JLabel("Original Price");
	public JLabel addItmInfo_stallOrdNumberLabel = new JLabel("<html>Ordinal Number of Stall<html/>");
	public JLabel addItmInfo_supplierIDLabel = new JLabel("Supplier ID");
	public JLabel addItmInfo_availableLabel = new JLabel("Available");
	public JButton addItmInfo_completedButt;
	public JLabel addItmInfo_imageLabel = new JLabel("Image");
	public JLabel addItmInfo_sellingPriceLabel = new JLabel("Selling Price");
	public JLabel addItmInfo_importDateLabel = new JLabel("Import Date");
	public JLabel addItmInfo_quantityInStockLabel = new JLabel("<html>Quantity<br/>In Stock<html/>\r\n\r\n");
	public JRadioButton addItmInfo_yesRadioButt = new JRadioButton("Yes");
	public JRadioButton addItmInfo_noRadioButt = new JRadioButton("No");
	public File itmImgFile;
	public UtilDateModel model = new UtilDateModel();
	public Properties prop = new Properties();
	public JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
	public JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

	public JPanel editItmInfoPanel = new JPanel();
	public ImageIcon editItmInfo_imgIcon = new ImageIcon();
	public JLabel editItmInfo_staffImgLabel = new JLabel();
	public JTextField editItmInfo_supplierIDTextField = new JTextField();
	public JTextField editItmInfo_sellingPriceTextField = new JTextField();
	public JTextField editItmInfo_itemCodeTextField = new JTextField();
	public JTextField editItmInfo_imageTextField = new JTextField();
	public JTextField editItmInfo_itemNameTextField = new JTextField ();
	public JRadioButton editItmInfo_yesRadioButt = new JRadioButton("Yes");
	public JRadioButton editItmInfo_noRadioButt = new JRadioButton("No");
	public JLabel editItmInfo_itemCodeLabel = new JLabel("<html>Item code<html/>\r\n");
	public JLabel editItmInfo_supplierIDLabel = new JLabel("<html>Supplier ID<html/>");
	public JLabel editItmInfo_itemNameLabel = new JLabel("<html>Item Name<html/>");
	public JLabel editItmInfo_ordinalNumOfStallLabel = new JLabel("<html>Ordinal number of Stall<html/>\r\n");
	public JLabel editItmInfo_importDateLabel = new JLabel("<html>Import Date<html/>\r\n");
	public JLabel editItmInfo_originalPriceLabel = new JLabel("<html>Ordinal Price<html/>\r\n");
	public JLabel editItmInfo_quantityInStockTextField = new JLabel("<html>Quantity in Stock<html/>\r\n");
	public JLabel editItmInfo_sellingPriceLabel = new JLabel("Selling Price\r\n");
	public JLabel editItmInfo_availableLabel = new JLabel("Available");
	public JButton editItmInfo_completedButt;
	public JTextField editItmInfo_ordinalNumOfStallTextField = new JTextField();
	public JTextField editItmInfo_importDateTextField = new JTextField();
	public JTextField editItmInfo_originalPriceTextField = new JTextField();
	public JTextField editItmInfo_quanityInStockTextField = new JTextField();



    //method for setting up components in Item Panel
	public void setItemPanel()
	{
		images.readIcons(addIcon, deleteIcon, editIcon, searchIcon, checkIcon, showIcon);

		itmPanel.setBounds(0, 0, 1194, 594);
		itmPanel.setLayout(null);

		centerTextInCell.setHorizontalAlignment(JLabel.CENTER);

		itmTable.setShowVerticalLines(false);
		itmTable.addMouseListener(new MouseInputAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if(SwingUtilities.isLeftMouseButton(e))
					{
						rowPoint = itmTable.rowAtPoint(e.getPoint());

						String Item_Code = null, Supplier_ID = null, Item_Name = null, Ordinal_Number_Of_Stall = null;
						Date Import_Date = null;
						float Selling_Price = 0;
						byte[] imgByte = null;
						
						Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
						String sql = "select Item_Code, Supplier_ID, Item_Name, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, Image from Items where Item_Code = '" + (String) itmTable.getValueAt(rowPoint, 0) + "'";
						try
						{
							Statement stm = connect.createStatement();
							ResultSet rs = stm.executeQuery(sql);
							while(rs.next() == true)
							{
								Item_Code = rs.getString("Item_Code");
								Supplier_ID = rs.getString("Supplier_ID");
								Item_Name = rs.getString("Item_Name");
								Selling_Price = rs.getFloat("Selling_Price");
								Ordinal_Number_Of_Stall = rs.getString("Ordinal_Number_Of_Stall");
								Import_Date = rs.getDate("Import_Date");
								Blob imgBlob = rs.getBlob("Image");
								imgByte = imgBlob.getBytes(1, (int) imgBlob.length()); 
							}
						}
						catch(Exception exc)
						{
							exc.printStackTrace();
						}
						setPanelShowItmInfo(Item_Code, Supplier_ID, Item_Name, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, imgByte);
						JOptionPane.showMessageDialog(null, showItmInfoPanel, "Item's full information", JOptionPane.INFORMATION_MESSAGE);
					}

					if(SwingUtilities.isRightMouseButton(e))
					{
						rowPoint = itmTable.rowAtPoint(e.getPoint());
					}
				}
			}
		);
		
		itmPanel_table.setBounds(10, 145, 1140, 370);
		itmPanel_table.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		itmPanel_table.getVerticalScrollBar().setOpaque(false);
		itmPanel_table.setViewportView(itmTable);
		itmPanel.add(itmPanel_table);
		
		itmPanel_textField.setBounds(340, 42, 476, 34);
		itmPanel_textField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		itmPanel_textField.addMouseListener(new MouseInputAdapter() 
			{
				public void mouseClicked(MouseEvent e){
					itmPanel_textField.setText("");
					itmPanel_textField.setForeground(Color.BLACK);
				}
			}
		);
		itmPanel.add(itmPanel_textField);
		
		itmPanel_searchOrCheckButt.setBounds(877, 42, 130, 34);
		itmPanel_searchOrCheckButt.setHorizontalTextPosition(SwingConstants.LEFT);
		itmPanel_searchOrCheckButt.addActionListener(this);
		itmPanel_searchOrCheckButt.setEnabled(false);
		itmPanel_searchOrCheckButt.setForeground(Color.red);
		itmPanel.add(itmPanel_searchOrCheckButt);
		
		itmPanel_showTblButt.setBounds(10, 113, 118, 34);
		itmPanel_showTblButt.setActionCommand("show item table");
		itmPanel_showTblButt.setToolTipText("Show all information in the items table");
		itmPanel_showTblButt.addActionListener(this);
		itmPanel.add(itmPanel_showTblButt);
		
		itmPanel_cheapItemsButt.setBounds(132, 113, 115, 34);
		itmPanel_cheapItemsButt.setActionCommand("show cheap items");
		itmPanel_cheapItemsButt.setToolTipText("Show all information of cheap Items (<500)");
		itmPanel_cheapItemsButt.addActionListener(this);
		itmPanel.add(itmPanel_cheapItemsButt);
		
		itmPanel_foreignitmButt.setBounds(254, 113, 125, 34);
		itmPanel_foreignitmButt.setActionCommand("show expensive items");
		itmPanel_foreignitmButt.setToolTipText("Show all information of expensive Items (>500)");
		itmPanel_foreignitmButt.addActionListener(this);
		itmPanel.add(itmPanel_foreignitmButt);
		
		itmPanel_priceDifferencesButt.setBounds(385, 113, 125, 34);
		itmPanel_priceDifferencesButt.setActionCommand("show price differences");
		itmPanel_priceDifferencesButt.setToolTipText("Show the differences between Selling Price and Original Price of all Items");
		itmPanel_priceDifferencesButt.addActionListener(this);
		itmPanel.add(itmPanel_priceDifferencesButt);
		
		itmPanel_top3BestSellButt.setBounds(514, 113, 120, 34);
		itmPanel_top3BestSellButt.setActionCommand("show top 3 best selling");
		itmPanel_top3BestSellButt.setToolTipText("Show top 3 best selling Items basing on Quantity of Orders");
		itmPanel_top3BestSellButt.addActionListener(this);
		itmPanel.add(itmPanel_top3BestSellButt);
		
		itmPanel_sellingPriceButt.setBounds(640, 113, 120, 34);
		itmPanel_sellingPriceButt.setActionCommand("check selling price");
		itmPanel_sellingPriceButt.setToolTipText("Show conclusion about the Selling Price of the selected Item");
		itmPanel_sellingPriceButt.addActionListener(this);
		itmPanel.add(itmPanel_sellingPriceButt);
		
		itmPanel_originalPriceButt.setBounds(766, 113, 120, 34);
		itmPanel_originalPriceButt.setActionCommand("check original price");
		itmPanel_originalPriceButt.setToolTipText("Show conclusion about the Original Price of the selected Item");
		itmPanel_originalPriceButt.addActionListener(this);
		itmPanel.add(itmPanel_originalPriceButt);
		
		itmPanel_addButt.setBounds(172, 42, 101, 34);
		itmPanel_addButt.setHorizontalTextPosition(SwingConstants.LEFT);
		itmPanel_addButt.setActionCommand("add item");
		itmPanel_addButt.setToolTipText("Add one more Item into the table");
		itmPanel_addButt.addActionListener(this);
		itmPanel.add(itmPanel_addButt);
		
		itmPanel_deleteButt.setBounds(61, 42, 101, 34);
		itmPanel_deleteButt.setHorizontalTextPosition(SwingConstants.LEFT);
		itmPanel_deleteButt.setActionCommand("delete item");
		itmPanel_deleteButt.setToolTipText("Delete a selected Item in the table");
		itmPanel_deleteButt.addActionListener(this);
		itmPanel.add(itmPanel_deleteButt);

		itmPanel_editButt.setBounds(995, 42, 101, 34);
		itmPanel_editButt.setHorizontalTextPosition(SwingConstants.LEFT);
		itmPanel_editButt.setActionCommand("edit item");
		itmPanel_editButt.setToolTipText("Edit a selected Item in the table");
		itmPanel_editButt.addActionListener(this);
		itmPanel.add(itmPanel_editButt);
		
		itmPanel_searchByItmNameButt.setToolTipText("Show full information of the Item basing on the selected Item Name");
		itmPanel_searchByItmNameButt.setActionCommand("search item by name _ option");
		itmPanel_searchByItmNameButt.addActionListener(this);
		itmPanel_searchByItmNameButt.setBounds(1022, 113, 130, 34);
		itmPanel.add(itmPanel_searchByItmNameButt);
		
		itmPanel_searchByItmCodeButt.setToolTipText("Show full information of the Item basing on the selected Item Code");
		itmPanel_searchByItmCodeButt.setActionCommand("search item by code _ option");
		itmPanel_searchByItmCodeButt.addActionListener(this);
		itmPanel_searchByItmCodeButt.setBounds(890, 113, 128, 34);
		itmPanel.add(itmPanel_searchByItmCodeButt);
	}



	public void setPanelShowItmInfo(String Item_Code, String Supplier_ID, String Item_Name, float Selling_Price, String Ordinal_Number_Of_Stall, Date Import_Date, byte[] url)
	{
		showItmInfoPanel.setPreferredSize(new Dimension(864, 445));
		showItmInfoPanel.setLayout(null);
		
		showItmInfo_itemCodeLabel.setBounds(24, 45, 87, 24);
		showItmInfoPanel.add(showItmInfo_itemCodeLabel);
		
		showItmInfo_itemNameLabel.setBounds(24, 194, 87, 24);
		showItmInfoPanel.add(showItmInfo_itemNameLabel);
		
		showItmInfo_sellingPriceLabel.setBounds(24, 249, 87, 24);
		showItmInfoPanel.add(showItmInfo_sellingPriceLabel);
		
		showItmInfo_supplierIDLabel.setBounds(24, 96, 87, 24);
		showItmInfoPanel.add(showItmInfo_supplierIDLabel);
		
		showItmInfo_importDateLabel.setBounds(24, 301, 87, 24);
		showItmInfoPanel.add(showItmInfo_importDateLabel);
		
		showItmInfo_itemCodeShowLabel.setText(Item_Code);
		showItmInfo_itemCodeShowLabel.setBounds(121, 45, 210, 24);
		showItmInfoPanel.add(showItmInfo_itemCodeShowLabel);
		
		showItmInfo_sellingPriceShowLabel.setText(Float.toString(Selling_Price) + " $");
		showItmInfo_sellingPriceShowLabel.setBounds(121, 249, 210, 24);
		showItmInfoPanel.add(showItmInfo_sellingPriceShowLabel);
		
		showItmInfo_supplierIDShowLabel.setText(Supplier_ID);
		showItmInfo_supplierIDShowLabel.setBounds(121, 96, 210, 24);
		showItmInfoPanel.add(showItmInfo_supplierIDShowLabel);
		
		showItmInfo_itemNameShowLabel.setText(Item_Name);
		showItmInfo_itemNameShowLabel.setBounds(121, 194, 210, 24);
		showItmInfoPanel.add(showItmInfo_itemNameShowLabel);
		
		showItmInfo_importDateShowLabel.setText(Import_Date.toString());
		showItmInfo_importDateShowLabel.setBounds(121, 301, 210, 24);
		showItmInfoPanel.add(showItmInfo_importDateShowLabel);
		
		images.readAndResizeImageByBytes(showItmInfo_imageImage, url, 250, 320);
		showItmInfo_itemImageLabel.setIcon(showItmInfo_imageImage);
		showItmInfo_itemImageLabel.setBounds(557, 56, 250, 320);
		showItmInfoPanel.add(showItmInfo_itemImageLabel);
		
		showItmInfo_stallOrdNumberLabel.setBounds(24, 135, 87, 39);
		showItmInfoPanel.add(showItmInfo_stallOrdNumberLabel);

		showItmInfo_stallOrdNumberShowLabel.setBounds(121, 140, 210, 24);
		showItmInfo_stallOrdNumberShowLabel.setText(Ordinal_Number_Of_Stall);
		showItmInfoPanel.add(showItmInfo_stallOrdNumberShowLabel);
	}



	//method for setting up components in Panel of Adding a new Item
	public void setPanelAddItmInfo()
	{
		addItmInfoPanel.setPreferredSize(new Dimension(864, 386));
		addItmInfoPanel.setLayout(null);
		
		addItmInfo_itemCodeTextField.setBounds(131, 93, 236, 20);
		addItmInfoPanel.add(addItmInfo_itemCodeTextField);
		addItmInfo_itemCodeTextField.setColumns(10);
		
		addItmInfo_itemNameTextField.setColumns(10);
		addItmInfo_itemNameTextField.setBounds(131, 138, 236, 20);
		addItmInfoPanel.add(addItmInfo_itemNameTextField);
		
		addItmInfo_supplierIDTextField.setColumns(10);
		addItmInfo_supplierIDTextField.setBounds(599, 93, 236, 20);
		addItmInfoPanel.add(addItmInfo_supplierIDTextField);
		
		addItmInfo_stallOrdNumberTextField.setColumns(10);
		addItmInfo_stallOrdNumberTextField.setBounds(599, 138, 236, 20);
		addItmInfoPanel.add(addItmInfo_stallOrdNumberTextField);
		
		addItmInfo_itemCodeLabel.setBounds(24, 91, 97, 24);
		addItmInfoPanel.add(addItmInfo_itemCodeLabel);
		
		addItmInfo_itemNameLabel.setBounds(24, 136, 97, 24);
		addItmInfoPanel.add(addItmInfo_itemNameLabel);
		
		addItmInfo_stallOrdNumberLabel.setBounds(479, 130, 110, 36);
		addItmInfoPanel.add(addItmInfo_stallOrdNumberLabel);
		
		addItmInfo_supplierIDLabel.setBounds(479, 91, 110, 24);
		addItmInfoPanel.add(addItmInfo_supplierIDLabel);
		
		chooseImageButt = new JButton("Choose an image");
		chooseImageButt.setBounds(569, 290, 140, 33);
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
							itmImgFile = fc.getSelectedFile();
						}
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}
			}
		);
		addItmInfoPanel.add(chooseImageButt);
		
		addItmInfo_sellingPriceLabel.setBounds(24, 185, 97, 24);
		addItmInfoPanel.add(addItmInfo_sellingPriceLabel);
		
		addItmInfo_importDateLabel.setBounds(479, 185, 99, 24);
		addItmInfoPanel.add(addItmInfo_importDateLabel);

		addItmInfo_sellingPriceTextField.setColumns(10);
		addItmInfo_sellingPriceTextField.setBounds(131, 185, 236, 20);
		addItmInfoPanel.add(addItmInfo_sellingPriceTextField);
		
		addItmInfo_originalPriceLabel.setBounds(24, 237, 97, 24);
		addItmInfoPanel.add(addItmInfo_originalPriceLabel);
		
		addItmInfo_originalPriceTextField.setColumns(10);
		addItmInfo_originalPriceTextField.setBounds(131, 239, 236, 20);
		addItmInfoPanel.add(addItmInfo_originalPriceTextField);

		addItmInfo_quantityInStockLabel.setBounds(479, 220, 110, 41);
		addItmInfoPanel.add(addItmInfo_quantityInStockLabel);
		
		addItmInfo_quantityInStockTextField.setColumns(10);
		addItmInfo_quantityInStockTextField.setBounds(599, 237, 236, 20);
		addItmInfoPanel.add(addItmInfo_quantityInStockTextField);
		
		addItmInfo_availableLabel.setBounds(24, 294, 80, 24);
		addItmInfoPanel.add(addItmInfo_availableLabel);

		addItmInfo_yesRadioButt.setBounds(121, 294, 55, 23);
		addItmInfo_yesRadioButt.addActionListener(this);
		addItmInfo_yesRadioButt.setActionCommand("yes");
		addItmInfoPanel.add(addItmInfo_yesRadioButt);
		
		addItmInfo_noRadioButt.setBounds(276, 294, 55, 23);
		addItmInfo_noRadioButt.addActionListener(this);
		addItmInfo_noRadioButt.setActionCommand("no");
		addItmInfoPanel.add(addItmInfo_noRadioButt);

		day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		month = Calendar.getInstance().get(Calendar.MONTH);
		year = Calendar.getInstance().get(Calendar.YEAR);
		datePanel.getModel().setDate(year, month, day);
		datePanel.getModel().setSelected(true);
		prop.put("text.today", "Today");
		prop.put("text.month", "Month");
		prop.put("text.year", "Year");
		datePicker.setBounds(599, 185, 236, 20);
		addItmInfoPanel.add(datePicker);
		
		addItmInfoPanel.add(addItmInfo_quantityInStockTextField);
	}



	public void setPanelEditItmInfo(String itemCode, String suppID, String itemName, float orgPrice, float sellPrice, String stallOrdNum, Date importDate, int stockQuant, boolean avai, byte[] initByteImg, byte[] addedByteOfImg)
	{
		editItmInfoPanel.setPreferredSize(new Dimension(864, 386));
		editItmInfoPanel.setLayout(null);
		
		editItmInfo_itemCodeTextField.setBounds(96, 93, 158, 20);
		editItmInfo_itemCodeTextField.setText(itemCode);
		editItmInfoPanel.add(editItmInfo_itemCodeTextField);
		editItmInfo_itemCodeTextField.setColumns(10);
		
		editItmInfo_supplierIDTextField.setColumns(10);
		editItmInfo_supplierIDTextField.setText(suppID);
		editItmInfo_supplierIDTextField.setBounds(96, 138, 158, 20);
		editItmInfoPanel.add(editItmInfo_supplierIDTextField);
		
		editItmInfo_sellingPriceTextField.setColumns(10);
		editItmInfo_sellingPriceTextField.setText(Float.toString(sellPrice));
		editItmInfo_sellingPriceTextField.setBounds(96, 243, 158, 20);
		editItmInfoPanel.add(editItmInfo_sellingPriceTextField);
		
		editItmInfo_itemNameTextField.setColumns(10);
		editItmInfo_itemNameTextField.setText(itemName);
		editItmInfo_itemNameTextField.setBounds(96, 192, 158, 20);
		editItmInfoPanel.add(editItmInfo_itemNameTextField);
		
		editItmInfo_yesRadioButt.setBounds(96, 286, 54, 23);
		editItmInfo_yesRadioButt.setActionCommand("yes");
		editItmInfo_yesRadioButt.addActionListener(this);
		editItmInfoPanel.add(editItmInfo_yesRadioButt);
		
		editItmInfo_noRadioButt.setBounds(187, 286, 67, 23);
		editItmInfo_noRadioButt.setActionCommand("no");
		editItmInfo_noRadioButt.addActionListener(this);
		editItmInfoPanel.add(editItmInfo_noRadioButt);

		if(avai = true)
		{
			editItmInfo_yesRadioButt.setSelected(true);
		}
		else if(avai = true)
		{
			editItmInfo_noRadioButt.setSelected(true);
		}
		
		editItmInfo_itemCodeLabel.setBounds(24, 91, 62, 24);
		editItmInfoPanel.add(editItmInfo_itemCodeLabel);
		
		editItmInfo_supplierIDLabel.setBounds(24, 136, 62, 24);
		editItmInfoPanel.add(editItmInfo_supplierIDLabel);
		
		editItmInfo_itemNameLabel.setBounds(24, 190, 62, 24);
		editItmInfoPanel.add(editItmInfo_itemNameLabel);
		
		editItmInfo_sellingPriceLabel.setBounds(24, 241, 62, 24);
		editItmInfoPanel.add(editItmInfo_sellingPriceLabel);
		
		editItmInfo_availableLabel.setBounds(24, 285, 62, 24);
		editItmInfoPanel.add(editItmInfo_availableLabel);

		images.readAndResizeImageByBytes(editItmInfo_imgIcon, initByteImg, 256, 226);

		editItmInfo_staffImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editItmInfo_staffImgLabel.setIcon(editItmInfo_imgIcon);
		editItmInfo_staffImgLabel.setBounds(557, 70, 256, 226);
		editItmInfoPanel.add(editItmInfo_staffImgLabel);
		
		editItmInfo_completedButt = new JButton("COMPLETED");
		editItmInfo_completedButt.addActionListener(this);
		editItmInfo_completedButt.setActionCommand("completed edit staff");
		editItmInfo_completedButt.setForeground(Color.RED);
		editItmInfo_completedButt.setFont(new Font("Times New Roman", Font.BOLD, 15));
		editItmInfo_completedButt.setBounds(364, 327, 132, 48);
		editItmInfoPanel.add(editItmInfo_completedButt);
		
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
							itmImgFile = fc.getSelectedFile();
							byte[] addedByteOfImg = Files.readAllBytes(itmImgFile.toPath());
							ImageIcon newImg = new ImageIcon();
							images.readAndResizeImageByBytes(newImg, addedByteOfImg, 256, 226);
							editItmInfo_staffImgLabel.setIcon(newImg);
						}
					}
					catch(Exception exp)
					{
						exp.printStackTrace();
					}
				}
			}
		);
		editItmInfoPanel.add(chooseImageButt);
		
		editItmInfo_ordinalNumOfStallLabel.setBounds(281, 93, 62, 37);
		editItmInfoPanel.add(editItmInfo_ordinalNumOfStallLabel);
		
		editItmInfo_ordinalNumOfStallTextField.setText((String) null);
		editItmInfo_ordinalNumOfStallTextField.setColumns(10);
		editItmInfo_ordinalNumOfStallTextField.setBounds(353, 93, 158, 20);
		editItmInfoPanel.add(editItmInfo_ordinalNumOfStallTextField);
		
		editItmInfo_importDateLabel.setBounds(281, 138, 62, 24);
		editItmInfoPanel.add(editItmInfo_importDateLabel);
		
		datePicker.setBounds(353, 138, 158, 20);
		editItmInfoPanel.add(datePicker);
		
		editItmInfo_originalPriceTextField.setText((String) null);
		editItmInfo_originalPriceTextField.setColumns(10);
		editItmInfo_originalPriceTextField.setBounds(353, 192, 158, 20);
		editItmInfoPanel.add(editItmInfo_originalPriceTextField);
		
		editItmInfo_quanityInStockTextField.setText((String) null);
		editItmInfo_quanityInStockTextField.setColumns(10);
		editItmInfo_quanityInStockTextField.setBounds(353, 243, 158, 20);
		editItmInfoPanel.add(editItmInfo_quanityInStockTextField);
		
		editItmInfo_originalPriceLabel.setBounds(281, 192, 62, 24);
		editItmInfoPanel.add(editItmInfo_originalPriceLabel);
		
		editItmInfo_quantityInStockTextField.setBounds(281, 243, 62, 24);
		editItmInfoPanel.add(editItmInfo_quantityInStockTextField);
	}



    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //when clicking into Show full table button
		if(e.getActionCommand().equals("show item table"))
		{
			refreshTables.ItemsTable(itmTable, itmTableModel, centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				itmPanel_editButt.setVisible(true);
			}
			itmPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Low cost
		if(e.getActionCommand().equals("show cheap items"))
		{
			itmTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 4) return ImageIcon.class;
					return Object.class;
				}
			};
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(100);
		
			itmTableModel.addColumn("Supplier Name");
			itmTableModel.addColumn("Stall Name");
			itmTableModel.addColumn("Item Name");
			itmTableModel.addColumn("Selling Price");
			itmTableModel.addColumn("Image");
			itmTableModel.setRowCount(0);

			ArrayList<Items> list;
			list = getView.getCheapItemList();
			for(Items itm:list)
			{
				itmTableModel.addRow(itm.cheapAndExpensiveItemsToArray());
			}

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);

			itmPanel_editButt.setVisible(false);
		}

		//when clicking into Expensive
		if(e.getActionCommand().equals("show expensive items"))
		{
			itmTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 4) return ImageIcon.class;
					return Object.class;
				}
			};
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(100);
		
			itmTableModel.addColumn("Supplier Name");
			itmTableModel.addColumn("Stall Name");
			itmTableModel.addColumn("Item Name");
			itmTableModel.addColumn("Selling Price");
			itmTableModel.addColumn("Image");
			itmTableModel.setRowCount(0);

			ArrayList<Items> list;
			list = getView.getExpensiveItemList();
			for(Items itm:list)
			{
				itmTableModel.addRow(itm.cheapAndExpensiveItemsToArray());
			}

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);

			itmPanel_editButt.setVisible(false);
		}

		//when click into Sell vs Org button
		if(e.getActionCommand().equals("show price differences"))
		{
			itmTableModel = new DefaultTableModel();
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(30);
		
			itmTableModel.addColumn("Item Name");
			itmTableModel.addColumn("Original Price");
			itmTableModel.addColumn("Selling Price");
			itmTableModel.addColumn("Price Difference");
			itmTableModel.setRowCount(0);

			ArrayList<Items> list;
			list = getView.getPriceDifferenceList();
			for(Items itm:list)
			{
				itmTableModel.addRow(itm.itemPriceDiffToArray());
			}

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);

			itmPanel_editButt.setVisible(false);
		}

		//when clicking into Top 3 Best sell button
		if(e.getActionCommand().equals("show top 3 best selling"))
		{
			itmTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 4) return ImageIcon.class;
					return Object.class;
				}
			};
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(100);
		
			itmTableModel.addColumn("Stall Name");
			itmTableModel.addColumn("Item Name");
			itmTableModel.addColumn("Selling Price");
			itmTableModel.addColumn("Quantity");
			itmTableModel.addColumn("Image");
			itmTableModel.setRowCount(0);

			ArrayList<Items> list;
			list = getProcedure.getTop3BestSellingItemsList();
			for(Items itm:list)
			{
				itmTableModel.addRow(itm.cheapAndExpensiveItemsToArray());
			}

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);

			itmPanel_editButt.setVisible(false);
		}

		//when clicking into Selling Price button
		if(e.getActionCommand() == "check selling price")
		{
			itmPanel_textField.setText("Please type the Item name you want here...");
			itmPanel_textField.setForeground(Color.LIGHT_GRAY);

			itmPanel_searchOrCheckButt.setActionCommand("check and give conclusion about the selling price");
			itmPanel_searchOrCheckButt.setEnabled(true);
			itmPanel_searchOrCheckButt.setText("Check");
			itmPanel_searchOrCheckButt.setIcon(checkIcon);

			itmPanel_editButt.setVisible(false);
		}
		//when clicking into Check button
		if(e.getActionCommand() == "check and give conclusion about the selling price")
		{
			itmTableModel = new DefaultTableModel();
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(100);

			conclusion = getFunction.checkSellingPriceOfSelectedItem(itmPanel_textField.getText(), conclusion);
			itmTableModel.addColumn("Conclusion");
			itmTableModel.setRowCount(0);
			itmTableModel.addRow(new Object[] {conclusion});

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);

			itmPanel_searchOrCheckButt.setText("Search");
			itmPanel_searchOrCheckButt.setIcon(searchIcon);
			itmPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Original Price button
		if(e.getActionCommand() == "check original price")
		{
			itmPanel_textField.setText("Please type the Item name you want here...");
			itmPanel_textField.setForeground(Color.LIGHT_GRAY);

			itmPanel_searchOrCheckButt.setActionCommand("check and give conclusion about the original price");
			itmPanel_searchOrCheckButt.setEnabled(true);
			itmPanel_searchOrCheckButt.setText("Check");
			itmPanel_searchOrCheckButt.setIcon(checkIcon);

			itmPanel_editButt.setVisible(false);
		}
		//when clicking into Check button
		if(e.getActionCommand() == "check and give conclusion about the original price")
		{
			itmTableModel = new DefaultTableModel();
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(100);

			conclusion = getFunction.checkOriginalPriceOfSelectedItem(itmPanel_textField.getText(), conclusion);
			itmTableModel.addColumn("Conclusion");
			itmTableModel.setRowCount(0);
			itmTableModel.addRow(new Object[] {conclusion});

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);

			itmPanel_searchOrCheckButt.setText("Search");
			itmPanel_searchOrCheckButt.setIcon(searchIcon);
			itmPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking into Search by Code button
		if(e.getActionCommand() == "search item by code _ option")
		{
			itmPanel_textField.setText("Please type the Item code you want here...");
			itmPanel_textField.setForeground(Color.LIGHT_GRAY);

			itmPanel_searchOrCheckButt.setActionCommand("search item by code _ action");
			itmPanel_searchOrCheckButt.setEnabled(true);
			itmPanel_searchOrCheckButt.setText("Search");
			itmPanel_searchOrCheckButt.setIcon(searchIcon);

			itmPanel_editButt.setVisible(false);
		}
		//when clicking into Search button
		if(e.getActionCommand() == "search item by code _ action")
		{
			itmTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 6) return ImageIcon.class;
					return Object.class;
				}
			};
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(100);
		
			itmTableModel.addColumn("Item Code");
			itmTableModel.addColumn("Supplier ID");
			itmTableModel.addColumn("Item Name");
			itmTableModel.addColumn("Selling Price");
			itmTableModel.addColumn("Ordinal Number Of Stall");
			itmTableModel.addColumn("Import Date");
			itmTableModel.addColumn("Image");
			itmTableModel.setRowCount(0);

			ArrayList<Items> list;
			list = getProcedure.getItemInfoBaseOnItemCodeList(itmPanel_textField.getText());
			for(Items itm:list)
			{
				itmTableModel.addRow(itm.itemToArray());
			}

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(5).setCellRenderer(centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				itmPanel_editButt.setVisible(true);
			}

			if(itmTable.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "This Item code is non-exist");
			}

			if(connectToDatabase.userRole == "manager")
			{
				itmPanel_editButt.setVisible(true);
			}
			itmPanel_searchOrCheckButt.setEnabled(false);
		}
		
		//when clicking into Search by name button
		if(e.getActionCommand() == "search item by name _ option")
		{
			itmPanel_textField.setText("Please type the Item name you want here...");
			itmPanel_textField.setForeground(Color.LIGHT_GRAY);

			itmPanel_searchOrCheckButt.setActionCommand("search item by name _ action");
			itmPanel_searchOrCheckButt.setEnabled(true);
			itmPanel_searchOrCheckButt.setText("Search");
			itmPanel_searchOrCheckButt.setIcon(searchIcon);

			itmPanel_editButt.setVisible(false);
		}
		//when clicking into Search button
		if(e.getActionCommand() == "search item by name _ action")
		{
			itmTableModel = new DefaultTableModel()
			{
				@Override
				public Class<?> getColumnClass(int column) 
				{
					if (column == 6) return ImageIcon.class;
					return Object.class;
				}
			};
			itmTableModel.setColumnCount(0);
			itmTable.setRowHeight(100);
		
			itmTableModel.addColumn("Item Code");
			itmTableModel.addColumn("Supplier ID");
			itmTableModel.addColumn("Item Name");
			itmTableModel.addColumn("Selling Price");
			itmTableModel.addColumn("Ordinal Number Of Stall");
			itmTableModel.addColumn("Import Date");
			itmTableModel.addColumn("Image");
			itmTableModel.setRowCount(0);

			ArrayList<Items> list;
			list = getProcedure.getItemInfoBaseOnItemNameList(itmPanel_textField.getText()); 
			for(Items itm:list)
			{
				itmTableModel.addRow(itm.itemToArray());
			}

			itmTable.setModel(itmTableModel);
			itmTable.getColumnModel().getColumn(0).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(1).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(2).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(3).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(4).setCellRenderer(centerTextInCell);
			itmTable.getColumnModel().getColumn(5).setCellRenderer(centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				itmPanel_editButt.setVisible(true);
			}

			if(itmTable.getRowCount() == 0)
			{
				JOptionPane.showMessageDialog(null, "This Item name is non-exist");
			}

			if(connectToDatabase.userRole == "manager")
			{
				itmPanel_editButt.setVisible(true);
			}
			itmPanel_searchOrCheckButt.setEnabled(false);
		}

		//when clicking in to 'Yes' radio butt
		if(e.getActionCommand() == "yes")
		{
			avai = true;
			addItmInfo_noRadioButt.setSelected(false);
		}
		//when clicking in to 'No' radio butt
		if(e.getActionCommand() == "no")
		{
			avai = false;
			addItmInfo_yesRadioButt.setSelected(false);
		}

		//when clicking into Add button
		if(e.getActionCommand() == "add item")
		{
			setPanelAddItmInfo();
			JOptionPane.showMessageDialog(null, addItmInfoPanel, "Add a new staff into table", JOptionPane.INFORMATION_MESSAGE);
			String ImagePath = null;

			try 
			{
				ImagePath = itmImgFile.getAbsolutePath();
			} 
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "You have not chosen an image yet !!");
			}

			java.util.Date utilDate = (java.util.Date) datePicker.getModel().getValue();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			editTable.addIntoItems(addItmInfo_itemCodeTextField.getText(), addItmInfo_supplierIDTextField.getText(), addItmInfo_itemNameTextField.getText(), Float.parseFloat(addItmInfo_sellingPriceTextField.getText()), addItmInfo_stallOrdNumberTextField.getText(), sqlDate, ImagePath, avai, Float.parseFloat(addItmInfo_originalPriceTextField.getText()), Integer.parseInt(addItmInfo_quantityInStockTextField.getText()));

			refreshTables.ItemsTable(itmTable, itmTableModel, centerTextInCell);
		}

		//when clicking into Delete button
		if(e.getActionCommand() == "delete item")
		{
			editTable.deleteOneRow("Items", "Item_Code", (String) itmTable.getValueAt(rowPoint, 0));
			editTable.deleteOneRow("Item_Management", "Item_Code", (String) itmTable.getValueAt(rowPoint, 0));

			refreshTables.ItemsTable(itmTable, itmTableModel, centerTextInCell);

			if(connectToDatabase.userRole == "manager")
			{
				itmPanel_editButt.setVisible(true);
			}
		}

		//when clicking Edit button
		if(e.getActionCommand() == "edit item")
		{
			String Item_Code = null, Supplier_ID = null, Item_Name = null, Ordinal_Number_Of_Stall = null;
			Date Import_Date = null;
			float Selling_Price = 0, Original_Price = 0;
			int Quantity_In_Stock = 0;
			Boolean Available = true;

			Connection connect =  connectToDatabase.connectToSql(actionListener.user, actionListener.password);
			String sqlItm = "select itm.Item_Code, Supplier_ID, Item_Name, Original_Price, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, Quantity_In_Stock, Available, Image from Items itm, Item_Management itmMng where itm.Item_Code = itmMng.Item_Code and itm.Item_Code = '" + (String) itmTable.getValueAt(rowPoint, 0) + "'";

			try
			{
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery(sqlItm);
				while(rs.next() == true)
				{
					Item_Code = rs.getString("Item_Code");
					Supplier_ID = rs.getString("Supplier_ID");
					Item_Name = rs.getString("Item_Name");
					Ordinal_Number_Of_Stall = rs.getString("Ordinal_Number_Of_Stall");
					Import_Date = rs.getDate("Import_Date");
					Selling_Price = rs.getFloat("Selling_Price");
					Original_Price = rs.getFloat("Original_Price");
					Quantity_In_Stock = rs.getInt("Quantity_In_Stock");
					Available = rs.getBoolean("Available");
					Blob imgBlob = rs.getBlob("Image");
					initByteImg = imgBlob.getBytes(1, (int) imgBlob.length()); 
				}
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
			setPanelEditItmInfo(Item_Code, Supplier_ID, Item_Name, Original_Price, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, Quantity_In_Stock, Available, initByteImg, addedByteOfImg);
			JOptionPane.showMessageDialog(null, editItmInfoPanel, "Edit Item information", JOptionPane.INFORMATION_MESSAGE);
		}
		//when clicking COMPLETED in edit item panel
		if(e.getActionCommand() == "completed edit item")
		{
			String Item_Code = editItmInfo_itemCodeTextField.getText();
			String Supplier_ID = editItmInfo_supplierIDTextField.getText();
			String Item_Name = editItmInfo_itemCodeTextField.getText();
			String Ordinal_Number_Of_Stall = editItmInfo_ordinalNumOfStallTextField.getText();
			java.util.Date utilDate = (java.util.Date) datePicker.getModel().getValue();
			java.sql.Date Import_Date = new java.sql.Date(utilDate.getTime());
			float Selling_Price = Float.parseFloat(editItmInfo_sellingPriceTextField.getText());
			float Original_Price = Float.parseFloat(editItmInfo_originalPriceTextField.getText());
			int Quantity_In_Stock = Integer.parseInt(editItmInfo_quanityInStockTextField.getText());
			boolean Available = false;

			if(editItmInfo_yesRadioButt.isSelected())
			{
				Available = true;
			}
			else if(editItmInfo_noRadioButt.isSelected())
			{
				Available = false;
			}

			//if user doesn't choose any new Image to edit, it will take the byte[] of initial ImageIcon
			if(addedByteOfImg == null)
			{
				addedByteOfImg = initByteImg;
			}

			editTable.editOneCellInItemsTable(Item_Code, Supplier_ID, Item_Name, Selling_Price, Ordinal_Number_Of_Stall, Import_Date, addedByteOfImg, Available, Original_Price, Quantity_In_Stock);

			refreshTables.ItemsTable(itmTable, itmTableModel, centerTextInCell);
		}
    }
}
