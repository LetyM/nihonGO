package Paneles;

import database.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * 
 * @author Secretaria
 */
 
public class Menu extends Application {

    //Enlace con la base de datos
    private IDatabaseHandler dbh;
    
    //Botones de cambio de ventana
    private Button btnMTest;
    private Button btnMBusqueda;
    private Button btnMPrincipal;
    private Button btnMTest1;
    private Button btnMTest2;
    private Button btnMLeccion;
    private Button btnMLeccion1;
    
    //Elementos de la ventana de Leccion1
    
    //Elementos de la ventada de Test1
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    private Button btnSiguiente;
    
    //Elementos de la ventana de Test2
    private TextField txtRespuesta;
    
    //Elementos de la ventana de Busqueda
    private Button btnBuscar;
    private TextField txtKanjiEntrada;
    private TextField txtKanjiSalida;
    
   //El Stage y las distintas Scenes
    private Stage stage;
    private Scene sceneMP;      //Menu Principal
    private Scene sceneBuscar;  //Pantalla de Busqueda
    private Scene sceneLeccion; //Pantalla Leccion
    private Scene sceneTest;   //Pantalla Test
    private Scene sceneTest1;  //Pantalla Test1
    private Scene sceneTest2;  //Pantalla Test2
    
    //Creamos el metodo start
    //Aqui creamos los elementos de la interfaz que interactua con el usuario
    @Override public void start(Stage primaryStage)
    {
        stage = primaryStage;
        
        //Creamos el DatebaseHandler
        dbh = new DatabaseHandler();
        
        //********************** MENU PRINCIPAL ***********************************************
        //BOTONES
        btnMBusqueda = new Button("Diccionario");
        btnMBusqueda.setOnAction(e -> SwitchBusquedaKanji());
        btnMTest = new Button("Test");
        btnMTest.setOnAction(e -> SwitchTest());
        btnMLeccion = new Button("Lecciones");
        btnMLeccion.setOnAction(e-> SwitchLeccion());
        
        //Creamos el panel del botón
        HBox paneMenuBuscar = new HBox(20, btnMBusqueda,btnMTest,btnMLeccion);
        paneMenuBuscar.setPadding(new Insets(10));
        paneMenuBuscar.setAlignment(Pos.BOTTOM_CENTER);

        //Añadimos paneMenuBuscar VBox
        VBox paneMP = new VBox(10,paneMenuBuscar);
        
        //Finalizamos y mostramos
        sceneMP = new  Scene(paneMP,200,300);
        primaryStage.setTitle("NihonGO");
        primaryStage.setScene(sceneMP);
        primaryStage.show();
        
        //********************** MENU DICCIONARIO ***********************************************
        //Creamos el Label para el Kanji
        Label lblKanji = new Label("Kanji");
        lblKanji.setMinWidth(10);
        lblKanji.setAlignment(Pos.BOTTOM_CENTER);
        
        //Creamos el campo en el que se escribe la entrada
        txtKanjiEntrada = new TextField();
        txtKanjiEntrada.setMinWidth(50);
        txtKanjiEntrada.setMaxWidth(50);
        //txtKanjiEntrada.setPromptText("Introduzca codigo del kanji");
        
        //Creamos el campo en el que se devuelve el resultado
        txtKanjiSalida = new TextField();
        txtKanjiSalida.setMinWidth(100);
        txtKanjiSalida.setMaxWidth(100);
        txtKanjiSalida.setEditable(false);
        
        //Creamos el botón de Busqueda
        btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> ClickedBuscar()); 
  
        //Creamos el botón para volver al Menú Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
        
        //Creamos el panel del txtKanji
        HBox paneKanji = new HBox(20,lblKanji,txtKanjiEntrada,txtKanjiSalida);
        paneKanji.setPadding(new Insets(10));
        
        //Creamos el panel de los botones
        HBox paneBuscar = new HBox(20, btnBuscar, btnMPrincipal);
        paneBuscar.setPadding(new Insets(10));
        paneBuscar.setAlignment(Pos.BOTTOM_CENTER);

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMBuscar = new VBox(10,paneKanji, paneBuscar);
        
        //Definimos el Scene
        sceneBuscar = new  Scene(paneMBuscar,200,300);
        
         //********************** MENU LECCION ***********************************************       
        //Creamos el botón de Leccion1
        btnMLeccion1 = new Button("Leccion1");
        //btnMLeccion1.setOnAction(e -> SwitchLeccion1()); 
        
        //Creamos el botón de Test2
        //btnMTest2 = new Button("Test2");
        //btnMTest2.setOnAction(e-> SwitchTest2());
        
        //Creamos el botón para volver al Menú Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
         
        //Creamos el panel de los botones
        HBox paneLeccion = new HBox(20, btnMLeccion1, btnMPrincipal);
        paneLeccion.setPadding(new Insets(10));
        paneLeccion.setAlignment(Pos.BOTTOM_CENTER);

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMLeccion = new VBox(10,paneLeccion);
        
        //Definimos el Scene
        sceneLeccion = new  Scene(paneMLeccion,200,300);       
        
        
        //********************** MENU TEST ***********************************************       
        //Creamos el botón de Test1
        btnMTest1 = new Button("Test1");
        btnMTest1.setOnAction(e -> SwitchTest1()); 
        
        //Creamos el botón de Test2
        btnMTest2 = new Button("Test2");
        btnMTest2.setOnAction(e-> SwitchTest2());
        
        //Creamos el botón para volver al Menú Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
         
