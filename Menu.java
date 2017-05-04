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
    
    //Elementos de la ventada de Test1
    private RadioButton rdbtn1;
    private RadioButton rdbtn2;
    private RadioButton rdbtn3;
    
    //Elementos de la ventana de Busqueda
    private Button btnBuscar;
    private TextField txtKanjiEntrada;
    private TextField txtKanjiSalida;
    
   //El Stage y las distintas Scenes
    private Stage stage;
    private Scene sceneMP;      //Menu Principal
    private Scene sceneBuscar;  //Pantalla de Busqueda
    private Scene sceneTest;   //Pantalla Test
    private Scene sceneTest1;  //Pantalla Test1
    
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
        
        //Creamos el panel del botón
        HBox paneMenuBuscar = new HBox(20, btnMBusqueda,btnMTest);
        paneMenuBuscar.setPadding(new Insets(10));
        paneMenuBuscar.setAlignment(Pos.CENTER);

        //Añadimos paneMenuBuscar VBox
        VBox paneMP = new VBox(10,paneMenuBuscar);
        
        //Finalizamos y mostramos
        sceneMP = new  Scene(paneMP,300,100);
        primaryStage.setTitle("NihonGO");
        primaryStage.setScene(sceneMP);
        primaryStage.show();
        
        //********************** MENU DICCIONARIO ***********************************************
        //Creamos el Label para el Kanji
        Label lblKanji = new Label("Kanji");
        lblKanji.setMinWidth(10);
        lblKanji.setAlignment(Pos.BOTTOM_RIGHT);
        
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
        paneBuscar.setAlignment(Pos.BASELINE_RIGHT);

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMBuscar = new VBox(10,paneKanji, paneBuscar);
        
        //Definimos el Scene
        sceneBuscar = new  Scene(paneMBuscar,300,100);
        
        //********************** MENU TEST ***********************************************       
        //Creamos el botón de Test1
        btnMTest1 = new Button("Test1");
        btnMTest1.setOnAction(e -> SwitchTest1()); 
        
        //Creamos el botón para volver al Menú Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
         
        //Creamos el panel de los botones
        HBox paneTest = new HBox(20, btnMTest1, btnMPrincipal);
        paneTest.setPadding(new Insets(10));
        paneTest.setAlignment(Pos.BASELINE_RIGHT);

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMTest = new VBox(10,paneTest);
        
        //Definimos el Scene
        sceneTest = new  Scene(paneMTest,300,100);
        
        //********************** MENU TEST 1 ***********************************************       
        //Creamos el botón para volver al Menú Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
        
        String[] res = dbh.gettest(0); //Consultamos en la base de datos
        //Creamos el Label para la pregunta
        Label lblPregunta = new Label(res[1]);
        lblPregunta.setMinWidth(10);
        lblPregunta.setAlignment(Pos.BOTTOM_RIGHT);
        
        //Creamos los radio buttons
        final ToggleGroup group = new ToggleGroup();
        rdbtn1 = new RadioButton();
        rdbtn1.setToggleGroup(group); 
        rdbtn1.setText(res[2]);
        rdbtn2 = new RadioButton();
        rdbtn2.setToggleGroup(group);
        rdbtn2.setText(res[3]);
        rdbtn3 = new RadioButton();
        rdbtn3.setToggleGroup(group);
        rdbtn3.setText(res[4]);
        
        //Creamos el panel del label de la pregunta
        HBox panePreguntaT1 = new HBox(20,lblPregunta);
        panePreguntaT1.setPadding(new Insets(10));
        
        //Creamos el panel de los botones
        HBox paneRespuestasT1 = new HBox(20, rdbtn1, rdbtn2, rdbtn3, btnMPrincipal);
        paneRespuestasT1.setPadding(new Insets(10));
        paneRespuestasT1.setAlignment(Pos.BASELINE_RIGHT);

        //Añadimos el paneBuscar y el paneKanji a un VBox
        VBox paneMTest1 = new VBox(10,panePreguntaT1,paneRespuestasT1);
        
        //Definimos el Scene
        sceneTest1 = new  Scene(paneMTest1,300,100);
     
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
     public void SwitchTest()
     {
      stage.setScene(sceneTest);
     }
     
    //Para Ir al Menu del Test1
     public void SwitchTest1()
     {
      stage.setScene(sceneTest1);
     }
     
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+       
   
    //MAIN
    public static void main(String[] args)
    {
        launch(args);
    }
}
