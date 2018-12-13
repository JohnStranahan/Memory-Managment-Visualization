package Controller;
import Model.Process;
import Model.BuddyAllocation;
import View.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Driver {

    static Queue waitingProcess = new LinkedList();
    static BuddyAllocation memory = new BuddyAllocation();

    public static void main(String[] args) {
    	boolean stop = false;
    	do {
//    		foreach(Process p in memory model){
//    			if(m.ttl == 0) {
//    				memory.endProcess(p);
////    				notify view
////    				notify model
//    			}
//    		}
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
//    		foreach(process in memory model){
//    			p.setTTL(p.getTTL() from gui);
//    		}
    	}while(!stop);
////        Process process = new Process("dog",10,20,1);
        

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
