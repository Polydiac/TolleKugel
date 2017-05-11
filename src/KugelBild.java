import sum.kern.*;
import java.util.Random;
import java.awt.Color;
/**
 * Created by Sören on 30.03.2017.
 */
public class KugelBild extends DrawThread{
    GuteKugel[] kugeln;
    BoeseKugel player;
    Score scoreboard;
    int spawnEntities;
    
    public void init(){
        spawnEntities = 2000;
        player = new BoeseKugel(30,500, 500, 20, Color.BLACK, this.bs, kb);
        kugeln = getKugelArray(spawnEntities, this.bs);
        scoreboard = new Score(900, 20, Color.GREEN, 25);
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
        player.update(frame);
        
        for(int i= 0; i< kugeln.length;i++){
            if(kugeln[i].checkCollision(player)){
                if(!kugeln[i].hide) {
                    scoreboard.incScore();
                    //player.rad = Easings.quadEaseOut((float)player.rad+1, 30, 170, 500);
                }
                kugeln[i].toggleAnim(frame);
                kugeln[i].hideK();

            }
        }
        scoreboard.draw();
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
        player.del();
        for(int i = 0; i< kugeln.length;i++){
            kugeln[i].del();
        }
        scoreboard.del();

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
