package ycel.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import ycel.data.CellContent;
import ycel.data.CellPosition;
import ycel.data.CellStyle;
import ycel.data.Document;
import ycel.data.NumberContent;

/**
 * GUI table.
 *
 */
@SuppressWarnings({"javadoc","serial"})
class YCelTable extends JTable {

  /**
   * Cell selection listener interface.
   */
  public interface CellSelectionListener {
    /**
     * Callback method.
     * @param p Position.
     * @param c Content.
     * @param s Style.
     */
    void onSelectedCell(CellPosition p, CellContent c, CellStyle s);
  }

  /**
   * Constructor.
   * @param model Model object.
   */
  public YCelTable(YCelModel model) {
    super(model);
    setCellSelectionEnabled(true);
    setGridColor(Color.BLACK);
    setShowHorizontalLines(true);
    setShowVerticalLines(true);
    setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    JTableHeader th = getTableHeader();
    th.setDefaultRenderer(new HeaderRenderer(th.getDefaultRenderer(), null));
    th.setResizingAllowed(false);
    th.setReorderingAllowed(false);
    
    TableCellRenderer tcr = getDefaultRenderer(Object.class);

    setDefaultRenderer(Object.class,
        new TableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table,
          Object value, boolean isSelected, boolean hasFocus, int row,
          int column) {
        JLabel jl = (JLabel) 
            tcr.getTableCellRendererComponent
            (table, value, 
                isSelected, hasFocus, 
                row, column);
        YCelModel m = (YCelModel) dataModel;
        Document doc = m.getDocument();
        CellPosition p = new CellPosition(m.getColumnName(column), row);
        CellContent c = (CellContent) value;
        CellStyle.DEFAULT_STYLE.applyTo(jl);
        CellStyle cs = doc.getStyle(p);
        if (cs != CellStyle.DEFAULT_STYLE) {
          cs.applyTo(jl);
        }
        Object eValue = c.evaluate(doc);
        String sValue;
        if (c instanceof NumberContent && 
            cs.decimalPlaces() != null) {
          sValue = String.format(
              "%." + cs.decimalPlaces() + "f",
              (Double) eValue);
        } else {
          sValue = eValue.toString();
        }
        jl.setText(sValue);
        return jl;
      }
    });
    setDefaultEditor(Object.class, new CellEditor());
  }
  
  /**
   * Add selection listener.
   * @param sl Selection listener.
   */
  public void addSelectionListener(CellSelectionListener sl) {
    ListSelectionListener lsl = new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        int r = getSelectedRow();  
        int c = getSelectedColumn();
        YCelModel m = (YCelModel) dataModel;
        Document doc = m.getDocument();
        CellPosition pos = new CellPosition(m.getColumnName(c), r);
        sl.onSelectedCell(pos, doc.getContent(pos), doc.getStyle(pos));
      }
    };
    getSelectionModel().addListSelectionListener(lsl);
    getColumnModel().getSelectionModel().addListSelectionListener(lsl);
  }

  /**
   * Method with custom actions when cell
   * edition is done.
   * @see JTable#editingStopped(ChangeEvent)
   */
  @Override 
  public void editingStopped(ChangeEvent e)  {
    super.editingStopped(e);
    int r = getSelectedRow();  
    int c = getSelectedColumn();
    YCelModel m = (YCelModel) dataModel;
    Document doc = m.getDocument();
    CellPosition pos = new CellPosition(m.getColumnName(c), r);
    YCelWindow.get().onSelectedCell(pos, doc.getContent(pos), doc.getStyle(pos));
  }
  /**
   * Method with custom actions when cell
   * edition is cancelled.
   * @see JTable#editingStopped(ChangeEvent)
   */
  @Override 
  public void editingCanceled(ChangeEvent e)  {
    super.editingCanceled(e);
    int r = getSelectedRow();  
    int c = getSelectedColumn();
    YCelModel m = (YCelModel) dataModel;
    Document doc = m.getDocument();
    CellPosition pos = new CellPosition(m.getColumnName(c), r);
    YCelWindow.get().onSelectedCell(pos, doc.getContent(pos), doc.getStyle(pos));
  }
  

  private static final Dimension
  VIEWPORT_DIMENSION = 
  new Dimension(GUIConstants.SS_WIDTH, 
      GUIConstants.SS_HEIGHT);

  /**
   * Build scroll pane for table.
   * @return Scroll pane.
   */
  public JScrollPane buildScrollPane() {
    JScrollPane sp = 
        new JScrollPane(this,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    sp.setRowHeaderView(new HeaderTable(getRowCount()));
    sp.setColumnHeaderView(getTableHeader());
    sp.setSize(VIEWPORT_DIMENSION);
    sp.setPreferredSize(VIEWPORT_DIMENSION);  
    return sp;
  }

}
