package database;

import Paneles.Traduccion;
import Paneles.TestDia;
import Paneles.Pregunta;
import Paneles.Palabra;
import Paneles.PreguntaTP;
import Paneles.TestP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Leticia
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

    public String[] getkanji(String InKanji){
      String[] OutKanji = new String[7];
      try{
          ResultSet rs = stmt.executeQuery("SELECT español, japones, kunyomi, onyomi, JLPT, KANKEN FROM kanji WHERE japones = '"+InKanji+"'" ); 
          rs.next(); //Introducimos esto para que apunte bien, no lo estaba haciendo
          for (int i=0; i<6; i++){
              OutKanji[i] = rs.getString(i+1);
          } 
      }
      catch(SQLException e1){
          for (int i=0; i<6; i++){
              OutKanji[i] = "Sin resultado";
          }
      }
      return OutKanji;
    }
    
    public String[] getAPalab(String InPalab){
      String[] OutPalab = new String[3];
      try{
          ResultSet rs = stmt.executeQuery("SELECT español, japones FROM palabra WHERE clave = '"+InPalab+"'" ); 
          rs.next(); 
          for (int i=0; i<2; i++){
              OutPalab[i] = rs.getString(i+1);
          } 
      }
      catch(SQLException e1){
          
      }
      return OutPalab;
    }
    
    public Traduccion getPalab(String InPalab){
        String japones, español, JLPT;
        Palabra pl;
        Traduccion tr = new Traduccion();
        
        try{
            ResultSet rs;
            rs =stmt.executeQuery("SELECT japones, JLPT FROM palabra WHERE español = '"+InPalab+"'");
            while (rs.next()){
                japones = rs.getString("japones");
                JLPT = rs.getString("JLPT");
                pl = new Palabra(japones, JLPT);
                tr.add(pl);
            }
            rs =stmt.executeQuery("SELECT español, JLPT FROM palabra WHERE japones = '"+InPalab+"'");
                while (rs.next()){
                    español = rs.getString("español");
                    JLPT = rs.getString("JLPT");
                    pl = new Palabra(español, JLPT);
                    tr.add(pl);
                }
        }
        catch(SQLException e){
            
        }   
        return tr;
    }
    
    public TestP getPreguntasTP(String nombre){
        TestP test = new TestP();
        String enunciado, respuesta;
        int codigo, tipo;
        PreguntaTP pregunta;       
        
        try{
            ResultSet rs;
            rs =stmt.executeQuery("SELECT codigo, tipo, enunciado, respuesta FROM testp WHERE nombre = '"+nombre+"'");
            while (rs.next()){
                codigo = rs.getInt("codigo");
                tipo = rs.getInt("tipo");
                enunciado = rs.getString("enunciado");
                respuesta = rs.getString("respuesta");
                pregunta = new PreguntaTP(codigo, tipo, enunciado, respuesta);
                test.add(pregunta);
            }        
        }
        catch(SQLException e){
            
        }
        return test;
    }
    
    public TestDia getPreguntas(){
        TestDia testdia = new TestDia();
        String enunciado, respuesta, opcion1, opcion2;
        int codigo, nivel;
        Date fecha;
        Pregunta pregunta;       
        
        try{
            ResultSet rs;
            rs =stmt.executeQuery("SELECT * FROM testd");
            while (rs.next()){
                codigo = rs.getInt("codigo");
                enunciado = rs.getString("enunciado");
                respuesta = rs.getString("respuesta");
                opcion1 = rs.getString("opcion1");
                opcion2 = rs.getString("opcion2");
                nivel = rs.getInt("nivel");
                fecha = rs.getDate("fecha");
                pregunta = new Pregunta(codigo, enunciado, respuesta, opcion1, opcion2, nivel, fecha);
                testdia.add(pregunta);
            }        
        }
        catch(SQLException e){
            
        }
        return testdia;
    }
    

    public void setNivel(int codigo, int nivel){
        
        try{
            stmt.executeUpdate("UPDATE testd SET nivel="+nivel+" WHERE codigo="+codigo);
        }
 
        catch(SQLException e){
            System.out.println("No se actualizo el nivel");
        }  
    }
    
    public void setFecha(int codigo, Date fecha){ 
        Format formato = new SimpleDateFormat("yyyy-MM-dd");
        String fstring;
        fstring = formato.format(fecha);

        try{
            stmt.executeUpdate("UPDATE testd SET fecha='"+fstring+"' WHERE codigo="+codigo);
        }

        catch(SQLException e){
            System.out.println("No se actualizo la fecha");
        }  
    }
    
    public List<String> getAllKanji(){
        List<String> kanji = new LinkedList<String>();
        String k;
        
        try{
            ResultSet rs;
            rs = stmt.executeQuery("SELECT japones FROM kanji");
            while (rs.next()){
                k = rs.getString("japones");
               kanji.add(k);    
            }
        }
        catch(SQLException e){
            
        }
        return kanji;
    }
    
    public List<String> getAllPalab(){
        List<String> palab = new LinkedList<String>();
        String p;
        
        try{
            ResultSet rs;
            rs = stmt.executeQuery("SELECT clave FROM palabra ORDER BY clave");
            while (rs.next()){
                p = rs.getString("clave");
               palab.add(p);    
            }
        }
        catch(SQLException e){
            
        }
        return palab;
    }
    
    public List<String> getAllTestP(){
        List<String> test = new LinkedList<String>();
        String t;
        
        try{
            ResultSet rs;
            rs = stmt.executeQuery("SELECT DISTINCT nombre FROM testp");
            while (rs.next()){
                t = rs.getString("nombre");
                test.add(t);    
            }
        }
        catch(SQLException e){
            
        }
        return test;
    }
    
    //Obtenemos todos los enunciados de las preguntas del test personalizado seleccionado
    public String[] getAllEnun(int n, String nombre){
        String[] enunciados = new String[n];
        int i;
        
        i=0;
        try{
            ResultSet rs;
            rs = stmt.executeQuery("SELECT enunciado FROM testp WHERE nombre = '"+nombre+"' ORDER BY codigo");
            while (rs.next()){
                enunciados[i] = rs.getString("enunciado");
                i++;
            }   
        }
        catch(SQLException e){
        }
        return enunciados;
    }
    
    //Obtenemos todos las respuestas de las preguntas del test personalizado seleccionado
    public String[] getAllRes(int n, String nombre){
        String[] respuestas = new String[n];
        int i;
        
        i=0;
        try{
            ResultSet rs;
            rs = stmt.executeQuery("SELECT respuesta FROM testp WHERE nombre = '"+nombre+"' ORDER BY codigo");
            while (rs.next()){
                respuestas[i] = rs.getString("respuesta");
                i++;
            }   
        }
        catch(SQLException e){
        }
        return respuestas;
    }
    
    //Obtenemos las opciones de respuestas para la pregunta del test personalizado seleccionado
    public String[] getAllOp(int n, int tipo, String res){
        String[] opciones = new String[n];
        int i;
        
        i=0;
        if (tipo==0){
            try{
                ResultSet rs;
                rs = stmt.executeQuery("SELECT kunyomi FROM kanji WHERE kunyomi <> '"+res+"'");
                while (rs.next()){
                    opciones[i] = rs.getString("kunyomi");
                    i++;
                }   
            }
            catch(SQLException e){
            }
        }
        if (tipo==31){
            try{
                ResultSet rs;
                rs = stmt.executeQuery("SELECT japones FROM palabra WHERE japones <> '"+res+"'");
                while (rs.next()){
                    opciones[i] = rs.getString("japones");
                    i++;
                }   
            }
            catch(SQLException e){
            }
        }
        if (tipo==32){
            try{
                ResultSet rs;
                rs = stmt.executeQuery("SELECT español FROM palabra WHERE español <> '"+res+"'");
                while (rs.next()){
                    opciones[i] = rs.getString("español");
                    i++;
                }   
            }
            catch(SQLException e){
            }
        }
        return opciones;
    }
    
    //Obtenemos el ultimo registro de kanji
    public int ordkanji(){
        int i;
        i = 0;
        try{
            ResultSet rs;
            rs =stmt.executeQuery("SELECT codigo FROM kanji ORDER BY codigo DESC LIMIT 1");
            rs.next();
            i = rs.getInt("codigo");
        }
        catch(SQLException e){
            
        }
      return i;
    }
    
    //Obtenemos el ultimo registro de palabra
    public int ordpalab(){
        int i;
        i = 0;
        try{
            ResultSet rs;
            rs =stmt.executeQuery("SELECT codigo FROM palabra ORDER BY codigo DESC LIMIT 1");
            rs.next();
            i = rs.getInt("codigo");
        }
        catch(SQLException e){
            
        }
      return i;
    }
    
    //Obtenemos el ultimo registro del Test Personalizado
    public int ordTP(){
        int i;
       i = 0;
        try{
            ResultSet rs;
            rs =stmt.executeQuery("SELECT codigo FROM testp ORDER BY codigo DESC LIMIT 1");
            rs.next();
            i = rs.getInt("codigo");
        }
        catch(SQLException e){
            
        }
      return i;
    }
    
    //Obtenemos el total de enunciados de un test concreto
    public int ordTC(String nombre){
        int i;
        i = 0;
        try{
            ResultSet rs;
            rs = stmt.executeQuery("SELECT count(*) AS i FROM testp WHERE nombre ='"+nombre+"'");
            rs.next();
            i = rs.getInt("i");
        }
        catch(SQLException e){
            
        }
        return i;
    }
    
    //Añadimos una pregunta a la tabla testp del tipo "Lectura de"
    public void addTPkanji(int ord, String nombre, String kanji, String respuesta){
        try{
            stmt.executeUpdate("INSERT INTO testP VALUES ("+ord+",'"+nombre+"',0,'Lectura de "+kanji+"','"+respuesta+"');");
        }
        catch (SQLException e){
            
        }
    }
    
    //Añadimos una pregunta a la tabla testp del tipo de traduccion
    public void addTPpalab(int ord, int tipo, String nombre, String palab, String respuesta){
        if (tipo==1){
            try{
                stmt.executeUpdate("INSERT INTO testP VALUES ("+ord+",'"+nombre+"',31,'"+palab+"','"+respuesta+"');");
            }
            catch (SQLException e){

            }  
        }
        if (tipo==2){
            try{
                stmt.executeUpdate("INSERT INTO testP VALUES ("+ord+",'"+nombre+"',32,'"+palab+"','"+respuesta+"');");
            }
            catch (SQLException e){

            }  
        }
    }
    
    //Borramos el test seleccionado
    public void borrarTest(String nombre){
        try{
            stmt.executeUpdate("DELETE FROM testp WHERE nombre ='"+nombre+"'");
        }
        catch (SQLException e){
            
        }
    }
}
 


