package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//visual representation of a medium process in the memory stack. color coded yellow.
public class MediumProcess extends ProcessGui {

    public MediumProcess(String name, int size, int timeLeft){
        super(name, size, timeLeft);
        rect = new Rectangle(200, size * 2, Color.YELLOW);
        buildPane();
    }
}
