import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SmallProcess extends Process{

    public SmallProcess(String name, int size, int timeLeft) {
        super(name, size, timeLeft);
        rect = new Rectangle(200, size * 2, Color.GREEN);
    }
}
