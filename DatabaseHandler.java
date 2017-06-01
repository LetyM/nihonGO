package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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
      String[] OutPregunta = new String[4];
      try{
          ResultSet rs;
          rs = stmt.executeQuery("SELECT pregunta, respuesta, Opcion1, Opcion2 FROM test WHERE codigo = "+InPregunta+"" ); 
          rs.next(); //Introducimos esto para que apunte bien, no lo estaba haciendo
          for (int i=0; i<4; i++){
            OutPregunta[i] = rs.getString(i+1);
          }
      }
      catch(SQLException e){
          System.out.println("Error en la obtencion del dato");
      }
     return OutPregunta;
    }
    
   /**
   * Seleccionamos de la tabla de test los registros con nivel 0
   * @param  
   * @return 
   */ 
    public TestDia getPreguntas(){
        TestDia testdia = new TestDia();
        String enunciado, respuesta, opcion1, opcion2;
        int codigo, nivel;
        Date fecha;
        Pregunta pregunta;       
        
        try{
            ResultSet rs;
            rs =stmt.executeQuery("SELECT * FROM test");
            while (rs.next()){
                codigo = rs.getInt("codigo");
                enunciado = rs.getString(2);
                respuesta = rs.getString(3);
                opcion1 = rs.getString(4);
                opcion2 = rs.getString(5);
                nivel = rs.getInt("nivel");
                fecha = rs.getDate("fecha");
                pregunta = new Pregunta(codigo, enunciado, respuesta, opcion1, opcion2, nivel, fecha);
                testdia.insertarPregunta(pregunta);
            }        
        }
        catch(SQLException e){
            
        }
        return testdia;
    }
}
 

