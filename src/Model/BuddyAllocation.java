package Model;
//This program contains the logic pertaining
//to the buddy system of memory allocation.
//It uses the memoryNode object.

public class BuddyAllocation extends MemoryModel
{
	//mNode is the head of the MemoryNode linked list.
	private MemoryNode mNode;

	//Constructor for class buddyAllocation.
	//@param process process to be allocated to memory.
	public BuddyAllocation()
	{
		mNode = new MemoryNode();
	}


	public MemoryNode getMNode()
	{
		return this.mNode;
	}

	//This method splits an array in two.
	//Will also store split arrays in new memory nodes.
	//@param arr array to be split.
	public void splitArray(MemoryNode node, Process process)
	{

		boolean[] arr = node.getAllocationArray();
		int size = arr.length;

		boolean[] a = new boolean[(size+1)/2];
		boolean[] b = new boolean[size-a.length];

		for(int i = 0; i < size; i++){
			if(i < a.length){
				a[i] = arr[i];
			}
			else{
				b[i - a.length] = arr[i];
			}
		}

		//Temporary Previous and Next nodes so 
		//I dont need to keep calling getNext and getPrev
		MemoryNode tempPrevious = node.getPrevious();
		MemoryNode tempNext = node.getNext();

		if(tempPrevious == null){
			MemoryNode split1 = new MemoryNode(null,null,a, process);
		        MemoryNode split2 = new MemoryNode(split1,tempNext,b, process);

			split1.setNext(split2);
		
		        //Sets the field mNode, which will represent the head
			//of the memoryNodes linked list. 
		        this.mNode = split1;
		}
		//If node is neither head nor tail of the list,
		//will set tempNext and tempPrev as parameters.
		else if((tempPrevious != null) && (tempNext != null)){
			//Creates new memoryNode and reassigns references to
			// the previous and next nodes to insert the new new
			// nodes in between the previous and next nodes.
			MemoryNode split1 = new MemoryNode(tempPrevious,null,a, process);
			tempPrevious.setNext(split1);
			MemoryNode split2 = new MemoryNode(split1,tempNext,b, process);
			split1.setNext(split2);
			tempNext.setPrevious(split2);
		}
		//If node is tail of the list, will set prev of first split to 
		//temp prev.
		else if((tempPrevious != null) && tempNext == null){
			MemoryNode split1 = new MemoryNode(tempPrevious,null,a, process);
			MemoryNode split2 = new MemoryNode(split1,null,b, process);
			split1.setNext(split2);
			tempPrevious.setNext(split1);
		}
	}

	//This method will merge two memoryNodes
	//When using this method, nodaA.getNext should be 
	//equal to nodeB, nodeB.getPrevious should equal Node A.
	//@param half of node to be merged.
	//@param second half of node to be merged
	public void merge(MemoryNode nodeA, MemoryNode nodeB)
	{
		boolean[] a = nodeA.getAllocationArray();
		boolean[] b = nodeB.getAllocationArray();

		//Merge the allocation arrays from both nodes into one.
		boolean[] mergedArray = new boolean[(a.length + b.length)];

		int j = 1;
                for(int i = 0;j <= mergedArray.length; i++){
                        if(i == (mergedArray.length/2)){
                                i = 0;
                        }
                        if(j > (mergedArray.length/2)){
                                mergedArray[j-1] = b[i];
                        }
                        else{
                                mergedArray[j-1] = a[i];
                        }
                        j++;
                }

		//Now that we have merged the array, we now need to merge the nodes.

		//Create previous and next objects for the nodes.
		//Since NodeA and nodeB are linked, we only need to know about
		//nodeA's previous and nodeB's next.
		MemoryNode tempPrevious = nodeA.getPrevious();
		MemoryNode tempNext = nodeB.getNext();

		//Check cases to see if the nodeA is the first node in the list,
		//if nodeB is the last node in the list, or if they don't fall into 
		//either of the cases.

		if((tempPrevious == null) && (tempNext == null)){
			MemoryNode newNode = new MemoryNode(null, null, mergedArray, null);

			this.mNode = newNode;
		}
		else if(tempPrevious == null){
			MemoryNode newNode = new MemoryNode(null, tempNext, mergedArray, null);
			tempNext.setPrevious(newNode);

			this.mNode = newNode;
		}
		else if(tempNext == null){
			MemoryNode newNode = new MemoryNode(tempPrevious, null, mergedArray, null);
			tempPrevious.setNext(newNode);
		}
		else if((tempPrevious != null) && (tempNext != null)){
			MemoryNode newNode = new MemoryNode(tempPrevious, tempNext, mergedArray, null);
			tempPrevious.setNext(newNode);
			tempNext.setPrevious(newNode);
		}

	}

	//This method allocates a process to a memoryNode
	//contained in the stack.
	//@param Process p to be allocated to stack.
	public void allocateProcess(Process p)
	{
		int pSize = p.getSize();
		
		//To allocate a process, we need to traverse the linked list of 
		//memoryNodes and compare the size of each node to the process 
		//which is attempting to be allocated
		MemoryNode n = this.mNode;
		MemoryNode smallest = n;
		int difference = 0;
		boolean found = false;

		while(found == false){
			while(n != null){
				
			        int blockSize = mNode.getAllocationArray().length;
			        difference = blockSize - pSize;

			        if((difference < pSize) && (difference > 0) && (n.isAllocated() == false)){
				        n.allocate(pSize);
				        found = true;
			        }
			        else{
				        n = n.getNext();
			        }
			}
			splitArray(findSmallest(), p );
		}

	}

	//This method is to control the re-merging of
	//processes once the TTL of a process expires.
	//@param memoryNode deadProcess which contains the 
	//process that has ended.
	public void endProcess(MemoryNode deadProcess)
	{
		MemoryNode tempPrevious = deadProcess.getPrevious();
		MemoryNode tempNext = deadProcess.getNext();

		if((deadProcess.getAllocationArray().length == tempNext.getAllocationArray().length)
			       	&& (tempNext.isAllocated() == false)){
			MemoryNode toMerge = tempNext;

		        merge(deadProcess,toMerge);
		}
		else if((deadProcess.getAllocationArray().length == tempPrevious.getAllocationArray().length)
				&& (tempPrevious.isAllocated() == false)){
			MemoryNode toMerge = tempPrevious;


		        merge(toMerge,deadProcess);
		}

	}

	//This method is a helper method for the allocate
	//function which finds the correct node to split
	//while searching for a block of memory that will most
	//appropriately hold the process.
	//
	//@return the smallest non-allocated memoryNode in the list.
//	public MemoryNode findSmallest()
//	{
//		MemoryNode search = this.mNode;
//
//		if((search.getPrevious() == null) && (search.getNext() == null)){
//			return search;
//		}
//
//		MemoryNode smallest = search;
//		boolean found = false;
//
//		while((search.getNext() != null) && (found != true)){
//			MemoryNode x = search.getNext();
//
//			if((search.getAllocationArray().length > x.getAllocationArray().length) && (x.isAllocated() == false)){
//				smallest = x;
//				found = true;
//			}
//
//			search = x;
//		}
//
//		return smallest;
//	}
}
