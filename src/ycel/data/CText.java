package ycel.data;


/**
 * Conteúdo de texto.
 *
 */
public final class CText implements CellContent {
  
  /**
   * Construtor.
   * @param text Texto.
   */
  public CText(String text) {
    // TODO
  }

  /**
   * Obtém fórmula. 
   * 
   * <p>A fórmula é uma string 
   * prefixada por <code>'</code> (plica)
   * e com todos os espaços
   * codificados com o caracter 
   * <code>_</code> ("underscore"),
   * ex. <code>'A_B_C</code> para o texto
   * <code>"A B C"</code>.
   * 
   * @return Fórmula para o texto representado.
   */
  @Override
  public String formula() {
    return ""; // TODO
  }

  /**
   * Avalia conteúdo. 
   * @return O texto representado.
   */
  @Override
  public String evaluate(CellValues cells) {
    return ""; // TODO
  }
}
