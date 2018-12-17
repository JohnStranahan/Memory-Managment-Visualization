package Controller;

import Model.*;
import Model.Process;
import View.*;
import javafx.stage.Stage;
import javafx.application.Application;

import java.util.Random;

public class MemoryController {

    private StackManager stackManager;
    private TableManager tableManager;

    //Book has these references
    private MemoryModel model;
    private MemoryView view;

    //Maybe manage both View elements from the Controller?
    public MemoryController() throws Exception{
       // view = new MemoryView();
       // stackManager = new StackManager(view.getStack());
       // tableManager = new TableManager(view.getTable());

    }

    public void initView() throws Exception {
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

    public void removeProcess() {

    }

    public void addProcess(){

    }

    public void interact() throws Exception {
        initView();
    }
}
