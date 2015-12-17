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
      double avg = 0.0;
      for(Double i : list) {
        avg = avg + i;
      }
      return avg/list.size();
    } 
  },
  /** Mínimo. */
  MIN {
    @Override
    double evaluate(List<Double> list) {
      double min = list.get(0);
      for (int i = 0; i < list.size()-1; i++) {
        min = Math.min(min,list.get(i+1));
      }
      return min;
    } 
  }, 
  /** Soma. */
  SUM {
    @Override
    double evaluate(List<Double> list) {
      double soma = 0.0;
      for(Double i : list) {
        soma = soma + i;
      }
      return soma;
    } 
  }, 
  /** Máximo */
  MAX {
    @Override
    double evaluate(List<Double> list) {
      double max = list.get(0);
      for (int i = 0; i < list.size()-1; i++) {
        max = Math.max(max,list.get(i+1));
      }
      return max;
    } 
  };

  /**
   * Avalia lista de valores.
   * @param list Lista de valores. 
   * @return Resultado da avaliar a lista.
   */
  abstract double evaluate(List<Double> list);
}
