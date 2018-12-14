package View;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

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
            list.add(p.showProcess());
            if(list.isEmpty()){
                list.get(nodeIndex).setTranslateY(stackIndex-p.getSize());
            }
            else{
                stackIndex -= p.getSize();
                list.get(nodeIndex).setTranslateY(stackIndex);
            }
            stackIndex -= p.getSize();
            nodeIndex++;
            totalMemAllocated += p.getSize();
            result = true;
            stored.add(p);
        }
        return result;
    }

    public void removeProcess(ProcessGui p){
        if (!stored.contains(p)) {

        }
        else {
            list.remove(p.deleteProcess());
        }
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
