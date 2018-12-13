package Model;

public interface NodeInterface {
    void clearAllocations();
    void setPrevious(MemoryNode previous);
    Object getPrevious();
    void setNext(MemoryNode next);
    void allocate(int bitSize);
    Object getNext();
    boolean[] getAllocationArray();
}
