package Controller;

import Model.*;
import Model.Process;
import View.*;
import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.application.Application;

import java.util.*;

//This class controls the GUI, generates processes, updates the process table etc.
public class MemoryController {
    Queue<Process> waitingProcess;
    FirstFitAllocator firstFitAllocator;

    public MemoryController() throws Exception {
        waitingProcess = new LinkedList<Process>();
        firstFitAllocator = new FirstFitAllocator();
    }

    public void initView() throws Exception {
        Application.launch(MemoryView.class, null); //launches GUI
    }

    public ProcessGui generateProcess() {
        Random rand = new Random();
        ProcessGui pg;

        int size = rand.nextInt(245) + 10;
        int timeLeft = rand.nextInt(9) + 1;
        int pid = rand.nextInt(99999999) + 1;  //generating the process fields randomly
        String name = "" + pid;

        Process p = new Process(name, size, timeLeft, pid);
        if (!firstFitAllocator.allocateProcess(p)) { //checking to see if process will fit
            waitingProcess.add(p);
        }
        if (size <= 32) {
            pg = new SmallProcess(name, size, timeLeft);
        } else if (size <= 128) {
            pg = new MediumProcess(name, size, timeLeft);  //creating different sized processes based on the randomly generated size
        } else {
            pg = new LargeProcess(name, size, timeLeft);
        }
        return pg;
    }
    //updates the table and stack. used when a process is added/deleted or when its runtime ends
    public void update(TableView<ProcessGui> table, StackManager manager) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    decrementCounter(table, manager);  
                    fillWaitingProcesses(table, manager);  //decrementing the counter and moving any waiting processes up in the queue or into the memory stack
                } catch (InterruptedException e) {
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
            if (p.getTimeLeft() < 1) {
                Platform.runLater(() -> {
                    manager.removeProcess(p); //Removes P from graphic stack
                    table.getItems().remove(p);// Removes P from table
                });
                firstFitAllocator.endProcess(processNode);

            } else {
                while (!processNode.getStoredProcess().getName().equals(p.getName())) {
                    processNode = processNode.getNext();
                }
                processNode.getStoredProcess().setTTL(processNode.getStoredProcess().getTTL() - 1);
                p.setTimeLeft(p.getTimeLeft() - 1);
            }
        }
        table.refresh();
    }


    public void fillWaitingProcesses(TableView<ProcessGui> table, StackManager manager) {
        if (!waitingProcess.isEmpty()) {
            if (firstFitAllocator.allocateProcess(waitingProcess.peek())) {
                System.out.println("ree");
                Process p = waitingProcess.remove();
                ProcessGui pg;
                if (p.getSize() <= 32) {
                    pg = new SmallProcess(p.getName(), p.getSize(), p.getTTL());
                } else if (p.getSize() <= 128) {
                    pg = new MediumProcess(p.getName(), p.getSize(), p.getTTL());
                } else {
                    pg = new LargeProcess(p.getName(), p.getSize(), p.getTTL());
                }
                Platform.runLater(() -> {
                    manager.addProcess(pg);
                    table.getItems().add(pg);
                });
            }
        }

    }


    public void interact() throws Exception {
        initView();
    }
}
