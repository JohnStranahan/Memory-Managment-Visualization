package Controller;
import Model;
import View;
import java.util.Queue;
import java.util.Random;

public class Main {
    Queue waitingProcess = new Queue();
    buddyAllocation memory = new buddyAllocation();
    public static void main(String[] args) {
//        buddyAllocation memory = new buddyAllocation();
    }

    public Queue getWaitingProcess() {
        return waitingProcess;
    }



//    public void setWaitingProcess(Queue waitingProcess) {
//        this.waitingProcess = waitingProcess;
//    }

    public static void addProcess(int pid, String name, int size, int ttl){
        /*
        *the fields of the new process will be generated at random in the view
        *and passed into the controller
        */
        Process newProcess = new Process(name, size, ttl, pid);
        memory.allocateProcess(newProcess);
    }

    public static void endProcess(){

    }


}
