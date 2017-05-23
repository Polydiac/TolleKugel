import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Soeren on 21.05.2017.
 */
public class Client {
    Socket socket;
    String playerId;
    String clientId;
    InetAddress address;
    int port;
    PrintWriter out;
    InputStreamReader in;

    public Client(String playerId, String clientId, InetAddress address, int port) {
        this.playerId = playerId;
        this.clientId = clientId;
        this.address = address;
        this.port = port;
    }

    public void initializeConnection(){
        try {
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream());
            in = new InputStreamReader(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Message read() throws IOException {
        Gson gson = new Gson();
        Message msg = gson.fromJson(String.valueOf(in.read()), Message.class);
        return msg;
    }

    public void write(Message msg){
        Gson gson = new Gson();
        String message = gson.toJson(msg);
        out.write(message);
    }

}