        //Creamos el panel de los botones
        HBox paneTest = new HBox(20, btnMTest1, btnMTest2, btnMPrincipal);
        paneTest.setPadding(new Insets(10));
        paneTest.setAlignment(Pos.BOTTOM_CENTER);

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMTest = new VBox(10,paneTest);
        
        //Definimos el Scene
        sceneTest = new  Scene(paneMTest,200,300);
        
        //********************** MENU TEST 1 ***********************************************       
        //Creamos el botón para volver al Menú Principal
        //btnMPrincipal = new Button("Menu Principal");
        //btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
        
        //Creamos el boton para volver a la Pantalla de los Test
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchTest());
        
        //Creamos el boton para pasar a la siguiente pregunta
        btnSiguiente = new Button("Aceptar");
        btnSiguiente.setOnAction(e-> SwitchSiguiente());
        
        String[] res = dbh.gettest(0); //Consultamos en la base de datos
        //Creamos el Label para la pregunta
        Label lblPreguntaT1 = new Label(res[0]);
        lblPreguntaT1.setMinWidth(10);
        lblPreguntaT1.setAlignment(Pos.BOTTOM_CENTER);
        
        //Creamos los radio buttons
        final ToggleGroup group = new ToggleGroup();
        rdbtn1 = new RadioButton();
        rdbtn1.setToggleGroup(group); 
        rdbtn1.setText(res[1]);
        rdbtn2 = new RadioButton();
        rdbtn2.setToggleGroup(group);
        rdbtn2.setText(res[2]);
        rdbtn3 = new RadioButton();
        rdbtn3.setToggleGroup(group);
        rdbtn3.setText(res[3]);
        
        //Creamos el panel del label de la pregunta
        HBox panePreguntaT1 = new HBox(20,lblPreguntaT1);
        panePreguntaT1.setPadding(new Insets(10));
        
        //Creamos el panel con las respuestas
        HBox paneRespuestasT1 = new HBox(20, rdbtn1, rdbtn2, rdbtn3);
        paneRespuestasT1.setPadding(new Insets(10));
        paneRespuestasT1.setAlignment(Pos.BASELINE_RIGHT);
        
        //Creamos el panel con los botones
        HBox paneBotonesT1 = new HBox(20,btnMTest,btnSiguiente);
        paneBotonesT1.setPadding(new Insets(10));

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMTest1 = new VBox(10,panePreguntaT1,paneRespuestasT1,paneBotonesT1);
        
        //Definimos el Scene
        sceneTest1 = new  Scene(paneMTest1,200,300); 
        
        //********************** MENU TEST 2 ***********************************************       
        //Creamos el boton para volver a la Pantalla de los Test
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchTest());
        
        //Creamos el boton para pasar a la siguiente pregunta
        btnSiguiente = new Button("Aceptar");
        btnSiguiente.setOnAction(e-> SwitchSiguiente());
        
        //Creamos el Label para la pregunta
        Label lblPreguntaT2 = new Label(res[0]);
        lblPreguntaT2.setMinWidth(10);
        lblPreguntaT2.setAlignment(Pos.BOTTOM_CENTER);
        
        //Creamos la caja de texo para la respuesta
        txtRespuesta = new TextField();
        
        //Creamos el panel del label de la pregunta
        HBox panePreguntaT2 = new HBox(20,lblPreguntaT2);
        panePreguntaT2.setPadding(new Insets(10));
        
        //Creamos el panel del label de la respuesta
        HBox paneRespuestasT2 = new HBox(20, txtRespuesta);
        paneRespuestasT2.setPadding(new Insets(10));
        paneRespuestasT2.setAlignment(Pos.BOTTOM_CENTER);
        
        //Creamos el panel con los botones
        HBox paneBotonesT2 = new HBox(20,btnMTest,btnSiguiente);
        paneBotonesT2.setPadding(new Insets(10));

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMTest2 = new VBox(10,panePreguntaT2,paneRespuestasT2,paneBotonesT2);
        
        //Definimos el Scene
        sceneTest2 = new  Scene(paneMTest2,200,300);  
    }
    
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+  
    
    //Creamos la funcion para cuando pulsan el botón Buscar
    public void ClickedBuscar()
    {
        if ("Buscar".equals(btnBuscar.getText()))
        {
            String texto = txtKanjiEntrada.getText();
            String resultado = dbh.getkankun(texto);
            txtKanjiSalida.setText(resultado);
        }
        else
        {
         btnBuscar.setText("Buscar");
        }
    }
   
    //Para Ir al Menu Inicial
     public void SwitchMenuInicial()
    {
      stage.setScene(sceneMP);
    }
     
    //Para Ir al Menu de Busqueda
    public void SwitchBusquedaKanji()
    {
      txtKanjiEntrada.setText("");
      txtKanjiSalida.setText("");
      stage.setScene(sceneBuscar);     
    }
    
     //Para Ir al Menu de los Test
     public void SwitchLeccion()
     {
      stage.setScene(sceneLeccion);
     }
     
    //Para Ir al Menu de los Test
     public void SwitchTest()
     {
      stage.setScene(sceneTest);
     }
     
    //Para Ir al Menu del Test1
     public void SwitchTest1()
     {  
      stage.setScene(sceneTest1);
     }
     
     //Para Ir a la siguiente pregunta
     public void SwitchSiguiente()
     {
      stage.setScene(sceneTest1);  
     }
     
     //Para Ir al Menu del Test2
     public void SwitchTest2()
     {  
      stage.setScene(sceneTest2);
     }

 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+       
   
    //MAIN
    public static void main(String[] args)
    {
        launch(args);
    }
}
