package TestCases;
public class ServerConnection_2 {

    private String address;
    private int portNumber;
    private boolean active;

    public ServerConnection_2(String address, int portNumber) {
        this.address = address;
        this.portNumber = portNumber;
        this.active = false;
    }

    public void open() {
        active = true;
    }

    public void close() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public String getStatus() {
        return address + ":" + portNumber + " active=" + active;
    }
}
