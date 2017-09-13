/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Paneles.Palabra;

/**
 *
 * @author Secretaria
 */
public interface ITraduccion {
    public void add(Palabra palabra);
    public Palabra getTraducc(int i);
    public int getNumPalabras();
}
