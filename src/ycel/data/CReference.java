package ycel.data;


/**
 * Referência a outra célula.
 * 
 * <p>
 * Este tipo de conteúdo é codificado
 * com uma fórmula <code>#p</code> 
 * onde <code>p</code>
 * é a posição
 * da célula referida, ex. <code>#A12</code>
 * para a célula na coluna <code>A</code>
 * e linha <code>12</code>.
 * </p>
 */
public final class CReference implements NumberContent {
  
  // TODO
  
  /**
   * Construtor.
   * @param pos Posição da outra célula.
   */
  public CReference(CellPosition pos) {
    // TODO
  }
  /**
   * Obtém formula. 
   * @return String iniciada por <code>#</code>
   *   seguida da string a descrever a posição.
   */
  @Override
  public String formula() {
    return "";
  }

  /**
   * Avalia referência.
   * @return Valor de avaliar a referência
   *    caso o seu conteúdo seja um objecto
   *    {@link NumberContent}, ou {@link Double#NaN}
   *    caso contrário.
   */
  @Override
  public Double evaluate(CellValues cells) {
    return 0.0;
  }

}
