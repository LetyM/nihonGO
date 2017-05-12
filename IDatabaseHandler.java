package database;

/**
 * Interfaz para el DatabaseHandler
 * @author Secretaria
 */
public interface IDatabaseHandler {
    /*kunyomi->kanji  kanji->kunyomi*/
   public String getkankun(String InKanji); 
   public String[] gettest(Integer InPregunta);
}
