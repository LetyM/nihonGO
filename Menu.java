package Paneles;

import database.*;
import javafx.application.*;
import static javafx.application.Application.launch;
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
    private Button btnMCrearLeccion;
    private Button btnMLeccion1;
    
    //Elementos de la ventana de Crear Leccion
    private TextField txtCLeccion;
    
    //Elementos de la ventada de Test1
    RadioButton rdbtn1 = new RadioButton();
    RadioButton rdbtn2 = new RadioButton();
    RadioButton rdbtn3 = new RadioButton();
    final ToggleGroup group = new ToggleGroup();
//    private RadioButton rdbtn1;
//    private RadioButton rdbtn2;
//    private RadioButton rdbtn3;
    private Button btnSiguiente;
    Label lblPreguntaT1 = new Label();
    VBox paneMTest1 = new VBox(10);
    HBox paneRespuestasT1 = new HBox(10);
    
    //Elementos de la ventana de Test2
    private TextField txtRespuesta;
    
    //Elementos de la ventana de Busqueda
    private Button btnBuscar;
    private TextField txtKanjiEntrada;
    private TextField txtKanjiSalida;
    
   //El Stage y las distintas Scenes
    private Stage stage;
    private Scene sceneMP;       //Menu Principal
    private Scene sceneBuscar;   //Pantalla de Busqueda
    private Scene sceneLeccion;  //Pantalla Leccion
    private Scene sceneCLeccion; //Pantalla Crear Leccion
    private Scene sceneTest;     //Pantalla Test
    private Scene sceneTest1;    //Pantalla Test1
    private Scene sceneTest2;    //Pantalla Test2
    
    //Creamos el metodo start
    //Aqui creamos los elementos de la interfaz que interactua con el usuario
    @Override public void start(Stage primaryStage)
    {
        stage = primaryStage;
        int ancho = 200;
        int largo = 250;
        int space = 20;
        
        //Creamos el DatebaseHandler
        dbh = new DatabaseHandler();
        
        //********************** MENU PRINCIPAL ***********************************************
        //Label Titulo
        Label lblMenuPrincipal = new Label("Menu Principal");
        
        //Botones
        btnMBusqueda = new Button("Busqueda");
        btnMBusqueda.setOnAction(e -> SwitchBusquedaKanji());
        btnMTest = new Button("Test");
        btnMTest.setOnAction(e -> SwitchTest());
        btnMLeccion = new Button("Lecciones");
        btnMLeccion.setOnAction(e-> SwitchLeccion());
        
        //AÃ±adimos los botones al panel
        //VBox paneMP = new VBox(10,lblMenuPrincipal,btnMLeccion,btnMTest,btnMBusqueda);
        VBox paneMP = new VBox(10,lblMenuPrincipal,btnMLeccion,btnMTest,btnMBusqueda);
        //paneMP.getChildren().addAll(btnMBusqueda);
        paneMP.setSpacing(space);
        paneMP.setAlignment(Pos.CENTER);
        
        //Finalizamos y mostramos
        sceneMP = new  Scene(paneMP,ancho,largo);
        primaryStage.setTitle("NihonGO");
        primaryStage.setScene(sceneMP);
        primaryStage.show();
        
        //********************** MENU BUSCAR ***********************************************
        //Creamos el Label para el Kanji
        Label lblKanji = new Label("Kanji");
        lblKanji.setMinWidth(10);
        lblKanji.setAlignment(Pos.TOP_CENTER);
        
        //Creamos el campo en el que se escribe la entrada
        txtKanjiEntrada = new TextField();
        txtKanjiEntrada.setMinWidth(50);
        txtKanjiEntrada.setMaxWidth(50);
        
        //Creamos el campo en el que se devuelve el resultado
        txtKanjiSalida = new TextField();
        txtKanjiSalida.setMinWidth(100);
        txtKanjiSalida.setMaxWidth(100);
        txtKanjiSalida.setEditable(false);
        
        //Creamos el botÃ³n de Busqueda
        btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> ClickedBuscar()); 
  
        //Creamos el botÃ³n para volver al MenÃº Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
        
        //Creamos el panel del txtKanjiEntrada y el boton Buscar
        HBox paneBuscar = new HBox(20,lblKanji,txtKanjiEntrada,btnBuscar);
        paneBuscar.setPadding(new Insets(10));
        paneBuscar.setAlignment(Pos.CENTER);
     
        //Creamos un VBox
        VBox paneMBuscar = new VBox(10, paneBuscar, txtKanjiSalida, btnMPrincipal);
        paneMBuscar.setSpacing(space);
        paneMBuscar.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneBuscar = new  Scene(paneMBuscar,ancho,largo);
        
         //********************** MENU LECCION ***********************************************       
        //Creamos el botÃ³n de Leccion1
        btnMLeccion1 = new Button("Leccion1");
        //btnMLeccion1.setOnAction(e -> SwitchLeccion1());
        
        //Creamos el boton CrearLeccion
        btnMCrearLeccion = new Button("Crear Leccion");
        btnMCrearLeccion.setOnAction(e-> SwitchCrearLeccion());
            
        //Creamos el botÃ³n para volver al MenÃº Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 

        //AÃ±adimos el paneBuscar y el paneKanji a un VBox
        VBox paneMLeccion = new VBox(10,btnMLeccion1,btnMCrearLeccion, btnMPrincipal);
        paneMLeccion.setSpacing(space);
        paneMLeccion.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneLeccion = new  Scene(paneMLeccion,ancho,largo);  
        
        //********************** MENU CREAR LECCION ***********************************************       
        //Creamos el campo para el Nombre de la Leccion
        Label lblCLeccion = new Label("Nombre Leccion");
        lblCLeccion.setMinWidth(10);
        lblCLeccion.setAlignment(Pos.TOP_CENTER);
        txtCLeccion = new TextField();
        txtCLeccion.setMinWidth(50);
        txtCLeccion.setMaxWidth(50);
        
        //Creamos los botones para seleccionar los elementos a aÃ±adir
        //y las etiquietas con los nombres
        //btnMCrearLeccion = new Button("Crear Leccion");
        //btnMCrearLeccion.setOnAction(e-> SwitchCrearLeccion());
        
        //Creamos el boton Aceptar
        
        //Creamos el boton Seleccionar Todo
        
        //Creamos el botÃ³n para volver al MenÃº de lecciones
        btnMLeccion = new Button("Volver");
        btnMLeccion.setOnAction(e -> SwitchLeccion()); 
         
        //Creamos el panel para dar nombre a la leccion
        HBox paneNCLeccion = new HBox(20, lblCLeccion, txtCLeccion);
        paneNCLeccion.setPadding(new Insets(10));
        paneNCLeccion.setAlignment(Pos.BOTTOM_CENTER);

        //AÃ±adimos el paneBuscar y el paneKanji a un VBox
        VBox paneMCLeccion = new VBox(10,paneNCLeccion,btnMLeccion);
        paneMCLeccion.setSpacing(space);
        paneMCLeccion.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneCLeccion = new  Scene(paneMCLeccion,ancho,largo); 
        
        
        //********************** MENU TEST ***********************************************       
        //Creamos el botÃ³n de Test1
        btnMTest1 = new Button("Test1");
        btnMTest1.setOnAction(e -> SwitchTest1()); 
        
        //Creamos el botÃ³n de Test2
        btnMTest2 = new Button("Test2");
        btnMTest2.setOnAction(e-> SwitchTest2());
        
        //Creamos el botÃ³n para volver al MenÃº Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
         
        //AÃ±adimos los botones a un VBox
        VBox paneMTest = new VBox(10,btnMTest1,btnMTest2,btnMPrincipal);
        paneMTest.setSpacing(space);
        paneMTest.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTest = new  Scene(paneMTest,ancho,largo);
        
        //********************** MENU TEST 1 ***********************************************       
        //Creamos el boton para volver a la Pantalla de los Test
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchTest());
        
        //Creamos el boton para pasar a la siguiente pregunta
        btnSiguiente = new Button("Aceptar");
        btnSiguiente.setOnAction(e-> SwitchSiguiente());
        
        GeneraTest(0);
        
