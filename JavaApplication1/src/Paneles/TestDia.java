package Paneles;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class TestDia implements ITestDia {
    private List<Pregunta> originales;
    private List<Pregunta> falladas;
    
    //Constructora de la clase
    public TestDia(){
        originales = new LinkedList<Pregunta>();
        falladas = new LinkedList<Pregunta>();
    }
    
    //Introducimos la segunda lista en la primera y reinciamos la segunda
    public void change(){
        originales = falladas; //Ahora originales apunta a donde apuntaba falladas
        falladas = new LinkedList<Pregunta>(); //Reiniciamos la lista de falladas
    }
 
    //Añadimos pregunta a la lista de falladas
    public void addF(Pregunta pregunta){
        falladas.add(pregunta);
    }
 
    public void delete(Pregunta pregunta){
        originales.remove(originales.indexOf(pregunta));
    }
   
    public Pregunta getPregunta(int i){
        return originales.get(i);
    } 
    
    public boolean halfEmpty(){
        return originales.isEmpty();
    }
    
    public boolean Empty(){
        boolean rs;
        rs = originales.isEmpty() && falladas.isEmpty();
        return rs;
    }

    //Añade preguntas al Test del dia
    public void add(Pregunta pregunta){ 
        Date fechahoy = new Date();
        
        int dif = (int) ((fechahoy.getTime()-pregunta.getFecha().getTime())/86400000);

        if (pregunta.getNivel()<=0) {
            originales.add(pregunta);
        } 
        else{
            int aux = (int) Math.pow(2,pregunta.getNivel());
            if (dif>=aux){
                originales.add(pregunta);
            }       
        }
    }
    
    //Numero de preguntas disponibles
    public int getNumPreguntas(){
        return originales.size();
    }
}
