package gameObjects;  /**
 * Created by Sören on 30.03.2017.
 */


import sum.kern.Bildschirm;
import sum.kern.Buntstift;
import sum.kern.Fenster;

import java.awt.*;

public class KugelImpl {

    public boolean hide = false;
    Buntstift st;
    Bildschirm bs;
    double rad;
    double x,y;
    double richt;
    double speed;
    boolean ri,le,to,bo = false;

    public KugelImpl(double prad, double px, double py, double speed, Color c, Bildschirm pbs){
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
    
    public boolean checkCollision(KugelImpl k){
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
}
