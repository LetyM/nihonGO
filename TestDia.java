/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.*;

/**
 *
 * @author Secretaria
 */
public class TestDia {
    private List<Pregunta> preguntas;
    
//    Constructora de la clase
    public TestDia(){
        preguntas = new Vector<Pregunta>();
    }
    
    public void insertarPregunta(Pregunta pregunta){
//        añadir la pregunta solo si cumple lo de fecha y nivel
        preguntas.add(pregunta);
    }
//    getNumpreguntas para ver el numero de preguntas que hay
//    getN que recibe el int n y devuelve la pregunta n-esima 
//    Con esto podemos randomizar la pregunta
//    


}
