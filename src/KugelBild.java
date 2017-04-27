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
    public void init(){

        player = new BoeseKugel(10,500, 500, 1, Color.BLUE, this.bs);
        kugeln = getKugelArray(10, this.bs);

    }
    
    public void draw(int frame){
        player.update(frame);
        for(int i = 0; i< kugeln.length;i++){
                kugeln[i].bewege();
        }

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
