package Paneles;

/*Primer intento de boton. BORRABLE*/

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 *  Boton sencillo 
 * @author Secretaria
 */

public class EasyButton extends Application
{
    public static void main(String[] args)
    {
        System.out.println("Ejecutando Menu Buscar");
        launch(args);
        System.out.println("Cerrado Menu Buscar");
    }

    Button btnBuscar;
    
    //Creamos el metodo start
    //Aqui creamos los elementos de la interfaz que interactua con el usuario
    @Override public void start(Stage primaryStage)
    {
        //Creamos el botón
        btnBuscar = new Button("Buscar");
        //btnBuscar.setText("Buscar"); esto haria falta si no lo hubiesemos definido arriba
        btnBuscar.setOnAction(e -> buttonClicked()); //Dirigimos a lo que hace el boton al ser pulsado

        //Añadimos el botón a un layout pane (posicion relativa entre botones/control)
        BorderPane paneBuscar = new BorderPane();
        paneBuscar.setCenter(btnBuscar);

        //Añadimos el layoutpane a un scene
        Scene sceneBuscar = new Scene(paneBuscar);//, 300, 250);

        //Finalizamos y mostramos
        primaryStage.setTitle("Buscar kunyomi");
        primaryStage.setScene(sceneBuscar);
        primaryStage.show();
    }
    
    //Creamos la funcion para cuando pulsan el botón
    public void buttonClicked()
    {
        if (btnBuscar.getText() == "Buscar")
        {
        btnBuscar.setText("Buscando...");
        }
        else
        {
         btnBuscar.setText("Buscar");
        }
    }
}