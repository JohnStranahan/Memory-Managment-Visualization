package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//visual representation of a large process in the stack. color coded red.
public class LargeProcess extends ProcessGui {

    public LargeProcess(String name, int size, int timeLeft){
        super(name, size, timeLeft);
        rect = new Rectangle(200, size * 2, Color.RED);
        buildPane();
    }
}
