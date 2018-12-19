package Controller;

import Model.*;
import Model.Process;
import View.*;
import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.application.Application;

import java.util.*;

public class MemoryController {
    Queue<Process> waitingProcess;
    BuddyAllocation buddyAllocation;

    public MemoryController() throws Exception{
        waitingProcess = new LinkedList<>();
        buddyAllocation = new BuddyAllocation();
    }

    public void initView() throws Exception {
        Application.launch(MemoryView.class, null);
    }

    public ProcessGui generateProcess() {
        Random rand = new Random();
        ProcessGui pg;

        int size = rand.nextInt(247) + 10;
        int timeLeft = rand.nextInt(30) + 1;
        int pid = rand.nextInt(99999999) + 1;
        String name = "" + pid;
        System.out.println(size);

        Process p = new Process(name , size, timeLeft, pid);
        if (!buddyAllocation.allocateProcess(p)) {
            waitingProcess.add(p);
        }

        if (size <= 32) {
            pg = new SmallProcess("Small", size, timeLeft);
        } else if (size <= 128) {
            pg = new MediumProcess("Medium", size, timeLeft);
        } else {
            pg = new LargeProcess("Large", size, timeLeft);
        }
        return pg;
    }

    public void update(TableView<ProcessGui> table, StackManager manager) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    tableTicker(table, manager);
                }
                catch (InterruptedException e) {

                }
            }
        }, 0, 1250);
    }

    public void tableTicker(TableView<ProcessGui> table, StackManager manager) throws InterruptedException {
        Iterator<ProcessGui> iter = table.getItems().iterator();

        while (iter.hasNext()) {
            ProcessGui p = iter.next();

            if (p.getTimeLeft() < 1) {
                //Literally have 0 idea why this works. But it helps deal with some JavaFX threading issue
                Platform.runLater(()->{
                    manager.removeProcess(p); //Removes P from graphic stack
                    table.getItems().remove(p);// Removes P from table
                });
            }
            else {
                p.setTimeLeft(p.getTimeLeft()-1);
            }
        }
        table.refresh();
    }


    public void removeProcess() {

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
