package ycel.data;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Estilo de uma célula (NÃO ALTERAR).
 * 
 * <p>
 * Em forma de string (tal e qual retornado por {@link #toString()}), 
 * um estilo tem uma representação</p>
 * <br>&nbsp;&nbsp;&nbsp;
 * <code>
 * dp=&lt;n&gt;;align=&lt;a&gt;;bg=&lt;bc&gt;;fg=&lt;fc&gt;;font=&lt;f&gt;;
 * </code>
 * <p>em que </p>
 * <ul>
 * <li><code>n</code> é o número de casas decimais
 *   a apresentar caso o estilo se aplique a uma
 *   célula com conteúdo de tipo {@link NumberContent};</li>
 * <li><code>a</code> é o alinhamento de texto 
 * ({@link CellStyle.Alignment});</li>
 * <li><code>bc</code> e <code>fc</code> são as cores
 * de fundo e topo a usar com valor RGB codificado
 * em hexadecimal, ex. <code>ff0000</code> designa a
 * cor vermelha (<code>255,0,0</code>); </li>
 * <li>e <code>f</code> codifica o tipo de letra ("font")
 * a usar.</li>
 * </ul>
 * 
 */
public final class CellStyle implements Cloneable {
  /**
   * Estilo por omissão usado nas folhas de cálculo.
   */
  public static final CellStyle
  DEFAULT_STYLE =
    new CellStyle()
      .font(new Font(Font.MONOSPACED, 0, 12))
      .alignment(Alignment.CENTER)
      .background(Color.WHITE)
      .foreground(Color.BLACK)
      .decimalPlaces(2);

  /**
   * Enumeração de alinhamentos.
   */
  public enum Alignment {
    /** Esquerdo. */
    LEFT(SwingConstants.LEFT), 
    /** Direito. */
    RIGHT(SwingConstants.RIGHT), 
    /** Centrado. */
    CENTER(SwingConstants.CENTER);
    
    /** 
     * Construtor.
     * @param v Valor da constante Swing.
     */
    private Alignment(int v) {
      swingConstant = v;
    }
    /**
     * Obtém constante Swing para o alinhamento.
     * @return Valor do alinhamento.
     */
    private int getSwingConstant() {
      return swingConstant;
    }
    /** Constante Swing. */
    private int swingConstant; 
  }
  
  /** Tipo de letra. */
  private Font font;
  /** Cor de fundo. */
  private Color bgColor;
  /** Cor de topo. */
  private Color fgColor;
  /** Alinhamento. */
  private Alignment alignment;
  /** Casas decimais para números */
  private Integer decPlaces;
 
  /**
   * Construtor.
   * 
   * Todos os atributos ficarão inicialmente indefinidos.
   */
  public CellStyle() {

  }

  /**
   * Altera tipo de letra.
   * @param f Tipo de letra.
   * @return <code>this</code>, para facilitar chamadas encadeadas.
   */
  public CellStyle font(Font f) {
    font = f;
    return this;
  }
  
  /**
   * Obtém tipo de letra.
   * @return Objecto {@link Font} para o tipo de letra.
   */
  public Font font() {
    return font;
  }
  /**
   * Altera cor de fundo.
   * @param c Cor.
   * @return <code>this</code>, para facilitar chamadas encadeadas.
   */
  public CellStyle background(Color c) {
    bgColor = c;
    return this;
  }
  /**
   * Obtém cor de fundo.
   * @return Cor de fundo. 
   */
  public Color background() {
    return bgColor;
  }
  
  /**
   * Altera número de casas decimais a usar para números.
   * @param places Casas decimais.
   * @return <code>this</code>, para facilitar chamadas encadeadas.
   */
  public CellStyle decimalPlaces(int places) {
    decPlaces = places;
    return this;
  }
  /**
   * Obtém número de casas decimais a usar para números.
   * 
   * @return Objecto Integer ou null se atributo não definido.
   */
  public Integer decimalPlaces() {
    return decPlaces;
  }
  /**
   * Altera cor de topo.
   * @param c Cor.
   * @return <code>this</code>, para facilitar chamadas encadeadas.
   */
  public CellStyle foreground(Color c) {
    fgColor = c;
    return this;
  }
  /**
   * Obtém cor de topo.
   * @return Cor de topo.
   */
  public Color foreground() {
    return fgColor;
  }
  /**
   * Altera alinhamento. 
   * @param a Tipo de alinhamento.
   * @return <code>this</code>, para facilitar chamadas encadeadas.
   */
  public CellStyle alignment(Alignment a) {
    alignment = a;
    return this;
  }
  /**
   * Obtém alinhamento.
   * @return Tipo de alinhamento.
   */
  public Alignment alignment() {
    return alignment;
  }

  /**
   * Aplica estilo a um objecto {@link JLabel}.
   * @param jl Objecto {@link JLabel} a configurar.
   */
  public void applyTo(JLabel jl) {
    if (font      != null) jl.setFont(font);
    if (bgColor   != null) jl.setBackground(bgColor);
    if (fgColor   != null) jl.setForeground(fgColor);
    if (alignment != null) jl.setHorizontalAlignment(alignment.getSwingConstant());
  }
  
  /**
   * Obtém representação na forma de string.
   * 
   * @return String com formato <code>"attr1=valor1; ... attrn=valorn"</code>
   *         para cada atributo definido.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (decPlaces != null) {
      sb.append(DP_ATTR)
        .append('=')
        .append(decPlaces)
        .append(';');
    }
    if (alignment != null) {
      sb.append(ALIGN_ATTR)
        .append('=')
        .append(alignment)
        .append(';');
    }
    if (bgColor != null) {
      sb.append(BG_ATTR)
        .append('=')
        .append(String.format("%06x", bgColor.getRGB() & 0xFF_FF_FF))
        .append(';');
    }
    if (fgColor != null) {
      sb.append(FG_ATTR)
        .append('=')
        .append(String.format("%06x", fgColor.getRGB() & 0xFF_FF_FF))
        .append(';');
    }
    if (font != null) {
      sb.append(FONT_ATTR)
        .append('=')
        .append(font.getFontName())
        .append('-')
        .append(font.getSize())
        .append(';');
    }
    return sb.toString();
  }
  
  /** Etiqueta para atributo de casas decimais. */ 
  public static final String DP_ATTR = "dp";
  /** Etiqueta para atributo de alinhamento. */ 
  public static final String ALIGN_ATTR = "align";
  /** Etiqueta para atributo de cor de fundo. */
  public static final String BG_ATTR = "bg";
  /** Etiqueta para atributo de cor de topo. */
  public static final String FG_ATTR = "fg";
  /** Etiqueta para atributo de tipo de letra. */
  public static final String FONT_ATTR = "font";
  /**
   * Cria cópia de um estilo.
   * @return Novo objecto com os mesmos atributos.
   */
  public CellStyle clone() {
    try {
      return (CellStyle) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e);
    }
  }
}
