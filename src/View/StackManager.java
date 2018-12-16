package View;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import View.*;
public class StackManager {
    private int nodeIndex, stackIndex;
    private ObservableList<Node> list;
    private ProcessGui curr;
    private int totalMemAllocated;
    private ArrayList<ProcessGui> stored;

    public StackManager(StackPane stack){
        nodeIndex = 0;
        stackIndex = 256;
        this.list = stack.getChildren();
        totalMemAllocated = 0;
        stored = new ArrayList<>();
    }

    public void setCurrentProcess(ProcessGui p){
        curr = p;
    }

    public ProcessGui getCurrentProcess(){
        return curr;
    }

    public boolean addProcess(ProcessGui p){
        boolean result = false;
        if(totalMemAllocated + p.getSize() <= 256){
            setCurrentProcess(p);
            list.add(p.getPane());
            stackIndex -= p.getSize();
            list.get(nodeIndex).setTranslateY(stackIndex);
            stackIndex -= p.getSize();
            nodeIndex++;
            totalMemAllocated += p.getSize();
            result = true;
            stored.add(p);
        }
        return result;
    }

    public void removeProcess(ProcessGui p){
        list.remove(nodeIndex-1);
        nodeIndex--;

    }

    public void killProcess(ProcessGui p){
        setCurrentProcess(p);

    }

    public int getNodeIndex(){
        return nodeIndex;
    }

    public int getStackIndex(){
        return stackIndex;
    }
}
