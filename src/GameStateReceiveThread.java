import com.google.gson.Gson;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.SocketException;


/**
 * Created by Soeren on 14.05.2017.
 */
public class GameStateReceiveThread implements Runnable{
    int port;
    Connection parent;


    public GameStateReceiveThread(int pport, Connection parentConnection){
        parent = parentConnection;
        this.port = pport;
        new Thread(this, "socket");
    }


    public void run(){
        while(true) {
            try {

                DatagramSocket socket = new DatagramSocket(port);
                DatagramPacket packet = new DatagramPacket(new byte[65536], 65536);
                socket.receive(packet);

                byte[] msg = packet.getData();
                String result = new String(msg);

                Gson gson = new Gson();
                GameState state = gson.fromJson(result, GameState.class);

                switch (state.type) {
                    case PLAYER:
                        parent.playerQueue.add(state.player);
                        break;
                    case GUTEKUGELN:
                        parent.queue.add(state.kugeln);
                        break;
                    case BROADCAST:
                        parent.sendMsg(new GameState(), packet.getAddress());
                        break;
                }

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
