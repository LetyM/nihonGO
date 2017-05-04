package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Secretaria
 */

public class DatabaseHandler implements IDatabaseHandler {
    private Connection miconexion;
    private Statement stmt;
    
    public DatabaseHandler(){
     String url = "jdbc:mysql://127.0.0.1:3306/nihongo";
     String username = "root";
     String password = "lapeli";
     
     try  {
         miconexion = DriverManager.getConnection(url, username, password);
         stmt = miconexion.createStatement();
         //System.out.println(stmt.isClosed()); para ver si la conexion esta cerrada
         //System.out.println("Conexion realizada con exito"); para ver que ha entrado aquí
     } catch (SQLException e) {
         miconexion = null;
         System.out.println("Conexion nula");
     }
    }
   /**
   * Seleccionamos de la tabla de kanji bien el kunyomi->kanji, bien kanji->kunyomi
   * @param InKanji 
   * @return OutKanji
   */ 
    public String getkankun(String InKanji){
      String OutKanji;
      try{
          ResultSet rs = stmt.executeQuery("SELECT kunyomi FROM kanji WHERE japones = '"+InKanji+"'" ); 
          rs.next(); //Introducimos esto para que apunte bien, no lo estaba haciendo
          OutKanji = rs.getString(1);
      }
      catch(SQLException e){
        try{
              ResultSet rs = stmt.executeQuery("SELECT japones FROM kanji WHERE kunyomi = '"+InKanji+"'" ); 
              rs.next(); 
              OutKanji = rs.getString(1);
        }
        catch(SQLException ee){
              OutKanji = "Sin resultados";
        }
      }
     return OutKanji;
    }
    
   /**
   * Seleccionamos de la tabla de test las opciones y respuesta para una pregunta dada
   * @param InPregunta 
   * @return OutRespuesta, OutOpcion1, OutOpcion2
   */ 
    public String[] gettest(Integer InPregunta){
      String[] OutPregunta = new String[5];
      String OutConcepto = null;
      String OutRespuesta = null;
      String OutOpcion1 = null;
      String OutOpcion2 = null;
      try{
          ResultSet rs;
          rs = stmt.executeQuery("SELECT pregunta, respuesta, Opcion1, Opcion2 FROM test WHERE codigo = "+InPregunta+"" ); 
          rs.next(); //Introducimos esto para que apunte bien, no lo estaba haciendo
          OutConcepto = rs.getString(1);
          OutRespuesta = rs.getString(2);
          OutOpcion1 = rs.getString(3);
          OutOpcion2 = rs.getString(4);
      }
      catch(SQLException e){
          System.out.println("Error en la obtencion del dato");
      }
      OutPregunta[1] = OutConcepto;
      OutPregunta[2] = OutRespuesta;
      OutPregunta[3] = OutOpcion1;
      OutPregunta[4] = OutOpcion2;
     return OutPregunta;
    }
}
 


