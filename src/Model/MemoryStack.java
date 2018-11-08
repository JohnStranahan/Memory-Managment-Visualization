package Model;

public class MemoryStack {
    private MemoryNode head;
    private int numProcesses;
    private int allocatedMemory;
    public MemoryStack()
    {
        allocatedMemory=0;
        numProcesses =0;
        head = null;
    }
    public boolean isEmpty(){
        return numProcesses == 0;
    }
    public void enterProcess(Process process)throws indexOutOfBoundsException{
        allocatedMemory = allocatedMemory + process.getSize();

    }
    public int getAllocatedMemory(){
        return allocatedMemory;
    }
    public int getNumProcesses(){
        return numProcesses;
    }
    public void removeProcess(Process process)throws indexOutOfBoundsException{

    }
    public String toString(){

    }
    public void deAllocateAll()
    {

        head = null;
        numProcesses = 0;
        allocatedMemory =0;
    }
}
