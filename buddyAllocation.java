//This program contains the logic pertaining
//to the buddy system of memory allocation.
//It uses the memoryNode object.

public class buddyAllocation
{
	//mNode is the head of the memoryNode linked list.
	private memoryNode mNode;

	//Constructor for class buddyAllocation.
	//@param process process to be allocated to memory.
	public buddyAllocation()
	{
		mNode = new memoryNode();
	}


	public memoryNode getMNode()
	{
		return this.mNode;
	}

	//This method splits an array in two.
	//Will also store split arrays in new memory nodes.
	//@param arr array to be split.
	public void splitArray(memoryNode node)
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
		memoryNode tempPrevious = node.getPrevious();
		memoryNode tempNext = node.getNext();

		if(tempPrevious == null){
			memoryNode split1 = new memoryNode(null,null,a);
		        memoryNode split2 = new memoryNode(split1,tempNext,b);

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
			memoryNode split1 = new memoryNode(tempPrevious,null,a);
			tempPrevious.setNext(split1);
			memoryNode split2 = new memoryNode(split1,tempNext,b);
			split1.setNext(split2);
			tempNext.setPrevious(split2);
		}
		//If node is tail of the list, will set prev of first split to 
		//temp prev.
		else if((tempPrevious != null) && tempNext == null){
			memoryNode split1 = new memoryNode(tempPrevious,null,a);
			memoryNode split2 = new memoryNode(split1,null,b);
			split1.setNext(split2);
			tempPrevious.setNext(split1);
		}
	}

	//This method will merge two memoryNodes
	//When using this method, nodaA.getNext should be 
	//equal to nodeB, nodeB.getPrevious should equal Node A.
	//@param half of node to be merged.
	//@param second half of node to be merged
	public void merge(memoryNode nodeA, memoryNode nodeB)
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
		memoryNode tempPrevious = nodeA.getPrevious();
		memoryNode tempNext = nodeB.getNext();

		//Check cases to see if the nodeA is the first node in the list,
		//if nodeB is the last node in the list, or if they don't fall into 
		//either of the cases.

		if((tempPrevious == null) && (tempNext == null)){
			memoryNode newNode = new memoryNode(null, null, mergedArray);

			this.mNode = newNode;
		}
		else if(tempPrevious == null){
			memoryNode newNode = new memoryNode(null, tempNext, mergedArray);
			tempNext.setPrevious(newNode);

			this.mNode = newNode;
		}
		else if(tempNext == null){
			memoryNode newNode = new memoryNode(tempPrevious, null, mergedArray);
			tempPrevious.setNext(newNode);
		}
		else if((tempPrevious != null) && (tempNext != null)){
			memoryNode newNode = new memoryNode(tempPrevious, tempNext, mergedArray);
			tempPrevious.setNext(newNode);
			tempNext.setPrevious(newNode);
		}

	}

	public void allocateProcess()
	{
	}
}
