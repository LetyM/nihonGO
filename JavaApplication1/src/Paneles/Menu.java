package Paneles;

import database.*;
import java.util.Date;
import java.util.List;
import javafx.application.*;
import static javafx.application.Application.launch;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * 
 * @author Leticia
 */
 
public class Menu extends Application {

    //Enlace con la base de datos
    private IDatabaseHandler dbh;
    
    //Llista que engloba a la lista del Test del dia  
    private ITestDia TD;
    
   //Llista que engloba a la lista del Test personalizado seleccionado 
    private ITestP TP;
    
    //Pregunta actual del Test Diario
    private Pregunta pregunta; 

    //Pregunta actual del Test Diario
    private PreguntaTP preguntaTP;
    
    //Botones de cambio de ventana
    private Button btnMTest;
    private Button btnMBusqueda;
    private Button btnMBDicc;
    private Button btnMBKanji;
    private Button btnMPrincipal;
    private Button btnMTestP;
    private Button btnITestP;
    private Button btnMCTest;
    private Button btnSiguiente;
    private Button btnSiguienteTP;
    private Button btnMTestD;
    
    //Elementos de la ventana de Crear Test
    private TextField txtCTest;
    private Button btnAKanji;
    private Button btnAPalabD;
    private Button btnAPalabI;
    ListView<String> listKanji = new ListView<>();
    ObservableList<String> itemsKanji = FXCollections.observableArrayList();
    ListView<String> listPalab = new ListView<>();
    ObservableList<String> itemsPalab = FXCollections.observableArrayList();
    
    //Elementos de la ventana de Tests personalizados
    private Button btnContTest;
    private Button btnClearTest;
    ListView<String> listTP = new ListView<>();
    ObservableList<String> itemsTP = FXCollections.observableArrayList();
    
    //Elementos de la ventana de contenido del test
    ListView<String> listCont = new ListView<>();
    ObservableList<String> itemsCont = FXCollections.observableArrayList();
    
    //Elementos de la ventana de elimiar test
    private Button btnAceptarBorrado;
    private Button btnCancelarBorrado;
    
    //Elementos de la ventana de Test Personalizado seleccionado
    int contadorTP; //Contamos si es la primera vez que pulsamos "Siguiente"
    private final RadioButton rdbtn0TP = new RadioButton();
    private final RadioButton rdbtn1TP = new RadioButton();
    private final RadioButton rdbtn2TP = new RadioButton();
    final ToggleGroup groupTP = new ToggleGroup(); 
            
    private final Label lblresultado1TP = new Label();
    private final Label lblresultado2TP = new Label();
    private final Label lblPreguntaTP = new Label();
    private final VBox paneMTestP = new VBox();
    private final VBox paneRespuestasTP = new VBox();
    
    //Elementos de la ventada de Test Diario
    int contador; //Contamos si es la primera vez que pulsamos "Siguiente"
    private final RadioButton rdbtn0D = new RadioButton();
    private final RadioButton rdbtn1D = new RadioButton();
    private final RadioButton rdbtn2D = new RadioButton();
    final ToggleGroup groupD = new ToggleGroup();
    
    private final Label lblresultado1 = new Label();
    private final Label lblresultado2 = new Label();
    private final Label lblPreguntaTD = new Label();
    private final VBox paneMTestD = new VBox();
    private final VBox paneRespuestasTD = new VBox();
    
    //Boton para Buscar
    private Button btnBuscar;
    
    //Elementos de la ventana de Busqueda por vocabulario
    private TextField txtVocabEntrada;
    ListView<String> listVocab = new ListView<>();
    ObservableList<String> itemsVocab = FXCollections.observableArrayList();
    
    
    //Elementos de la ventana de Busqueda por kanji
    private TextField txtKanjiEntrada;
    private TextField txtKanjiSalida0;
    private TextField txtKanjiSalida1;
    private TextField txtKanjiSalida2;
    private TextField txtKanjiSalida3;
    private TextField txtKanjiSalida4;
    private TextField txtKanjiSalida5;
    
   //El Stage y las distintas Scenes
    private Stage stage;
    private Scene sceneMP;       //Menu Principal
    private Scene sceneMB;       //Pantalla Menu Busqueda
    private Scene sceneBKanji;   //Pantalla de Busqueda Kanji
    private Scene sceneBDicc;    //Pantalla de Busqueda Vocabulario
    private Scene sceneCTest;    //Pantalla Crear Test
    private Scene sceneTest;     //Pantalla Test
    private Scene sceneMTestP;    //Pantalla Test Personalizados
    private Scene sceneTestP;    //Pantalla Test Personaliado seleccionado
    private Scene sceneTPV;      //Pantalla Volver del TP
    private Scene sceneCont;     //Pantalla Contenido Test
    private Scene sceneEliminar; //Pantalla Eliminar Test
    private Scene sceneTestD;    //Pantalla Test Diario
    private Scene sceneTDV;      //Pantalla Volver del TD



