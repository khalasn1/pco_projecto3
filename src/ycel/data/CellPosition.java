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
   */
  @Override
  public int compareTo(CellPosition other) {
	int res;
	int colunas = this.getCol().compareTo(other.getCol());
    if (colunas == 0){
    	if (this.getRow() < other.getRow()){
    		res = -1;
    	}
    	else if(this.getRow() > other.getRow()){
    		res = 1;
    	}
    	else{
    		res = 0;
    	}}
    else if(colunas == 1){
    	res = 1;
    }
    else {
    	res = -1;
    }
    return res;	
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
  public static final 
  Comparator<String> COLUMN_COMPARATOR = null; // TODO

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
    return true; // TODO 
  }
}
