package ycel.io;

/**
 * Excepção lançada no caso de erro 
 * de leitura ou escrita no ficheiro.
 */
@SuppressWarnings("serial")
public final class YCelIOException extends RuntimeException {
  /**
   * Construtor.
   * @param message Mensagem de erro.
   * @param cause Excepção original associada ao erro.
   */
  public YCelIOException(String message, Throwable cause) {
    super(message + " : " + cause.getClass().getSimpleName(), cause);
  }
}
