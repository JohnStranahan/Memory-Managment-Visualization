package Model;

/*
* non-buddy system abstract memory
* buddy-system will be a sub class of this
* buddy sytem will need split and merge methods
*/
public abstract class MemoryModel {
    private int numProcesses;
    private int freeMemory;
    private MemoryNode head;

    public MemoryModel(){
        numProcesses = 0;
        freeMemory = 256;
        head = null;
    }

    public boolean isEmpty(){
        return numProcesses == 0;
    }
    public void empty(){
        head = null;
        numProcesses = 0;
        freeMemory = 256;
    }

    abstract void addProcess(Process process);

    abstract void removeProcess();

    public boolean enoughSpace(Process process, int availableMemory){
        return process.getSize() <= availableMemory;
    }
    public void notifyObserver(){

    }

}