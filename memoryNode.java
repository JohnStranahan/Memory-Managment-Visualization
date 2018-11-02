//This class represents each individual memory
//node that will be represented in the memory 
//stack. Each node has a reference to the previous
//and next nodes in the stack. 

public class memoryNode
{
	private memoryNode next;
	private memoryNode previous;
	private boolean[] allocatedBits;

	//Constructor for class memoryNode
	//@param previous: sets previous node
	//@param next: sets next node.
	//@param size: size for allocatedBits array
	public memoryNode(memoryNode previous, memoryNode next, int size)
	{
		this.previous = previous;
		this.next = next;
		allocatedBits = new boolean[size];
	}

	//Constructor for setting up node with
	//no node references.
	public memoryNode()
	{
		previous = null;
		next = null;
		allocatedBits = new boolean[256];
	}

	//Mutator for previous node.
	//@param previous: sets previous node.
	public void setPrevious(memoryNode previous)
	{
		this.previous = previous;
	}

	//Accessor for previous node.
	//@return previous: gets the previous node.
	public memoryNode getPrevious()
	{
		return previous;
	}


	//Mutator for the next  node.
	//@param next: sets next node.
	public void setNext(memoryNode next)
	{
		this.next = next;
	}

	//Accessor for the next  node.
	//@return next: gets the next node.
	public memoryNode getNext()
	{
		return next;
	}


	//Sets values in array to true to simulate
	//memory allocation.
	//@param biteSize: the size of the process being allocated.
	public void allocate(int biteSize)
	{
		for(int i = 0; i < biteSize; i++){
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
