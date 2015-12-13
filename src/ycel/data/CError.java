package ycel.data;

/**
 * Conteúdo com erro (NÃO ALTERAR).
 * 
 * <p>
 * Objectos deste tipo servem para assinalar
 * com fórmulas mal formatadas.
 * </p>
 * 
 */
public final class CError implements CellContent {

  /** Fórmula com erro. */
  private final String errFormula; 

  /**
   * Constructor.
   * @param formulaWithError Fórmula com erro.
   */
  public CError(String formulaWithError) {
    this.errFormula = formulaWithError; 
  }
  /**
   * Obtém fórmula.
   * @return Devolve fórmula com erro.
   */
  @Override
  public String formula() {
    return errFormula;
  }
  /**
   * Avalia conteúdo.
   * @return <code>"Error: "</code> seguido
   *    da fórmula com erro.
   */
  @Override
  public Object evaluate(CellValues cells) {
    return "Error: " + errFormula;  
  }

}
