  /**
 * Created by Sören on 30.03.2017.
 */


import sum.kern.*;
import java.awt.Color;
import java.util.Hashtable;

public class Kugel {
    String id;
    boolean hide = false;
    transient Buntstift  st;
    transient Bildschirm bs;
    double rad;
    double x,y;
    double richt;
    double speed;
    boolean ri,le,to,bo = false;

    public Kugel(){
    }

    public Kugel(double prad, double px, double py, double speed, Color c, Bildschirm pbs, String id){
        this.id = id;
        bs = pbs;
        this.speed = speed;
        st = new Buntstift((Fenster) pbs);
        rad = prad;
        x = px;
        y = py;
        st.setzeFuellmuster(1);
        st.setzeFarbe(c);
    }

    public void draw(){
        st.bewegeBis(this.x, this.y);
        st.zeichneKreis(this.rad);
    }

    public void del(){
        st.radiere();
        draw();
        st.normal();
    }
    
    public boolean checkCollision(Kugel k){
        if(Math.sqrt(Math.pow(Math.abs(this.x - k.x),2)+ Math.pow(Math.abs(this.y - k.y),2)) < k.rad + this.rad){
            return true;
        }
        else {
            return false;
        }
    }
    
    public void hideK(){
        this.hide = true;
    }

    public Hashtable serialize(){
        Hashtable dict = new Hashtable();
        dict.put("x", x);
        dict.put("y", y);
        dict.put("hide", hide);
        dict.put("rad", rad);
        dict.put("richt", richt);
        dict.put("speed", speed);
        return dict;
    }
}
