
package Model;
//This program contains the logic pertaining
//to the buddy system of memory allocation.
//It uses the memoryNode object.

public class BuddyAllocation
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

		MemoryNode tempPrevious = node.getPrevious();
		MemoryNode tempNext = node.getNext();

		if(node.getPrevious() == null){

			if(tempNext != null){
				tempNext.setPrevious(null);
				node.setNext(null);
			}
			MemoryNode split1 = new MemoryNode(null,null,a,null);
			MemoryNode split2 = new MemoryNode(split1,tempNext, b, null);
			if(tempNext != null){
				tempNext.setPrevious(split2);
			}
			split1.setNext(split2);

			//Sets the field mNode, which will represent the head
			//of the memoryNodes linked list.
			this.mNode = split1;
		}
		//If node is neither head nor tail of the list,
		//will set tempNext and tempPrev as parameters.
		else if((node.getPrevious() != null) && (node.getNext() != null)){

			//Creates new memoryNode and reassigns references to
			// the previous and next nodes to insert the new new
			// nodes in between the previous and next nodes.
			MemoryNode split1 = new MemoryNode(node.getPrevious(),null,a, null);
			node.getPrevious().setNext(split1);
			MemoryNode split2 = new MemoryNode(split1,node.getNext(),b, null);
			split1.setNext(split2);
			node.getNext().setPrevious(split2);
		}
		//If node is tail of the list, will set prev of first split to
		//temp prev.
		else if((node.getPrevious() != null) && (node.getNext() == null)){
			MemoryNode split1 = new MemoryNode(node.getPrevious(),null,a,null);
			node.getPrevious().setNext(split1);
			MemoryNode split2 = new MemoryNode(split1,null,b,null);
			split1.setNext(split2);
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
			newNode.clearAllocations();
//			newNode.setStoredProcess(null);
			this.mNode = newNode;
		}
		else if(tempPrevious == null){
			MemoryNode newNode = new MemoryNode(null, tempNext, mergedArray, null);
			tempNext.setPrevious(newNode);
			newNode.clearAllocations();
			this.mNode = newNode;
		}
		else if(tempNext == null){
			MemoryNode newNode = new MemoryNode(tempPrevious, null, mergedArray, null);
			newNode.clearAllocations();
			tempPrevious.setNext(newNode);
		}
		else if((tempPrevious != null) && (tempNext != null)){
			MemoryNode newNode = new MemoryNode(tempPrevious, tempNext, mergedArray, null);
			newNode.clearAllocations();
			tempPrevious.setNext(newNode);
			tempNext.setPrevious(newNode);
		}

	}

	//This method allocates a process to a memoryNode
	//contained in the stack.
	//@param Process p to be allocated to stack.
	//public void allocateProcess(Process p)
	//{
	//	int pSize = p.getSize();

	//To allocate a process, we need to traverse the linked list of
	//memoryNodes and compare the size of each node to the process
	//which is attempting to be allocated
	//	MemoryNode n = this.mNode;
	//	MemoryNode smallest = n;
	//	int difference = 0;
	//	boolean found = false;

	//	while(found == false){
	//		while((n != null) && (found == false)){

	//		        int blockSize = mNode.getAllocationArray().length;
	//		        difference = blockSize - pSize;

	//		        if((difference < pSize) && (difference > 0) && (n.isAllocated() == false)){
	//			        n.allocate(pSize);
	//			        found = true;
	//		        }
	//		        else{
	//			        n = n.getNext();
	//		        }
	//		}
	//		if(found == false){
	//		        splitArray(findSmallest(), p);
	//		}
	//	}

