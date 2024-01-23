package Communication.Channels;

public class HealthChecker implements Runnable{
    private long maxIdleTime;

    private boolean waitingKeepAlive;

    private Channel channel;

    public HealthChecker(long maxIdleTime, Channel channel) {
        this.maxIdleTime = maxIdleTime;
        this.waitingKeepAlive = false;
        this.channel = channel;
    }

    @Override
    public void run() {

    }
}
