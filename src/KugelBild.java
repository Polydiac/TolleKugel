//import java.lang.arry
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
    
    public void init(){

        player = new BoeseKugel(30,500, 500, 20, Color.BLACK, this.bs);
        kugeln = getKugelArray(1000, this.bs);
        scoreboard = new Score(900, 20, Color.GREEN);
        
    }
    
    public void draw(int frame){
        
        for(int i = 0; i< kugeln.length;i++){
            if(!kugeln[i].hide){
                    kugeln[i].bewege();
            }
        }
        player.update(frame);
        
        for(int i= 0; i< kugeln.length;i++){
            if(kugeln[i].checkCollision(player)){
                scoreboard.incScore();
                kugeln[i].hideK();
            }
        }
        scoreboard.draw();
    }
    
    public void delete(){
        player.del();
        for(int i = 0; i< kugeln.length;i++){
                kugeln[i].del();
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