    //Creamos el metodo start
    //Aqui creamos los elementos de la interfaz que interactua con el usuario
    @Override public void start(Stage primaryStage)
    {
        stage = primaryStage;
        int ancho = 300;
        int largo = 450;
        int spaceV = 20;
        int spaceH = 5;
        int minw = 5;
        int insets = 5;
        int width = 150;
        int setPrefWidth = 150;
        int setPrefHeight = 70;
        
        //Creamos el DatebaseHandler
        dbh = new DatabaseHandler();    
        
        paneRespuestasTP.setSpacing(spaceV);
        paneRespuestasTD.setSpacing(spaceV);
               
        //********************** MENU PRINCIPAL ***********************************************
        //Label Titulo
        Label lblMenuPrincipal = new Label("Menú Principal");
        
        //Botones
        btnMCTest = new Button("Crear Test");
        btnMCTest.setOnAction(e-> SwitchCTest());
        btnMTest = new Button("Tests");
        btnMTest.setOnAction(e -> SwitchMTest());
        btnMBusqueda = new Button("Diccionario");
        btnMBusqueda.setOnAction(e -> SwitchDiccionario());
        
        
        //Añadimos los botones al panel
        VBox paneMP = new VBox(spaceV,lblMenuPrincipal,btnMCTest,btnMTest,btnMBusqueda);
        paneMP.setAlignment(Pos.CENTER);
        
        //Finalizamos y mostramos
        sceneMP = new  Scene(paneMP,ancho,largo);
        primaryStage.setTitle("NihonGO");
        primaryStage.setScene(sceneMP);
        primaryStage.show();
        
        //********************** MENU DICCIONARIO ***********************************************
        //Label Titulo
        Label lblMenuBusqueda = new Label("Menú Diccionario");
        
        //Botones
        btnMBDicc = new Button("Vocabulario");
        btnMBDicc .setOnAction(e -> SwitchBusquedaVocab());
        btnMBKanji = new Button("Kanji");
        btnMBKanji.setOnAction(e -> SwitchBusquedaKanji());
        
        //Creamos el botón para volver al Menú Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
        
        //Creamos el VBox
        VBox paneMB = new VBox(spaceV,lblMenuBusqueda,btnMBDicc,btnMBKanji,btnMPrincipal);
        paneMB.setAlignment(Pos.CENTER);
        
        //Creamos el scene
        sceneMB = new  Scene(paneMB,ancho,largo);
        
        //********************* MENU BUSCAR VOCABULARIO *********************************************
        //Creamos la etiqueta de la cabecera
        Label lblMenuBDicc = new Label("Vocabulario");
        lblMenuBDicc.setAlignment(Pos.TOP_CENTER);
        
        //Creamos la etiqueta de la cabecera de los resultados
        Label lblMBDResultados = new Label("Resultados");
        lblMBDResultados.setAlignment(Pos.TOP_CENTER);
        
        //Creamos el campo en el que se escribe la entrada
        txtVocabEntrada = new TextField();
        txtVocabEntrada.setMinWidth(100);
        txtVocabEntrada.setMaxWidth(100);
        
        //Asignamos la lista de respuestas
        listVocab.setItems(itemsVocab);
        listVocab.setMaxSize(250, 150);
        
        //Creamos el botón de Busqueda
        btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> ClickedBuscarV()); 
  
        //Creamos el botón para volver al Menú Buscar
        btnMBusqueda = new Button("Volver");
        btnMBusqueda.setOnAction(e -> SwitchDiccionario());
        
        //Creamos el panel del txtKanjiEntrada y el boton Buscar
        HBox paneBuscarV = new HBox(spaceH,txtVocabEntrada,btnBuscar);
        paneBuscarV.setPadding(new Insets(insets));
        paneBuscarV.setAlignment(Pos.CENTER);
     
        //Creamos los paneles para los resultados
        VBox paneResultadoV = new VBox(spaceV,listVocab);
        paneResultadoV.setPadding(new Insets(insets));
        paneResultadoV.setAlignment(Pos.CENTER);
        
        //Creamos un VBox
        VBox paneMBDicc = new VBox(lblMenuBDicc, paneBuscarV, lblMBDResultados, paneResultadoV, btnMBusqueda);
        paneMBDicc.setSpacing(5);
        paneMBDicc.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneBDicc = new  Scene(paneMBDicc,ancho,largo);
        
        //********************** MENU BUSCAR POR KANJI***********************************************
        //Creamos la etiqueta de la cabecera
        Label lblMenuBKanji = new Label("Buscar Kanji");
        lblMenuBKanji.setAlignment(Pos.TOP_CENTER);
        
        //Creamos la etiqueta de la cabecera de los resultados
        Label lblMBKResultados = new Label("Resultado");
        lblMBKResultados.setAlignment(Pos.TOP_CENTER);
        
        //Creamos el campo en el que se escribe la entrada
        txtKanjiEntrada = new TextField();
        txtKanjiEntrada.setMinWidth(width/2);
        txtKanjiEntrada.setMaxWidth(width/2);
        
