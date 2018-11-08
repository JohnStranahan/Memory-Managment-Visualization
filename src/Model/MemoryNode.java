package Model;

//This class represents each individual memory
//node that will be represented in the memory
//stack. Each node has a reference to the previous
//and next nodes in the stack.

public class MemoryNode implements nodeInterface
{
    private MemoryNode next;
    private MemoryNode previous;
    private boolean[] allocatedBits;

    //Constructor for class memoryNode
    //@param previous: sets previous node
    //@param next: sets next node.
    //@param size: size for allocatedBits array
    public MemoryNode(MemoryNode previous, MemoryNode next, int size)
    {
        this.previous = previous;
        this.next = next;
        allocatedBits = new boolean[size];
    }

    //Constructor for setting up node with
    //no node references.
    public MemoryNode()
    {
        previous = null;
        next = null;
        allocatedBits = new boolean[256];
    }

    //Mutator for previous node.
    //@param previous: sets previous node.
    public void setPrevious(MemoryNode previous)
    {
        this.previous = previous;
    }

    //Accessor for previous node.
    //@return previous: gets the previous node.
    public MemoryNode getPrevious()
    {
        return previous;
    }


    //Mutator for the next  node.
    //@param next: sets next node.
    public void setNext(MemoryNode next)
    {
        this.next = next;
    }

    //Accessor for the next  node.
    //@return next: gets the next node.
    public MemoryNode getNext()
    {
        return next;
    }


    //Sets values in array to true to simulate
    //Memory allocation.
    //@param biteSize: the size of the process being allocated.
    public void allocate(Process newProcess)
    {
        int processSize = newProcess.getSize();
        
        for(int i = 0; i < processSize; i++){
            allocatedBits[i] = true;
        }
    }

    //This method clears any allocation in the array.
    public void clearAllocations()
    {
        int length = allocatedBits.length;
        allocatedBits = new boolean[length];
    }

    //Accessor method for allocatedBits
    //@return allocatedBits field.
    public boolean[] getAllocationArray()
    {
        return allocatedBits;
    }






}
