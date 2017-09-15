/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

/**
 *
 * @author Secretaria
 */
public class Palabra{
    private String palabra;
    private String JLPT;
    
    public Palabra(String plb, String JL){
        palabra = plb;
        JLPT = JL;
    }
    
    public String getPal(){
        return this.palabra;
    }
        
        
}
