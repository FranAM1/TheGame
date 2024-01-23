// CommunicationController.java
package Communication;

public class CommunicationController {
    private ServerConnector serverConnector;
    private ClientConnector clientConnector;

    public CommunicationController() {
        serverConnector = new ServerConnector();
        clientConnector = new ClientConnector();
    }
}