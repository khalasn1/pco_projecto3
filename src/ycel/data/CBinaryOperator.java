package ycel.data;

/**
 * Operador binário, usado em conjunção com {@link CBinaryOperation}.
 *
 * <p>Cada elemento da enumeração deve implementar
 * o método {@link #evaluate(double, double)}.
 * </p>
 */
public enum CBinaryOperator {
  /** Soma. */
  ADD("+") {
    @Override
    double evaluate(double a, double b) {
      return 0.0; // TODO
    } 
  },
  /** Subtracção. */
  SUB("-") {
    @Override
    double evaluate(double a, double b) {
      return 0.0; // TODO
    }
  },
  /** Multiplicação. */
  MUL("*") {
    @Override
    double evaluate(double a, double b) {
      return 0.0; // TODO
    }
  },  
  /** Divisão. */
  DIV("/") {
    @Override
    double evaluate(double a, double b) {
      return 0.0; // TODO
    }
  },
  /** Mínimo. */ 
  MIN("min"){
    @Override
    double evaluate(double a, double b) {
      return 0.0; // TODO
    }
  }, 
  /** Máximo */
  MAX("max") {
    @Override
    double evaluate(double a, double b) {
      return 0.0; // TODO
    }
  };
  
  /** Representação em forma de string. */
  private final String asString;
  
  /**
   * Construtor.
   * @param asString Representação em string.
   */
  private CBinaryOperator(String asString) {
    this.asString = asString;
  }
  
  /** 
   * Obtém representação na forma de string.
   * @return String a codificar o operador.
   */
  @Override 
  public String toString() {
    return asString;
  }
  
  /**
   * Avalia o operador.
   * @param a Primeiro valor.
   * @param b Segundo valor.
   * @return Resultado de avaliação.
   */
  abstract double evaluate(double a, double b);

 
}
