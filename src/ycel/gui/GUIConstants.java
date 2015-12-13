package ycel.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * GUI constants.
 */
@SuppressWarnings("javadoc")
final class GUIConstants {
  
  public static final int MAX_ROWS = 10000;
  
  public static final int MAX_COLS = 10000;

  public static final 
  Border EMPTY_BORDER = BorderFactory.createEmptyBorder();
  
  public static final 
  Border CELL_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
 
  public static final 
  Font DEFAULT_FONT = new Font(Font.MONOSPACED,0, 12);

  public static final int SS_WIDTH = 800;
  
  public static final int SS_HEIGHT = 600;
  
  private GUIConstants() { }

}
