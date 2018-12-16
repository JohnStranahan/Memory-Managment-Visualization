package Controller;

import Model.BuddyAllocation;
import Model.Process;
import View.*;
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
        
        //Size is between 1 and 256(max possible size our memory can hold)
        int size = rand.nextInt(247) + 10;
        //Amount of time the process is alive is completely random as it should be
        int timeLeft = rand.nextInt(30) + 1;
        int pid = rand.nextInt(99999999) + 1;
        String name = "" + pid;

        Process p = new Process(name , size, timeLeft, pid);
        //To be determined based on Gene's and Nick's discretion.

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
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {


                }
                catch (InterruptedException e) {

                }
            }
        }, 0, 1000);
    }

    public void removeProcess() {

    }

    public void interact() throws Exception {
        initView();
    }
}
