import sum.kern.Bildschirm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Soeren on 27.04.2017.
 */
public class GuteKugel extends Kugel {
    double richt;
    float dur,change,start,beginning;
    boolean anim = false;

    public GuteKugel(){

    }

    public GuteKugel(double prad, double px, double py, double pricht, double speed, Color c, Bildschirm pbs, String id) {
        super(prad, px, py, speed, c, pbs, id);
        richt = pricht;
        this.st.dreheBis(pricht);
    }

    public void bewege(int frame){
        this.bewege(this.speed, frame);

    }
    public void bewege(double weite, int frame){

        st.bewegeUm(weite);
        x = st.hPosition();
        y = st.vPosition();
        if(x >= (bs.breite() - rad)){
            richt = richtUmkehren( Border.RIGHT);
            st.dreheBis(richt);
            ri = true;
            st.bewegeUm(weite+rad*2);
        }
        else if (y >= bs.hoehe() - rad){
            richt = richtUmkehren( Border.BOTTOM);
            st.dreheBis(richt);
            bo = true;
            st.bewegeUm(weite+rad*2);
        }
        else if (x <= rad){
            richt = richtUmkehren( Border.LEFT);
            st.dreheBis(richt);
            le = true;
            st.bewegeUm(weite+rad*2);
        }
        else if (y <= rad){
            richt = richtUmkehren( Border.TOP);
            st.dreheBis(richt);
            to = true;
            st.bewegeUm(weite+rad*2);

        }
        else {
            ri = false;
            le = false;
            to = false;
            bo = false;
        }

        st.normal();
        draw();

    }

    public void draw(){
        st.bewegeBis(x,y);
        //if(!(x<0-this.rad||y<0-this.rad||x>bs.breite()+this.rad||y>bs.breite()+this.rad)){
        st.zeichneKreis(rad);
        //}
    }

    private double richtUmkehren( Border b){
        double r = this.richt;
        switch (b){
            case TOP:
                if(r > 90&& r < 180){
                    return (180 - r)+180;
                }
                else if(r > 0&& r < 90){
                    return 360 - r;
                }
                break;
            case BOTTOM:
                if(r > 270&& r < 360){
                    return (360 - r);
                }
                else if(r>180&& r<270){
                    return 180 - (r - 180);
                }
                break;
            case RIGHT:
                if(r > 0&& r < 90){
                    return 180 - r;
                }
                else if(r>270&&r<360){
                    return 270 - (r - 270);
                }
                break;
            case LEFT:
                if(r > 180&& r < 270){
                    return (270 - r)+270;
                }
                else if(r > 90&& r< 180){
                    return 90-(r - 90);
                }
                break;
        }
        return r;

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
    public Hashtable serialize(){
        Hashtable dict = super.serialize();
        dict.put("start", start);
        dict.put("change", change);
        dict.put("dur", dur);
        dict.put("beginning", beginning);
        dict.put("anim", anim);
        return dict;
    }
}
