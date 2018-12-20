package Model;

/*
* non-buddy system abstract memory
* buddy-system will be a sub class of this
* buddy system will need split and merge methods
*/
public abstract class MemoryModel {
   
    private MemoryNode mNode;
    int numNodes;
    public MemoryModel(){
        
        mNode = new MemoryNode();
    }

	public boolean isEmpty() {
		if (numNodes == 0) {
			return true;
		}
		return false;
	}
    abstract void endProcess(MemoryNode deadProcess);
    
    abstract boolean allocateProcess(Process p);


    

}