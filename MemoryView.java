import javafx.application.Application;
import javafx.beans.NamedArg;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


/*
    Created the Enum Memory Size because I was lazy



 */

public class MemoryView extends Application{

    private static final int TOTAL_MEMORY_ALLOCATED  = 256;
    private static MemorySize MEMORY_SIZE = MemorySize.KB;

    /*
        Overridden method from Application class that sets up a stage(frame of the window) and a scene(where
        all the JavaFx components are held
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
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
        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-color: black");
        stack.setMinWidth(200);
        stack.setMaxWidth(200);
        stack.setMinHeight(512);
        stack.setMaxHeight(512);
        Process small = new SmallProcess("small", 32, 45);
        Process medium = new MediumProcess("medium", 64, 20);
        Process large = new LargeProcess("large", 128, 30);
        stack.getChildren().addAll(large.showProcess(), medium.showProcess(), small.showProcess());
        stack.getChildren().get(0).setTranslateY(-100);
        stack.getChildren().get(1).setTranslateY(128);
        stack.getChildren().get(2).setTranslateY(224);

        //Organize Visual Stack components vertically
        VBox vbox = new VBox(totalMemAllocated, stack, fillerBar);
        vbox.setPadding(new Insets(25));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_LEFT);
        return vbox;
    }

    private GridPane drawControl(){

        //Creating UI Components
        Button simulate = new Button("Simulate");
        Button arrive = new Button("New Process");
        Button depart = new Button("Remove Process");
        TextField inputTime = new TextField();


        //Organize Input Control in a grid
        GridPane grid = new GridPane();
        grid.add(inputTime, 0, 0);
        grid.add(simulate, 1, 0);
        grid.add(arrive, 0, 1);
        grid.add(depart, 1, 1);
        grid.setVgap(15);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private TableView drawProcessTable(){
        //Create Table and its items
        TableView<Process> table = new TableView<Process>();
        table.setEditable(false);
        TableColumn column1 = new TableColumn("Process Name");
        TableColumn column2 = new TableColumn("Size (" + MEMORY_SIZE.toString() + ")");
        TableColumn column3 = new TableColumn("Time Left");
        table.setPrefWidth(260);
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("size"));
        column3.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
        table.getColumns().addAll(column1, column2, column3);

        //Add new rows
        Process process = new SmallProcess("Hacking Client", 28, 11);
        table.getItems().add(process);
        return table;
    }

    /*
        Starts the program
     */
    public static void main(String[] args){
        launch(args);
    }
}
