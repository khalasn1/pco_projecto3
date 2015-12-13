package ycel.io;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import java.util.StringTokenizer;

import ycel.data.CBinaryOperation;
import ycel.data.CBinaryOperator;
import ycel.data.CError;
import ycel.data.CellContent;
import ycel.data.CellPosition;
import ycel.data.CReference;
import ycel.data.CNumber;
import ycel.data.CRangeOperation;
import ycel.data.CRangeOperator;
import ycel.data.CText;
import ycel.data.CUndefined;
import ycel.data.CellStyle;
import ycel.data.NumberContent;

/**
 * Classe utilitária para leitura de posições, 
 * conteúdos e estilos (NÃO ALTERAR).
 * 
 */
public final class CellParser {

  /**
   * Lê conteúdo a partir de um scanner.
   * @param s Scanner.
   * @return O conteúdo de uma célula.
   */
  public static CellContent parseContent(Scanner s) {
    if (!s.hasNext()) {
      return CUndefined.INSTANCE;
    }
    if (s.hasNextDouble()) {
      return new CNumber(s.nextDouble());
    }
    String str = s.next();
    
    if (str.charAt(0) == '\'') {
      return new CText(str.substring(1).replace('_', ' '));
    }
    
    if (str.charAt(0) == '#') {
      return new CReference(parsePosition(str.substring(1)));
    }
    
    CRangeOperator rop = getOperator(CRangeOperator.values(),str);

    if (rop != null) {
      return new CRangeOperation(rop, 
                                parsePosition(s.next()), 
                                parsePosition(s.next()));       
    }
    
    CBinaryOperator bop = getOperator(CBinaryOperator.values(), str);
   
    if (bop != null) {
      return new CBinaryOperation(bop,
          (NumberContent) parseContent(s),
          (NumberContent) parseContent(s));
    }

    return new CError(str);
  }

  /**
   * Lê posição de uma string.
   * @param str String.
   * @return Posição lida.
   */
  static CellPosition parsePosition(String str) {
    int pos = 0;
    boolean readCol = true;
    while (pos < str.length() && readCol) {
      char c = Character.toUpperCase(str.charAt(pos));
      if (c < 'A' || c > 'Z') {
        readCol = false;
      } else {
        pos++;
      }
    }
    String col = str.substring(0, pos);
    int row = Integer.parseInt(str.substring(pos));
    return new CellPosition(col,row);
  }
  
 
  /**
   * Lê estilo a partir de uma string.
   * @param s String de input.
   * @return Estilo de uma célula.
   */
  public static CellStyle parseStyle(String s) {
    StringTokenizer stFields = new StringTokenizer(s,";");
    CellStyle cs = new CellStyle();
    while (stFields.hasMoreTokens()) {
      StringTokenizer st = new StringTokenizer(stFields.nextToken(),"=");
      if (st.countTokens() == 2) {
        String prop = st.nextToken();
        String value = st.nextToken();
        switch(prop) {
          case CellStyle.DP_ATTR:
            cs.decimalPlaces(Integer.parseInt(value));
            break;
          case CellStyle.FONT_ATTR:
            cs.font(Font.decode(value));
            break;
          case CellStyle.FG_ATTR:
            cs.foreground(new Color(Integer.parseInt(value.toUpperCase(), 16)));
            break;
          case CellStyle.BG_ATTR:
            cs.background(new Color(Integer.parseInt(value.toUpperCase(), 16)));
            break;
          case CellStyle.ALIGN_ATTR:
            cs.alignment(CellStyle.Alignment.valueOf(value.toUpperCase()));
            break;
        }
      }
    }
    return cs;
  }

  /**
   * Método auxiliar para leitura de operadores.
   * @param values Valores possíveis.
   * @param str String.
   * @return Operador se argumento
   *   corresponde a operador ou <code>null</code>.
   */
  private static <E extends Enum<E>> E
  getOperator(E[] values, String str) {
    for (E elem : values) {
      if (elem.toString().equals(str)) {
        return elem;
      }
    }
    return null;
  }
}
