/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Paneles.Palabra;
import java.util.Vector;

/**
 *
 * @author Secretaria
 */
public class Traduccion implements ITraduccion{
    private Vector<Palabra> traducc;
   
    public Traduccion(){
        traducc = new Vector<Palabra>();
    }
    
    public void add(Palabra palabra){
        traducc.add(palabra);
    }
    
    public Palabra getTraducc(int i){
        return traducc.get(i);
    }
    
    public int getNumPalabras(){
        int num_preguntas;
        num_preguntas = traducc.size();
        return num_preguntas;
    }
}
