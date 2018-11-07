//This program contains the logic pertaining
//to the buddy system of memory allocation.
//It uses the memoryNode object.

public class buddyAllocation
{
	private memoryNode mNode;

	//Constructor for class buddyAllocation.
	//@param process process to be allocated to memory.
	public buddyAllocation()
	{
		mNode = new memoryNode();
	}

	public void split(memoryNode toSplit)
	{
//		if((toSplit.getAllocationArray.length)%2 != 0){
//			System.out.println("Memory Block has reached minimum size.");
//		}
//		else{
//
//		}
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

			System.out.println("Doing the first condition.");
		}
		//If node is neither head nor tail of the list,
		//will set tempNext and tempPrev as parameters.
		else if((tempPrevious != null) && (tempNext != null)){
			memoryNode split1 = new memoryNode(tempPrevious,null,a);
			tempPrevious.setNext(split1);
			memoryNode split2 = new memoryNode(split1,tempNext,b);
			split1.setNext(split2);
			tempNext.setPrevious(split2);

			System.out.println("Doing the second condition");
		}
		//If node is tail of the list, will set prev of first split to 
		//temp prev.
		else if(tempNext == null){
			memoryNode split1 = new memoryNode(tempPrevious,null,a);
			memoryNode split2 = new memoryNode(split1,null,b);

			split1.setNext(split2);
			System.out.println("Doing the third condition");
		}
		System.out.println("Here.");
	}
}
