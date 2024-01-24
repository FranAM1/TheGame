package Communication.Channels;

import Communication.Interlocutors.Interlocutor;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Channel implements Runnable{
    private Socket socket;
    private ObjectOutputStream writer;
    private InputStreamReader reader;
    private Interlocutor interlocutor;
    private long lastCheckTime;
    private HealthChecker healthChecker;

    public Channel() {
        this.lastCheckTime = System.currentTimeMillis();
    }

    public void loadOutputInput(Socket socket) {
        try {
            this.writer = new ObjectOutputStream(socket.getOutputStream());
            this.reader = new InputStreamReader(socket.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean ping() {
        return false;
    }


    @Override
    public void run() {

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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
