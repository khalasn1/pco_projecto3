package ycel.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import ycel.data.CellContent;
import ycel.data.CellPosition;
import ycel.data.CellStyle;
import ycel.data.Document;
import ycel.io.CellParser;
import ycel.io.DocumentIO;

/**
 * Main window.
 */
@SuppressWarnings({"javadoc","serial"})
public class YCelWindow extends JFrame 
implements YCelTable.CellSelectionListener {


  /** Singleton instance. */
  private static YCelWindow INSTANCE = null;

  /** Get main window.
   * @return Singleton instance.
   */
  public static YCelWindow get() {
    if (INSTANCE == null) {
      INSTANCE = new YCelWindow();
    }
    return INSTANCE;
  }

  private final JTextField posField;
  private final JTextField formulaField;
  private final YCelTable table;
  private final YCelModel model;
  private final JFileChooser fileChooser;
  private final JTextField styleField;
 // private final FontChooser fontChooser;
  /**
   * Constructor.
   */
  private YCelWindow() {
    // Call superclass constructor.
    super("YCel");    
    Box layout = Box.createVerticalBox();

    fileChooser = new JFileChooser(".");
    fileChooser.setFileFilter(new FileFilter() {
      @Override
      public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(".txt");
      }
      @Override
      public String getDescription() {
        return "yCell files (*.ycel.txt)";
      }
    });

    JPanel bPanel = panel(FlowLayout.LEFT);
    for (AppAction b : AppAction.values()) {
      bPanel.add(b.createButton()); 
    }
    layout.add(bPanel);
    posField = new JTextField(4);
    formulaField = new JTextField(25);
    styleField = new JTextField(55);
    posField.setEditable(false);
    posField.setBorder(GUIConstants.EMPTY_BORDER);
    formulaField.setEditable(false);
    posField.setBorder(GUIConstants.EMPTY_BORDER);
    posField.setFont(GUIConstants.DEFAULT_FONT);
    formulaField.setFont(GUIConstants.DEFAULT_FONT);
    styleField.setFont(GUIConstants.DEFAULT_FONT);

    model = new YCelModel();
    table = new YCelTable(model);
    layout.add(table.buildScrollPane(), BorderLayout.CENTER);
    table.addSelectionListener(this);

    styleField.addActionListener(
        e -> { 
          int r = table.getSelectedRow();
          int c = table.getSelectedColumn();
          if (r >= 0 && c >= 0) {
            model.onStyleChanged(r, c, CellParser.parseStyle(styleField.getText()));         
            repaint();
          } 
        }
        );
//    fontChooser = new FontChooser();
//    fontChooser.addItemListener(
//        e -> { 
//          int r = table.getSelectedRow();
//          int c = table.getSelectedColumn();
//          if (r >= 0 && c >= 0) {
//            model.onFontChanged(r, c, (Font) e.getItem());
//          }
//          repaint();
//        });
    layout.add(panel(FlowLayout.LEFT, 
        label("Position"),
        posField,
        label("Formula:"),
        formulaField,
       // fontChooser,
        label("Style:"),
        styleField));
    add(layout);
    pack();
    setVisible(true);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(screenSize.width/2 - getWidth()/2, 
        screenSize.height/2 - getHeight()/2);
  }

  /** 
   * Create new panel for specified components.
   * @param align Alignment.
   * @param components Components in panel.
   * @return A new panel.
   */
  private static JPanel panel(int align, Component... components) {
    return panel(new FlowLayout(align), components);
  }
  /** 
   * Create new panel with given layout for specified components 
   * @param layout Layout.
   * @param components Components in panel.
   * @return A new panel.
   */
  private static JPanel panel(LayoutManager layout, Component... components) {
    JPanel panel = new JPanel(layout);
    for (Component comp : components) {
      panel.add(comp);
    }
    return panel;
  }

  /**
   * Create label.
   * @param text Text for label.
   * @return New label with given text.
   */
  private JLabel label(String text) {
    return new JLabel(text);
  }

  /**
   * Callback method for method selection.
   */
  @Override
  public void onSelectedCell(CellPosition p, CellContent c, CellStyle cs) {
    posField.setText(p.toString());
    formulaField.setText(c.formula());
    // fontChooser.selectFont(cs.font());
    styleField.setText(cs.toString());
  }


  /** 
   * Display error message using a pop-up window.
   * @param title Window title.
   * @param message Window message.
   */
  private static void displayError(String title, String message) {
    JOptionPane.showMessageDialog(INSTANCE, message, title, JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Action enumeration.
   */
  private enum AppAction implements ActionListener {
    /** New document. */
    New {
      @Override
      void action() {
        INSTANCE.model.setDocument(new Document());
        INSTANCE.repaint();
      }

    },
    /** Open document. */
    Open {
      @Override
      void action() {
        JFileChooser fc = INSTANCE.fileChooser;
        if (fc.showOpenDialog(INSTANCE) != JFileChooser.APPROVE_OPTION) {
          return;
        }
        File f = fc.getSelectedFile();
        if (f != null) {
          try {
            Document doc = DocumentIO.load(f);
            INSTANCE.model.setDocument(doc);
            INSTANCE.repaint();
          } catch (Throwable e) {
            displayError("Error saving document!", e.getMessage());
          }
        }
      }
    },
    /** Save document. */
    Save {
      @Override
      void action() {
        JFileChooser fc = INSTANCE.fileChooser;
        if (fc.showSaveDialog(INSTANCE) != JFileChooser.APPROVE_OPTION) {
          return;
        }
        File f = fc.getSelectedFile();
        if (f != null) {
          try {
            Document doc = 
                INSTANCE.model.getDocument();
            DocumentIO.save(f,doc);
          } catch (Throwable e) {
            displayError("Error loading document!", e.getMessage());
          }
        }
      }
    };
    /**
     * Create button.
     * @return Button for action.
     */
    private Component createButton() {
      String id = toString();
      JButton jb = new JButton(new ImageIcon("resources/icons/" + id + ".png"));
      jb.setContentAreaFilled(false);
      jb.setToolTipText(id);
      jb.addActionListener(this);
      jb.setBorder(BorderFactory.createEmptyBorder());
      jb.setActionCommand(id);
      return jb;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
      action();
    }
    /** Execute action. */
    abstract void action();
  }



}