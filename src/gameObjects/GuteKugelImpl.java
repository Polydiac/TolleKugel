package gameObjects;

import sum.kern.Bildschirm;
import util.Border;
import util.Easings;

import java.awt.*;

/**
 * Created by Soeren on 27.04.2017.
 */
public class GuteKugelImpl extends KugelImpl {
    double richt;
    float dur,change,start,beginning;
    public boolean anim = false;

    public GuteKugelImpl(double prad, double px, double py, double pricht, double speed, Color c, Bildschirm pbs) {
        super(prad, px, py, speed, c, pbs);
        richt = pricht;
        this.st.dreheBis(pricht);
    }

    public void bewege(int frame){
        this.bewege(this.speed, frame);

    }
    public void bewege(double weite, int frame){
        draw();

    }

    public void draw(){
        st.bewegeBis(x,y);
        st.zeichneKreis(rad);
    }

    public void toggleAnim(int startFrame){
        this.anim = true;
        dur = 50;
        change = (float) -this.rad;
        start = startFrame;
        beginning = (float) this.rad;

    }
    public void animateOut(int frame){
        if(this.rad > 0){
            this.rad = Easings.elasticEaseOut(frame-start, beginning , this.change, this.dur);
        } else{
            this.anim = false;
        }
    }
}
