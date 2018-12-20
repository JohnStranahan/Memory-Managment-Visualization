package Model;

public class FirstFitAllocator {
    MemoryNode mNode;
    MemoryNode head;
    int sizeLeft;
    int numNodes;

    public FirstFitAllocator() {
        mNode = new MemoryNode();
        head = mNode;
        sizeLeft = 256;
        numNodes = 0;
    }

    public MemoryNode getHead() {
        return this.head;
    }

    public boolean allocateProcess(Process p) {
        if (p.getSize() < sizeLeft) {
            mNode.setStoredProcess(p);
            mNode.setNext(new MemoryNode());
            mNode = mNode.getNext();
            sizeLeft -= p.getSize();
            numNodes++;
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (numNodes == 0) {
            return true;
        }
        return false;
    }

    public void endProcess(MemoryNode processToEnd) {
        if (head == null || processToEnd == null) {
            return;
        }
        if (head == processToEnd) {
            head = processToEnd.getNext();
        }
        if (processToEnd.getNext() != null) {
            processToEnd.getNext().setPrevious(processToEnd.getPrevious());
        }
        if (processToEnd.getPrevious() != null) {
            processToEnd.getPrevious().setNext(processToEnd.getNext());
        }
        sizeLeft -= processToEnd.getStoredProcess().getSize();
        return;

    }
}
