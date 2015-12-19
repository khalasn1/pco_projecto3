package ycel.data;


import java.util.*;


/**
 * Operação sobre gama de células.
 * 
 * <p>
 * Uma operação deste tipo é 
 * é construída com um operador {@link CRangeOperator}
 * que é aplicado aos valores de células de uma gama definida
 * por duas posições.
 * </p>
 * 
 * <p>
 * Exemplos:
 * </p>
 * <ol>
 * <li><code>SUM A1 A10</code> designa a soma dos valores das células
 * na coluna <code>A</code>, linhas <code>1</code> a <code>10</code>.</li>
 * <li><code>AVG A7 F7</code> designa a média dos valores das células
 * nas colunas <code>A</code> a <code>F</code>, coluna <code>7</code>.</li>
 * <li><code>MAX A1 B10</code>
 * designa o máximo dos valores no "rectângulo"
 * com "canto superior esquerdo" na posição <code>A1</code>
 * e "canto inferior direito" na posição <code>B10</code>.</li>
 * </ol>
 *
 */
public final class CRangeOperation implements NumberContent {

  private CRangeOperator op;
  private CellPosition start;
  private CellPosition end;

  /**
   * Construtor.
   * @param op Operador.
   * @param start Início da gama.
   * @param end Fim da gama.
   */
  public CRangeOperation(CRangeOperator op, CellPosition start, CellPosition end) {
    this.op = op;
    this.start = start;
    this.end = end;
  }

  /**
   * Obtém fórmula.
   * 
   * @return String com o formato <code>op start end</code>.
   */
  @Override
  public String formula() {
    return op + " " + start.toString() + " " + end.toString();
  }

  /** 
   * Avalia conteúdo.
   *
   * <p>
   * A avaliação resulta dedc
   * aplicar o operador de gama à lista
   * à gama de células  <code>c = (pos,content)</code> que obedece
   * às seguintes 3 condições:
   * </p>
   * <ol>
   *  <li>
   *  A coluna de <code>pos</code> está 
   *  entre as colunas de <code>start</code> e <code>end</code>;
   *  </li>
   *  <li>
   *  A linha de <code>pos</code> está 
   *  entre as linhas de <code>start</code> e <code>end</code>;
   *  </li> 
   *  <li>
   *  e {@code content} é um objecto do tipo {@link NumberContent}.
   *  </li>
   * </ol>
   * <p>
   * Se a lista de conteúdos nessas condições for vazia,
   * deve ser retornado {@link Double#NaN}.
   * Se a lista não for vazia, deve ser retornado o valor de aplicar o operador
   * {@link CRangeOperator} à lista 
   * usando o método {@link CRangeOperator#evaluate(java.util.List) CRangeOperator.evaluate(List&lt;Double&gt;)}.
   * </p>
   * <p>
   * <b>Nota:</b> para uma implementação (mais) eficiente
   * pode considerar o uso dos métodos
   * {@link NavigableMap#ceilingEntry}
   * e {@link NavigableMap#higherEntry(Object)}.
   * </p>
   * 
   * @return Valor de avaliação (ver descrição acima).
   */
  @Override
  public Double evaluate(CellValues cv) {

    //Lista para guardar os doubles no range
    List<Double> e = new ArrayList<>();

    /*
      * Apesar da nota do professor sobre a utilização do NavigableMap#ceilingEntry,#hightEntry ,
      * foi utilizado o metodo subMap.entrySet para iterar as celulas no range.
     */
    for (Map.Entry<CellPosition, CellContent> i: cv.allCells().subMap(start, true, end, true) .entrySet()) {
      if(i.getKey().getRow() >= start.getRow() && i.getKey().getRow() <= end.getRow())
        if (i.getValue() instanceof NumberContent)
          e.add(((NumberContent) i.getValue()).evaluate(cv));
    }

    if (e.size() > 0)
      return op.evaluate(e);

    return Double.NaN;
  }
}
