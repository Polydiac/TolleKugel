import sum.kern.*;

import javax.swing.*;
import java.net.InetAddress;
import java.util.Random;
import java.awt.Color;


/**
 * Created by Sören on 30.03.2017.
 */
public class KugelBild extends DrawThread{
    GuteKugel[] kugeln;
    PowerUp[] powerUps;
    String playerId;
    BoeseKugel[] players;
    Score[] scoreboards;
    int spawnEntities;
    Connection con;
    boolean multiplayer;

    public void init(){
        multiplayerSetup();
        scoreboards = new Score[2];

        powerUps = new PowerUp[5];
        for(int i=0; i<powerUps.length; i++){
            powerUps[i] = new PowerUp(60, 500, 500, 30, 10, Color.black, this.bs, "test");
        }

        spawnEntities = 2000;
        players = new BoeseKugel[2];
        players[0] = new BoeseKugel(30,500, 500, 20, Color.BLACK, this.bs, this.kb, 'w', 's', 'a', 'd', "player1");
        players[1] = new BoeseKugel(30,700, 500, 20, Color.BLACK, this.bs, this.kb, 'u', 'j', 'h', 'k',"player2");
        kugeln = getKugelArray(spawnEntities, this.bs);
        for(int i = 0; i <scoreboards.length;i++){
            scoreboards[i] = new Score(70, (i+1)*40, Color.GREEN, 25, this.bs);
        }

    }

    public void draw(int frame){

        for(int i = 0; i< kugeln.length;i++){
            if(!kugeln[i].hide){
                kugeln[i].bewege(frame);
            }
            if(kugeln[i].anim){
                kugeln[i].animateOut(frame);
                kugeln[i].draw();
            }
            for(int o=0;o<players.length;o++){
                if(kugeln[i].checkCollision(players[o])){
                    if(!kugeln[i].hide) {
                        scoreboards[o].incScore();


                        //player.rad = Easings.quadEaseOut((float)player.rad+1, 30, 170, 500);
                    }
                    kugeln[i].toggleAnim(frame);
                    kugeln[i].hideK();

                }
            }
        }
        for(int i=0;i<players.length;i++){
            players[i].update(frame);
            /*for(int x=0;x<players.length;x++){
                if(x != i && players[i].checkCollision(players[x])){

                }
            }*/
        }

        for(int i=0; i < scoreboards.length;i++){
            scoreboards[i].draw();
        }
        int currentBalls = 0;
        for(int i = 0; i< kugeln.length;i++){
            if(!kugeln[i].hide) {
                currentBalls++;
            }
        }
        if(currentBalls < kugeln.length * (2/3)){
            System.out.println("Respawning");
            respawn(frame);
        }
        if(multiplayer){
            for (int i=0; i<players.length;i++){
                if(players[i].id != playerId){
                    players[i] = con.getPlayers();
                }
            }
        }

        for(int i=0; i<powerUps.length; i++){
            powerUps[i].bewege(frame);
            powerUps[i].draw();
        }

        this.sendGameState(PackageType.PLAYER);

    }

    public void respawn(int frame){
        Random rnd = new Random(System.currentTimeMillis());
        if(true){
            for(int i = 0; i< kugeln.length;i++){
                if(kugeln[i].hide) {
                    kugeln[i] = new GuteKugel(5 + rnd.nextInt(50), rnd.nextDouble() * bs.breite(), rnd.nextDouble() * bs.hoehe(), rnd.nextInt(360), rnd.nextDouble() * 10, new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)), bs, Integer.toString(i));
                }
            }
        }
    }

    public void delete(){
        for(int i=0;i<players.length;i++){
            players[i].del();
        }
        for(int i = 0; i< kugeln.length;i++){
            kugeln[i].del();
        }
        for(int i = 0; i <scoreboards.length;i++){
            scoreboards[i].del();
        }
        for(int i = 0; i<powerUps.length; i++){
            powerUps[i].del();
        }
        

    }

    private void sendGameState(PackageType type){
        if(con != null){
            switch(type){
                case PLAYER:
                    for(int p = 0; p < this.players.length; p++){
                        if(this.players[p].id == this.playerId){
                            GameState gameState = new GameState(this.players[p], this.playerId);
                            con.sendMsg(gameState);
                        }
                    }
                    break;
                case GUTEKUGELN:
                    GameState gameState = new GameState(this.kugeln, this.playerId);
                    con.sendMsg(gameState);
                    break;
            }
        }
        else{
            System.out.println("No peer found!");
        }
    }

    public void multiplayerSetup(){
        Object[] optionsMultiplayer = {"Online Multiplayer", "Local Multiplayer"};
        int multiplayer = JOptionPane.showOptionDialog(null, "Local or online multiplayer",
                "Multiplayer",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, optionsMultiplayer, optionsMultiplayer[0]);
        Object[] optionsHost = {"Host", "Wait for host"};
        if(multiplayer == 0){
            int host =  JOptionPane.showOptionDialog(null, "Are you the host?",
                    "Multiplayer",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, optionsHost, optionsHost[1]);
            int port = new Integer(JOptionPane.showInputDialog(null, "Send on which Port"));
            int listenOn = new Integer(JOptionPane.showInputDialog(null, "Listen on what Port"));
            if(host == 0){
                new Connection().sendMsg(new GameState("player1"));
                JOptionPane.showMessageDialog(null, "Connecting to other clients. Please Wait...");
                InetAddress peerURL = new Connection().getConnection();
                con = new Connection(peerURL.getCanonicalHostName(), port, listenOn);
            } else {
                InetAddress peerURL = new Connection().getConnection();
                con = new Connection(peerURL.getCanonicalHostName(), port, listenOn);
                con.sendMsg(new GameState(), peerURL);
            }
        }
    }

    public static void main(String[]args){


        new KugelBild();
    }

    public static GuteKugel[] getKugelArray(int kugelCount, Fenster pbs){
        Random rnd = new Random(System.currentTimeMillis());
        GuteKugel[] kugeln = new GuteKugel[kugelCount];
        for(int i = 0; i<kugeln.length;i++){
            kugeln[i] = new GuteKugel(5+rnd.nextInt(50),rnd.nextDouble()*pbs.breite(),rnd.nextDouble()*pbs.hoehe(), rnd.nextInt(360), rnd.nextDouble()*10,new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)) , pbs, Integer.toString(i));
        }
        return kugeln;
    }
}
