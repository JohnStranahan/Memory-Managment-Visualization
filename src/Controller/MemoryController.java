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
    FirstFitAllocator firstFitAllocator;

    public MemoryController() throws Exception{
        waitingProcess = new LinkedList<>();
        firstFitAllocator = new FirstFitAllocator();
    }

    public void initView() throws Exception {
        Application.launch(MemoryView.class, null);
    }

    public ProcessGui generateProcess() {
        Random rand = new Random();
        ProcessGui pg;

        int size = rand.nextInt(247) + 10;
        int timeLeft = rand.nextInt(10) + 1;
        int pid = rand.nextInt(99999999) + 1;
        String name = "" + pid;
        System.out.println(size);

        Process p = new Process(name , size, timeLeft, pid);
        if (!firstFitAllocator.allocateProcess(p)) {
            waitingProcess.add(p);
        }

        if (size <= 32) {
            pg = new SmallProcess(name, size, timeLeft);
        } else if (size <= 128) {
            pg = new MediumProcess(name, size, timeLeft);
        } else {
            pg = new LargeProcess(name, size, timeLeft);
        }
        return pg;
    }

    public void update(TableView<ProcessGui> table, StackManager manager) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("ree");
                    decrementCounter(table, manager);
                }
                catch (InterruptedException e) {
                    System.out.println("no");
                }
            }
        }, 0, 1000);
    }

    public void decrementCounter(TableView<ProcessGui> table, StackManager manager) throws InterruptedException {
        Iterator<ProcessGui> iter = table.getItems().iterator();
        MemoryNode processNode = firstFitAllocator.getHead();


        while (iter.hasNext()) {
            ProcessGui p = iter.next();
            if (p.getTimeLeft() == 0) {
                //Literally have 0 idea why this works. But it helps deal with some JavaFX threading issue
                Platform.runLater(()->{
                    manager.removeProcess(p); //Removes P from graphic stack
                    table.getItems().remove(p);// Removes P from table
                });
                firstFitAllocator.endProcess(processNode);
            }
            else {
                System.out.println(processNode.getStoredProcess().toString());
                while (!processNode.getStoredProcess().getName().equals(p.getName())) {
                    processNode = processNode.getNext();
                }
                processNode.getStoredProcess().setTTL(processNode.getStoredProcess().getTTL()-1);
                p.setTimeLeft(p.getTimeLeft()-1);
            }
        }
        table.refresh();
    }


    public void removeProcess() {

    }




    public void interact() throws Exception {
        initView();

//        TableView<ProcessGui> table = MemoryView.getTable();
//        StackManager manager = MemoryView.getManager();
//        update(table, manager);

    }
}
