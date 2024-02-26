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

    public void setSocket(Socket socket) {
        this.socket = socket;
        try {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Streams created");

            this.healthChecker = new HealthChecker(10000, this);
            new Thread(this.healthChecker).start();
        } catch (IOException e) {
            System.out.println("Error creating output and input streams");
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
                        case KEEP_ALIVE:
                            System.out.println("Ping recibido");
                            this.sendPingBack();
                            break;
                        case KEEP_ALIVE_BACK:
                            System.out.println("Ping recibido de vuelta");
                            this.healthChecker.setKillSocket(false);
                            break;
                        default:
                            System.out.println("Unknown data frame type");
                            break;
                    }
                }
            }catch (IOException | ClassNotFoundException e) {
                System.out.println("Error reading object "+e.getMessage());
                setDownChannel();
            }
        }
    }

    public void sendPing() {
        try {
            DataFrame data = new DataFrame(DataFrameType.KEEP_ALIVE, "Ping");
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            System.out.println("Ping send");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPingBack() {
        try {
            DataFrame data = new DataFrame(DataFrameType.KEEP_ALIVE_BACK, "PingBack");
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDownChannel() {
        try {
            stopHealthChecker();
            this.objectInputStream.close();
            this.objectOutputStream.close();
            this.socket.close();
            this.socket = null;
            System.out.println("Channel down");
        } catch (IOException e) {
            System.out.println("Error closing socket");
            e.printStackTrace();
        }
    }

    private void stopHealthChecker() {
        if (this.healthChecker != null) {
            this.healthChecker.stop();
            healthChecker = null;
        }
    }


    public Socket getSocket() {
        return socket;
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