        //Creamos las etiquetas para los resultados
        Label lblResultado0 = new Label("Significado");
        lblResultado0.setMinWidth(minw);
        lblResultado0.setAlignment(Pos.TOP_CENTER);
        Label lblResultado1 = new Label("Kanji");
        lblResultado1.setMinWidth(minw);
        lblResultado1.setAlignment(Pos.TOP_CENTER);
        Label lblResultado2 = new Label("Kunyomi");
        lblResultado2.setMinWidth(minw);
        lblResultado2.setAlignment(Pos.TOP_CENTER);
        Label lblResultado3 = new Label("Onyomi");
        lblResultado3.setMinWidth(minw);
        lblResultado3.setAlignment(Pos.TOP_CENTER);
        Label lblResultado4 = new Label("JLPT");
        lblResultado4.setMinWidth(minw);
        lblResultado4.setAlignment(Pos.TOP_CENTER);
        Label lblResultado5 = new Label("Kanken");
        lblResultado5.setMinWidth(minw);
        lblResultado5.setAlignment(Pos.TOP_CENTER);   
         
        //Creamos los campos con los resultados
        txtKanjiSalida0 = new TextField();
        txtKanjiSalida0.setMinWidth(width);
        txtKanjiSalida0.setMaxWidth(width);
        txtKanjiSalida0.setEditable(false);
        txtKanjiSalida1 = new TextField();
        txtKanjiSalida1.setMinWidth(width);
        txtKanjiSalida1.setMaxWidth(width);
        txtKanjiSalida1.setEditable(false);
        txtKanjiSalida2 = new TextField();
        txtKanjiSalida2.setMinWidth(width);
        txtKanjiSalida2.setMaxWidth(width);
        txtKanjiSalida2.setEditable(false);
        txtKanjiSalida3 = new TextField();
        txtKanjiSalida3.setMinWidth(width);
        txtKanjiSalida3.setMaxWidth(width);
        txtKanjiSalida3.setEditable(false);
        txtKanjiSalida4 = new TextField();
        txtKanjiSalida4.setMinWidth(width);
        txtKanjiSalida4.setMaxWidth(width);
        txtKanjiSalida4.setEditable(false);
        txtKanjiSalida5 = new TextField();
        txtKanjiSalida5.setMinWidth(width);
        txtKanjiSalida5.setMaxWidth(width);
        txtKanjiSalida5.setEditable(false);
        
