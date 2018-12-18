package View;

import Controller.MemoryController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;

import java.util.*;

import java.util.concurrent.TimeUnit;

/*
    Created the Enum Memory Size because I was lazy



 */

public class MemoryView extends Application{

    private static final int TOTAL_MEMORY_ALLOCATED  = 256;
    private static MemorySize MEMORY_SIZE = MemorySize.KB;
    private StackPane stack = new StackPane();
    private TableView<ProcessGui> table = new TableView<>();
    private StackManager manager = new StackManager(stack);
    private MemoryController controller;
    /*
            Overridden method from Application class that sets up a stage(frame of the window) and a scene(where
            all the JavaFx components are held
         */

    public void start(Stage primaryStage) throws Exception {

        controller = new MemoryController();
        //Construct the highest level pane
        BorderPane border = new BorderPane();
        ObservableList borderList = border.getChildren();

        //Set up MenuBar at the top of GUI
        HBox hbox = drawMenuBar();
        border.setTop(hbox);

        //Set up the visual Memory Stack tool on the left of the GUI
        VBox vbox = drawMemoryStack();
        border.setLeft(vbox);

        //Set up the UI Control in the center of the GUI
        GridPane grid = drawControl();
        border.setCenter(grid);

        //Set up Processes Table on the right of the GUI
        TableView table = drawProcessTable();
        border.setRight(table);

        //Creating a scene object
        Scene borderScene = new Scene(border, 850, 650);


        primaryStage.setTitle("Memory Manager z3000");
        primaryStage.setScene(borderScene);
        primaryStage.show();
    }

    private HBox drawMenuBar(){
        //Creating Menu Options
        Menu file = new Menu("File");
        Menu chart = new Menu("Chart");

        //Creating Nested Menu Options
        MenuItem reset = new MenuItem("Reset");
        RadioMenuItem on = new RadioMenuItem("On");
        RadioMenuItem off = new RadioMenuItem("Off");
        off.setSelected(true);
        SeparatorMenuItem sep = new SeparatorMenuItem();
        ToggleGroup chartGroup = new ToggleGroup();
        chartGroup.getToggles().addAll(on, off);
        file.getItems().add(reset);
        //Have the chart screen off by default
        off.setSelected(true);
        chart.getItems().addAll(on, sep, off);

        //Create extra space beyond menu options
        Region spacer = new Region();
        spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.SOMETIMES);

        //Build the Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file, chart);

        //Append menuBar to Hbox to create single row
        HBox hbox = new HBox(menuBar, spacer); //addHBox()
        return hbox;
    }

    private VBox drawMemoryStack(){
        //Creating Visual Stack components
        Label totalMemAllocated = new Label("Total Memory Allocated: " + TOTAL_MEMORY_ALLOCATED + " (" + MEMORY_SIZE.toString() + ")");
        Label totalMemUsed = new Label("Total Memory Used: ");
        ProgressBar pBar = new ProgressBar();
        pBar.setProgress(.75);
        HBox fillerBar = new HBox(totalMemUsed, pBar);
        fillerBar.setAlignment(Pos.BOTTOM_LEFT);
        StackPane backGround = new StackPane();

        //10 pixel border around memory stack
        backGround.setStyle("-fx-background-color: blue");
        backGround.setMinWidth(210);
        backGround.setMaxWidth(210);
        backGround.setMinHeight(522);
        backGround.setMaxHeight(522);
        backGround.getChildren().add(stack);

        stack.setStyle("-fx-background-color: gray");
        stack.setMinWidth(200);
        stack.setMaxWidth(200);
        stack.setMinHeight(512);
        stack.setMaxHeight(512);

        //Organize Visual Stack components vertically
        VBox vbox = new VBox(totalMemAllocated, backGround, fillerBar);
        vbox.setPadding(new Insets(25));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_LEFT);
        return vbox;
    }

    private GridPane drawControl() throws InterruptedException{

        //Creating UI Components
        Button simulate = new Button("Simulate");
        Button arrive = new Button("New Process");
        arrive.setOnAction(arriveHandler);
        Button depart = new Button("Remove Process");
        depart.setOnAction(removeHandler);
        TextField inputTime = new TextField();
        inputTime.setPromptText("(secs)");



        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    tableTicker();
                }
                catch (InterruptedException e) {

                }
            }
        }, 0, 1250);




        //Organize Input Control in a grid
        GridPane grid = new GridPane();
        grid.add(inputTime, 0, 0);
        grid.add(arrive, 0, 1);
        grid.add(simulate, 1, 0);
        grid.add(depart, 1, 1);
        grid.setVgap(15);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        return grid;


    }

    private TableView drawProcessTable(){
        //Create Table and its items

        table.setEditable(true);
        TableColumn column1 = new TableColumn("Process Name");
        TableColumn column2 = new TableColumn("Size (" + MEMORY_SIZE.toString() + ")");
        TableColumn column3 = new TableColumn("Time Left");
        table.setPrefWidth(260);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("size"));
        column3.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
        table.getColumns().addAll(column1, column2, column3);

        //Add new rows

        return table;
    }

    public StackPane getStack(){
        return stack;
    }

    public TableView<ProcessGui> getTable() {
        return table;
    }


    public void tableTicker() throws InterruptedException {
        Iterator<ProcessGui> iter = table.getItems().iterator();

        while (iter.hasNext()) {
            ProcessGui p = iter.next();

            if (p.getTimeLeft() < 1) {
                //Literally have 0 idea why this works. But it helps deal with some JavaFX threading issue
                Platform.runLater(()->{
                    manager.removeProcess(p); //Removes P from graphic stack
                    table.getItems().remove(p);// Removes P from table
                });
            }
            else {
                p.setTimeLeft(p.getTimeLeft()-1);
            }
        }
        table.refresh();
    }

    private EventHandler<ActionEvent> arriveHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ProcessGui p = controller.generateProcess();
            if (manager.addProcess(p)) {
                table.getItems().add(p);
            }
            event.consume();

            //controller.clickNewProcess()
        }
    };

    private EventHandler<ActionEvent> removeHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ProcessGui p = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(p);
            manager.removeProcess(p);
            event.consume();

            //controller.clickRemoveProcess()
        }
    };
}
