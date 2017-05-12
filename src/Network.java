import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;
import java.util.ArrayList;
import org.msgpack.*;

/**
 * Created by Soeren on 12.05.2017.
 */
public class Network extends Thread{
    ArrayList<DatagramSocket> sockets;

    public void run() {
        sockets = new ArrayList<>();
        for (int i = 0; i < sockets.size(); i++)
            try {
                sockets[] = new DatagramSocket(1010);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<DatagramSocket> scanNetwork() {

    }

    public void send(Object obj) {
        byte[] raw = msgpack.write();
        DatagramPacket packet = new DatagramPacket(raw, raw.length, );
    }
}
