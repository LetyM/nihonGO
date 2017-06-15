package database;
import java.util.Date;
import java.util.Vector;


/**
 *
 * @author Secretaria
 */
public class TestDia {
    private Vector<Pregunta> preguntas;
    
//    Constructora de la clase
    public TestDia(){
        preguntas = new Vector<Pregunta>();
    }
    
    public Pregunta getPregunta(int i){
        return preguntas.get(i);
    }   
     
    public void insertarPregunta(Pregunta pregunta){ 
        Date fechahoy = new Date();
        
//        añadir la pregunta solo si cumple lo de fecha y nivel
        if (pregunta.getNivel()==0) {
            preguntas.add(pregunta);
        } 
        else{
            if (fechahoy==fechahoy){
                
            }
        }
    }
    
//    getNumpreguntas para ver el numero de preguntas que hay
    public int getNumPreguntas(){
        int num_preguntas;
        num_preguntas = preguntas.size();
        return num_preguntas;
    }
//    getN que recibe el int n y devuelve la pregunta n-esima 
//    Con esto podemos randomizar la pregunta
//    

}
