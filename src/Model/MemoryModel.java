package Model;

/*
* non-buddy system abstract memory
* buddy-system will be a sub class of this
* buddy system will need split and merge methods
*/
public abstract class MemoryModel {
   
    private MemoryNode mNode;

    public MemoryModel(){
        
        mNode = new MemoryNode();
    }


    abstract void endProcess(MemoryNode deadProcess);
    
    abstract void allocateProcess(Process p);

    public MemoryNode findSmallest() {
    	MemoryNode search = this.mNode;

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
    

}