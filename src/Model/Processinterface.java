package Model;

public interface Processinterface {
    int size =0;
    String name ="";
    int pid =0;
    int ttl =0;

    void Process();
    String getName();
    void setSize();
    void setName();
    int getSize();
    void setTtl();
    int getTtl();
    void setPid();
    int getPid();


}