        //Creamos el botón de Busqueda
        btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> ClickedBuscar()); 
  
        //Creamos el botón para volver al Menú Buscar
        btnMBusqueda = new Button("Volver");
        btnMBusqueda.setOnAction(e -> SwitchDiccionario());
        
        //Creamos el panel del txtKanjiEntrada y el boton Buscar
        HBox paneBuscar = new HBox(spaceH,txtKanjiEntrada,btnBuscar);
        paneBuscar.setPadding(new Insets(insets));
        paneBuscar.setAlignment(Pos.CENTER);
     
        //Creamos los paneles para los resultados
        HBox paneResultado0 = new HBox(spaceH,lblResultado0,txtKanjiSalida0);
        paneResultado0.setPadding(new Insets(insets));
        paneResultado0.setAlignment(Pos.CENTER);
        HBox paneResultado1 = new HBox(spaceH+40,lblResultado1,txtKanjiSalida1);
        paneResultado1.setPadding(new Insets(insets));
        paneResultado1.setAlignment(Pos.CENTER);
        HBox paneResultado2 = new HBox(spaceH+15,lblResultado2,txtKanjiSalida2);
        paneResultado2.setPadding(new Insets(insets));
        paneResultado2.setAlignment(Pos.CENTER);
        HBox paneResultado3 = new HBox(spaceH+20,lblResultado3,txtKanjiSalida3);
        paneResultado3.setPadding(new Insets(insets));
        paneResultado3.setAlignment(Pos.CENTER);
        HBox paneResultado4 = new HBox(spaceH+40,lblResultado4,txtKanjiSalida4);
        paneResultado4.setPadding(new Insets(insets));
        paneResultado4.setAlignment(Pos.CENTER);
        HBox paneResultado5 = new HBox(spaceH+20,lblResultado5,txtKanjiSalida5);
        paneResultado5.setPadding(new Insets(insets));
        paneResultado5.setAlignment(Pos.CENTER);
        
        
        //Creamos un VBox
        VBox paneMBuscar = new VBox(lblMenuBKanji, paneBuscar, lblMBKResultados, paneResultado0, paneResultado1, 
                paneResultado2, paneResultado3, paneResultado4, paneResultado5, btnMBusqueda);
        paneMBuscar.setSpacing(5);
        paneMBuscar.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneBKanji = new  Scene(paneMBuscar,ancho,largo);
        
        //********************** MENU CREAR TEST ***********************************************
        //Creamos la etiqueta con la cabecera
        Label lblMenuCrea = new Label("Menú Crear Test");
        
        //Creamos el campo para el Nombre de la Leccion
        Label lblCTest = new Label("Nombre Test");
        lblCTest.setMinWidth(minw);
        lblCTest.setAlignment(Pos.TOP_CENTER);
        txtCTest = new TextField();
        txtCTest.setMinWidth(100);
        txtCTest.setMaxWidth(100);
        
        //Creamos el panel para dar nombre a la leccion
        HBox paneNCLeccion = new HBox(spaceH, lblCTest, txtCTest);
        paneNCLeccion.setPadding(new Insets(insets));
        paneNCLeccion.setAlignment(Pos.BOTTOM_CENTER);
        
        //Etiqueta explicativa
        Label lblAKanji = new Label("Lectura de");
        lblAKanji.setAlignment(Pos.TOP_CENTER);
        
        //Lista con los kanjis 
        listKanji.setItems(itemsKanji);
        listKanji.setPrefWidth(setPrefWidth);
        listKanji.setPrefHeight(setPrefHeight);
        
        //Creamos el boton para añadir el kanji al test
        btnAKanji = new Button("Añadir");
        btnAKanji.setOnAction(e -> clickedAKanji());
        
        //Creamos el HBox para añadir kanji
        HBox paneAKanji = new HBox (listKanji,btnAKanji);
        paneAKanji.setSpacing(20);
        paneAKanji.setAlignment(Pos.CENTER);
        
        //Etiqueta explicativa
        Label lblAPalab = new Label("ES -> JP  //  ES <- JP");
        lblAPalab.setAlignment(Pos.TOP_CENTER);
        
        //Lista con las palabras
        listPalab.setItems(itemsPalab);
        listPalab.setPrefWidth(setPrefWidth);
        listPalab.setPrefHeight(setPrefHeight);
        
        //Creamos el boton para añadir el palabra hacia la derecha
        btnAPalabD = new Button("Añadir ->");
        btnAPalabD.setOnAction(e -> clickedAPalabD());
        
        //Creamos el boton para añadir el palabra hacia la izquierda
        btnAPalabI = new Button("Añadir <-");
        btnAPalabI.setOnAction(e -> clickedAPalabI());
        
        //Creamos un VBox para los botones de añadir
        VBox APalab = new VBox(spaceV,btnAPalabD,btnAPalabI);
        APalab.setAlignment(Pos.CENTER);
        
        //Creamos el HBox para añadir palabras
        HBox paneAPalab = new HBox (listPalab,APalab);
        paneAPalab.setSpacing(20);
        paneAPalab.setAlignment(Pos.CENTER);
            
        //Creamos el boton para volver al Menu Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 

        //
        VBox paneMCtest = new VBox(spaceV,lblMenuCrea,paneNCLeccion,lblAKanji,paneAKanji,lblAPalab,paneAPalab,btnMPrincipal);
        paneMCtest.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneCTest = new  Scene(paneMCtest,ancho,largo);  

        //********************** MENU TEST ***********************************************       
        //Creamos la etiqueta con la cabecera
        Label lblMenuTest = new Label("Menú Test");
        
        //Creamos el boton para los Test Persoanlizados
        btnMTestP = new Button("Personalizados");
        btnMTestP.setOnAction(e -> SwitchMTestP());

        //Creamos el botón de TestD
        btnMTestD = new Button("Test Diario");
        btnMTestD.setOnAction(e-> SwitchMTestD());
        
        //Creamos el botón para volver al Menú Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial());
        
        //Añadimos los botones a un VBox
        VBox paneMTest = new VBox(spaceV,lblMenuTest,btnMTestP,btnMTestD,btnMPrincipal);
        paneMTest.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTest = new  Scene(paneMTest,ancho,largo);
        
        //********************** MENU TESTs PERSONALIZADOS ***********************************************
        //Creamos la etiqueta con la cabecera
        Label lblMenuTP = new Label("Menú Test Personalizados");
        
        //Lista con los kanjis 
        listTP.setItems(itemsTP);
        listTP.setPrefWidth(100);
        listTP.setPrefHeight(70);
        
        //Creamos el boton para ver el contenido del test
        btnContTest = new Button("Contenido");
        btnContTest.setOnAction(e -> SwitchMCont());
        
        //Creamos el boton para borrar el test seleccionado
        btnClearTest = new Button("Eliminar");
        btnClearTest.setOnAction(e -> SwitchMElim());
        
        //VBox para los botones
        VBox CTest = new VBox(spaceV,btnContTest,btnClearTest);
        CTest.setAlignment(Pos.CENTER);
        
        //Creamos el HBox para añadir ver el contenido del test y borrar un test
        HBox paneContTest = new HBox (listTP,CTest);
        paneContTest.setSpacing(20);
        paneContTest.setAlignment(Pos.CENTER);
        
        //Boton para comenzar el test seleccionado
        btnITestP = new Button("¡Empezar!");
        btnITestP.setOnAction(e -> SwitchITestP());
            
        //Creamos el boton para volver al Menu de Tests
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchMTest()); 

        //Panel con los botones de volver y empezar
        HBox paneBTP = new HBox(spaceH,btnMTest,btnITestP);
        paneBTP.setPadding(new Insets(insets));
        paneBTP.setAlignment(Pos.CENTER); 
        
        //
        VBox paneMTP = new VBox(spaceV,lblMenuTP, paneContTest, paneBTP);
        paneMTP.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneMTestP = new Scene(paneMTP,ancho,largo);
        
       //********************** MENU CONTENIDO TEST ***********************************************
        listCont.setItems(itemsCont);
        
        //Creamos el boton para los Test Persoanlizados
        btnMTestP = new Button("Volver");
        btnMTestP.setOnAction(e -> SwitchMTestP());
        
        //
        VBox paneMTC = new VBox(spaceV, listCont, btnMTestP);
        paneMTC.setAlignment(Pos.CENTER);

        sceneCont = new Scene(paneMTC,ancho,largo);
        
       //********************** MENU ELIMINAR CONTENIDO TEST ***********************************************
        Label lblEliminar = new Label("¿Eliminar el test seleccionado?");
       
        //Boton para Aceptar
        btnAceptarBorrado = new Button("Aceptar");
        btnAceptarBorrado.setOnAction(e-> ClickedABorrar());
        
        //Boton para Cancelar
        btnCancelarBorrado = new Button("Cancelar");
        btnCancelarBorrado.setOnAction(e-> ClickedCBorrar());
        
        //Panel con los botones
        HBox paneBotonBorrar = new HBox(spaceH,btnAceptarBorrado,btnCancelarBorrado);
        paneBotonBorrar.setAlignment(Pos.CENTER);
        
        //
        VBox paneMEliminar = new VBox(spaceV,lblEliminar,paneBotonBorrar);
        paneMEliminar.setAlignment(Pos.CENTER);
        
        sceneEliminar = new Scene(paneMEliminar,ancho,largo);
        
       //********************** MENU TEST PERSONALIZADO SELECCIONADO ***********************************************
        //Creamos el panel con las respuestas
        paneRespuestasTP.getChildren().addAll(rdbtn0TP, rdbtn1TP, rdbtn2TP);
        paneRespuestasTP.setPadding(new Insets(insets));
        paneRespuestasTP.setAlignment(Pos.CENTER);
        
        //Creamos el boton para volver a la Pantalla de los Test personalizados
        btnMTestP = new Button("Volver");
        btnMTestP.setOnAction(e -> SwitchMTestP());
        
        //Creamos el boton para pasar a la siguiente pregunta
        btnSiguienteTP = new Button("Aceptar"); 
        btnSiguienteTP.setOnAction(e-> SwitchSiguienteTP());
        
        //Creamos el panel con los botones
        HBox paneBotonesTP = new HBox(spaceH,btnMTestP,btnSiguienteTP);
        paneBotonesTP.setPadding(new Insets(insets));
        paneBotonesTP.setAlignment(Pos.CENTER);
        
        //Añadimos el VBox
        paneMTestP.getChildren().addAll(lblresultado1TP,lblresultado2TP,lblPreguntaTP,paneRespuestasTP,paneBotonesTP);
        paneMTestP.setSpacing(spaceV);
        paneMTestP.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTestP = new Scene(paneMTestP,ancho,largo); 
  
        //********************** MENU TEST PERSONALIZADO VOLVER ***********************************************
        Label lblTPV = new Label("¡Acabaste!");
        lblTPV.setMinWidth(minw);
        lblTPV.setAlignment(Pos.TOP_CENTER);
        
       //Creamos el boton para volver a la Pantalla de los Test Personalizados
        btnMTestP = new Button("Volver");
        btnMTestP.setOnAction(e -> SwitchMTestP());

        //Creamos el panel con los botones
        HBox paneTPV = new HBox(spaceH,btnMTestP);
        paneTPV.setPadding(new Insets(insets));
        paneTPV.setAlignment(Pos.CENTER);

        VBox paneMTPV = new VBox(spaceV,lblTPV,paneTPV);  
        paneMTPV.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTPV = new  Scene(paneMTPV,ancho,largo); 
              
        //********************** MENU TEST DIARIO ***********************************************
       //Creamos el boton para volver a la Pantalla de los Test
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchMTest());
        
        //Creamos el boton para pasar a la siguiente pregunta
        btnSiguiente = new Button("Aceptar"); 
        btnSiguiente.setOnAction(e-> SwitchSiguienteD());
        
        //Creamos el panel con las respuestas
        paneRespuestasTD.getChildren().addAll(rdbtn0D, rdbtn1D, rdbtn2D);
        paneRespuestasTD.setPadding(new Insets(insets));
        paneRespuestasTD.setAlignment(Pos.CENTER);
        
        //Creamos el panel con los botones
        HBox paneBotonesTD = new HBox(spaceH,btnMTest,btnSiguiente);
        paneBotonesTD.setPadding(new Insets(insets));
        paneBotonesTD.setAlignment(Pos.CENTER);

        //Añadimos el VBox
        paneMTestD.getChildren().addAll(lblresultado1,lblresultado2,lblPreguntaTD,paneRespuestasTD,paneBotonesTD);
        paneMTestD.setSpacing(spaceV);
        paneMTestD.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTestD = new Scene(paneMTestD,ancho,largo); 
        
        //********************** MENU TEST DIARIO VOLVER ***********************************************
        Label lblTDV = new Label("¡Acabaste por hoy!");
        lblTDV.setMinWidth(minw);
        lblTDV.setAlignment(Pos.TOP_CENTER);
        
       //Creamos el boton para volver a la Pantalla de los Test
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchMTest());

        //Creamos el panel con los botones
        HBox paneTDV = new HBox(spaceH,btnMTest);
        paneTDV.setPadding(new Insets(insets));
        paneTDV.setAlignment(Pos.CENTER);

        VBox paneMTDV = new VBox(spaceV,lblTDV,paneTDV);  
        paneMTDV.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTDV = new  Scene(paneMTDV,ancho,largo);         
    }
    
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+  
    //Funcion para añadir kanjis a el test creado
    public void clickedAKanji(){
        int ord;
        String nombre, kanji, respuesta;
        
        ord = dbh.ordTP()+1;
        nombre = txtCTest.getText();
        kanji = listKanji.getSelectionModel().getSelectedItem();
        respuesta = dbh.getkanji(kanji)[2];
        
        dbh.addTPkanji(ord,nombre,kanji,respuesta);
    }
    
    //Funcion para añadir palabras hacia la derecha
    public void clickedAPalabD(){
        int ord;
        String nombre, clave, palab, respuesta;
        
        ord = dbh.ordTP()+1;
        nombre = txtCTest.getText();
        clave = listPalab.getSelectionModel().getSelectedItem();
        palab = dbh.getAPalab(clave)[0];
        respuesta = dbh.getAPalab(clave)[1];
        
        dbh.addTPpalab(ord,1,nombre,palab,respuesta);
    }
    
    //Funcion para añadir palabras hacia la izquierda
    public void clickedAPalabI(){
        int ord;
        String nombre, clave, palab, respuesta;
        
        ord = dbh.ordTP()+1;
        nombre = txtCTest.getText();
        clave = listPalab.getSelectionModel().getSelectedItem();
        palab = dbh.getAPalab(clave)[1];
        respuesta = dbh.getAPalab(clave)[0];
        
        dbh.addTPpalab(ord,2,nombre,palab,respuesta);
    }
    
    //Funcion para eliminar el test personalizado seleccionado
    public void ClickedABorrar(){
        String nombre;
        
        nombre = listTP.getSelectionModel().getSelectedItem();
        
        dbh.borrarTest(nombre);
        itemsTP.remove(nombre);
        
        //Volvemos
        stage.setScene(sceneMTestP);
    }
    
    //Funcion para cancelar el eliminar el test personalizado seleccionado
    public void ClickedCBorrar(){
        //Volvemos
        stage.setScene(sceneMTestP);
    }
    
    //Funcion para buscar por vocabulario
    public void ClickedBuscarV(){
        String texto = txtVocabEntrada.getText();
        ITraduccion resultado;
        int n;
        
        resultado = dbh.getPalab(texto);
        n = resultado.getNumPalabras();

        itemsVocab.clear();
        for (int i=0; i<n; i++){
            itemsVocab.add(resultado.getTraducc(i).getPal());
        }
    } 
    
    //Creamos la funcion para buscar por Kanji
    public void ClickedBuscar(){
            String texto = txtKanjiEntrada.getText();
            String[] resultado = new String[6];
            resultado = dbh.getkanji(texto);
            
            txtKanjiSalida0.setText(resultado[0]);
            txtKanjiSalida1.setText(resultado[1]);
            txtKanjiSalida2.setText(resultado[2]);
            txtKanjiSalida3.setText(resultado[3]);
            txtKanjiSalida4.setText(resultado[4]);
            txtKanjiSalida5.setText(resultado[5]);
    }
    
    //Función que genera el test diario
    private void GeneraTestP(){
        TP = dbh.getPreguntasTP(listTP.getSelectionModel().getSelectedItem()); 
    }
    
     //Función para actualizar el test Diario
    private void ActualizaTestP(){
        Random rand = new Random();
        int num_preguntas; 
        int i, n, tipo;
        int Res0, Res1, Res2;
        String[] res = new String[3];

        num_preguntas = TP.getNumPreguntas();
        i = rand.nextInt(num_preguntas);
        preguntaTP = TP.getPregunta(i);
        tipo = preguntaTP.getTipo();
        
        //Randomizamos en que boton esta cada respuesta
        Res0 = rand.nextInt(3);
        Res1 = rand.nextInt(3);
        while (Res1==Res0){
            Res1 = rand.nextInt(3);
        }
        Res2 = rand.nextInt(3);
        while (Res2==Res0 || Res2==Res1){
            Res2 = rand.nextInt(3);
        }
        
        //Definimos las opciones
        res[Res0]=preguntaTP.getRes();
        if (tipo==0){
            n = dbh.ordkanji();
            String[] opciones = new String[n];
            opciones = dbh.getAllOp(n, tipo, preguntaTP.getRes());
            res[Res1]=opciones[0];
            res[Res2]=opciones[1];
        }
        else if (tipo==31 || tipo==32){
            n = dbh.ordpalab(); 
            String[] opciones = new String[n];
            opciones = dbh.getAllOp(n, tipo, preguntaTP.getRes());
            res[Res1]=opciones[0];
            res[Res2]=opciones[1];
        }
        else{
            System.out.println("Opcion no definida");
        }
        
        
      //Actualizamos el Label para el enunciado 
        lblPreguntaTP.setText(preguntaTP.getEnun());
      
      //Actualizamos los radio buttons
        rdbtn0TP.setToggleGroup(groupTP); 
        rdbtn0TP.setText(res[0]);
        rdbtn1TP.setToggleGroup(groupTP);
        rdbtn1TP.setText(res[1]);
        rdbtn2TP.setToggleGroup(groupTP);
        rdbtn2TP.setText(res[2]);
    }
    
    //Función que genera el test diario
    private void GeneraTestD(){
      TD = dbh.getPreguntas();
    }
    
     //Función para actualizar el test Diario
    private void ActualizaTestD(){
        Random rand = new Random();
        int num_preguntas; 
        int i;
        int Res0, Res1, Res2;
        
        num_preguntas = TD.getNumPreguntas();
        i = rand.nextInt(num_preguntas);
        pregunta = TD.getPregunta(i);
        
        //Randomizamos en que boton esta cada respuesta
        Res0 = rand.nextInt(3);
        Res1 = rand.nextInt(3);
        while (Res1==Res0){
            Res1 = rand.nextInt(3);
        }
        Res2 = rand.nextInt(3);
        while (Res2==Res0 || Res2==Res1){
            Res2 = rand.nextInt(3);
        }
        
      //Actualizamos el Label para el enunciado 
        lblPreguntaTD.setText(pregunta.getEnun());
      
      //Actualizamos los radio buttons
        rdbtn0D.setToggleGroup(groupD); 
        rdbtn0D.setText(pregunta.getRes()[Res0]);
        rdbtn1D.setToggleGroup(groupD);
        rdbtn1D.setText(pregunta.getRes()[Res1]);
        rdbtn2D.setToggleGroup(groupD);
        rdbtn2D.setText(pregunta.getRes()[Res2]);
    }
       
 
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
    //Para Ir al Menu Inicial
     public void SwitchMenuInicial()
    {
      stage.setScene(sceneMP);
    }
    
    //Para ir la Menú de Búsqueda
    public void SwitchDiccionario(){
        stage.setScene(sceneMB);
    }
    
    public void SwitchBusquedaVocab(){
        
        txtVocabEntrada.setText("");
        itemsVocab.clear();
        stage.setScene(sceneBDicc);
    }
    
    //Para Ir al Menu de Busqueda por Kanji
    public void SwitchBusquedaKanji(){
        
        txtKanjiEntrada.setText("");
        txtKanjiSalida0.setText("");
        txtKanjiSalida1.setText("");
        txtKanjiSalida2.setText("");
        txtKanjiSalida3.setText("");
        txtKanjiSalida4.setText("");
        txtKanjiSalida5.setText("");
        stage.setScene(sceneBKanji);     
    }
    
     //Para Ir al Menu de creacion de tests
     public void SwitchCTest(){
         
         //Reseteamos el campo de entrada para la busqueda
         txtCTest.setText("");
         
         //Lista kanjis disponibles
         List<String> kanji;
         int nk;
       
         kanji = dbh.getAllKanji();
         nk = kanji.size();
        
         itemsKanji.clear();
         for (int i=0; i<nk; i++){
             itemsKanji.add(kanji.get(i));
         } 
        
         //Lista palabras disponibles
         List<String> palab;
         int np;
       
         palab = dbh.getAllPalab();
         np = palab.size();
        
         itemsPalab.clear();
         for (int i=0; i<np; i++){
             itemsPalab.add(palab.get(i));
         } 
        
         //Cambiamos de pantalla
          stage.setScene(sceneCTest);
     }
     
     //Para Ir al Menu de los Test
     public void SwitchMTest(){
         stage.setScene(sceneTest);
     }
     
     //Para ir al Menu de los Tests personalizados
     public void SwitchMTestP(){
        //Lista test disponibles
        List<String> test;
        int nt;
       
        test = dbh.getAllTestP();
        nt = test.size();
        
        itemsTP.clear();
        for (int i=0; i<nt; i++){
            itemsTP.add(test.get(i));
        }  
        
        //Cambiamos de pantalla
         stage.setScene(sceneMTestP);
     }
     
     //Para ir al Menu del contenido del test personalizado seleccionado
     public void SwitchMCont(){
         //Lista de contenido
        String[] enun, res;
        int nc;
        
        nc = dbh.ordTC(listTP.getSelectionModel().getSelectedItem());
        enun = dbh.getAllEnun(nc,listTP.getSelectionModel().getSelectedItem());
        res = dbh.getAllRes(nc,listTP.getSelectionModel().getSelectedItem());
        
        itemsCont.clear();
        for (int i=0; i<nc; i++){
            itemsCont.add(enun[i] + " -> " + res[i]);
        } 
        
        //Cambiamos de pantalla
         stage.setScene(sceneCont);
     }
     
     //Para ir la Menu de borrado de test personalizado
     public void SwitchMElim(){
         //Cambiamos de pantalla
         stage.setScene(sceneEliminar);
     }
     
    //Para comenzar al test personalizado seleccionado         
    public void SwitchITestP(){
        contadorTP = 0;
        lblresultado1TP.setText("");
        lblresultado2TP.setText("");
        rdbtn0TP.setSelected(false);
        rdbtn1TP.setSelected(false);
        rdbtn2TP.setSelected(false);
        
        GeneraTestP();
        ActualizaTestP();
        stage.setScene(sceneTestP);
    }

     //Para Ir al Menu del TestD
     public void SwitchMTestD(){ 
         contador = 0;
         lblresultado1.setText("");
         lblresultado2.setText("");
         rdbtn0D.setSelected(false);
         rdbtn1D.setSelected(false);
         rdbtn2D.setSelected(false);
        
         GeneraTestD();
         if (TD.Empty()){
             stage.setScene(sceneTDV);  
         }
         else{
             ActualizaTestD();
             stage.setScene(sceneTestD);
         }
         
     }
            
    // Para ir a la siguiente pregunta del Test Personalizado seleccionado
     public void SwitchSiguienteTP(){ 
         
        if (!TP.halfEmpty()){
            if (contadorTP==0){

             if ((rdbtn0TP.isSelected() && rdbtn0TP.getText()==preguntaTP.getRes()) || 
                 (rdbtn1TP.isSelected() && rdbtn1TP.getText()==preguntaTP.getRes()) || 
                 (rdbtn2TP.isSelected() && rdbtn2TP.getText()==preguntaTP.getRes()) ){

                 lblresultado1TP.setText("¡Correcto! よくできました!");
                 lblresultado2TP.setText("La respuesta correcta es : " + preguntaTP.getRes());
             }
             else{
                 lblresultado1TP.setText("¡Fallaste! 頑張って!");
                 lblresultado2TP.setText("La respuesta correcta era : " + preguntaTP.getRes());
                 TP.addF(preguntaTP);
             }
             TP.delete(preguntaTP);
             contadorTP = 1;
            }
            else{   
              ActualizaTestP();
              lblresultado1TP.setText("");
              lblresultado2TP.setText("");
              rdbtn0TP.setSelected(false);
              rdbtn1TP.setSelected(false);
              rdbtn2TP.setSelected(false);
              contadorTP = 0;
            } 
        }
        else if(!TP.Empty()){
            TP.change();
            ActualizaTestP();
            lblresultado1TP.setText("");
            lblresultado2TP.setText("");
            rdbtn0TP.setSelected(false);
            rdbtn1TP.setSelected(false);
            rdbtn2TP.setSelected(false);
            contadorTP = 0;
        }
        else{
            lblresultado1TP.setText("");
            lblresultado2TP.setText("");
            rdbtn0TP.setSelected(false);
            rdbtn1TP.setSelected(false);
            rdbtn2TP.setSelected(false);
            contadorTP = 0;
            stage.setScene(sceneTPV); 
        }
     }
     
    // Para ir a la siguiente pregunta del TestD
     public void SwitchSiguienteD(){ 
         
        if (!TD.halfEmpty()){
            if (contador==0){
             int codigo, nivel;

             codigo = pregunta.getCod();
             nivel = pregunta.getNivel();
             Date fechahoy = new Date();

             if ((rdbtn0D.isSelected() && rdbtn0D.getText()==pregunta.getRes()[0]) || 
                 (rdbtn1D.isSelected() && rdbtn1D.getText()==pregunta.getRes()[0]) || 
                 (rdbtn2D.isSelected() && rdbtn2D.getText()==pregunta.getRes()[0]) ){

                 lblresultado1.setText("¡Correcto! よくできました!");
                 lblresultado2.setText("La respuesta correcta es : " + pregunta.getRes()[0]);
                 dbh.setNivel(codigo,nivel+1);
                 dbh.setFecha(codigo,fechahoy); 
             }
             else{
                 lblresultado1.setText("¡Fallaste! 頑張って!");
                 lblresultado2.setText("La respuesta correcta era : " + pregunta.getRes()[0]);
                 dbh.setNivel(codigo, nivel-1); 
                 TD.addF(pregunta); 
             }
             TD.delete(pregunta);
             contador = 1;
            }
            else{   
              ActualizaTestD();
              lblresultado1.setText("");
              lblresultado2.setText("");
              rdbtn0D.setSelected(false);
              rdbtn1D.setSelected(false);
              rdbtn2D.setSelected(false);
              contador = 0;
            } 
        }
        else if(!TD.Empty()){
            TD.change();
            ActualizaTestD();
            lblresultado1.setText("");
            lblresultado2.setText("");
            rdbtn0D.setSelected(false);
            rdbtn1D.setSelected(false);
            rdbtn2D.setSelected(false);
            contador = 0;
        }
        else{
            lblresultado1.setText("");
            lblresultado2.setText("");
            rdbtn0D.setSelected(false);
            rdbtn1D.setSelected(false);
            rdbtn2D.setSelected(false);
            contador = 0;
            stage.setScene(sceneTDV); 
        }
     }
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+       
   
    //MAIN
    public static void main(String[] args)
    {
        launch(args);
    }
}