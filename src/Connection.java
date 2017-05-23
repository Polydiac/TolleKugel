import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

/**
 * Created by Soeren on 12.05.2017.
 */

public class Connection implements Runnable{
    KugelBild parent;
    int port = 2828;
    String url;
    String username = System.getProperty("user.name");
    String playerID;
    ServerSocket socket;
    PrintWriter out;
    InputStreamReader in;

    public Connection(KugelBild parent){
        this.parent = parent;
        DatagramPacket pack = new DatagramPacket(new byte[65536], 65536);

    }


    public void run(){

    }
}

/*public class Connection {

    static int port = 2828;
    int listenOn;
    String url;
    String username;
    String playerID;
    GameStateReceiveThread connection;
    LinkedList<GuteKugel[]> queue;
    LinkedList<BoeseKugel> playerQueue;

    public Connection(String purl) {
        this(purl, 2828, 2828);
    }
    public Connection() {
        this(null, 2828, 2828);
    }

    public Connection(String purl, int pport, int lport) {
        port = pport;
        listenOn = lport;
        url = purl;
        queue = new LinkedList<GuteKugel[]>();
        playerQueue = new LinkedList<BoeseKugel>();
        new Thread(new Runnable() {
            public void run() {
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(port);
                    DatagramPacket packet = new DatagramPacket(new byte[65536], 65536);
                    //socket.setSoTimeout(10000);
                    while(true) {
                        socket.receive(packet);

                        byte[] msg = packet.getData();
                        String result = new String(msg).trim();
                        System.out.println(result);

                        Gson gson = new Gson();
                        Message state = gson.fromJson(result, Message.class);

                        switch (state.type) {
                            case PLAYER:
                                playerQueue.add(state.player);
                                break;
                            case GUTEKUGELN:
                                queue.add(state.kugeln);
                                break;
                            case BROADCAST:
                                sendMsg(new Message(), packet.getAddress());
                                break;
                        }
                    }


                } catch (SocketException e) {
                    e.printStackTrace();
                    if(socket != null){
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally{
                    if(socket != null){
                        socket.close();
                    }
                }
            }

        }).start();
    }

    public void sendMsg(Message state){
        try {
            this.sendMsg(state, InetAddress.getByName(this.url));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public void sendMsg(Message state, InetAddress url) {
        System.out.println(this.port);
        System.out.println(this.listenOn);

        try {
            Gson gson = new Gson();
            String json = gson.toJson(state);
            System.out.println(json);
            byte[] raw = json.getBytes();
            System.out.println(raw.length);
            DatagramSocket socket = new DatagramSocket();
            switch (state.type){
                case PLAYER:
                    DatagramPacket packPlayer = new DatagramPacket(raw, raw.length, url, port);
                    System.out.println(packPlayer.getAddress());
                    socket.send(packPlayer);
                    break;
                case GUTEKUGELN:
                    DatagramPacket pack = new DatagramPacket(raw, raw.length, url, port);
                    System.out.println(pack.getAddress());
                    socket.send(pack);
                    break;
                case BROADCAST:
                    socket.setBroadcast(true);
                    DatagramPacket packBroadcast = new DatagramPacket(raw, raw.length, InetAddress.getByName("255.255.255.255"), port);
                    System.out.println(packBroadcast.getAddress());
                    socket.send(packBroadcast);
                    break;
                case CONNECTION:
                    DatagramPacket returnPackage = new DatagramPacket(raw, raw.length, url, port);
                    System.out.println(returnPackage.getAddress());
                    socket.send(returnPackage);
                    break;
            }
            socket.close();

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

    public InetAddress getConnection(){
        try {
            DatagramPacket pack = new DatagramPacket(new byte[65536], 65536);
            DatagramSocket socket = new DatagramSocket(this.port);
            socket.setBroadcast(true);
            socket.setSoTimeout(2000000);
            socket.receive(pack);
            socket.close();
            System.out.println("Connection address:"+pack.getAddress());
            return pack.getAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }
}*/
