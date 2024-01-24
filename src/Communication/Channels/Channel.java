package Communication.Channels;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Channel implements Runnable{
    private Socket socket;
    private OutputStreamWriter os;
    private InputStreamReader is;
    private BufferedReader br;
    private BufferedWriter bw;
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
            this.os = new OutputStreamWriter(socket.getOutputStream());
            this.is = new InputStreamReader(socket.getInputStream());

            this.br = new BufferedReader(this.is);
            this.bw = new BufferedWriter(this.os);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        while (true) {

        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStreamWriter getOs() {
        return os;
    }

    public void setOs(OutputStreamWriter os) {
        this.os = os;
    }

    public InputStreamReader getIs() {
        return is;
    }

    public void setIs(InputStreamReader is) {
        this.is = is;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
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
