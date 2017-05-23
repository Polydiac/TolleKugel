import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

/**
 * Created by Soeren on 21.05.2017.
 */
public class Server implements Runnable {
    BoeseKugel[] players;
    GuteKugel[] entities;
    Score[] scoreboards;
    DatagramSocket socket;
    int port = 2828;
    ArrayList<Client> clients;

    public Server(int port){
        this.port = port;
    }

    public void run(){
        try{
            byte[] buf = encodeToBytePacket(new Message(PackageType.BROADCAST));
            DatagramPacket pack = new DatagramPacket(buf, buf.length, InetAddress.getByName("255.255.255.255"), port);
            socket = new DatagramSocket(port+1);
            socket.send(pack);
            long startTime = System.currentTimeMillis();
            clients = new ArrayList<Client>();
            while(startTime - System.currentTimeMillis() > 1000) {
                DatagramPacket packet = new DatagramPacket(new byte[65536], 65536);
                socket.receive(packet);
                InetAddress addr = packet.getAddress();
                Message msg = decodeMessage(packet.getData());
                clients.add(new Client(msg.playerID, msg.username, addr, packet.getPort()));
            }
            for (Client client : clients) {
                client.initializeConnection();
            }


        } catch(IOException e){
            e.printStackTrace();
        }
        boolean running = true;

        while(!running){
            for (int i = 0; i <clients.size(); i++) {
                try {
                    Message msg = clients.get(i).read();
                    switch (msg.type) {
                        case PLAYER:
                            for(int x = 0; x<players.length; x++){
                                if(players[x].id == msg.playerID){
                                    players[x] = msg.player;
                                }
                            }
                            break;
                        case GUTEKUGELN:
                            for(int x = 0; x<players.length; x++){
                                if(players[x].id == msg.playerID){
                                    players[x] = msg.player;
                                }
                            }
                            break;
                        case BROADCAST:
                            break;
                        case CONNECTION:
                            break;
                        case REQUEST:
                            switch(msg.requestType){
                                case PLAYER:
                                    for(int x = 0; x<players.length; x++){
                                        if(players[x].id != msg.playerID) {
                                            clients.get(i).write(new Message(players[x], "SERVER"));
                                        }
                                    }
                                    break;
                                case GUTEKUGELN:
                                    clients.get(i).write(new Message(entities, "SERVER"));
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                } catch (IOException e) {

                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static byte[] encodeToBytePacket(Message msg){
        Gson gson = new Gson();
        String json = gson.toJson(msg);
        return json.getBytes();
    }

    public static Message decodeMessage(byte[] data){
        String json = new String(data);
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }
}
