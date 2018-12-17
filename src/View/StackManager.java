package View;

import Model.Process;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import View.*;
public class StackManager {
    private int stackIndex;
    private ObservableList<Node> list;
    private int totalMemAllocated;

    public StackManager(StackPane stack){
        stackIndex = 256;
        this.list = stack.getChildren();
        totalMemAllocated = 0;
    }

    /*
        Returns the index of the Process's pane in the stack's children list
     */
    public int getProcessIndex(ProcessGui p) {
        return list.indexOf(p.getPane());
    }

    public boolean addProcess(ProcessGui p){
        boolean result = false;
        if(totalMemAllocated + p.getSize() <= 256){
            list.add(p.getPane());
            stackIndex -= p.getSize();
            list.get(getProcessIndex(p)).setTranslateY(stackIndex);
            stackIndex -= p.getSize();
            totalMemAllocated += p.getSize();
            result = true;
        }
        return result;
    }

    public void removeProcess(ProcessGui p){
        list.remove(getProcessIndex(p));
        stackIndex += p.getSize() * 2;
        totalMemAllocated -= p.getSize();

    }

    public int getStackIndex(){
        return stackIndex;
    }
}
