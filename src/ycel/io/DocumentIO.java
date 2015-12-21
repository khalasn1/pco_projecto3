package ycel.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Map.Entry;


import ycel.data.CellContent;
import ycel.data.CellPosition;
import ycel.data.CellStyle;
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
      int numbercells = in.nextInt();

      for (int i = 0; i < numbercells; i++)
        doc.setContent(CellParser.parsePosition(in.next()),CellParser.parseContent(in));

      int style = in.nextInt();

      for (int i = 0; i < style; i++)
        doc.setStyle(CellParser.parsePosition(in.next()), CellParser.parseStyle(in.next()));

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

      // imprime tamanho das celulas
      out.append("" + doc.allCells().size()).append("\n");

      // itera varias Entries das celulas e imprime.
      for (Entry<CellPosition, CellContent> e : doc.allCells().entrySet()) {
        out.append(e.getKey().toString())
                .append(" ")
                .append(e.getValue().formula())
                .append("\n");
      }

      // imprime tamanho do estilo
      out.append("" + doc.allStyles().size()).append("\n");

      // itera varias Entries dos estilos e imprime.
      for (Entry<CellPosition, CellStyle> e : doc.allStyles().entrySet()) {
        out.append(e.getKey().toString())
                .append(" ")
                .append(e.getValue().toString())
                .append("\n");
      }

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
