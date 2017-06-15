package Paneles;

import database.*;
import java.util.Date;
import javafx.application.*;
import static javafx.application.Application.launch;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.util.Random;


/**
 * 
 * @author Secretaria
 */
 
public class Menu extends Application {

    //Enlace con la base de datos
    private IDatabaseHandler dbh;
    
    //Respuesta de la pregunta actual del Test Diario
    private String respuesta;
    
    //Listado de preguntas para randomizarlas y la variable para recorrerlo
//    private int[] listado = new int[50];
//    private int ilistado;
    
    //Botones de cambio de ventana
    private Button btnMTest;
    private Button btnMBusqueda;
    private Button btnMPrincipal;
    private Button btnMTest1;
    private Button btnMTest2;
    private Button btnMLeccion;
    private Button btnMCrearLeccion;
    private Button btnMLeccion1;
    private Button btnSiguiente;
    private Button btnMTestD;
    
    //Elementos de la ventana de Crear Leccion
    private TextField txtCLeccion;
    
    //Elementos de la ventada de Test1
    RadioButton rdbtn0 = new RadioButton();
    RadioButton rdbtn1 = new RadioButton();
    RadioButton rdbtn2 = new RadioButton();
    final ToggleGroup group = new ToggleGroup();
    
    Label lblPreguntaT1 = new Label();
    VBox paneMTest1 = new VBox(10);
    HBox paneRespuestasT1 = new HBox(10);
    
    //Elementos de la ventada de Test Diario
    RadioButton rdbtn0D = new RadioButton();
    RadioButton rdbtn1D = new RadioButton();
    RadioButton rdbtn2D = new RadioButton();
    final ToggleGroup groupD = new ToggleGroup();
    
    Label lblPreguntaTD = new Label();
    VBox paneMTestD = new VBox(10);
    HBox paneRespuestasTD = new HBox(10);
    
    
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
    private Scene sceneTestD;    //Pantalla Test Diario


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
        
        //Creamos el botÃ³n de TestD
        btnMTestD = new Button("Test Diario");
        btnMTestD.setOnAction(e-> SwitchTestD());
        
        //Creamos el botÃ³n para volver al MenÃº Principal
        btnMPrincipal = new Button("Volver");
        btnMPrincipal.setOnAction(e -> SwitchMenuInicial()); 
         
        //AÃ±adimos los botones a un VBox
        VBox paneMTest = new VBox(10,btnMTest1,btnMTest2,btnMTestD,btnMPrincipal);
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
        
        //Creamos el panel con las respuestas
        //HBox paneRespuestasT1 = new HBox(20, rdbtn0, rdbtn1, rdbtn2);
        paneRespuestasT1.getChildren().addAll(rdbtn0, rdbtn1, rdbtn2);
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
        
        //********************** MENU TEST DIARIO ***********************************************       
       //Creamos el boton para volver a la Pantalla de los Test
        btnMTest = new Button("Volver");
        btnMTest.setOnAction(e -> SwitchTest());
        
        //Creamos el boton para pasar a la siguiente pregunta
        btnSiguiente = new Button("Aceptar");
//        btnSiguiente.setOnAction(e-> SwitchSiguienteD(ilistado));
        btnSiguiente.setOnAction(e-> SwitchSiguienteD());
        
        //Creamos el panel con las respuestas
        paneRespuestasTD.getChildren().addAll(rdbtn0D, rdbtn1D, rdbtn2D);
        paneRespuestasTD.setPadding(new Insets(10));
        paneRespuestasTD.setAlignment(Pos.BASELINE_RIGHT);
        
        //Creamos el panel con los botones
        HBox paneBotonesTD = new HBox(20,btnMTest,btnSiguiente);
        paneBotonesTD.setPadding(new Insets(10));
        paneBotonesTD.setAlignment(Pos.CENTER_RIGHT);

        //AÃ±adimos el VBox
        paneMTestD.getChildren().addAll(lblPreguntaTD,paneRespuestasTD,paneBotonesTD);
        paneMTestD.setSpacing(space);
        paneMTestD.setAlignment(Pos.CENTER);
        