//        String[] res = dbh.gettest(0); //Consultamos en la base de datos
//        //Creamos el Label para la pregunta
//        lblPreguntaT1.setText(res[0]);
//        lblPreguntaT1.setMinWidth(10);
//        lblPreguntaT1.setAlignment(Pos.BOTTOM_CENTER);
//        
//        //Creamos los radio buttons
//        rdbtn1.setToggleGroup(group); 
//        rdbtn1.setText(res[1]);
//        rdbtn2.setToggleGroup(group);
//        rdbtn2.setText(res[2]);
//        rdbtn3.setToggleGroup(group);
//        rdbtn3.setText(res[3]);

        //Creamos el panel con las respuestas
        //HBox paneRespuestasT1 = new HBox(20, rdbtn1, rdbtn2, rdbtn3);
        paneRespuestasT1.getChildren().addAll(rdbtn1, rdbtn2, rdbtn3);
        paneRespuestasT1.setPadding(new Insets(10));
        paneRespuestasT1.setAlignment(Pos.BASELINE_RIGHT);
        
        //Creamos el panel con los botones
        HBox paneBotonesT1 = new HBox(20,btnMTest,btnSiguiente);
        paneBotonesT1.setPadding(new Insets(10));
        paneBotonesT1.setAlignment(Pos.CENTER_RIGHT);

        //AÃ±adimos el VBox
        paneMTest1.getChildren().addAll(lblPreguntaT1,paneRespuestasT1,paneBotonesT1);
        paneMTest1.setSpacing(space);
        paneMTest1.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTest1 = new  Scene(paneMTest1,ancho,largo); 
        
        //********************** MENU TEST 2 ***********************************************       
        //Creamos el boton para volver a la Pantalla de los Test
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchTest());
        
        //Creamos el boton para pasar a la siguiente pregunta
        btnSiguiente = new Button("Aceptar");
        btnSiguiente.setOnAction(e-> SwitchSiguiente());
        
        //Creamos el Label para la pregunta
