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
        return list.lastIndexOf(p.getPane());
    }

    public boolean addProcess(ProcessGui p){
        boolean result = false;
        if(totalMemAllocated + p.getSize() <= 256 && p != null){
            list.add(p.getPane());
            stackIndex -= p.getSize();
            int index = getProcessIndex(p);
            System.out.println("Add: " + p.getName() + " " + index);
            list.get(index).setTranslateY(stackIndex);
            stackIndex -= p.getSize();
            totalMemAllocated += p.getSize();
            result = true;
        }
        return result;
    }

    public void removeProcess(ProcessGui p){
        int index = getProcessIndex(p);
        System.out.println("Remove: " + p.getName() + " " + index);
        list.remove(index);
        stackIndex += p.getSize() * 2;
        totalMemAllocated -= p.getSize();

    }

    /*
    Called every time a change is made to the stack in the event it needs to be re-organized
     */
    public void shift(){
        if(list.isEmpty()){
            stackIndex = 256;
        }
        else{

        }
    }

    public int getStackIndex(){
        return stackIndex;
    }
}
