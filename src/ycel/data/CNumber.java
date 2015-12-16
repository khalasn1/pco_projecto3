package ycel.data;

import ycel.data.CReference;

/**
 * Conteúdo de célula definido por um número. 
 */
public final class CNumber implements NumberContent {

  private double value;
  /**
   * Construtor.
   * @param value Valor a representar.
   */
  public CNumber(double value) {
    this.value = value;
  }

  /**
   * Obtém fórmula.
   * @return String representando o número.
   * @see String#valueOf(double)
   */
  @Override
  public String formula() {
    return String.valueOf(value);
    }

  /**
   * Avalia conteúdo.
   * @return Número representado.
   */
  @Override
  public Double evaluate(CellValues cv) {

    return ; // TODO
  }
  
}
