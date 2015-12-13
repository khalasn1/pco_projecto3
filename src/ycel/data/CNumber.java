package ycel.data;


/**
 * Conteúdo de célula definido por um número. 
 */
public final class CNumber implements NumberContent {

  // TODO  
  /**
   * Construtor.
   * @param value Valor a representar.
   */
  public CNumber(double value) {
    // TODO
  }

  /**
   * Obtém fórmula.
   * @return String representando o número.
   * @see String#valueOf(double)
   */
  @Override
  public String formula() {
    return String.valueOf(0.0); // TODO
  }

  /**
   * Avalia conteúdo.
   * @return Número representado.
   */
  @Override
  public Double evaluate(CellValues cv) {
    return 0.0; // TODO
  }
  
}
