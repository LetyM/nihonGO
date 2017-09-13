package Paneles;

/**
 *
 * @author Leticia
 */
public class PreguntaTP {

   private int codigo;
   private int tipo;
   private String enunciado;
   private String respuesta;
   
   //Constructora de la clase
   public PreguntaTP (int cod, int tip, String enun, String res){ 
       codigo = cod;
       tipo = tip;
       enunciado = enun;
       respuesta = res;
    }
   
   public int getCod(){
       return this.codigo;
   }
   
   public int getTipo(){
       return this.tipo;
   }
   
   public String getEnun(){
       return this.enunciado;
   }
   
   public String getRes(){
       return this.respuesta;
   }
}
