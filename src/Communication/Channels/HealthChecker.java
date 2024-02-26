package Communication.Channels;

public class HealthChecker implements Runnable {

    private boolean working = true;
    private Channel channel;
    private long timeOut;
    private boolean killSocket = false;

    public HealthChecker(long timeOut, Channel channel) {
        this.channel = channel;
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            while (working) {
                long currentTime = System.currentTimeMillis();
                long timeLastMessage = channel.getLastCheckTime();
                long diferencia = currentTime - timeLastMessage;

                if (diferencia > timeOut) {
                    try {
                        if (this.killSocket == true) {

                            System.out.println("HealthChecker detenido, matando socket");
                            channel.setDownChannel();
                            working = false;
                        }

                        System.out.println("HealthChecker: el ultimo mensaje es de hace: " + diferencia);
                        this.killSocket = true;
                        this.channel.sendPing();
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Thread Test Chanel terminado");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        System.err.println("Deteniendo HealthChecker Connection...");
        working = false;
    }

    public void pararEjecucion() {
        System.err.println("Deteniendo HealthChecker Connection...");
        working = false;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isKillSocket() {
        return killSocket;
    }

    public void setKillSocket(boolean killSocket) {
        this.killSocket = killSocket;
    }
}
