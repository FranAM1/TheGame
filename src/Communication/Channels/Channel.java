package Communication.Channels;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Channel implements Runnable{
    private Socket socket;
    private OutputStream os;
    private InputStream is;
    private long lastCheckTime;
    private HealthChecker healthChecker;

    public Channel(Socket socket, long maxIdleTime) {
        this.socket = socket;

        this.initChannel(socket);
        this.lastCheckTime = System.currentTimeMillis();

        this.healthChecker = new HealthChecker(maxIdleTime, this);
        new Thread(this.healthChecker).start();
    }

    public void initChannel(Socket socket) {
        try {
            this.os = this.socket.getOutputStream();
            this.is = this.socket.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        try {
            this.os.write(message.getBytes());
            this.os.flush();
        } catch (Exception e) {
            throw new RuntimeException("CHANNEL: Error al enviar el mensaje "+e);
        }
    }

    public boolean ping() {
        try {
            this.os.write(0);
            this.os.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                int read = this.is.read();
                if(read == -1){
                    throw new RuntimeException("CHANNEL: Error al leer el mensaje");
                }else{
                    System.out.println("CHANNEL: Mensaje recibido: "+read);
                }
                this.lastCheckTime = System.currentTimeMillis();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public long getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(long lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public HealthChecker getHealthChecker() {
        return healthChecker;
    }

    public void setHealthChecker(HealthChecker healthChecker) {
        this.healthChecker = healthChecker;
    }
}
