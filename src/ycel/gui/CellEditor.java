package ycel.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Scanner;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import ycel.data.CellContent;
import ycel.data.CError;
import ycel.io.CellParser;

/**
 * Cell editor.
 */
@SuppressWarnings({ "javadoc", "serial" })
public class CellEditor extends AbstractCellEditor implements TableCellEditor {

  private final JTextField tf;


  public CellEditor() {
    tf = new JTextField(100);
    tf.setFont(GUIConstants.DEFAULT_FONT);
    tf.setBackground(Color.BLACK);
    tf.setForeground(Color.WHITE);
    tf.setBorder(BorderFactory.createEmptyBorder());
  }

  @Override
  public boolean isCellEditable(EventObject e) {
    return ! (e instanceof MouseEvent) 
        || ((MouseEvent)e).getClickCount() >= 2;
  }

  @Override
  public Object getCellEditorValue() {
    return parse(tf.getText());
  }


  private CellContent parse(String s) {
    try {
      Scanner sc = new Scanner(s);
      CellContent cell = CellParser.parseContent(sc);
      if (sc.hasNext() && ! (cell instanceof CError) ) {
        cell = new CError(s);
      }
      return cell;
    } catch (Throwable e) {
        return new CError(tf.getText());
      }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
      tf.setText(((CellContent) value).formula());
      return tf;
    }
  }