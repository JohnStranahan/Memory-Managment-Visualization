package Model;

public interface NodeInterface {
    Object next;
    Object previous;
    boolean[] allocatedBits;
    void clearAllocations();
    void setPrevious();
    Object getPrevious();
    void setNext();
    void allocate();
    Object getNext();
    boolean[] getAllocationArray();
}