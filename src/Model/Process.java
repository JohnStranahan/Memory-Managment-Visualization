package Model;

//This class represents a process object that will contain
//a size and time to live.
import java.util.*;
public class Process implements Processinterface
{
    private final String name;
    private int size;
    private int ttl;
    private int pid;
    //Constructor for class process.
    //@param name: name of the process
    //@param size: size of the process
    //@param ttl: time to live of the process
    public Process(String name, int size, int ttl)
    {
        this.name = name;
        this.size = size;
        this.ttl = ttl;
    }

    //Accessor for name.
    //@return name
    public String getName()
    {
        return name;
    }

    //Mutator for size field.
    //@param size sets the size.
    public void setSize(int size)
    {
        this.size = size;
    }

    //Accessor for size.
    //@return size the size of the process.
    public int getSize()
    {
        return size;
    }


    //Mutator for ttl field.
    //@param ttl sets the ttl.
    public void setTTL(int ttl)
    {
        this.ttl = ttl;
    }

    //Accessor for ttl.
    //@return ttl the ttl of the process.
    public int getTTL()
    {
        return ttl;
    }

    public void setPid(int pid)
    {
        this.pid = pid;
    }

    //Accessor for pid.
    //@return pid the pid of the process.
    public int getPid()
    {
        return pid;
    }
}