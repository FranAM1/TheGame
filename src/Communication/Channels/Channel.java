package Communication.Channels;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Channel implements Runnable{
    private Socket socket;
    private OutputStream os;
    private InputStream is;

    private HealthChecker healthChecker;

    @Override
    public void run() {

    }
}
