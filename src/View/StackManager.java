package View;

import Model.Process;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.Stack;

import View.*;
public class StackManager {
    private int stackIndex;
    private ObservableList<Node> list;
    private int totalMemAllocated;
    private ArrayList<ProcessGui> stored;

    public StackManager(StackPane stack){
        stackIndex = 256;
        this.list = stack.getChildren();
        totalMemAllocated = 0;
        stored = new ArrayList<>();
    }

    /*
        Returns the index of the Process's pane in the stack's children list
     */
    public int getProcessIndex(ProcessGui p) {
        return list.lastIndexOf(p.getPane());
    }

    public boolean addProcess(ProcessGui p){
        boolean result = false;
        if(totalMemAllocated + p.getSize() <= 256){
            list.add(p.getPane());
            stored.add(p);
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
        shiftOn(p, index);
        stored.remove(index);
        totalMemAllocated -= p.getSize();

    }

    /*
    Called every time a change is made to the stack in the event it needs to be re-organized
     */
    public void shiftOn(ProcessGui removed, int index){
        int removedSize = removed.getSize();
        System.out.println(removedSize);
        list.remove(index);
        for(int i = index; i < list.size(); i++){
            StackPane pane = (StackPane)list.get(index);
            list.get(index).setTranslateY(removedSize + pane.getHeight()/2);
            System.out.println(list.get(index).getTranslateY());
        }

        if(list.isEmpty()){
            stackIndex = 256;
        }
    }

    public int getStackIndex(){
        return stackIndex;
    }
}
