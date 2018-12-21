package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//visual representation of a small process in the memory stack. color coded green.
public class SmallProcess extends ProcessGui{

    public SmallProcess(String name, int size, int timeLeft) {
        super(name, size, timeLeft);
        rect = new Rectangle(200, size * 2, Color.GREEN);
        buildPane();
    }
}
