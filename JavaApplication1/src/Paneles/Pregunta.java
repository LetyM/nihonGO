package Paneles;
import java.util.Date;

/**
 *
 * @author Leticia
 */
public class Pregunta  {

   private int codigo;
   private String enunciado;
   private String respuesta;
   private String opcion1;
   private String opcion2;
   private int nivel;
   private Date fecha;
   
   //Constructora de la clase
   public Pregunta (int cod, String enun, String res, String op1, String op2, int niv, Date fech){ 
       codigo = cod;
       enunciado = enun;
       respuesta = res;
       opcion1 = op1;
       opcion2 = op2;
       nivel = niv;
       fecha = fech;
    }
   
   public int getCod(){
       return this.codigo;
   }
   
   public String getEnun(){
       return this.enunciado;
   }
   
   public String[] getRes(){
       String[] opciones = new String[3];
       opciones[0]=this.respuesta;
       opciones[1]=this.opcion1;
       opciones[2]=this.opcion2;
       return opciones;
   }
   
   public int getNivel(){
       return this.nivel;
   }
   
   public Date getFecha(){
       return this.fecha;
   }
}

