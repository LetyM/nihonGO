/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.util.Date;

/**
 *
 * @author Secretaria
 */
public class Pregunta {
//  	codigo INTEGER(20) NOT NULL PRIMARY KEY,
//	pregunta VARCHAR(30) NOT NULL,
//    respuesta VARCHAR(10) NOT NULL,
//    opcion1 VARCHAR(10) NOT NULL,
//    opcion2 VARCHAR(10) NOT NULL,
//    nivel INTEGER(10), #nivel adquirido por el usuario
//    fecha DATETIME  
   private int codigo;
   private String pregunta;
   private String respuesta;
   private String opcion1;
   private String opcion2;
   private int nivel;
   private Date fecha;
   
   public Pregunta (int cod, String pre, String res, String op1, String op2, int niv, Date fech){ 
       codigo = cod;
       pregunta = pre;
       respuesta = res;
       opcion1 = op1;
       opcion2 = op2;
       nivel = niv;
       fecha = fech;
 }
}

