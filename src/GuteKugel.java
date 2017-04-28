import sum.kern.Bildschirm;

import java.awt.*;

/**
 * Created by Soeren on 27.04.2017.
 */
public class GuteKugel extends Kugel {
    double richt;
    double dur,change,start;
    public GuteKugel(double prad, double px, double py, double pricht, double speed, Color c, Bildschirm pbs) {
        super(prad, px, py, speed, c, pbs);
        richt = pricht;
        this.st.dreheBis(pricht);
    }

    public void bewege(){
        this.bewege(this.speed);

    }
    public void bewege(double weite){
        st.bewegeUm(weite);
        x = st.hPosition();
        y = st.vPosition();
        if(x >= bs.breite() - rad && !ri){
            richt = richtUmkehren( Border.RIGHT);
            st.dreheUm(richt);
            ri = true;
            st.bewegeUm(weite+rad*2);
        }
        else if (y >= bs.hoehe() - rad && !bo){
            richt = richtUmkehren( Border.BOTTOM);
            st.dreheUm(richt);
            bo = true;
            st.bewegeUm(weite+rad*2);
        }
        else if (x <= rad && !le){
            richt = richtUmkehren( Border.LEFT);
            st.dreheUm(richt);
            le = true;
            st.bewegeUm(weite+rad*2);
        }
        else if (y <= rad &&!to){
            richt = richtUmkehren( Border.TOP);
            st.dreheUm(richt);
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
        if(!(x<0-this.rad||y<0-this.rad||x>bs.breite()+this.rad||y>bs.breite()+this.rad)){
            st.zeichneKreis(rad);
        }

    }

    private double richtUmkehren( Border b){
        Vector2D normal;
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
        }
        Vector2D ang = new Vector2D((float)Math.cos(Math.toRadians(this.richt)), (float)Math.sin(Math.toRadians(this.richt)));
        ang = ang.normalize();
        normal.normalize();
        double angle = Math.toDegrees(Math.acos(ang.dot(normal)/1))*2;
        double reflection = ((360-(angle*2))/2);
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
        }

    }
    public void toggleAnim(int startFrame){
        
    }
    public void animateOut(int frame){
        
    }
}
