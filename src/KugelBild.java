import sum.kern.*;
import java.util.Random;
import java.awt.Color;
import java.util.Hashtable;

/**
 * Created by Sören on 30.03.2017.
 */
public class KugelBild extends DrawThread{
    GuteKugel[] kugeln;
    BoeseKugel[] players;
    Score[] scoreboards;
    int spawnEntities;

    public void init(){
        scoreboards = new Score[2];
        spawnEntities = 2000;
        players = new BoeseKugel[2];
        players[0] = new BoeseKugel(30,500, 500, 20, Color.BLACK, this.bs, this.kb, 'w', 's', 'a', 'd');
        players[1] = new BoeseKugel(30,500, 500, 20, Color.BLACK, this.bs, this.kb, 'u', 'j', 'h', 'k');
        kugeln = getKugelArray(spawnEntities, this.bs);
        for(int i = 0; i <scoreboards.length;i++){
            scoreboards[i] = new Score(900, (i+1)*40, Color.GREEN, 25);
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
        }
        for(int i=0;i<players.length;i++){
            players[i].update(frame);
        }

        for(int i= 0; i< kugeln.length;i++){

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
        for(int i=0; i < scoreboards.length;i++){
            scoreboards[i].draw();
        }
        int currentBalls = 0;
        for(int i = 0; i< kugeln.length;i++){
            if(!kugeln[i].hide) {
                currentBalls++;
            }
        }
        if(currentBalls < spawnEntities * (2/3)){
            respawn(frame);
        }
    }

    public void respawn(int frame){
        Random rnd = new Random(System.currentTimeMillis());
        if(frame%1==0){
            for(int i = 0; i< kugeln.length;i++){
                if(kugeln[i].hide) {
                    kugeln[i] = new GuteKugel(5 + rnd.nextInt(50), rnd.nextDouble() * bs.breite(), rnd.nextDouble() * bs.hoehe(), rnd.nextInt(360), rnd.nextDouble() * 10, new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)), bs);
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
        

    }

    public static void main(String[]args){
        new KugelBild();
    }

    public static GuteKugel[] getKugelArray(int kugelCount, Fenster pbs){
        Random rnd = new Random(System.currentTimeMillis());
        GuteKugel[] kugeln = new GuteKugel[kugelCount];
        for(int i = 0; i<kugeln.length;i++){
            kugeln[i] = new GuteKugel(5+rnd.nextInt(50),rnd.nextDouble()*pbs.breite(),rnd.nextDouble()*pbs.hoehe(), rnd.nextInt(360), rnd.nextDouble()*10,new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)) , pbs);
        }
        return kugeln;
    }
}
