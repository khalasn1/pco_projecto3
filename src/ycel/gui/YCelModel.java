package ycel.gui;


import java.awt.Font;

import javax.swing.table.AbstractTableModel;

import ycel.data.CellContent;
import ycel.data.CellPosition;
import ycel.data.CellStyle;
import ycel.data.Document;

/**
 * Model for {@link YCelTable}.
 *
 */
@SuppressWarnings({ "javadoc", "serial" })
class YCelModel extends AbstractTableModel {
  private Document doc = new Document();

  void setDocument(Document d) {
    doc = d;
  }
  Document getDocument() {
    return doc;
  }
  private CellPosition position(int row, int col) {
    return new CellPosition(super.getColumnName(col), row);
  }
  @Override
  public int getColumnCount() {
    return GUIConstants.MAX_COLS;
  }

  @Override
  public int getRowCount() {
    return GUIConstants.MAX_ROWS;
  }

  @Override
  public Object getValueAt(int row, int col) {
    return doc.getContent(position(row,col));
  }

  @Override
  public boolean isCellEditable(int row, int col) {
    return true;
  }

  @Override
  public void setValueAt(Object value, int row, int col) {
    doc.setContent(position(row,col), (CellContent) value);
  }
  
  
  void onStyleChanged(int r, int c, CellStyle cs) {
    doc.setStyle(position(r,c), cs);
  }
  void onFontChanged(int r, int c, Font f) {
     CellStyle cs = doc.getStyle(position(r,c));
     Font curr = cs.font();
     if (!f.getName().equals(curr.getName())) {
       cs.font(new Font(f.getName(),0, curr.getSize()));
     }
  }
}

