package ycel.data;

import java.util.Comparator;

/**
 * Posição de uma célula.
 * 
 * <p>
 * Objectos desta classe devem ser imutáveis.
 * </p>
 */
public final class CellPosition implements Comparable<CellPosition> {

  final String col;
  final int row;

  /**
   * Construtor.
   * @param col Coluna.
   * @param row Linha.
   */
  public CellPosition(String col, int row) {
    this.col = col;
    this.row = row;
  }

  /**
   * Obtém coluna.
   * @return Valor da coluna.
   */
  public String getCol() {
    return this.col;
  }
  /**
   * Obtém linha.
   * @return Valor da linha.
   */
  
  public int getRow() {
    return this.row;
  }

  /**
   * Compara com outra posição.
   * 
   * <p>
   * A comparação entre posições deve
   * ter em conta primeiro a ordem entre colunas
   * definida por {@link #COLUMN_COMPARATOR}
   * e depois a ordem entre (o valor inteiro das) linhas. 
   * 
   * </p>
   * @param other A outra posição.
   * @return Valor conforme descrito em {@link Comparable#compareTo(Object)}.
   *
   * cenas
   */
  @Override
  public int compareTo(CellPosition other) {

      if (COLUMN_COMPARATOR.compare(getCol(), other.getCol()) == 0) {
          if(getRow() > other.getRow()) {
              return -1;
          }
          else if (getRow() < other.getRow()) {
              return 1;
          }
          else {
              return 0;
          }
      }
      else if (COLUMN_COMPARATOR.compare(getCol(), other.getCol()) == 1) {
          return 1;
      }
      else {
          return -1;
      }
    }

  /**
   * Comparador de colunas.
   * <p>
   * O comparador deve permitir ordenar as colunas
   * tendo em conta primeiro o tamanho das strings
   * respectivas e só depois a  "ordem natural" 
   * definida por {@link String#compareTo(String)}.
   * (ex. para termos <code>A, B, ..., AB</code>
   * e não <code>A, ..., AB, ..., B</code>).
   * </p>
   */
  public static final Comparator<String> COLUMN_COMPARATOR = new Comparator<String>() {
      @Override
      public int compare(String s, String t1) {

          if (s.length() > t1.length()) {
              return 1;
          }
          else if (s.length() < t1.length()) {
              return 0;
          }
          else {
              for (int i = 0; i < s.length(); i++) {
                  if (s.charAt(i) < t1.charAt(i)) {
                      return 1;
                  }
              }
              return 0;
          }
      }
  };

  /**
   * Obtém representação textual.
   * @return String que codifica a coluna seguida da linha.
   */
  @Override
  public String toString() {
    return getCol() + getRow();
  }

  /** 
   * Testa equivalência de conteúdo.
   * @param o Outra referência. 
   * @return <code>true</code> se e só se 
   *   <code>o</code> se refere a um objecto
   *   {@link CellPosition} com os mesmos
   *   valores para linha e para a coluna.
   */
  @Override
  public boolean equals(Object o) {

      if (o.getClass().equals(CellPosition.class)) {
          if (this.getCol().equals(((CellPosition)o).getCol()) && this.getRow() == ((CellPosition)o).getRow()) {
              return true;
            }
          }
    return false;
  }
}
