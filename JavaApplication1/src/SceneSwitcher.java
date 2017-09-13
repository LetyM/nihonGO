import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

public class SceneSwitcher extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    // class fields for Click-Counter scene     
    int iClickCount = 0;
    Label lblClicks;
    Button btnClickMe;
    Button btnSwitchToScene2;
    Scene scene1;

    // class fields for Add-Subtract scene  
    int iCounter = 0;
    Label lblCounter;
    Button btnAdd;
    Button btnSubtract;
    Button btnSwitchToScene1;
    Scene scene2;

    // class field for stage
    Stage stage;      

    @Override public void start(Stage primaryStage)
    {
        stage = primaryStage;    

        // Build the Click-Counter scene       →37

        lblClicks = new Label();
        lblClicks.setText("You have not clicked the button.");

        btnClickMe = new Button();
        btnClickMe.setText("Click me please!");
        btnClickMe.setOnAction(
            e -> btnClickMe_Click() );

        btnSwitchToScene2 = new Button();
        btnSwitchToScene2.setText("Switch!");
                btnSwitchToScene2.setOnAction(
            e -> btnSwitchToScene2_Click() );

        VBox pane1 = new VBox(10);
        pane1.getChildren().addAll(lblClicks, btnClickMe,
            btnSwitchToScene2);

        scene1 = new Scene(pane1, 250, 150);


        // Build the Add-Subtract scene       →59

        lblCounter = new Label();
        lblCounter.setText(Integer.toString(iCounter));

        btnAdd = new Button();
        btnAdd.setText("Add");
        btnAdd.setOnAction(
            e -> btnAdd_Click() );

        btnSubtract = new Button();
        btnSubtract.setText("Subtract");
        btnSubtract.setOnAction(
            e -> btnSubtract_Click() );

        btnSwitchToScene2 = new Button();
        btnSwitchToScene2.setText("Switch!");
        btnSwitchToScene2.setOnAction(
            e -> btnSwitchToScene1_Click() );

        HBox pane2 = new HBox(10);
        pane2.getChildren().addAll(lblCounter, btnAdd,
            btnSubtract, btnSwitchToScene2);

       scene2 = new Scene(pane2, 300, 75);

        // Set the stage with scene 1 and show the stage       →84
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Scene Switcher");
        primaryStage.show();
    }


    // Event handlers for scene 1       →91

    public void btnClickMe_Click()
    {
        iClickCount++;
        if (iClickCount == 1)
        {
            lblClicks.setText("You have clicked once.");
        }
        else
        {
            lblClicks.setText("You have clicked "
                + iClickCount + " times." );
        }
    }

    private void btnSwitchToScene2_Click()
    {
        stage.setScene(scene2);
    }


    // Event handlers for scene 2       →112

    private void btnAdd_Click()
    {
        iCounter++;
        lblCounter.setText(Integer.toString(iCounter));
    }

    private void btnSubtract_Click()
    {
        iCounter--;
        lblCounter.setText(Integer.toString(iCounter));
    }

    private void btnSwitchToScene1_Click()
    {
        stage.setScene(scene1);
    }

}