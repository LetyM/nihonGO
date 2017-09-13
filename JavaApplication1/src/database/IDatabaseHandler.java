package database;

import Paneles.Traduccion;
import Paneles.TestDia;
import Paneles.TestP;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Interfaz para el DatabaseHandler
 * @author Leticia
 */

public interface IDatabaseHandler {
   public Traduccion getPalab(String InVocab);
   public String[] getkanji(String cod); 
   public String[] getAPalab(String InPalab);
   public TestP getPreguntasTP(String nombre);
   public TestDia getPreguntas();
   public void setNivel(int codigo, int nivel);
   public void setFecha(int codigo, Date fecha);
   public List<String> getAllKanji();
   public List<String> getAllPalab();
   public List<String> getAllTestP();
   public String[] getAllEnun(int n, String nombre);
   public String[] getAllRes(int n, String nombre);
   public String[] getAllOp(int n, int tipo, String res);
   public int ordkanji();
   public int ordpalab();
   public int ordTP();
   public int ordTC(String nombre);
   public void addTPkanji(int ord, String nombre, String kanji, String respuesta);
   public void addTPpalab(int ord, int tipo, String nombre, String palab, String respuesta);
   public void borrarTest(String nombre);
}
