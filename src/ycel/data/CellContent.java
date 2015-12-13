package ycel.data;

/**
 * Interface para conteúdo de uma célula.
 */
public interface CellContent {
  /**
   * Devolve a fórmula associada ao conteúdo.
   * @return Uma string que codifica o conteúdo
   *   da célula.
   */
  String formula();

  /**
   * Avalia o conteúdo da célula.
   * @param cv Objecto para leitura de outras 
   *           células, caso necessário.
   * @return Objecto resultante da avaliação.
   */
  Object evaluate(CellValues cv);
}
