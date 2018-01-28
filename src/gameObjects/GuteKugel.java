package gameObjects;

import javafx.util.Pair;
import sum.kern.Bildschirm;
import util.Border;
import util.Easings;

import java.awt.*;

/**
 * Created by Soeren on 27.04.2017.
 */
public class GuteKugel extends Kugel {
    double richt;
    float dur,change,start,beginning;
    public boolean anim = false;
    int bbreite, bhoehe;

    public GuteKugel(double prad, double px, double py, double pricht, double speed, Color c, int pbbreite, int pbhoehe) {
        super(prad, px, py, speed, c);
        richt = pricht;
        bbreite = pbbreite;
        bhoehe = pbhoehe;
    }

    public void bewege(int frame){
        this.bewege(this.speed, frame);

    }
    public void bewege(double weite, int frame){

        moveBy(weite, this.richt);
        if(x >= (bbreite - rad)){
            richt = richtUmkehren( Border.RIGHT);
            moveBy(weite+rad*2, richt);
            ri = true;
        }
        else if (y >= bhoehe - rad){
            richt = richtUmkehren( Border.BOTTOM);
            moveBy(weite+rad*2, richt);
            bo = true;
        }
        else if (x <= rad){
            richt = richtUmkehren( Border.LEFT);
            le = true;
            moveBy(weite+rad*2, richt);
        }
        else if (y <= rad){
            richt = richtUmkehren( Border.TOP);
            to = true;
            moveBy(weite+rad*2, richt);
        }
        else {
            ri = false;
            le = false;
            to = false;
            bo = false;
        }

        draw();

    }

    public void draw(){
    }

    private void moveBy(double weite, double richt){
        x = x + Math.cos(Math.toRadians(richt))*weite;
        y = y + Math.sin(Math.toRadians(richt))*weite;
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
}
