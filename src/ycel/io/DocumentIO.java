package ycel.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


import ycel.data.Document;

/**
 * Classe utilitária para input/output.
 *
 */
public final class DocumentIO {

  /**
   * Carrega documento a partir de ficheiro.
   * 
   * <p>
   * <b>Nota</b>: use {@link CellParser#parsePosition(String)}
   * para ler posições, {@link CellParser#parseContent(Scanner)}
   * para ler conteúdos, e {@link CellParser#parseStyle(String)}
   * para ler estilos.
   * </p>
   * @param f Ficheiro.
   * @return Documento lido.
   * @throws FileNotFoundException se ficheiro não existe.
   * @throws YCelIOException se houver um erro na leitura do ficheiro.
   */
  public static Document load(File f) 
      throws FileNotFoundException, YCelIOException {
    Scanner in = new Scanner(f);
    try {
      Document doc = new Document();
      // TODO
      return doc;
    } 
    catch (Throwable e) {
      e.printStackTrace(System.err);
      throw new YCelIOException("File load error", e);
    }
    finally {
      in.close();
    }

  }

  /**
   * Grava documento em ficheiro.
   * @param f Ficheiro.
   * @param doc Documento
   * @throws FileNotFoundException se directório do ficheiro não existe.
   */
  public static void save(File f, Document doc) throws FileNotFoundException {
    PrintStream out = new PrintStream(f);
    try { 
      // TODO
    }
    catch (Throwable e) {
      e.printStackTrace(System.err);
      throw new YCelIOException("File save error", e);
    }
    finally {
      out.close();
    }
  }

  /**
   * Construtor privado, para inibir instanciação
   * (conforme padrão "utility class").
   */
  private DocumentIO() {

  }
}
