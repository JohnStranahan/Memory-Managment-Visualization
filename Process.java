import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class Process {

    private String name;
    private double size;
    private int timeLeft;
    protected Rectangle rect;


    public Process(String name, double size, int timeLeft){
        this.name = name;
        this.size = size;
        this.timeLeft = timeLeft;
    }

    public String getName(){
        return name;
    }

    public double getSize(){
        return size;
    }

    public int getTimeLeft(){
        return timeLeft;
    }

    public StackPane showProcess(){
        Text text = new Text(this.getName());
        StackPane pane = new StackPane(rect, text);
        return pane;
    }
}
