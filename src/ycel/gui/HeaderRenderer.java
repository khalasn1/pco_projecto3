package ycel.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import ycel.data.CellStyle;

@SuppressWarnings("javadoc")
public class HeaderRenderer implements TableCellRenderer {

  private final TableCellRenderer delegate;
  private final Dimension preferredSize;
  
  public static final CellStyle HEADER_STYLE 
    = CellStyle.DEFAULT_STYLE
     .clone()
     .background(Color.LIGHT_GRAY)
     .foreground(Color.BLACK);

  public HeaderRenderer(TableCellRenderer tcrDelegate, Dimension ps) {
    delegate = tcrDelegate;
    preferredSize = ps;
  }

  @Override
  public Component 
  getTableCellRendererComponent
  (JTable table, Object value,
      boolean isSelected, boolean hasFocus,
      int row, int column) 
  {
    JLabel jl = (JLabel) 
        delegate.getTableCellRendererComponent
        (table, value, 
            isSelected, hasFocus, 
            row, column);
    HEADER_STYLE.applyTo(jl);
    if (preferredSize != null) {
      jl.setPreferredSize(preferredSize);
    }
    return jl;
  }
}
