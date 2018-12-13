package Model;

/**
 * This class represents a process object that will contain a size and time to live.
 */
public class Process implements ProcessInterface {
    private String name;
    private int size;
    private int ttl;
    private int pid;

    /**
     * Constructor for class process.
     *
     * @param name: name of the process
     * @param size: size of the process
     * @param ttl:  time to live of the process
     * @param pid:  process i.d.
     */
    public Process(String name, int size, int ttl, int pid) {
        this.name = name;
        this.size = size;
        this.ttl = ttl;
        this.pid = pid;
    }

    /**
     * Accessor for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for size
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Accessor for ttl
     *
     * @return ttl;
     */
    public int getTTL() {
        return ttl;
    }

    /**
     * Accessor for pid
     *
     * @return pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * Mutator for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator for size
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Mutator for ttl
     *
     * @param ttl
     */
    public void setTTL(int ttl) {
        this.ttl = ttl;
    }

    /**
     * Mutator for pid
     *
     * @param pid
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * Overridden toString() function
     *
     * @return name + size + ttl + pid
     */
    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", ttl=" + ttl +
                ", pid=" + pid +
                '}';
    }

}