package Model;

/**
 * This class represents each individual memory
 * node that will be represented in the memory
 * stack. Each node has a reference to the previous
 * and next nodes in the stack.
 */
public class MemoryNode implements NodeInterface
{
	private MemoryNode next;
	private MemoryNode previous;
	private boolean[] allocatedBits;
	private Process storedProcess;

    /**
     * Constructor for class memoryNode
     * @param previous: the previous memory node
     * @param next: the next memory node
     * @param allocatedBits: allocated number of bits
     * @param storedProcess: process stored in the node
     */
	public MemoryNode(MemoryNode previous, MemoryNode next, boolean[] allocatedBits, Process storedProcess)
	{
		this.previous = previous;
		this.next = next;
		this.allocatedBits = allocatedBits;
		this.storedProcess = storedProcess;
	}
    /**
     * Constructor for setting up Node
     */
	public MemoryNode()
	{
		previous = null;
		next = null;
		allocatedBits = new boolean[256];
		storedProcess = null;
	}
    /**
     * Accessor for previous node.
     * @return previous
     */
    public MemoryNode getPrevious()
    {
        return previous;
    }
    /**
     * Accessor for the next node
     * @return next
     */
    public MemoryNode getNext()
    {
        return next;
    }
    /**
     * Mutator for previous node.
     * @param previous
     */
	public void setPrevious(MemoryNode previous)
	{
		this.previous = previous;
	}
    /**
     * Mutator for the next node
     * @param next
     */
    public void setNext(MemoryNode next)
    {
        this.next = next;
    }
    /**
     * Sets values in array to true to simulate memory allocation
     * @param bitSize: the size of the process being allocated
     */
	public void allocate(int bitSize)
	{
		for(int i = 0; i < bitSize; i++){
			allocatedBits[i] = true;
		}
	}
    /**
     * This method clears any allocation in the array
     */
	public void clearAllocations(){
		int length = allocatedBits.length;
		allocatedBits = new boolean[length];
	}
    /**
     * Accessor method for allocatedBits
     * @return allocatedBits
     */
	public boolean[] getAllocationArray()
	{
		return allocatedBits;
	}
	/**
     * This method checks the allocation array to see if this object is allocated or not
     * @return
     */
	public boolean isAllocated()
	{
		for(int i = 0; i < allocatedBits.length; i++){
			if(allocatedBits[i] == true){
				return true;
			}
		}

		return false;
	}
}
