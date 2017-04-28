import sum.kern.Bildschirm;

import java.awt.*;

/**
 * Created by Soeren on 27.04.2017.
 */
public class GuteKugel extends Kugel {
    double richt;
    float dur,change,start,beginning;
    boolean anim = false;

    public GuteKugel(double prad, double px, double py, double pricht, double speed, Color c, Bildschirm pbs) {
        super(prad, px, py, speed, c, pbs);
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
        if(x >= bs.breite() - rad){
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
        /*Vector2D normal;
        switch(b){
            case TOP:
                normal = new Vector2D(0, -1);
                break;
            case BOTTOM:
                normal = new Vector2D(0, 1);
                break;
            case LEFT:
                normal = new Vector2D(-1, 0);
                break;
            case RIGHT:
                normal = new Vector2D(1, 0);
                break;
            default:
                normal = null;
        }*/
        /*
        Vector2D ang = new Vector2D((float)Math.cos(Math.toRadians(this.richt)), (float)Math.sin(Math.toRadians(this.richt)));
        ang = ang.normalize();

        switch(b){
            case TOP:
                ang.y = ang.y*-1;
                break;
            case BOTTOM:
                ang.y = ang.y*-1;
                break;
            case LEFT:
                ang.x = ang.x*-1;
                break;
            case RIGHT:
                ang.x = ang.x*-1;
                break;
        }*/
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
            case LEFT:
                if(r > 0&& r < 90){
                    return 180 - r;
                }
                else if(r>270&&r<360){
                    return 270 - (r - 270);
                }
                break;
            case RIGHT:
                if(r > 180&& r < 270){
                    return (270 - r)+270;
                }
                else if(r > 90&& r< 180){
                    return 90-(r - 90);
                }
                break;
        }
        return 0;
        /*

        normal.normalize();
        double angle = Math.toDegrees(Math.acos(ang.dot(normal)/1))*2;
        double reflection = (360-(angle*2))/2;
        double r = this.richt;
        switch (b){
            case TOP:
                if(r > 90&& r < 180){
                    return reflection;
                }
                else{
                    return -reflection;
                }
            case BOTTOM:
                if(r > 270&& r < 360){
                    return reflection;
                }
                else{
                    return -reflection;
                }
            case LEFT:
                if(r > 0&& r < 90){
                    return reflection;
                }
                else{
                    return -reflection;
                }
            case RIGHT:
                if(r > 180&& r < 270){
                    return reflection;
                }
                else{
                    return -reflection;
                }
            default:
                return 0;
        }*/

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
