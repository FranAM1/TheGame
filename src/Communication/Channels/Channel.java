package Communication.Channels;

import Communication.Interlocutors.Interlocutor;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Channel implements Runnable{
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Interlocutor interlocutor;
    private long lastCheckTime;
    private HealthChecker healthChecker;

    public Channel() {
        socket = null;
        this.lastCheckTime = System.currentTimeMillis();
    }

    public void loadOutputInput(Socket socket) {
        this.socket = socket;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String getMessage() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (scanner.hasNextLine()) {
                    String inputMessage = scanner.nextLine();
                    this.sendMessage(inputMessage);
                }
            }
        }
    }


    public boolean ping() {
        return false;
    }


    @Override
    public void run() {
        new Thread(this::handleInput).start();
        while (true) {
            String message = getMessage();
            if (message != null) {
                System.out.println("Message received: "+message);
            }
        }
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
