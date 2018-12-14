package Controller;
import Model.MemoryNode;
import Model.Process;
import Model.BuddyAllocation;
import View.*;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.application.Application;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Driver {
    public static void main(String[] args) {
        System.out.println("hello");
    	MemoryController controller = new MemoryController();
    	controller.interact();
    	Queue<Process> waitingProcess = new LinkedList<>();
    	Process newProcess = new Process("process", 63,5,10);

    	BuddyAllocation BA = new BuddyAllocation();
		BA.allocateProcess(newProcess);
		System.out.println(BA.getMNode().getStoredProcess().getName());

        //Launches the GUI
//        Application.launch(MemoryView.class, null);
    	boolean stop = false;
    	do {
            MemoryNode n = BA.getMNode();
            while((n != null)){

                if(n.getStoredProcess().getTTL() == 0){
                    BA.endProcess(n);
                }
                else{
                    n = n.getNext();
                }

            }
            // random process generation and allocation


            if(waitingProcess.isEmpty()) {
    				Process p = waitingProcess.remove();
    				BA.allocateProcess(p);
//    				notify view
//    				notify model
    			}
            else{
                Random ran = new Random();
                int rSize = ran.nextInt(256);
                int rTTL = ran.nextInt(10) +5;
                int rPid = ran.nextInt();
                Process randomProcess = new Process(ran.toString(), rSize,rTTL,rPid);
                if(randomProcess.getSize() > BA.getMNode().getAllocationArray().length){
                    waitingProcess.add(randomProcess);
                }
                else{
                    BA.allocateProcess(randomProcess);
                }
            }

//            System.out.println(BA.getMNode().getStoredProcess().getName());

//    		if(user add process) {
//
//    			if(waitingProcess.isEmpty()) {
//    				Process p = waitingProcess.dequeue();
//    				memory.allocateProcess(p);
////    				notify view
////    				notify model
//    			}
//    			else {
//    				Process p = new process(params from random)
  				/*
  				 * if(proces is to big) {
  				 * add to queue
  				 *  notify view
  				 * notify model
  				 * }
  				 * else{
  				 * memory.allocateProcess(p);
  				 * notify view
  				 * notify model
  				 * }
    	*/

//    			}
//    		}
//
            /*
            * This decrements all ttls for stored processes
            */
   		    MemoryNode t = BA.getMNode();
            while((t != null)){
                int ttl =t.getStoredProcess().getTTL();
                t.getStoredProcess().setTTL(--ttl);
            }

    	}while(!stop);
////        Process process = new Process("dog",10,20,1);
        System.out.print("end program");
        

    }

//    public Queue getWaitingProcess() {
//        return waitingProcess;
//    }



//    public void setWaitingProcess(Queue waitingProcess) {
//        this.waitingProcess = waitingProcess;
//    }

//    public static void addProcess(int pid, String name, int size, int ttl){
//        /*
//        *the fields of the new process will be generated at random in the view
//        *and passed into the controller
//        */
//        Process newProcess = new Process(name, size, ttl, pid);
//        memory.allocateProcess(newProcess);
//    }

//    public static void endProcess(){
//        /*
//        remove process from view and the buddy system
//         */
//
//    }


}
