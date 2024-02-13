package Communication.Interlocutors;

import Enums.PeerLocation;

public class Peer extends Interlocutor {
    private PeerLocation location;

    public Peer(String ip, int port, PeerLocation location) {
        super(ip, port);
        this.location = location;
    }

    public PeerLocation getLocation() {
        return location;
    }

    public void setLocation(PeerLocation location) {
        this.location = location;
    }


}