        //Definimos el Scene
        sceneTestD = new  Scene(paneMTestD,ancho,largo); 
         
    }
    
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+  
    
    //Función que genera la pregunta del test
    private void GeneraTest(int i)
    {
//      Date fechahoy = new Date();
      String[] res = dbh.gettest(i); //Consultamos en la base de datos
       //Creamos el Label para la pregunta 
        lblPreguntaT1.setText(res[0]);
        
       //Creamos los radio buttons
        rdbtn0.setToggleGroup(group); 
        rdbtn0.setText(res[1]);
        rdbtn1.setToggleGroup(group);
        rdbtn1.setText(res[2]);
        rdbtn2.setToggleGroup(group);
        rdbtn2.setText(res[3]);  
    }
    
      //La idea es que aqui generemos una lista de las preguntas para que aparecezcan todas pero en cualquier orden
//    private int[] GeneraListado(int[] listado){
//      TestDia res = dbh.getPreguntas();
//      Random rand = new Random(); 
//      int num_preguntas = res.getNumPreguntas();
//      
//      //Inicializamos a 0. Seguramente haya una forma más elegante de hacer esto
//      for(int j=0; j<50; j++){
//        listado[j] = 0; 
//      }
//      
//      for(int j=0; j<num_preguntas; j++){
//        listado[j] = rand.nextInt(num_preguntas); 
//      }
//      
//      return listado;
//    }
    
     //Función para el test Diario
     //Aqui le pasariamos le listado he iríamos incrementando para sacar la siguiente pregunta
     //No consigo que se actualice el numero para que pregunte la siguiente del listado
//    private int GeneraTestD(int[] listado, int ilistado)
      private void GeneraTestD(){
        TestDia res = dbh.getPreguntas(); //Consultamos en la base de datos
        Pregunta pregunta;
        Random rand = new Random();
        int num_preguntas = res.getNumPreguntas();
      //      int num_preguntas = res.getNumPreguntas();
         int i;
         int Res0, Res1, Res2;
        
        i = rand.nextInt(num_preguntas);
        pregunta = res.getPregunta(i);
        respuesta = pregunta.getRes()[0];
        
        
        Res0 = rand.nextInt(3);
        Res1 = rand.nextInt(3);
        while (Res1==Res0){
            Res1 = rand.nextInt(3);
        }
        Res2 = rand.nextInt(3);
        while (Res2==Res0 || Res2==Res1){
            Res2 = rand.nextInt(3);
        }
//      if(ilistado<=num_preguntas){
      //Creamos el Label para el enunciado 
//      lblPreguntaTD.setText(res.getPregunta(listado[ilistado]).getEnun());
        lblPreguntaTD.setText(pregunta.getEnun());
      
      //Creamos los radio buttons
         rdbtn0D.setToggleGroup(groupD); 
//      rdbtn0D.setText(res.getPregunta(listado[ilistado]).getRes()[0]);
        rdbtn0D.setText(pregunta.getRes()[Res0]);
         rdbtn1D.setToggleGroup(groupD);
//      rdbtn1D.setText(res.getPregunta(listado[ilistado]).getRes()[1]);
         rdbtn1D.setText(pregunta.getRes()[Res1]);
         rdbtn2D.setToggleGroup(groupD);
//      rdbtn2D.setText(res.getPregunta(listado[ilistado]).getRes()[2]); 
        rdbtn2D.setText(pregunta.getRes()[Res2]);
      
//       ilistado++;
//      }
        
//      return ilistado;
      
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
      rdbtn0.setSelected(false);
      rdbtn1.setSelected(false);
      rdbtn2.setSelected(false);
      GeneraTest(1);
     }
     
     //Para Ir al Menu del Test2
     public void SwitchTest2()
     {  
      stage.setScene(sceneTest2);
     }
     
     //Para Ir al Menu del TestD
     public void SwitchTestD()
     {  
      GeneraTestD();
      stage.setScene(sceneTestD);
     }

//     public void SwitchSiguienteD(int ilistado)
     public void SwitchSiguienteD()
     { 
        if ((rdbtn0D.isSelected() && rdbtn0D.getText()==respuesta) || 
            (rdbtn1D.isSelected() && rdbtn1D.getText()==respuesta) || 
            (rdbtn2D.isSelected() && rdbtn2D.getText()==respuesta) ){
            GeneraTestD();
        }
        rdbtn0D.setSelected(false);
        rdbtn1D.setSelected(false);
        rdbtn2D.setSelected(false);
           
     }
 //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+       
   
    //MAIN
    public static void main(String[] args)
    {
        launch(args);
    }
}