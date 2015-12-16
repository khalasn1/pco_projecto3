package ycel.data;


/**
 * Operação binária.
 * 
 * <p>
 * A operação binária é construída a partir
 * de um operador binário do tipo {@link CBinaryOperator})
 * e dois operandos {@link NumberContent}.
 * Note-se que a definição pode ser recursiva, 
 * Por exemplo numa folha de cálculo
 * <code>+ #A1 / #A2 3 </code>
 * irá representar a soma
 * do valor da célula <code>A1</code>
 * com o a divisão do valor de <code>A2</code> 
 * por <code>3</code>.
 * </p>
 *
 */
public final class CBinaryOperation implements NumberContent {
  private CBinaryOperator op;
  private NumberContent left;
  private NumberContent right;

  /**
   * Construtor.
   * @param op Operador.
   * @param left Primeiro operando (esquerdo).
   * @param right Segundo operando (direito).
   */
  public CBinaryOperation(CBinaryOperator op, 
      NumberContent left,
      NumberContent right) {
    
	  this.op = op;
	  this.left = left;
	  this.right = right;
  }

  /**
   * Obtém fórmula.
   * <p>
   * A string retornada deve ter a forma
   * <code>
   * op s1 s2
   * </code>
   * onde <code>op</code> é a representação
   * em string do operador, e <code>s1</code>
   * e <code>s2</code> são as fórmulas
   * dos operandos, ex. 
   * <code>+ 1 2</code>
   * se o operador é {@link CBinaryOperator#ADD}
   * e os operandos são objectos {@link CNumber}
   * representando <code>1</code> e <code>2</code>.
   * </p>
   *
   */
  @Override
  public String formula() {
    return ""; // TODO
  }

  /**
   * Avalia contéudo.
   * 
   * <p>O resultado deverá resultar 
   * de chamar {@link CBinaryOperator#evaluate}
   * para o operador passando como argumentos
   * dois valores que resultam de avaliar 
   * previamente os operandos.
   * </p>
   * 
   * @return Valor nas condições descritas acima.
   */
  @Override
  public Double evaluate(CellValues cv) {
    return 0.0; // TODO
  }

}
