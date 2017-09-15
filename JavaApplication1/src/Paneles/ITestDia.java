/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Paneles.Pregunta;

/**
 *
 * @author Secretaria
 */
public interface ITestDia {
    public void change();
    public void addF(Pregunta pregunta);
    public void add(Pregunta pregunta);
    public void delete(Pregunta pregunta);
    public Pregunta getPregunta(int i);
    public boolean halfEmpty();
    public boolean Empty();
    public int getNumPreguntas();
}
