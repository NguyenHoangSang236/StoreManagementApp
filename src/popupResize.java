import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import java.awt.Dimension;

public class popupResize extends JComboBox
{
    private String type;
    private boolean layingOut = false;
    private int widestLengh = 0;
    private boolean wide = false;

    public popupResize(Object[] objs) 
    {
        super(objs);
    }

    public boolean isWide() {
        return wide;
    }

    // Setting the JComboBox wide
    public void setWide(boolean wide) {
        this.wide = wide;
        widestLengh = getWidestItemWidth();

    }

    public Dimension getSize() {
        Dimension dim = super.getSize();
        if (!layingOut && isWide())
            dim.width = Math.max(widestLengh, dim.width);
        return dim;
    }

    public int getWidestItemWidth() {

        int numOfItems = this.getItemCount();
        Font font = this.getFont();
        FontMetrics metrics = this.getFontMetrics(font);
        int widest = 0;
        for (int i = 0; i < numOfItems; i++) {
            Object item = this.getItemAt(i);
            int lineWidth = metrics.stringWidth(item.toString());
            widest = Math.max(widest, lineWidth);
        }

        return widest + 5;
    }

    public void doLayout() {
        try {
            layingOut = true;
            super.doLayout();
        } finally {
            layingOut = false;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        type = t;
    }
}
