package ycel;

import java.util.Locale;

import ycel.gui.YCelWindow;

/**
 * Main program.
 * 
 * @author Eduardo Marques, DI/FCUL, 2015.
 */
public class Program {
  
  /**
   * Program entry point.
   * 
   * @param args
   *          Arguments are ignored.
   */
  public static void main(String[] args) {
    Locale.setDefault(Locale.Category.DISPLAY, Locale.ENGLISH);
    Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH);
    YCelWindow.get();
  }
  
  /**
   * Private constructor to prevent instantiation.
   */
  private Program() { }
}

  