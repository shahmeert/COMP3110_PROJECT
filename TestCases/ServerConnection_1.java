package TestCases;
public class ServerConnection_1 {

    private String host;
    private int port;
    private boolean connected;

    public ServerConnection_1(String host, int port) {
        this.host = host;
        this.port = port;
        this.connected = false;
    }

    public void connect() {
        connected = true;
    }

    public void disconnect() {
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }

    public String description() {
        return "Server " + host + ":" + port + ", connected=" + connected;
    }
}
