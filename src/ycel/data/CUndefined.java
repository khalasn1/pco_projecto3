package ycel.data;


/**
 * Conteúdo não definido (NÃO MODIFICAR).
 * 
 * <p>A classe é "singleton", i.e., 
 * está apenas definida uma instância
 * acessível via {@link #INSTANCE}.
 * </p>
 *
 */
public final class CUndefined implements CellContent {

  /**
   * Instância única ("singleton").
   */
  public static final 
  CUndefined INSTANCE = new CUndefined();
  
  /**
   * Construtor privado para habilitar
   * padrão "singleton".
   */
  private CUndefined() {
    
  }
  
  /**
   * Obtém fórmula.
   * @return String vazia.
   */
  @Override
  public String formula() {
    return "";
  }

  /**
   * Avalia conteúdo.
   * @return String vazia.
   */
  @Override
  public String evaluate(CellValues cells) {
    return "";
  }

}
