package ycel.data;

import java.util.NavigableMap;

/**
 * Interface de conveniência para 
 * leitura de células (NÃO ALTERAR).
 * 
 * <p>O interface é implementado 
 * pela classe {@link Document}.</p>
 *
 */
public interface CellValues {
  /**
   * Obtém conteúdo da célula.
   * @param pos Posição da célula.
   * @return Conteúdo da célula.
   */
  CellContent getContent(CellPosition pos);
  
  /**
   * Modifica conteúdo da célula.
   * @param pos Posição da célula.
   * @param c Conteúdo.
   */
  void setContent(CellPosition pos, CellContent c);
  
  /**
   * Obtém todas as células como um mapa
   * de posições em conteúdo.
   * @return Um mapa de  {@link CellPosition} em {@link CellContent}.
   */
  NavigableMap<CellPosition, CellContent>
  allCells();
}
