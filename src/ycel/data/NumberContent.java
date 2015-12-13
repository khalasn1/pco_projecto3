package ycel.data;

/**
 * Interface para conteúdos de células 
 * cuja avaliação resulta num valor {@link Double}.
 *
 */
public interface NumberContent extends CellContent {
  /**
   * Avalia o conteúdo.
   * @return Valor {@link Double} que é o resultado
   *    da avaliação.
   */
  @Override
  Double evaluate(CellValues cells);
}
