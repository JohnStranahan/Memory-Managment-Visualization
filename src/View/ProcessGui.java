package View;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class ProcessGui {

    private String name;
    private int size;
    private int timeLeft;
    protected StackPane pane;
    protected Rectangle rect;


    public ProcessGui(String name, int size, int timeLeft){
        this.name = name;
        this.size = size;
        this.timeLeft = timeLeft;
        pane = new StackPane();

    }

    public String getName(){
        return name;
    }

    public int getSize(){
        return size;
    }

    public int getTimeLeft(){
        return timeLeft;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }

    public StackPane getPane(){
        Text text = new Text(this.getName());

        //Create black borders around Process pane
        Line south = new Line(0, 0, 197, 0);
        south.setTranslateY(size - 2);
        south.setStrokeWidth(3);

        Line north = new Line(0, 0, 197, 0);
        north.setTranslateY(-size + 1);
        north.setStrokeWidth(3);

        Line east = new Line(0, -size + 2, 0, size - 2);
        east.setTranslateX(98);
        east.setStrokeWidth(3);

        Line west = new Line(0, -size + 2, 0, size - 2);
        west.setTranslateX(-99);
        west.setStrokeWidth(3);

        pane.getChildren().addAll(rect, text, south, north, east, west);
        return pane;
    }
}
