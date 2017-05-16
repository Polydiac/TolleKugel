import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

/**
 * Created by Soeren on 12.05.2017.
 */
public class Connection {
    static int port = 2828;
    String url;
    GameStateReceiveThread[] connections;
    LinkedList<GuteKugel[]> queue;
    LinkedList<BoeseKugel> playerQueue;

    public Connection(String purl) {
        this(purl, 2828);
    }
    public Connection() {
        this(null, 2828);
    }

    public Connection(String purl, int pport) {
        connections = new GameStateReceiveThread[2];
        port = pport;
        url = purl;
        queue = new LinkedList<GuteKugel[]>();
        playerQueue = new LinkedList<BoeseKugel>();
        connections[0] = new GameStateReceiveThread(port, this);
        connections[1] = new GameStateReceiveThread(port, this);
    }


    public void sendMsg(GameState state) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(state);
            byte[] raw = json.getBytes();
            System.out.println(raw.length);
            DatagramSocket socket = new DatagramSocket();
            switch (state.type){
                case PLAYER:
                    DatagramPacket packPlayer = new DatagramPacket(raw, raw.length, InetAddress.getByName(url), port);
                    socket.send(packPlayer);
                    break;
                case GUTEKUGELN:
                    DatagramPacket pack = new DatagramPacket(raw, raw.length, InetAddress.getByName(url), port);
                    socket.send(pack);
                    break;
                case BROADCAST:
                    DatagramPacket packBroadcast = new DatagramPacket(raw, raw.length, InetAddress.getByName("255.255.255.255"), port);
                    socket.send(packBroadcast);
                    break;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public GuteKugel[] getGameState(){
        return queue.removeFirst();
    }
    public BoeseKugel getPlayers(){
        return playerQueue.removeFirst();
    }


}
