import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class refreshTables 
{
    public static void StaffTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer)
    {
        model = new DefaultTableModel()
        {
            @Override
            public Class<?> getColumnClass(int column) 
            {
                if (column == 5) return ImageIcon.class;
                return Object.class;
            }
        };
        model.setColumnCount(0);
        table.setRowHeight(100);
    
        model.addColumn("Staff ID");
        model.addColumn("Position");
        model.addColumn("Staff Name");
        model.addColumn("Gender");
        model.addColumn("Hometown");
        model.addColumn("Image");
        model.setRowCount(0);

        ArrayList<Staff> list;
        list = getTable.getStaffListToShow();
        for(Staff staff:list)
        {
            model.addRow(staff.staffToArray());
        }
        table.setModel(model);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
    }    



    public static void SupplierTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer)
    {
        model = new DefaultTableModel()
        {
            @Override
            public Class<?> getColumnClass(int column) 
            {
                if (column == 4) return ImageIcon.class;
                return Object.class;
            }
        };
        model.setColumnCount(0);
        table.setRowHeight(100);
    
        model.addColumn("Supplier ID");
        model.addColumn("Supplier Name");
        model.addColumn("Location");
        model.addColumn("Phone Number");
        model.addColumn("Image");
        model.setRowCount(0);

        ArrayList<Supplier> list;
        list = getTable.getSupplierListToShow();
        for(Supplier supplier:list)
        {
            model.addRow(supplier.supplierToArray());
        }
        table.setModel(model);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
    }



    public static void ItemsTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer)
    {
        model = new DefaultTableModel()
        {
            @Override
            public Class<?> getColumnClass(int column) 
            {
                if (column == 6) return ImageIcon.class;
                return Object.class;
            }
        };
        model.setColumnCount(0);
        table.setRowHeight(100);
    
        model.addColumn("Item Code");
        model.addColumn("Supplier ID");
        model.addColumn("Item Name");
        model.addColumn("Selling Price");
        model.addColumn("Ordinal Number Of Stall");
        model.addColumn("Import Date");
        model.addColumn("Image");
        model.setRowCount(0);

        ArrayList<Items> list;
        list = getTable.getItemListToShow();
        for(Items itm:list)
        {
            model.addRow(itm.itemToArray());
        }

        table.setModel(model);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
    }



    public static void StallTable(JTable table, DefaultTableModel model, DefaultTableCellRenderer cellRenderer)
    {
        model = new DefaultTableModel();
        
        model.setColumnCount(0);
        table.setRowHeight(100);
    
        model.addColumn("Ordinal Number");
        model.addColumn("Stall Name");
        model.addColumn("Supplier ID");
        model.setRowCount(0);

        ArrayList<Stall> list;
        list = getTable.getStallListToShow();
        for(Stall stall:list)
        {
            model.addRow(stall.stallToArray());
        }

        table.setModel(model);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

    }
}
