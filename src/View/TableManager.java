package View;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
/*
In the current system, each process is added as the first row resulting in a bottom-up view. If we had this class that managed the table
we could keep an internal index so all current processes would be viewed top-down.
 */
public class TableManager {
    private ObservableList<Node> list;
    public TableManager(TableView table){
    }
}
