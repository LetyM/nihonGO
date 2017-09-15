package Paneles;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class TestP implements ITestP {
    private List<PreguntaTP> originales;
    private List<PreguntaTP> falladas;
    
    //Constructora de la clase
    public TestP(){
        originales = new LinkedList<PreguntaTP>();
        falladas = new LinkedList<PreguntaTP>();
    }
    
    //Introducimos la segunda lista en la primera y reinciamos la segunda
    public void change(){
        originales = falladas; //Ahora originales apunta a donde apuntaba falladas
        falladas = new LinkedList<PreguntaTP>(); //Reiniciamos la lista de falladas
    }
 
    //Añadimos pregunta a la lista de falladas
    public void addF(PreguntaTP pregunta){
        falladas.add(pregunta);
    }
 
    public void delete(PreguntaTP pregunta){
        originales.remove(originales.indexOf(pregunta));
    }
   
    public PreguntaTP getPregunta(int i){
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

    //Añade preguntas al Test Personalizado
    public void add(PreguntaTP pregunta){
        originales.add(pregunta);
    }
    
    //Numero de preguntas disponibles
    public int getNumPreguntas(){
        return originales.size();
    }
}
