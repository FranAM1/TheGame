package Communication.Interlocutors;

import java.util.Objects;

public class Interlocutor {
    private String ip;
    private int port;

    public Interlocutor(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Interlocutor){
            Interlocutor interlocutor = (Interlocutor) object;
            return interlocutor.getIp().equals(this.ip) && interlocutor.getPort() == this.port;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
