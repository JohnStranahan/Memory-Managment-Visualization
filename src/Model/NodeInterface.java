package Model;

public interface NodeInterface {
    void clearAllocations();
    void setPrevious(MemoryNode previous);
    Object getPrevious();
    void setNext();
    void allocate();
    Object getNext();
    boolean[] getAllocationArray();
}
