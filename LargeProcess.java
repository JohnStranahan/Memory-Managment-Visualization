import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LargeProcess extends Process {

    public LargeProcess(String name, double size, int timeLeft){
        super(name, size, timeLeft);
        rect = new Rectangle(200, size * 2, Color.RED);
    }
}
