package ycel.data;

import java.util.List;

/**
 * Enumeração de operadores de avaliação para 
 * {@link CRangeOperation}.
 *  
 * <p>
 * Cada elemento na enumeração define 
 * uma implementação do método {@link #evaluate(List) double evaluate(List&lt;Double&gt;)}
 * que toma como argumento uma lista de valores e devolve
 * um resultado.
 * </p>
 *
 */
public enum CRangeOperator {
  /** Média */
  AVG {
    @Override
    double evaluate(List<Double> list) {
      // TODO
      return 0;
    } 
  },
  /** Mínimo. */
  MIN {
    @Override
    double evaluate(List<Double> list) {
      // TODO
      return 0;
    } 
  }, 
  /** Soma. */
  SUM {
    @Override
    double evaluate(List<Double> list) {
      // TODO
      return 0;
    } 
  }, 
  /** Máximo */
  MAX {
    @Override
    double evaluate(List<Double> list) {
      // TODO
      return 0;
    } 
  };

  /**
   * Avalia lista de valores.
   * @param list Lista de valores. 
   * @return Resultado da avaliar a lista.
   */
  abstract double evaluate(List<Double> list);
}
