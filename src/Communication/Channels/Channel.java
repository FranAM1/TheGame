package Communication.Channels;

import Communication.Interlocutors.Interlocutor;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Channel implements Runnable{
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private Interlocutor interlocutor;
    private long lastCheckTime;
    private HealthChecker healthChecker;

    public Channel(Interlocutor interlocutor) {
        socket = null;
        this.lastCheckTime = System.currentTimeMillis();
        this.interlocutor = interlocutor;
    }

    public boolean ping() {
        return false;
    }

    public void sendDataFrame(Object dataFrame) {
        try {
            objectOutputStream.writeObject(dataFrame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

    }

    public int getPort() {
        return socket.getPort();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Interlocutor getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(Interlocutor interlocutor) {
        this.interlocutor = interlocutor;
    }
}
