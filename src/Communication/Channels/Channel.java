package Communication.Channels;

import Communication.CommunicationController;
import Communication.Interlocutors.Interlocutor;
import DTO.AppFrame;
import DTO.DataFrame;
import Enums.DataFrameType;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Channel implements Runnable{
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private CommunicationController cc;
    private Interlocutor interlocutor;
    private long lastCheckTime;
    private HealthChecker healthChecker;

    public Channel(Interlocutor interlocutor, CommunicationController cc) {
        socket = null;
        this.lastCheckTime = System.currentTimeMillis();
        this.interlocutor = interlocutor;
        this.cc = cc;
    }

    public boolean ping() {
        return false;
    }

    public void sendObject(Object object) {
        try {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            System.out.println("Object sent");
        } catch (IOException e) {
            System.out.println("Error sending object");
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while(socket != null){
            try{
                Object readObject =  objectInputStream.readObject();

                if (readObject instanceof DataFrame) {
                    DataFrame dataFrame = (DataFrame) readObject;
                    switch (dataFrame.getType()) {
                        case APPLICATION_FRAME:
                            System.out.println("Received application frame");
                            AppFrame appFrame = (AppFrame) dataFrame.getObject();
                            this.cc.handleAppFrame(appFrame, this.interlocutor);
                            break;
                        default:
                            System.out.println("Unknown data frame type");
                            break;
                    }
                }
            }catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading object "+e.getMessage());
            }
        }
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
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Streams created");
        } catch (IOException e) {
            System.out.println("Error creating output and input streams");
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