//        Label lblPreguntaT2 = new Label(res[0]);
//        lblPreguntaT2.setMinWidth(10);
//        lblPreguntaT2.setAlignment(Pos.BOTTOM_CENTER);
        
        //Creamos la caja de texo para la respuesta
        txtRespuesta = new TextField();

        //Creamos el panel del label de la respuesta
        HBox paneRespuestasT2 = new HBox(20, txtRespuesta);
        paneRespuestasT2.setPadding(new Insets(10));
        paneRespuestasT2.setAlignment(Pos.BOTTOM_CENTER);
        
        //Creamos el panel con los botones
        HBox paneBotonesT2 = new HBox(20,btnMTest,btnSiguiente);
        paneBotonesT2.setPadding(new Insets(10));
        paneBotonesT2.setAlignment(Pos.CENTER_RIGHT);

        //AÃ±adimos el paneBuscar y el paneKanji a un VBox
        VBox paneMTest2 = new VBox(10,paneRespuestasT2,paneBotonesT2); //,lblPreguntaT2
        paneMTest2.setSpacing(space);
        paneMTest2.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTest2 = new  Scene(paneMTest2,ancho,largo);  
    }
    
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+  
    
    //Función que genera la pregunta del test
    public void GeneraTest(int i)
    {
      String[] res = dbh.gettest(i); //Consultamos en la base de datos
       //Creamos el Label para la pregunta 
        lblPreguntaT1.setText(res[0]);
        
       //Creamos los radio buttons
        rdbtn1.setToggleGroup(group); 
        rdbtn1.setText(res[1]);
        rdbtn2.setToggleGroup(group);
        rdbtn2.setText(res[2]);
        rdbtn3.setToggleGroup(group);
        rdbtn3.setText(res[3]);  
    }
    
    
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+     
    //Creamos la funcion para cuando pulsan el boton Buscar
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
    
     //Para Ir al Menu de las Lecciones
     public void SwitchLeccion()
     {
      stage.setScene(sceneLeccion);
     }
     
    //Para Ir al Menu para crear lecciones
     public void SwitchCrearLeccion()
     {
      stage.setScene(sceneCLeccion);
     }
     
    //Para Ir al Menu de los Test
     public void SwitchTest()
     {
      stage.setScene(sceneTest);
     }
     
    //Para Ir al Menu del Test1
     public void SwitchTest1()
     {  
      GeneraTest(0);   
      stage.setScene(sceneTest1);
     }
     
     //Para Ir a la siguiente pregunta
     public void SwitchSiguiente()
     {
      GeneraTest(1);
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
