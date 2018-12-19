package Controller;

import Model.*;
import Model.Process;
import View.*;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;

import java.util.Random;

public class MemoryController {

    //Book has these references
    private MemoryView view;
    public StackManager stackManager;
    public TableView<ProcessGui> table;
    
    public MemoryController(){
        view = new MemoryView(this);
        stackManager = view.getStackManager();
        table = view.getTable();
        Application.launch(MemoryView.class, null);
    }

    public void initView(){
        Application.launch(MemoryView.class, null);
    }

    public ProcessGui generateProcess() {
        Random rand = new Random();
        ProcessGui pg;
        
        //Size is between 1 and 256(max possible size our memory can hold)
        int size = rand.nextInt(247) + 10;
        //Amount of time the process is alive is completely random as it should be
        int timeLeft = rand.nextInt(30) + 1;
        int pid = rand.nextInt(99999999) + 1;
        String name = "" + pid;

        Process p = new Process(name , size, timeLeft, pid);

        if (size <= 32) {
            pg = new SmallProcess("Small", size, timeLeft);
        } else if (size <= 128) {
            pg = new MediumProcess("Medium", size, timeLeft);
        } else {
            pg = new LargeProcess("Large", size, timeLeft);
        }
        return pg;
    }

    public void countdown() {

    }

    public void clickRemoveProcess() {
        ProcessGui p = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(p);
        stackManager.removeProcess(p);
    }

    public void clickNewProcess(){
        ProcessGui p = generateProcess();
        if (stackManager.addProcess(p)) {
            table.getItems().add(p);
        }
    }

    public void addProcess(){

        /*
        generate new process p
        model.getFreeSpace
        if p.getSize > freeSpace
            add to model.waitingQueue
        else
            add to view.stack
            add to view.table
         */
    }

    public void interact() throws Exception {
        initView();
    }
}