//	}

	//This method is to control the re-merging of
	//processes once the TTL of a process expires.
	//@param memoryNode deadProcess which contains the
	//process that has ended.
	public void endProcess(MemoryNode deadProcess)
	{
                MemoryNode n = this.mNode;
                int count = 0;
                boolean found = false;

                System.out.println("Ending process");

                //searches memory stack to find index of node to be merged.
                while((n.getNext() != null) && (found == false)){
                        count++;
                        if(n == deadProcess){
                                System.out.println("Found it!");
                                found = true;
                        }
                        
                }


                MemoryNode tempPrevious = deadProcess.getPrevious();
                MemoryNode tempNext = deadProcess.getNext();
                deadProcess.clearAllocations();

                System.out.println("Index: " + count);

                System.out.println("Past counter");
                if(n.getStoredProcess().getSize() >128) {
                	n.clearAllocations();
                	n.setStoredProcess(null);
                }
                else if(((count % 2) == 0) && (deadProcess.getAllocationArray().length == tempPrevious.getAllocationArray().length)
                                && (tempPrevious.isAllocated() == false)){
                        System.out.println("first condition");
                        merge(tempPrevious, deadProcess);
                }
                else if(((count % 2) != 0) && (deadProcess.getAllocationArray().length == tempNext.getAllocationArray().length)
                                && tempNext.isAllocated() == false){
                        System.out.println("second condition");
                        merge(deadProcess,tempNext);
                }
                else if(((count % 2) == 0) && (deadProcess.getAllocationArray().length == tempNext.getAllocationArray().length)
                                && (tempNext.isAllocated() == false)){
                        System.out.println("third condition");
                        merge(deadProcess,tempNext);
                }
                else{
                        System.out.println("Cannot merge at this time.");
                }	
	}

	public int findBestFit(int Size){

        boolean bestSize = false;
        int bestFit = 0;
        //First find the best size of memory for the process
        for(int i = 256; i >= 2 && bestSize == false; i= i/2){
            if((i - Size) < Size){
                bestSize = true;
                bestFit = i;
            }
        }

        return bestFit;
    }
	
	public MemoryNode findSmall()
    {
        MemoryNode search = mNode;

        if((search.getPrevious() == null) && (search.getNext() == null)){
            return search;
        }

        MemoryNode smallest = search;
        boolean found = false;

        while((search.getNext() != null) && (found != true)){
            MemoryNode x = search.getNext();

            if((search.getAllocationArray().length > x.getAllocationArray().length) && (x.isAllocated() == false)){
                smallest = x;
                found = true;
            }

            search = x;
        }

        return smallest;
    }
	
	public boolean allocateProcess(Process p)
    {
        boolean bestSize = false;
        int bestFit = findBestFit(p.getSize());

        //now find node to allocate process
        MemoryNode sNode = null;
        MemoryNode toAllocate = null;
        int iterations = 1;

        while(bestSize == false){
            sNode = mNode;
            while(sNode != null && bestSize == false){
                if((sNode.getAllocationArray().length == bestFit) && (sNode.isAllocated() == false)){
                    bestSize = true;
                    toAllocate = sNode;
                }
                else{
                    sNode = sNode.getNext();
                }
            }
            if(bestSize == false){
                sNode = mNode;
                System.out.println("Split to fit!");
                MemoryNode s = findSmall();
                if(s.isAllocated() == false){
                    splitArray(findSmall(),null);
                }
                else{
                    System.out.println("Here");
                    int x = p.getSize();
                    boolean hasSplit = false;
                    while(x <= sNode.getAllocationArray().length && !hasSplit){
                        x = x*2;
                        x = findBestFit(x);
//                        System.out.println("Best fit is: " + x);
                        sNode = mNode;
                        while(sNode != null && hasSplit == false){
                            if(sNode.getAllocationArray().length == x &&
                                    sNode.isAllocated() == false){
                                hasSplit = true;
                                System.out.println("Found it!");
                                System.out.println("Splitting " + sNode.getAllocationArray().length);
                                splitArray(sNode,null);

                            }
                            else{
                                sNode = sNode.getNext();
                            }
                        }
                    }
                }
                sNode = mNode;
            }
            iterations++;
            if(iterations > 4){
                break;
            }
        }


        if(bestSize){

            System.out.println("The best fit node is " + toAllocate + " of size " + toAllocate.getAllocationArray().length);
            toAllocate.allocate(p.getSize());
			sNode.setStoredProcess(p);
            return true;
        }
        else{
            System.out.println("There is no node big enough for that process currently.");
            return false;
        }


    }
}

