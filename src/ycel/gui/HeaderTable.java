package ycel.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


@SuppressWarnings({"javadoc", "serial"})
public class HeaderTable extends JTable {

  public HeaderTable(int rows) {
    super(new DefaultTableModel() {
      @Override
      public int getRowCount() {
        return rows;
      }
      @Override 
      public int getColumnCount() {
        return 1;
      }

      @Override
      public boolean isCellEditable(int row, int col) {
        return false;
      }

      @Override
      public Object getValueAt(int row, int col) { 
        return row;
      }
    });
    setRowSelectionAllowed(false);
    setColumnSelectionAllowed(false);
    setCellSelectionEnabled(false);
    
    setGridColor(Color.BLACK);
    setShowGrid(true);
    setShowHorizontalLines(true);
    setShowVerticalLines(true);
    setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    getColumnModel().setColumnMargin(0);
    setPreferredScrollableViewportSize(new Dimension(75, 0));
  }
  @Override
  public TableCellRenderer getCellRenderer(int row, int column) {
    return new HeaderRenderer(new DefaultTableCellRenderer(), new Dimension(25,0));
  }
}
