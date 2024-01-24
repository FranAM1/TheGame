package Communication.Channels;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Channel implements Runnable{
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private long lastCheckTime;
    private HealthChecker healthChecker;
    private Scanner sc;

    public Channel(Socket socket, long maxIdleTime) {
        this.socket = socket;
        this.sc = new Scanner(System.in);

        this.loadOutputInput(socket);
        this.lastCheckTime = System.currentTimeMillis();

        this.healthChecker = new HealthChecker(maxIdleTime, this);
        new Thread(this.healthChecker).start();
    }

    public void loadOutputInput(Socket socket) {
        try {
            this.writer = new PrintWriter(socket.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean ping() {
        return false;
    }


    @Override
    public void run() {
        while (true) {
            this.writer.println("Hello from client");
            this.writer.flush();

            try {
                String message = this.reader.readLine();
                System.out.println("Message received: " + message);
            } catch (IOException e) {
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

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
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
