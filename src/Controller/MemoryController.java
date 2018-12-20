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
        waitingProcess = new LinkedList<Process>();
        buddyAllocation = new BuddyAllocation();
    }

    public void initView() throws Exception {
        Application.launch(MemoryView.class, null);
    }

    public ProcessGui generateProcess() {
        Random rand = new Random();
        ProcessGui pg;

        int size = rand.nextInt(245) + 10;
        int timeLeft = rand.nextInt(9) + 1;
        int pid = rand.nextInt(99999999) + 1;
        String name = "" + pid;
        System.out.println(size);

        Process p = new Process(name , size, timeLeft, pid);
        System.out.println(p.getName());
        MemoryNode fnode = buddyAllocation.getMNode();
        while(fnode != null) {
        	System.out.println(fnode.getAllocationArray().length);
        	System.out.println(fnode.isAllocated());
        	fnode = fnode.getNext();
        }
        boolean fits = buddyAllocation.allocateProcess(p);
        
        
        
        if (!fits) {
            waitingProcess.add(p);
//            return null;
        }
//        else {
    	if (size <= 32) {
            pg = new SmallProcess(name, size, timeLeft);
        } else if (size <= 128) {
            pg = new MediumProcess(name, size, timeLeft);
        } else {
            pg = new LargeProcess(name, size, timeLeft);
        }
        return pg;
//        }


        
    }

    public void update(TableView<ProcessGui> table, StackManager manager) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
//                    System.out.println("ree");
//                    System.out.println(buddyAllocation.getMNode().getStoredProcess().getName());
                    decrementCounter(table, manager);

                }
                catch (InterruptedException e) {
                    System.out.println("no");
                }
            }
        }, 0, 1250);
    }

    public void decrementCounter(TableView<ProcessGui> table, StackManager manager) throws InterruptedException {
        Iterator<ProcessGui> iter = table.getItems().iterator();
        MemoryNode processNode = buddyAllocation.getMNode();

        while (iter.hasNext()) {
            ProcessGui p = iter.next();
//            processNode = processNode.getNext();
            if (p.getTimeLeft() < 1) {
                //Literally have 0 idea why this works. But it helps deal with some JavaFX threading issue
                Platform.runLater(()->{
                    manager.removeProcess(p); //Removes P from graphic stack
                    table.getItems().remove(p);// Removes P from table
                });
                buddyAllocation.endProcess(processNode);
                
            }
            else {

//                MemoryNode processNode = buddyAllocation.getMNode();
//            	processNode = processNode.getNext();
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

