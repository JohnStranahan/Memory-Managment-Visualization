package Model;

public interface ProcessInterface {

    String getName();

    int getSize();

    int getTTL();

    int getPid();

    void setName(String name);

    void setSize(int size);

    void setTTL(int TTL);

    void setPid(int PID);


}