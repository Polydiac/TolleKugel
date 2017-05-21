import java.awt.Color;

import sum.kern.*;
import sum.multimedia.Bild;

/**
 * Beschreiben Sie hier die Klasse Score.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Score
{
    int score = 0;
    double x,y;
    Color colour;
    Bildschirm bs;
    Buntstift st;
    
    public Score(double px, double py, Color c, int pSchriftgroesse, Bildschirm pbs){
        bs = pbs;
        x = px;
        y = py;
        st = new Buntstift();
        st.setzeFarbe(c);
        st.setzeSchriftgroesse(pSchriftgroesse);
        
    }
    public void setScore(int sc){
        score = sc;
    }
    public void incScore(){
        score ++;
    }
    public void draw(){
        st.bewegeBis(bs.breite()-x,y );
        st.schreibeZahl(score);
    }
    public void del(){
        st.radiere();
        draw();
        st.normal();
    }
}
