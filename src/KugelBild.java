//import java.lang.arry
import sum.kern.*;
import java.util.Random;
import java.awt.Color;
/**
 * Created by Sören on 30.03.2017.
 */
public class KugelBild extends DrawThread{
    Kugel[] kugeln;
    public void init(){
        
        kugeln = getKugelArray(10, this.bs);
    }
    
    public void draw(int frame){
        for(int i = 0; i< kugeln.length;i++){
                kugeln[i].bewege();
            }
    }
    
    public void delete(){
        for(int i = 0; i< kugeln.length;i++){
                kugeln[i].del();
            }
    }
    
    public static void main(String[]args){
        new KugelBild();
    }
    public static Kugel[] getKugelArray(int kugelCount, Fenster pbs){
        Random rnd = new Random(System.currentTimeMillis());
        Kugel[] kugeln = new Kugel[kugelCount];
        for(int i = 0; i<kugeln.length;i++){
            kugeln[i] = new Kugel(5+rnd.nextInt(50),rnd.nextDouble()*pbs.breite(),rnd.nextDouble()*pbs.hoehe(), rnd.nextInt(360), rnd.nextDouble()*10,new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)) , pbs);
        }
        return kugeln;
    }
}
