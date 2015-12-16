package ycel.data;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Representação de uma folha de cálculo.
 *  
 * <p>
 * Internamente, a classe deve usar
 * dois objectos {@link TreeMap} para
 * mapear posições ({@link CellPosition})
 * em conteúdos ({@link CellContent}) 
 * ou estilos ({@link CellStyle}).
 * </p>
 * <p>
 * O documento não tem limitação ao número
 * de células a representar. Desse modo,
 * deve apenas guardar
 * as células com conteúdo ou estilo definido, i.e.,
 * entradas posição-contéudo 
 * em que o conteúdo difere de
 * {@link CUndefined#INSTANCE},
 * e entradas posição-estilo em que o estilo difere
 * de {@link CellStyle#DEFAULT_STYLE}.
 * </p>
 * 
 */
public final class Document implements CellValues {

  /** Mapa de posições em conteúdos. */
  private final TreeMap<CellPosition,CellContent> contentMap;
  
  /** Mapa de posições em estilos. */
  private final TreeMap<CellPosition,CellStyle> styleMap;
  
  /** 
   * Construtor.
   */
  public Document() {
    contentMap = new TreeMap<>();
    styleMap = new TreeMap<>();
  }
  /**
   * Obtém mapa definindo todas as células.
   * @return Mapa de posições em conteúdos.
   */
  @Override
  public NavigableMap<CellPosition, CellContent> 
  allCells() {
    return contentMap;
  }
  
  /**
   * Obtém mapa definindo todas os estilos.
   * @return Mapa de posições em estilos.
   */
  public NavigableMap<CellPosition, CellStyle> 
  allStyles() {
    return styleMap;
  }
  /** 
   * Obtém conteúdo da célula.
   * @param pos Posição
   * @return Valor da posição, se definida no documento, 
   * ou {@link CUndefined#INSTANCE} caso contrário.
   */
  @Override
  public CellContent getContent(CellPosition pos) {
    if (contentMap.containsKey(pos)){
    	return contentMap.get(pos);
    }
    return CUndefined.INSTANCE;
  }
  
  /**
   * Atribui conteúdo a uma célula.
   * 
   * <p>
   * Se o argumento <code>c</code> for {@link CUndefined#INSTANCE}
   * a entrada no mapa de conteúdos deve ser apagada, caso contrário
   * deverá ser criada ou actualizada uma entrada.
   * </p>
   * 
   * @param pos Posição
   * @param c Conteúdo.
   */
  @Override
  public void setContent(CellPosition pos, CellContent c) {
    if(c == CUndefined.INSTANCE){
    	contentMap.remove(c);
    }
    else{
    	
    }
  }
  
  
  /**
   * Obtém estilo da célula.
   * <p>
   * Deverá ser devolvido {@link CellStyle#DEFAULT_STYLE}
   * caso não tenha sido atribuido um estilo à célula.
   * </p>
   * @param pos Posição.
   * @return Um objecto {@link CellStyle}.
   */
  public CellStyle getStyle(CellPosition pos) {
    // TODO
    return CellStyle.DEFAULT_STYLE;
  }
  
  /**
   * Atribui estilo à célula.
   * <p>
   * Se o argumento <code>style</code> for {@link CellStyle#DEFAULT_STYLE}
   * a entrada no mapa de estilos deve ser apagada,
   * caso contrário
   * deverá ser criada ou actualizada uma entrada.
   * </p>
   * 
   * @param pos Posição.
   * @param style Estilo.
   */
  public void setStyle(CellPosition pos, CellStyle style) {
    // TODO
  }
}
